package com.dharma.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping(value = "/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${dharma.path}")
    private String filePath;

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("dharmaFile") MultipartFile file) {
        if (file.isEmpty()) {
            return "file empty";
        }

        String fileName = file.getOriginalFilename();
        logger.info("file name：" + fileName);

        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("file suffix：" + suffixName);

        fileName = UUID.randomUUID() + suffixName;

        File dest = new File(filePath + fileName);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
            return "upload done:" + filePath + fileName;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return "upload failed";
    }

}
