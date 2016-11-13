package org.ict4h;

import org.ict4h.util.Database;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

import java.sql.SQLException;

@Ignore
public class BaseTest {

    @BeforeClass
    public static void setUpClass() throws SQLException {
        Database.createDatabase();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        Database.dropAll();
    }
}
