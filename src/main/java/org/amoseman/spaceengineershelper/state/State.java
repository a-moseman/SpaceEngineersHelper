package org.amoseman.spaceengineershelper.state;

import org.amoseman.spaceengineershelper.resource.Cost;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class State {
    private final List<String> resourceNames;
    private final HashMap<String, Cost> resourceCosts;

    public State(List<String> resourceNames, List<Cost> resourceCosts) {
        this.resourceNames = resourceNames;
        this.resourceCosts = new HashMap<>();
        for (Cost cost : resourceCosts) {
            this.resourceCosts.put(cost.getOut().getName(), cost);
        }
    }

    public Set<String> getResourceCostsKeys() {
        return resourceCosts.keySet();
    }

    public boolean hasCost(String name) {
        return resourceCosts.containsKey(name);
    }

    public Cost getCost(String name) {
        if (hasCost(name)) {
            return resourceCosts.get(name);
        }
        throw new RuntimeException("Cost does not exist");
    }

    public List<String> getResourceNames() {
        return resourceNames;
    }
}
