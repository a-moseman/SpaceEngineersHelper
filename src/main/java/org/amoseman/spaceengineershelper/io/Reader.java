package org.amoseman.spaceengineershelper.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static List<String> read(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}
