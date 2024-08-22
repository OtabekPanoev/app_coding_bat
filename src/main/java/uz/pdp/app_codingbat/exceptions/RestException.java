package uz.pdp.app_codingbat.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import jakarta.validation.constraints.NotNull;
import uz.pdp.app_codingbat.enums.ErrorTypeEnum;

import java.util.function.Supplier;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestException extends RuntimeException {

    private HttpStatus status;
    private final ErrorTypeEnum errorTypeEnum;


    private RestException(@NotNull ErrorTypeEnum errorTypeEnum) {
        this.errorTypeEnum = errorTypeEnum;
        this.status = errorTypeEnum.getStatus();
    }

    private RestException(@NotNull ErrorTypeEnum errorTypeEnum, HttpStatus status) {
        this.errorTypeEnum = errorTypeEnum;
        this.status = status;
    }

    public static RestException restThrow(@NotNull ErrorTypeEnum errorTypeEnum) {
        return new RestException(errorTypeEnum);
    }

    public static RestException restThrow(@NotNull ErrorTypeEnum errorTypeEnum, HttpStatus status) {
        return new RestException(errorTypeEnum, status);
    }

    public static Supplier<RestException> thew(@NotNull ErrorTypeEnum errorTypeEnum) {
        return () -> new RestException(errorTypeEnum);
    }

}
