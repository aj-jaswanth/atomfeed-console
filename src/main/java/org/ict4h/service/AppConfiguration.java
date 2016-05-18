package org.ict4h.service;

import org.apache.log4j.Logger;
import org.ict4h.domain.configuration.AppConfig;
import org.ict4h.domain.configuration.AppConfigs;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class AppConfiguration {
    private static Logger logger = Logger.getLogger(AppConfiguration.class);
    public static final String DEFAULT_APP_CONFIG_FILE = "applicationConfig.yml";
    private AppConfigs appconfigs = new AppConfigs();

    public AppConfiguration() throws SQLException{
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_APP_CONFIG_FILE);
        Constructor constructor = new Constructor(AppConfig.class);
        Yaml yaml = new Yaml(constructor);
        logger.info("Reading app details from a yaml file");
        for (Object data : yaml.loadAll(in)) {

            AppConfig obj = (AppConfig) data;
            appconfigs.add(obj);
        }

//        Properties prop = new Properties();
//        try {
//
//            File jarPath=new File(AppConfiguration.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//            String propertiesPath=jarPath.getParentFile().getParentFile().getAbsolutePath();
//            logger.info(propertiesPath);
//            InputStream in = new FileInputStream(propertiesPath+"/"+DEFAULT_APP_CONFIG_FILE);
//            logger.info(propertiesPath+"/"+DEFAULT_APP_CONFIG_FILE);
//            Constructor constructor = new Constructor(AppConfig.class);
//            Yaml yaml = new Yaml(constructor);
//            logger.info("Reading app details from a yaml file");
//            for (Object data : yaml.loadAll(in)) {
//
//                AppConfig obj = (AppConfig) data;
//                appconfigs.add(obj);
//            }
//            //System.out.println(" propertiesPath-"+propertiesPath);
//
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//
    }
    public AppConfigs getAppConfigs() {
        return appconfigs;
    }

    public AppConfig getAppConfigForApp(String appName) {
        return appconfigs.getForApp(appName);
    }
}
