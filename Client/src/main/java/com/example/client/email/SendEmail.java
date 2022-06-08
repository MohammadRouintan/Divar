package com.example.client.email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class SendEmail {

    private String serverEmail;
    private String clientEmail;
    private String code;
    private String host;
    private Properties properties;
    private Session session;
    private String titleMassage;
    private String textMassage;

    public SendEmail(){

    }

    public String getServerEmail() {
        return serverEmail;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public String getCode() {
        return code;
    }

    public String getHost() {
        return host;
    }

    public String getTitleMassage() {
        return titleMassage;
    }

    public String getTextMassage() {
        return textMassage;
    }

    public void setServerEmail(String serverEmail) {
        this.serverEmail = serverEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setTitleMassage(String titleMassage) {
        this.titleMassage = titleMassage;
    }

    public void setTextMassage(String textMassage) {
        this.textMassage = textMassage;
    }
}
