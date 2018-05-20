package com.duynguyen.personal.personalproject.web.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public abstract class BaseController {

    protected String accessKey = System.getenv("AWS_ACCESS_KEY_ID");

    protected String secretKey = System.getenv("AWS_SECRET_ACCESS_KEY");

    protected String bucketName = System.getenv("AWS_BUCKET_NAME");
}
