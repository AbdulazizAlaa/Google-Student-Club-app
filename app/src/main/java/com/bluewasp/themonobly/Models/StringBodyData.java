package com.bluewasp.themonobly.Models;

import org.apache.http.entity.mime.content.StringBody;

/**
 * Created by Lenovo on 1/6/2015.
 */
public class StringBodyData {

    private String key;
    private StringBody stringBody;


    public StringBodyData(String key, StringBody stringBody) {
        super();
        this.key = key;
        this.stringBody = stringBody;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public StringBody getStringBody() {
        return stringBody;
    }
    public void setStringBody(StringBody stringBody) {
        this.stringBody = stringBody;
    }

}
