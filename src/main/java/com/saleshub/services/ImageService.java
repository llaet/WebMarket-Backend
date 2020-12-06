package com.saleshub.services;

import com.saleshub.services.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public BufferedImage getJPEGImageFromFile(MultipartFile multipartFile){

        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        String png = "png";

        if(!fileExtension.equals("jpg") &&
                !fileExtension.equals(png)){
            throw new FileException("Somente imagens JPEG e PNG são permitidas");
        }

        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());

            if(image.equals(png)) {
                image = pngToJPEG(image);
            }
            return image;

        } catch (IOException e) {
            throw new FileException("Erro ao ler o arquivo");
        }
    }

    private BufferedImage pngToJPEG(BufferedImage image) {

        BufferedImage jpgImage =
                new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        jpgImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);

        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage image, String extension) {
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, extension, outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ioe) {
            throw new FileException("Erro ao ler arquivo");
        }
    }

    public BufferedImage cropImage(BufferedImage image){
        int minImageSideSize = (image.getHeight() <= image.getWidth()) ? image.getHeight() : image.getWidth();

        return Scalr.crop(
                image,
                (image.getWidth() / 2) - minImageSideSize / 2,
                (image.getHeight() / 2) - minImageSideSize / 2,
                minImageSideSize,
                minImageSideSize);
    }

    public BufferedImage resizeImage(BufferedImage image, int size){
        return Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, size);
    }
}
