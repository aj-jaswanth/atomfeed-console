package org.ict4h.domain;

import org.springframework.http.HttpStatus;

public class Server implements java.io.Serializable{

    String serverUrl;
    HttpStatus status;

    public Server() {
    }

    public Server(String serverUrl, HttpStatus status) {
        this.serverUrl = serverUrl;
        this.status = status;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
