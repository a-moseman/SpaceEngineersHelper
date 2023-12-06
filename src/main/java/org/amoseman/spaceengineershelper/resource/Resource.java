package org.amoseman.spaceengineershelper.resource;

public class Resource {
    private final String name;
    private final double amount;

    public Resource(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s: %f", name, amount);
    }
}
