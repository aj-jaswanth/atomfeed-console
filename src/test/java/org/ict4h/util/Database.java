package org.ict4h.util;

import java.sql.*;

public class Database {

    public static void createDatabase() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(true);
        runStatement(CREATE_MARKERS_TABLE, connection);
        runStatement(CREATE_FAILED_EVENTS_TABLE, connection);
        runStatement(CREATE_FAILED_EVENTS_RETRY_LOG_TABLE, connection);
        connection.close();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
    }

    private static final String CREATE_MARKERS_TABLE = "CREATE TABLE markers (\n" +
            "  feed_uri varchar(255) NOT NULL,\n" +
            "  last_read_entry_id varchar(255) DEFAULT NULL,\n" +
            "  feed_uri_for_last_read_entry varchar(255) DEFAULT NULL,\n" +
            "  PRIMARY KEY (feed_uri)\n" +
            ");";
    private static final String CREATE_FAILED_EVENTS_TABLE = "CREATE TABLE failed_events (\n" +
            "  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n" +
            "  feed_uri varchar(255) DEFAULT NULL,\n" +
            "  failed_at timestamp NOT NULL,\n" +
            "  error_message varchar(4000) DEFAULT NULL,\n" +
            "  event_id varchar(255) DEFAULT NULL,\n" +
            "  event_content varchar(4000) DEFAULT NULL,\n" +
            "  error_hash_code int(11) DEFAULT NULL,\n" +
            "  title varchar(255) DEFAULT NULL,\n" +
            "  retries int(11) NOT NULL,\n" +
            "  tags varchar(255) DEFAULT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            ")";

    private static final String CREATE_FAILED_EVENTS_RETRY_LOG_TABLE = "CREATE TABLE failed_event_retry_log (\n" +
            "  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n" +
            "  feed_uri varchar(255) DEFAULT NULL,\n" +
            "  failed_at timestamp NOT NULL,\n" +
            "  error_message varchar(4000) DEFAULT NULL,\n" +
            "  event_id varchar(255) DEFAULT NULL,\n" +
            "  event_content varchar(4000) DEFAULT NULL,\n" +
            "  PRIMARY KEY (id)\n" +
            ")";

    public static void runStatement(String sql) throws SQLException {
        Connection connection = getConnection();
        runStatement(sql, connection);
        connection.close();
    }

    public static void runStatement(String sql, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
    }

    public static int runStatement(String sql, String eventId) throws SQLException {
        ResultSet resultSet;
        Integer result = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        statement = connection.prepareStatement(sql);
        statement.setString(1,eventId);
        resultSet=statement.executeQuery();
        while(resultSet.next())
        {
            result = resultSet.getInt(9);
        }
        statement.close();
        return result;
    }

    public static void dropAll() throws SQLException {
        runStatement("DROP ALL OBJECTS");
    }
}
