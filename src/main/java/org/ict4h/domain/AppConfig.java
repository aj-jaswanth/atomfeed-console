package org.ict4h.domain;

public class AppConfig {

    private String appname;
    private String db_type;
    private String db_url;
    private String db_uid;
    private String db_pwd;

    public AppConfig() {
    }

    public AppConfig(String appname, String db_type, String db_url, String db_uid, String db_pwd) {
        this.appname = appname;
        this.db_type = db_type;
        this.db_url = db_url;
        this.db_uid = db_uid;
        this.db_pwd = db_pwd;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getDb_type() {
        return db_type;
    }

    public void setDb_type(String db_type) {
        this.db_type = db_type;
    }

    public String getDb_url() {
        return db_url;
    }

    public void setDb_url(String db_uri) {
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
