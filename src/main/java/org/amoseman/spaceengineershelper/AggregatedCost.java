package org.amoseman.spaceengineershelper;

import org.amoseman.spaceengineershelper.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AggregatedCost {
    private final Resource resource;
    private final HashMap<String, Integer> cost;

    public AggregatedCost(Resource resource) {
        this.resource = resource;
        this.cost = new HashMap<>();
    }

    public void add(int multiplier, Resource resource) {
        String key = resource.getName();
        int amount = resource.getAmount() * multiplier;
        if (cost.containsKey(key)) {
            amount += cost.get(key);
        }
        cost.put(key, amount);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(resource.toString());
        Set<String> keys = cost.keySet();
        for (String key : keys) {
            out.append("\n\t").append(key).append(": ").append(cost.get(key));
        }
        return out.toString();
    }
}
