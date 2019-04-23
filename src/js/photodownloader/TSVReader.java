package js.photodownloader;

import java.util.function.Function;

public class TSVReader implements Function<String, Image> {
    @Override
    public Image apply(String s) {
        // split line with tab
        String[] row = s.split("\t");
        // skip the very first row, assuming it contains header
        if (row[0].equals("#")) {
            return null;
        }
        // return image object
        return new Image(
            row[Main.config.getInt("urlColumnIndex")],
            row[Main.config.getInt("renameToColumnIndex")]
        );
    }
}
