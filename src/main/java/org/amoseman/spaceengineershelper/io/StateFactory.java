package org.amoseman.spaceengineershelper.io;

import org.amoseman.spaceengineershelper.resource.Cost;

import java.util.ArrayList;
import java.util.List;

public class StateFactory {
    private List<String> resourceNames;
    private List<Cost> resourceCosts;

    public StateFactory() {
        this.resourceNames = new ArrayList<>();
        this.resourceCosts = new ArrayList<>();
    }

    public StateFactory addName(String name) {
        resourceNames.add(name);
        return this;
    }

    public StateFactory addCost(Cost cost) {
        resourceCosts.add(cost);
        return this;
    }

    public State build() {
        return new State(resourceNames, resourceCosts);
    }
}
