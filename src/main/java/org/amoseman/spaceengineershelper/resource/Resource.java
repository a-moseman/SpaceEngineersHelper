package org.amoseman.spaceengineershelper.resource;

public class Resource {
    private final String name;
    private final int amount;

    public Resource(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s: %d", name, amount);
    }
}
