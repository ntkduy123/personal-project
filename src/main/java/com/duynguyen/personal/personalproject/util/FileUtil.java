package com.duynguyen.personal.personalproject.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static File convertMultiPartToFile(MultipartFile file, String fileName) throws IOException {
        File convertFile = new File(fileName);
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

//    public static void saveToTemp(MultipartFile multipartFile, String fileName) throws IOException {
//        File convertFile  = new File("src/main/resources/static/images/" + fileName);
//        FileOutputStream fos = new FileOutputStream(convertFile);
//
//        fos.write(multipartFile.getBytes());
//        fos.close();
//    }
}
