package com.project.tcg.domain.user.presentation;

import com.project.tcg.domain.user.presentation.dto.request.LoginRequest;
import com.project.tcg.domain.user.presentation.dto.request.SignupRequest;
import com.project.tcg.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.project.tcg.domain.user.presentation.dto.response.QueryUserInfoResponse;
import com.project.tcg.domain.user.service.LoginService;
import com.project.tcg.domain.user.service.LogoutService;
import com.project.tcg.domain.user.service.QueryMyInfoService;
import com.project.tcg.domain.user.service.QueryUserInfoService;
import com.project.tcg.domain.user.service.SignupService;
import com.project.tcg.domain.user.service.TokenRefreshService;
import com.project.tcg.domain.user.service.UpdateUserInfoService;
import com.project.tcg.domain.user.service.WithdrawalService;
import com.project.tcg.global.utils.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final SignupService signupService;
    private final LoginService loginService;
    private final TokenRefreshService tokenRefreshService;
    private final UpdateUserInfoService updateUserInfoService;
    private final QueryMyInfoService queryMyInfoService;
    private final QueryUserInfoService queryUserInfoService;
    private final LogoutService logoutService;
    private final WithdrawalService withdrawalService;

    @PostMapping
    public TokenResponse signup(@RequestBody @Valid SignupRequest request){
        return signupService.execute(request);
    }

    @PostMapping("/auth")
    public TokenResponse login(@RequestBody @Valid LoginRequest request){
        return loginService.execute(request);
    }

    @PutMapping("/auth")
    public TokenResponse tokenRefresh(@RequestHeader("X-Refresh-Token") String refreshToken){
        return tokenRefreshService.execute(refreshToken);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateUserInfo(@RequestBody @Valid UpdateUserInfoRequest request) {
        updateUserInfoService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updateUserPassword(@RequestBody @Valid UpdateUserInfoRequest request) {
        updateUserInfoService.execute(request);
    }

    @GetMapping
    public QueryUserInfoResponse queryMyInfo() {
        return queryMyInfoService.execute();
    }

    @GetMapping("{user-id}")
    public QueryUserInfoResponse  queryUserInfo(@PathVariable("user-id") Long userId) {
        return queryUserInfoService.execute(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/logout")
    public void logout(){
        logoutService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void withdrawal(){
        withdrawalService.execute();
    }
}