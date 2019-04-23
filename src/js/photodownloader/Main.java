package ru.crpt.catalog.pd;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {

    static Config config;

    public static void main(String[] args) {
        (new Main()).run();
    }

    private void run() {
        try {
            // load config
            Main.config = new Config();
            // check if set all required variables
            Main.config.require("tsvFileName");
            Main.config.require("urlColumnIndex");
            Main.config.require("renameToColumnIndex");
            Main.config.require("s3UriPrefix");
            Main.config.require("downloadToFolder");

            // for each line do
            Files
                .lines(Paths.get(Main.config.get("tsvFileName")), StandardCharsets.UTF_8)
                .map(new TSVReader())
                .filter(Objects::nonNull)
                .forEach(new ImageWriter());

        } catch (ConfigException e) {
            // this is Main.config::require exception
            System.out.println("Something wrong with your config: " + e.getMessage());

        } catch (IOException e) {
            // this is generic exception
            e.printStackTrace();
        }

        System.out.println("Finished");
    }
}
