package com.github;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.file.Path;

@UtilityClass
public class Constants {
    private static final String BASE_PATH = System.getProperty("user.dir");
    public static final Path RESOURCES_PATH = Path.of(
            BASE_PATH + File.separator + "src" + File.separator + "main" + File.separator + "resources");
    public static final String SQLITE_CONNECTION_STRING = "jdbc:sqlite:" + RESOURCES_PATH + File.separator + "myDatabase.db";
    public static final String JFSQL_CONNECTION_STRING = "jdbc:jfsql:" + RESOURCES_PATH + File.separator + "myDatabase";
    public static final String SCRIPTS_FOLDER = RESOURCES_PATH + File.separator + "scripts";

}
