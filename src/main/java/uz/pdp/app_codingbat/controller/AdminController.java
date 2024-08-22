package uz.pdp.app_codingbat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_codingbat.config.core.BaseURI;
import uz.pdp.app_codingbat.config.core.GlobalVar;
import uz.pdp.app_codingbat.config.logger.Logger;
import uz.pdp.app_codingbat.entity.User;
import uz.pdp.app_codingbat.payload.auth.req.ReqSignIn;
import uz.pdp.app_codingbat.payload.base.ApiResult;


@RequestMapping(BaseURI.API1 + BaseURI.ADMIN)
@RestController
@RequiredArgsConstructor
@Slf4j
public class AdminController {


    @PostMapping
//    @PreAuthorize(value = "hasAuthority('SOLVE_PROBLEM1')")
    public ApiResult<String> test(@Valid @RequestBody ReqSignIn reqSignIn) {

        User user = GlobalVar.getUser();
        System.out.println("GlobalVar.getRequestId() = " + GlobalVar.getRequestId());
        System.out.println(user);

//        try {
//            throw new RuntimeException("adhgfvbjhsd");
//        } catch (Exception e) {
//            Logger.error(e);
//        }
//
////        log.trace("reqSignIn: {} {}", reqSignIn, "End");
//        Logger.info("reqSignIn: {} {}", reqSignIn, "End");
        return ApiResult.successResponse();
    }

}
