package org.ict4h.domain;

import org.ict4h.atomfeed.client.domain.Marker;
import org.ict4h.atomfeed.client.repository.AllMarkers;
import org.ict4h.atomfeed.client.repository.jdbc.AllMarkersJdbcImpl;
import org.ict4h.atomfeed.jdbc.JdbcConnectionProvider;
import org.ict4h.service.AppConfiguration;
import org.springframework.context.annotation.Configuration;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AppStatus {

    private final AllMarkers markers;
    AppConfiguration appConfiguration = new AppConfiguration();
    AppConfigs appConfigs = appConfiguration.getAppDetails();
    AppConfig appConfig = new AppConfig();
    String db_url,db_uid,db_pwd;
    List<Marker> marker;


    public AppStatus(String app_name) throws SQLException{
        org.ict4h.atomfeed.Configuration.getInstance("Apps.yml");

        for(int j=0;j<appConfigs.size();j++)
        {
            appConfig=(AppConfig)appConfigs.get(j);
            if((appConfig.getAppName()).compareToIgnoreCase(app_name)==0){
                db_url = appConfig.getDb_url();
                db_uid = appConfig.getDb_uid();
                db_pwd = appConfig.getDb_pwd();
            }


        }
        this.markers = new AllMarkersJdbcImpl(new JdbcConnectionProvider() {
            @Override
            public Connection getConnection() throws  SQLException {
                return DriverManager.getConnection(db_url, db_uid, db_pwd);
            }
        });
    }



    public List<Marker> getMarker() {
        return marker;
    }

    public void setMarker() {
        this.marker= markers.getMarkerList();
    }

}
