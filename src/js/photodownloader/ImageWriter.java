package ru.crpt.catalog.pd;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class ImageWriter implements Consumer<Image> {

    private final String S3UriPrefix;
    private final String localFolderPrefix;

    ImageWriter() {
        // local variables from config
        S3UriPrefix = Main.config.get("s3UriPrefix");
        localFolderPrefix = Main.config.get("downloadToFolder");
    }

    @Override
    public void accept(Image image) {
        // get destination folder
        Path destination = Paths.get(localFolderPrefix + image.getRenameTo());

        // open web stream
        try (InputStream stream = new URL(S3UriPrefix + image.getUrl()).openStream()) {

            // check if destination folder exists
            if (!Files.exists(destination)) {
                // create destination folder if needed
                Files.createDirectories(destination.getParent());
            }

            // download file to destination folder
            Files.copy(stream, destination);

            System.out.println("Downloaded " + image.getUrl() + " to " + destination.toString());

        } catch (FileAlreadyExistsException e ) {
            System.out.println("[ERROR] File already exists at " + destination.toString());
        } catch (IOException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
