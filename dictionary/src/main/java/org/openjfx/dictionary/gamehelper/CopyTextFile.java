package org.openjfx.dictionary.gamehelper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyTextFile {
    public static void Copy(String sourceFileName, String destinationFileName) {
        try (FileReader fileReader = new FileReader(sourceFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(destinationFileName)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Đọc từng dòng từ tệp nguồn và ghi vào tệp đích
                fileWriter.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

