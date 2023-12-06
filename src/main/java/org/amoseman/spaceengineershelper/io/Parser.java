package org.amoseman.spaceengineershelper.io;
import org.amoseman.spaceengineershelper.resource.Cost;
import org.amoseman.spaceengineershelper.resource.Resource;
import org.amoseman.spaceengineershelper.state.State;
import org.amoseman.spaceengineershelper.state.StateFactory;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static State parse(List<String> lines) {
        StateFactory factory = new StateFactory();
        parseCosts(parseNames(0, lines, factory), lines, factory);
        return factory.build();
    }

    private static int parseNames(int index, List<String> lines, StateFactory factory) {
        while (index < lines.size()) {
            String line = lines.get(index);
            index++;
            if (line.isEmpty()) {
                break;
            }
            factory.addName(line);
        }
        return index;
    }

    private static void parseCosts(int index, List<String> lines, StateFactory factory) {
        while (index < lines.size()) {
            String line = lines.get(index);
            Resource out = parseResource(line);
            List<Resource> in = new ArrayList<>();
            index++;
            while (index < lines.size() && (line = lines.get(index)).startsWith("\t")) {
                line = line.replaceAll("\t", "");
                in.add(parseResource(line));
                index++;
            }
            factory.addCost(new Cost(out, in));
        }
    }

    private static Resource parseResource(String line) {
        String[] parts = line.split(": ");
        if (parts.length == 1) {
            return new Resource(parts[0], 1);
        }
        else {
            long amount = Long.parseLong(parts[1]);
            return new Resource(parts[0], amount);
        }
    }
}
