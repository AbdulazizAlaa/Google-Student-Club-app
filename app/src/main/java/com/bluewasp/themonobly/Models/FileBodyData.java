package com.bluewasp.themonobly.Models;

import org.apache.http.entity.mime.content.FileBody;

/**
 * Created by Lenovo on 1/6/2015.
 */
public class FileBodyData {

    String key;
    private FileBody fileBody;

    public FileBodyData(String key, FileBody fileBody) {
        super();
        this.key = key;
        this.fileBody = fileBody;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FileBody getFileBody() {
        return fileBody;
    }

    public void setFileBody(FileBody fileBody) {
        this.fileBody = fileBody;
    }

}
