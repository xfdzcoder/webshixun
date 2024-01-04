package com.example.webshixun.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class Base64ToMultipartFile implements MultipartFile {

    //解码Base64图像数据后,返回的数组对象
    private final byte[] fileContent;

   //扩展名（例如jpeg）
    private final String extension;

    //内容类型（例如image/jpeg）
    private final String contentType;


    /**
     * @param base64      未解码之前的后面部分（例如/9j/4AA...）
     * @param dataUri     为解码之前的整个部分,格式类似于: data:image/png;base64,/9j/4AA...
     */
    public Base64ToMultipartFile(String base64, String dataUri) {
        this.fileContent = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        this.extension = dataUri.split(";")[0].split("/")[1];
        this.contentType = dataUri.split(";")[0].split(":")[1];
    }

    public Base64ToMultipartFile(String base64, String extension, String contentType) {
        this.fileContent = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        this.extension = extension;
        this.contentType = contentType;
    }


    @Override
    public String getName() {
        return "param_" + System.currentTimeMillis();
    }

    @Override
    public String getOriginalFilename() {
        return "file_" + System.currentTimeMillis() + "." + extension;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return fileContent == null || fileContent.length == 0;
    }

    @Override
    public long getSize() {
        return fileContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return fileContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileContent);
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContent);
        }
    }

}

