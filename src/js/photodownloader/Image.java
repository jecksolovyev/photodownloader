package ru.crpt.catalog.pd;

class Image {

    private String url;
    private String renameTo;

    Image(String url, String renameTo) {
        this.url = url;
        this.renameTo = renameTo;
    }

    String getUrl() {
        return url;
    }

    String getRenameTo() {
        return renameTo;
    }
}
