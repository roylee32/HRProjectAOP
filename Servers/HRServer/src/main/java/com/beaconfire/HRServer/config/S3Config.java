package com.beaconfire.HRServer.config;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${aws.access_key}")
    private String access_key_id;

    @Value("${aws.secret_access_key}")
    private String secret_access_key;

    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonS3 s3client(){
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(access_key_id, secret_access_key);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
        return s3Client;
    }

}
