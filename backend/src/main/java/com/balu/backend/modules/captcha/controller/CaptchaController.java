package com.balu.backend.modules.captcha.controller;

import com.balu.backend.modules.captcha.model.CaptchaResponseDto;
import com.balu.backend.modules.captcha.model.VerifyCaptchaDto;
import com.balu.backend.modules.captcha.service.CaptchaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/captcha")
@CrossOrigin(
        origins = {"*"},
        methods = {RequestMethod.POST, RequestMethod.OPTIONS}
)
@AllArgsConstructor
public class CaptchaController {
    private CaptchaService captchaService;

    @PostMapping("/verify")
    public CaptchaResponseDto verifyCaptcha(@RequestBody VerifyCaptchaDto dto){
        return captchaService.verifyCaptcha(dto);
    }
}
