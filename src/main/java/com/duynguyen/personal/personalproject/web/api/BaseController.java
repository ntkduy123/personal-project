package com.duynguyen.personal.personalproject.web.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public abstract class BaseController {

    @Value("${aws.access-key}")
    protected String accessKey;

    @Value("${aws.secret-key}")
    protected String secretKey;

    @Value("${aws.bucket-name}")
    protected String bucketName;

}
