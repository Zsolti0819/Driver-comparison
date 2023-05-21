package com.github.jfsql.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResultSetPrinter {

    public static void printResultSet(final ResultSet resultSet, final String filePath)
        throws SQLException, IOException {
        final int numColumns = resultSet.getMetaData().getColumnCount();
        final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));

        while (resultSet.next()) {
            for (int i = 1; i <= numColumns; i++) {
                writer.printf("%-15s", resultSet.getString(i));
            }
            writer.println();
        }
        writer.close();
    }

}
