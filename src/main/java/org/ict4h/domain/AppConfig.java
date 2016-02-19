package org.ict4h.domain;


public class AppConfig {

    private String appName;
    private String db_url;
    private String db_uid;
    private String db_pwd;

    public AppConfig() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDb_url() {
        return db_url;
    }

    public void setDb_url(String db_url) {
        this.db_url= db_url;
    }

    public String getDb_pwd() {
        return db_pwd;
    }

    public void setDb_pwd(String db_pwd) {
        this.db_pwd = db_pwd;
    }

    public String getDb_uid() {
        return db_uid;
    }

    public void setDb_uid(String db_uid) {
        this.db_uid = db_uid;
    }
}
