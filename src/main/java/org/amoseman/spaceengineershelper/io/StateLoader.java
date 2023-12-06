package org.amoseman.spaceengineershelper.io;

import org.amoseman.spaceengineershelper.state.State;

import java.io.IOException;
import java.util.List;

public class StateLoader {
    public static State load(String path) {
        List<String> lines;
        try {
            lines = Reader.read(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Parser.parse(lines);
    }
}
