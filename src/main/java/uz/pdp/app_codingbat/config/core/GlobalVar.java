package uz.pdp.app_codingbat.config.core;

import uz.pdp.app_codingbat.config.UserPrincipal;
import uz.pdp.app_codingbat.entity.User;

import java.time.LocalDateTime;

public class GlobalVar {

    private final static ThreadLocal<LocalDateTime> START_TIME = ThreadLocal.withInitial(LocalDateTime::now);
    private final static ThreadLocal<Long> START_TIME_LONG = ThreadLocal.withInitial(System::currentTimeMillis);
    private final static ThreadLocal<String> H_REQUEST_ID = ThreadLocal.withInitial(String::new);
    private final static ThreadLocal<String> USER_UUID = ThreadLocal.withInitial(String::new);
    private final static ThreadLocal<User> USER = ThreadLocal.withInitial(() -> null);
    private final static ThreadLocal<UserPrincipal> AUTH_USER = ThreadLocal.withInitial(() -> null);

    public static UserPrincipal getUserPrincipal() {
        return GlobalVar.AUTH_USER.get();
    }

    public static void setUserPrincipal(UserPrincipal authUser) {
        GlobalVar.AUTH_USER.set(authUser);
    }

    public static String getUserUUID() {
        return GlobalVar.USER_UUID.get();
    }

    public static void setUserUuid(String userUuid) {
        GlobalVar.USER_UUID.set(userUuid);
    }

    public static LocalDateTime getStartTime() {
        return GlobalVar.START_TIME.get();
    }

    public static void initStartTime() {
        GlobalVar.START_TIME.set(LocalDateTime.now());
    }

    public static Long getStartTimeLong() {
        return GlobalVar.START_TIME_LONG.get();
    }

    public static void initStartTimeLong() {
        GlobalVar.START_TIME_LONG.set(System.currentTimeMillis());
    }

    public static String getRequestId() {
        return GlobalVar.H_REQUEST_ID.get();
    }

    public static void setRequestId(String requestId) {
        GlobalVar.H_REQUEST_ID.set(requestId);
    }

}
