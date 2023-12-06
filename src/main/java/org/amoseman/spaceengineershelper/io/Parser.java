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
        int index = 0;
        Resource resource = null;
        List<Resource> in = new ArrayList<>();
        while (index < lines.size()) {
            String line = lines.get(index);
            index++;
            if (line.startsWith("//")) {
                continue;
            }
            Resource temp = parseResource(line);
            if (line.startsWith("\t")) {
                in.add(temp);
            }
            else {
                factory.addName(temp.getName());
                if (!in.isEmpty()) {
                    factory.addCost(new Cost(resource, in));
                    in = new ArrayList<>();
                }
                resource = temp;
            }
        }
        if (!in.isEmpty()) {
            factory.addCost(new Cost(resource, in));
        }
        return factory.build();
    }

    private static Resource parseResource(String line) {
        line = line.replaceAll("\t", "");
        String[] parts = line.split(": ");
        if (parts.length == 1) {
            return new Resource(parts[0], 1);
        }
        else {
            double amount = Double.parseDouble(parts[1]);
            return new Resource(parts[0], amount);
        }
    }
}
