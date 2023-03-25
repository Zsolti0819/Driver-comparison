package com.github.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResultSetPrinter {

    public static void printResultSet(final ResultSet resultSet) throws SQLException {
        final int numColumns = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= numColumns; i++) {
                System.out.printf("%-15s", resultSet.getString(i));
            }
            System.out.println();
        }
    }

}
