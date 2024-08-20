package uz.pdp.app_codingbat.payload.auth.res;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.app_codingbat.entity.User;
import uz.pdp.app_codingbat.entity.enums.UserStatus;

import java.util.UUID;

@Getter
@Setter
public class ResUserSimple {
    private UUID id;
    private String email;
    private UserStatus status;
    private String role;

    public ResUserSimple(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.role = user.getRole().getName();
    }
}
