package com.duynguyen.personal.personalproject.service;

import java.io.File;

public interface AWSService {
    void setup(String accessKey, String secretKey);

    String uploadFile(String bucketName, File file);
}
