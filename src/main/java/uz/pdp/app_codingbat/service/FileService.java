package uz.pdp.app_codingbat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.app_codingbat.config.logger.Logger;
import uz.pdp.app_codingbat.entity.Attachment;
import uz.pdp.app_codingbat.enums.ErrorTypeEnum;
import uz.pdp.app_codingbat.exceptions.RestException;
import uz.pdp.app_codingbat.payload.base.ResBaseMsg;
import uz.pdp.app_codingbat.payload.file.res.ResUploadFile;
import uz.pdp.app_codingbat.repository.AttachmentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static uz.pdp.app_codingbat.enums.ErrorTypeEnum.FILE_CANNOT_DELETED;
import static uz.pdp.app_codingbat.enums.ErrorTypeEnum.FILE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FileService {

    private final AttachmentRepository attachmentRepository;

    @Value("${application.file.basedir}")
    private String baseDir;
    @Value("${application.file.download-url}")
    private String downloadUrl;

    public ResUploadFile upload(MultipartFile file) {
        if (file == null || file.isEmpty())
            throw RestException.restThrow(FILE_NOT_FOUND);

        Attachment attachment = mapAttachment(file);

        try {
            Files.createDirectories(Paths.get(attachment.getUploadPath()));
            Files.copy(file.getInputStream(), Paths.get(attachment.getFilePath()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            Logger.error(e);
            throw RestException.restThrow(ErrorTypeEnum.ERROR_SAVING_FILE);
        }

        attachmentRepository.save(attachment);
        return new ResUploadFile(attachment.getHashId().toString(), downloadUrl);
    }

    public ResponseEntity<?> download(UUID hashId, String view) {
        Attachment attachment = attachmentRepository.findByHashId(hashId)
                .orElseThrow(() -> RestException.restThrow(ErrorTypeEnum.ATTACHMENT_NOT_FOUND));

        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(attachment.getContentType()))
                    .headers(httpHeaders -> {
                        httpHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
                        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, view + "; filename=" + attachment.getOriginalName());
                    })
                    .body(Files.readAllBytes(Path.of(attachment.getFilePath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResBaseMsg delete(UUID hashId) {

        Attachment attachment = attachmentRepository.findByHashId(hashId)
                .orElseThrow(() -> RestException.restThrow(FILE_NOT_FOUND));

        try {
            Files.deleteIfExists(Path.of(attachment.getFilePath()));  // deletes file from system
        } catch (IOException e) {
            throw RestException.restThrow(FILE_CANNOT_DELETED);
        }

        attachmentRepository.deleteById(attachment.getId()); // deletes file from db

        return new ResBaseMsg("File successfully deleted");
    }

    private Attachment mapAttachment(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String extension = Objects.requireNonNull(StringUtils.getFilenameExtension(originalName)).toLowerCase();
        UUID hashId = UUID.randomUUID();
        Long fileSize = file.getSize();
        String uploadPath = dirByDateAndExtension(extension);
        String name = String.format("%s.%s", hashId, extension);
        String contentType = file.getContentType();

        return Attachment.builder()
                .originalName(originalName)
                .extension(extension)
                .hashId(hashId)
                .fileSize(fileSize)
                .uploadPath(uploadPath)
                .name(name)
                .contentType(contentType)
                .build();
    }

    private String dirByDateAndExtension(String extension) {
        String path = baseDir;

        LocalDate date = LocalDate.now();
        path = path + String.format(
                "/%d/%d/%d/%s",
                date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth(),
                extension.toLowerCase());
        return path;
    }
}
