package uz.pdp.app_codingbat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.app_codingbat.config.core.BaseURI;
import uz.pdp.app_codingbat.config.logger.Logger;
import uz.pdp.app_codingbat.payload.base.ApiResult;
import uz.pdp.app_codingbat.payload.base.ResBaseMsg;
import uz.pdp.app_codingbat.payload.file.res.ResUploadFile;
import uz.pdp.app_codingbat.service.FileService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(BaseURI.API1 + BaseURI.FILE)
public class FileControllerV1 {

    private final FileService fileService;

    @PostMapping(
            value = BaseURI.UPLOAD,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ApiResult<ResUploadFile> upload(@RequestPart("file") MultipartFile multipartFile) {
        return ApiResult.successResponse(fileService.upload(multipartFile));
    }

    @GetMapping(value = BaseURI.DOWNLOAD + "/{hashId}")
    public ResponseEntity<?> download(
            @PathVariable UUID hashId,
            @RequestParam(defaultValue = "inline") String view
    ) {
        return fileService.download(hashId, view);
    }

    @DeleteMapping("/{hashId}")
    public ApiResult<ResBaseMsg> deleteFile(@PathVariable UUID hashId){
        return ApiResult.successResponse(fileService.delete(hashId));
    }
}
