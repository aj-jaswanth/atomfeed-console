package org.ict4h.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.ict4h.domain.configuration.AppConfig;
import org.ict4h.service.AppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Component
public class ConnectionPools {
    private ComboPooledDataSource cpds;
    private HashMap<String,ComboPooledDataSource> datasources = new HashMap<>();
    private AppConfiguration appConfiguration;

    @Autowired
    public ConnectionPools(AppConfiguration appConfiguration)throws SQLException {
        this.appConfiguration = appConfiguration;
    }

    @PostConstruct
    public void setupConfiguration() {
        List<AppConfig> appConfigs = appConfiguration.getAppConfigs();
        cpds = new ComboPooledDataSource();
        for(AppConfig appConfig : appConfigs) {

            cpds.setJdbcUrl(appConfig.getDbUrl());
            cpds.setUser(appConfig.getDbUser());
            cpds.setPassword(appConfig.getDbPassword());
            cpds.setAutoCommitOnClose(true);
            datasources.put(appConfig.getAppName(), cpds);
        }
    }

    public Connection getConnection(String appName)throws SQLException{
        return datasources.get(appName).getConnection();
    }
}
