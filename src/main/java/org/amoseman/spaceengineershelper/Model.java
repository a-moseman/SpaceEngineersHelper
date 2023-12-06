package org.amoseman.spaceengineershelper;

import org.amoseman.spaceengineershelper.io.StateLoader;
import org.amoseman.spaceengineershelper.state.State;
import org.amoseman.spaceengineershelper.resource.Cost;
import org.amoseman.spaceengineershelper.resource.Resource;

import java.util.List;

public class Model {
    private State state;

    public Model(String path) {
        state = StateLoader.load(path);
    }

    public AggregatedCost getAggregatedCost(Resource resource) {
        AggregatedCost aggregatedCost = new AggregatedCost(resource);
        getAggregatedCostHelper(1, resource, aggregatedCost);
        return aggregatedCost;
    }

    private void getAggregatedCostHelper(long multiplier, Resource resource, AggregatedCost aggregatedCost) {
        if (!state.hasCost(resource.getName())) {
            aggregatedCost.add(multiplier, resource);
            return;
        }
        long m = multiplier * resource.getAmount();
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
