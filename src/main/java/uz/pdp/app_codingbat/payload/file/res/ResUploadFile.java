package uz.pdp.app_codingbat.payload.file.res;


import lombok.*;

@Getter
@Setter
public class ResUploadFile {
    private String hashId;
    private String imageDownloadUrl;

    public ResUploadFile(String hashId, String imageDownloadUrl) {
        this.hashId = hashId;
        this.imageDownloadUrl = imageDownloadUrl.concat("/").concat(hashId);
    }
}
