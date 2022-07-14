package com.project.tcg.domain.user.presentation;

import com.project.tcg.domain.user.presentation.dto.request.CheckNameDuplicationRequest;
import com.project.tcg.domain.user.presentation.dto.request.LoginRequest;
import com.project.tcg.domain.user.presentation.dto.request.SignupRequest;
import com.project.tcg.domain.user.service.CheckNameDuplicationService;
import com.project.tcg.domain.user.service.LoginService;
import com.project.tcg.domain.user.service.LogoutService;
import com.project.tcg.domain.user.service.ModifyProfileService;
import com.project.tcg.domain.user.service.SignupService;
import com.project.tcg.domain.user.service.TokenRefreshService;
import com.project.tcg.domain.user.service.WithdrawalService;
import com.project.tcg.global.utils.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final CheckNameDuplicationService checkNameDuplicationService;
    private final SignupService signupService;
    private final LoginService loginService;
    private final TokenRefreshService tokenRefreshService;

    private final ModifyProfileService modifyProfileService;

    private final LogoutService logoutService;
    private final WithdrawalService withdrawalService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/user/id")
    public void checkIdDuplication(@Valid @RequestBody CheckNameDuplicationRequest request){
        checkNameDuplicationService.execute(request);
    }

    @PostMapping("/user")
    public TokenResponse signup(@RequestBody @Valid SignupRequest request){
        return signupService.execute(request);
    }

    @PatchMapping("/user/profile")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void profile(@RequestPart MultipartFile file) {
        modifyProfileService.execute(file);
    }

    @PostMapping("/user/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request){
        return loginService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/user/logout")
    public void logout(){
        logoutService.execute();
    }

    @PutMapping("/user/auth")
    public TokenResponse tokenRefresh(@RequestHeader("X-Refresh-Token") String refreshToken){
        return tokenRefreshService.execute(refreshToken);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/user")
    public void withdrawal(){
        withdrawalService.execute();
    }
}