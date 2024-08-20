package uz.pdp.app_codingbat.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority {

    ADD_LANGUAGE,
    EDIT_LANGUAGE,
    SOLVE_PROBLEM,
    EDIT_ROLE,
    ADD_ROLE,
    GET_USERS,
    DELETE_USER,
    GET_USER_PROBLEMS;

    @Override
    public String getAuthority() {
        return name();
    }
}
