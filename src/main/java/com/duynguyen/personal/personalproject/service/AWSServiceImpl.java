package com.duynguyen.personal.personalproject.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AWSServiceImpl implements AWSService {

    private String AWS_S3_BASE_URL = "https://s3-us-west-2.amazonaws.com/";

    private String IMAGE_PATH = "/images/";

    private static AmazonS3 amazonS3;

    @Override
    public void setup(String accessKey, String secretKey) {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_WEST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    @Override
    public String uploadFile(String bucketName, File file) {
        amazonS3.putObject(new PutObjectRequest(
                bucketName, "images/" + file.getName(), file).withCannedAcl(CannedAccessControlList.PublicRead));

        return AWS_S3_BASE_URL + bucketName + IMAGE_PATH + file.getName();
    }

}
