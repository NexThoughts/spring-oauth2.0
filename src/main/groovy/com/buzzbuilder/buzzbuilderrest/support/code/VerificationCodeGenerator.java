package com.buzzbuilder.buzzbuilderrest.support.code;

import org.springframework.web.context.request.ServletWebRequest;


public interface VerificationCodeGenerator {

    /**
     * 生成验证码
     * @return
     */
    VerificationCode generator(ServletWebRequest request);

}
