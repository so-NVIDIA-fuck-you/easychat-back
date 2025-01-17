package org.itheima.easychatback.controller;

import com.wf.captcha.ArithmeticCaptcha;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.itheima.easychatback.Utils.RedisUtils;
import org.itheima.easychatback.annotation.GlobalInterceptor;
import org.itheima.easychatback.entity.constants.Constants;
import org.itheima.easychatback.entity.vo.UserInfoVO;
import org.itheima.easychatback.exception.BusinessException;
import org.itheima.easychatback.mapper.UserInfoMapper;
import org.itheima.easychatback.redis.RedisComponent;
import org.itheima.easychatback.result.Result;
import org.itheima.easychatback.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController("accountController")
@RequestMapping("/account")
@Validated
public class AccountController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private  UserInfoService userInfoService;

    @Autowired
    private RedisComponent redisComponent;



    @RequestMapping("/checkCode")
    public Result<Map<String, String>> checkCode() {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 42);
        String code = captcha.text();
        log.info("验证码是:{}", code);
        String checkCodeKey = UUID.randomUUID().toString();
        redisUtils.setEx(Constants.REDIS_KEY_CHECK_CODE+checkCodeKey, code, Constants.REDIS_TIME_OUT, TimeUnit.SECONDS);

        String checkBase64 = captcha.toBase64();
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("checkCode", checkBase64);
        resultMap.put("checkCodeKey", checkCodeKey);
        return Result.success(resultMap);
    }

    @PostMapping("/register")
    public Result register(@NotEmpty String checkCodeKey,
                           @NotEmpty @Email String email,
                           @NotEmpty String passWord,
                           @NotEmpty String nickName,
                           @NotEmpty String checkCode) {
        try {

            if (!checkCode.equalsIgnoreCase((String) redisUtils.get(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey))) {
                throw new BusinessException("图片验证码不正确");
            }
            userInfoService.register(email,passWord,nickName);

            return Result.success();

        } finally {
            redisUtils.delete(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey);
        }
    }


    @PostMapping("/login")
    public Result login(@NotEmpty String checkCodeKey,
                           @NotEmpty @Email String email,
                           @NotEmpty String passWord,
                           @NotEmpty String checkCode) {
        try {
            if (!checkCode.equalsIgnoreCase((String) redisUtils.get(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey))) {
                throw new BusinessException("图片验证码不正确");
            }

            UserInfoVO userInfoVO=userInfoService.login(email,passWord);

            return Result.success(userInfoVO);

        } finally {
            redisUtils.delete(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey);
        }
    }

    @GetMapping("/getSysSetting")
    @GlobalInterceptor
    public Result login() {
        return Result.success(redisComponent.getSysSetting());
    }








}
