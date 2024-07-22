package com.gmail.merikbest2015.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;

@Configuration
public class ImageConfiguration {

    @Value("${amazon.aws.access-key}")
    private String awsAccessKey;

    @Value("${amazon.aws.secret-key}")
    private String awsAccessSecret;
    
    @Value("${amazon.aws.endpoint}")
    private String awsEndPoint;

    @Bean
    public AmazonS3 s3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsAccessSecret);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new EndpointConfiguration( awsEndPoint , "us-east-1"))
                .withPathStyleAccessEnabled(true) // Test 
                //.withRegion(Regions.EU_CENTRAL_1)
                .build();
    }
}


