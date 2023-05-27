package File;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import File.File;
import com.opencsv.CSVReader;

public class ReadFileCSV {
    public static List<File> read(String path) {
        List<File> data = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            String[] line = reader.readNext(); // HEADER ignorar

            while ((line = reader.readNext()) != null) {
                if ( ! lineHasFieldEmpty(line) ){

                    File file = new File(
                        line[0],
                        line[1],
                        Integer.parseInt(line[4]),
                        line[2],
                        line[3]
                    );

                    data.add(file);
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
