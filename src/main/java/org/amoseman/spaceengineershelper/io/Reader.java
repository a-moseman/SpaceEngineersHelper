package org.amoseman.spaceengineershelper.io;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static List<String> read(String path) {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader())
    }
}
