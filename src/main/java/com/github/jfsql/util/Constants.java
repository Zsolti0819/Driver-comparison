package com.github.jfsql.util;

import java.io.File;
import java.nio.file.Path;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    private static final String pwd = String.valueOf(Path.of("").toAbsolutePath());
    public static final Path RESOURCES_PATH = Path.of(pwd, "src", "main", "resources");
    public static final String SQLITE_CONNECTION_STRING =
        "jdbc:sqlite:" + RESOURCES_PATH + File.separator + "databases" + File.separator + "myDatabase.db";
    public static final String JFSQL_CONNECTION_STRING =
        "jdbc:jfsql:" + RESOURCES_PATH + File.separator + "databases" + File.separator + "myDatabase";
    public static final String[] CONNECTION_STRINGS = {JFSQL_CONNECTION_STRING, SQLITE_CONNECTION_STRING};
    public static final String SCRIPTS_FOLDER = String.valueOf(RESOURCES_PATH.resolve("scripts"));

}
