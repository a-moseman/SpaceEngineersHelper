package org.amoseman.spaceengineershelper.resource;

import java.util.List;

public class Cost {
    private final Resource out;
    private final List<Resource> in;

    public Cost(Resource out, List<Resource> in) {
        this.out = out;
        this.in = in;
    }

    public Resource getOut() {
        return out;
    }

    public List<Resource> getIn() {
        return in;
    }
}
