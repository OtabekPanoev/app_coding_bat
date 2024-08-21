package uz.pdp.app_codingbat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_codingbat.config.core.BaseURI;
import uz.pdp.app_codingbat.config.logger.Logger;
import uz.pdp.app_codingbat.payload.auth.req.ReqSignIn;
import uz.pdp.app_codingbat.payload.base.ApiResult;


@RequestMapping(BaseURI.API1 + BaseURI.ADMIN)
@RestController
@RequiredArgsConstructor
public class AdminController {

    @PostMapping
//    @PreAuthorize(value = "hasAuthority('SOLVE_PROBLEM1')")
    public ApiResult<String> test(@Valid @RequestBody ReqSignIn reqSignIn) {
        Logger.info("Testing Admin API {}", reqSignIn);
        return ApiResult.successResponse();
    }

}
