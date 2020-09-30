package com.saleshub.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    public void uploadFile(String localFilePath){

        try {
            File file = new File(localFilePath);
            this.s3Client.putObject(new PutObjectRequest(this.bucketName, "teste", file));
        } catch (AmazonServiceException ase) {
            this.LOG.info("AmazonServiceException" + ase.getErrorMessage());
            this.LOG.info("Status code" + ase.getErrorCode());
        } catch (AmazonClientException ace) {
            this.LOG.info("AmazonClienteException" + ace.getMessage());
        }
    }
}
