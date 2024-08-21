package uz.pdp.app_codingbat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import uz.pdp.app_codingbat.entity.template.AbsLongEntity;
import uz.pdp.app_codingbat.entity.template.AbsLongWithAuditEntity;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "attachment")
public class Attachment extends AbsLongWithAuditEntity {

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Column(unique = true, columnDefinition = "uuid", name = "hash_id", nullable = false)
    private UUID hashId;

    @Column(name = "extension", nullable = false)
    private String extension;

    @Column(name = "upload_path", nullable = false)
    private String uploadPath;

    @Column(name = "name")
    private String name;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "content_type")
    private String contentType;

    public String getFilePath() {
        if (null == this.uploadPath || null == this.hashId || null == this.extension) {
            throw new RuntimeException("Please initialize MyFile parameters");
        } else {
            return String.format("%s/%s.%s", this.uploadPath, this.hashId, this.extension);
        }
    }

}
