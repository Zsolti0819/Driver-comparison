package com.github;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResultSetPrinter {

    public static void printResultSet(final ResultSet rs) throws SQLException {
        final int numColumns = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= numColumns; i++) {
                System.out.printf("%-15s", rs.getString(i));
            }
            System.out.println();
        }
    }

}
