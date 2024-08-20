package uz.pdp.app_codingbat.external.email.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendEmailDto {
    private String from;
    private String to;
    private String subject;
    private String body;
    private boolean html;
}