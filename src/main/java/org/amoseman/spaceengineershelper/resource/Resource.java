package org.amoseman.spaceengineershelper.resource;

public class Resource {
    private final String name;
    private final long amount;

    public Resource(String name, long amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s: %d", name, amount);
    }
}
