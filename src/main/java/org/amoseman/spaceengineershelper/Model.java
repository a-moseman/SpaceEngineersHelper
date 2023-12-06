package org.amoseman.spaceengineershelper;

import org.amoseman.spaceengineershelper.io.Parser;
import org.amoseman.spaceengineershelper.io.Reader;
import org.amoseman.spaceengineershelper.io.State;
import org.amoseman.spaceengineershelper.resource.Cost;
import org.amoseman.spaceengineershelper.resource.Resource;

import java.io.IOException;
import java.util.List;

public class Model {
    private State state;

    public Model(String path) {
        List<String> lines = null;
        try {
            lines = Reader.read(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        state = Parser.parse(lines);
    }

    public AggregatedCost getAggregatedCost(Resource resource) {
        AggregatedCost aggregatedCost = new AggregatedCost(resource);
        getAggregatedCostHelper(1, resource, aggregatedCost);
        return aggregatedCost;
    }

    private void getAggregatedCostHelper(int multiplier, Resource resource, AggregatedCost aggregatedCost) {
        if (!state.hasCost(resource.getName())) {
            aggregatedCost.add(multiplier, resource);
            return;
        }
        int m = multiplier * resource.getAmount();
        Cost cost = state.getCost(resource.getName());
        List<Resource> in = cost.getIn();
        for (Resource child : in) {
            getAggregatedCostHelper(m, child, aggregatedCost);
        }
    }

    public boolean isResource(String name) {
        for (String resource : state.getResourceNames()) {
            if (name.equals(resource)) {
                return true;
            }
        }
        return false;
    }
}
