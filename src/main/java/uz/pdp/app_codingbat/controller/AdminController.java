package uz.pdp.app_codingbat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_codingbat.config.base.BaseURI;
import uz.pdp.app_codingbat.payload.auth.req.ReqRefreshToken;
import uz.pdp.app_codingbat.payload.auth.req.ReqSignIn;
import uz.pdp.app_codingbat.payload.auth.req.ReqSignUp;
import uz.pdp.app_codingbat.payload.auth.res.ResSignIn;
import uz.pdp.app_codingbat.payload.auth.res.TokenDto;
import uz.pdp.app_codingbat.payload.base.ApiResult;
import uz.pdp.app_codingbat.payload.base.ResBaseMsg;
import uz.pdp.app_codingbat.service.AuthService;


@RequestMapping(BaseURI.API1 + BaseURI.ADMIN)
@RestController
@RequiredArgsConstructor
public class AdminController {

    @PostMapping
    @PreAuthorize(value = "hasAuthority('SOLVE_PROBLEM1')")
    public ApiResult<String> test(@Valid @RequestBody ReqSignIn reqSignIn) {
        System.out.println(reqSignIn);
        return ApiResult.successResponse();
    }

}
