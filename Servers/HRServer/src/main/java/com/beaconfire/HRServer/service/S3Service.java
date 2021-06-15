package com.beaconfire.HRServer.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;


@Service
public class S3Service {

    private AmazonS3 s3client;

    @Value("${aws.bucket}")
    private String bucketName;

    @Autowired
    public void setS3client(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public String getSignedUrlByPath(String path){
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, path)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    public String getDefaultAvatar(){
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, "default_avatar.png")
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    public void uploadFile(String keyName, MultipartFile file){
        try{
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            s3client.putObject(bucketName, keyName, file.getInputStream(), metadata);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public ByteArrayOutputStream downloadFile(String keyName){
        try{
            S3Object s3Object = s3client.getObject(new GetObjectRequest(bucketName, keyName));
            InputStream is = s3Object.getObjectContent();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0 , buffer.length)) != -1){
                baos.write(buffer, 0, len);
            }
            return baos;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
