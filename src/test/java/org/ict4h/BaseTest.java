package org.ict4h;

import org.ict4h.util.Database;
import org.junit.BeforeClass;
import org.junit.Ignore;

import java.sql.SQLException;

@Ignore
public class BaseTest {

    private static boolean isInitialized = false;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        if (!isInitialized) {
            Database.createDatabase();
            isInitialized = true;
        }
    }

}
