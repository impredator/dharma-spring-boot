package com.dharma.demo.controller;

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

    @Value("${dharma.fileurl}")
    private String filePath;

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("dharmaFile") MultipartFile file) {
        if (file.isEmpty()) {
            return "file empty";
        }

        String fileName = generateFileName(file.getOriginalFilename());

        File dest = makeFile(filePath + fileName);

        try {
            file.transferTo(dest);
            return "<img src='/upload/" + fileName + "' />";
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return "upload failed";
    }

    private String generateFileName(String originalName) {
        logger.info("filer name: " + originalName);
        String suffixName = originalName.substring(originalName.lastIndexOf("."));
        logger.info("file suffix: " + suffixName);

        return UUID.randomUUID() + suffixName;
    }

    private File makeFile(String fileName) {
        File dest = new File(fileName);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        return dest;
    }
}
