package hashing;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFileCSV {
    public static List<String[]> read(String path) {
        List<String[]> data = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            String[] line = reader.readNext(); // HEADER ignorar

            while ((line = reader.readNext()) != null) {
                if ( ! lineHasFieldEmpty(line) ){
                    data.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    private static Boolean lineHasFieldEmpty(String[] line){
        for (String field : line) {
            if ( field.isEmpty() ) return true;
        }
        return false;
    }
}
