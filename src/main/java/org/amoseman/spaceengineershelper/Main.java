package org.amoseman.spaceengineershelper;

import org.amoseman.spaceengineershelper.ui.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Model(args[0]);
        View view = new View(model);
    }
}