package com.saleshub.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.saleshub.services.exceptions.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream inputStream = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();

            return uploadFile(inputStream,fileName,contentType);
        } catch (IOException ioe) {
            throw new FileException("URL to URI conversion error");
        }
    }

    public URI uploadFile(InputStream inputStream, String fileName, String contentType){
            try {

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(contentType);
            this.s3Client.putObject(bucketName, fileName, inputStream, metadata);

            return  this.s3Client.getUrl(this.bucketName,fileName).toURI();

        } catch (URISyntaxException use) {
               throw new FileException("URL to URI conversion error");
            }
    }

}

