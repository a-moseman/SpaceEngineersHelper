package org.amoseman.spaceengineershelper.ui;

import org.amoseman.spaceengineershelper.model.AggregatedCost;
import org.amoseman.spaceengineershelper.model.Model;
import org.amoseman.spaceengineershelper.resource.Resource;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private final Model model;
    private final TextField nameField;
    private final TextField amountField;
    private final TextArea output;
    private int lastOutputLength = 0;

    public View(Model model) {
        this.model = model;
        setSize(640, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        nameField = new TextField();
        amountField = new TextField();
        JButton button = new JButton("Calculate");
        button.addActionListener(e -> calculate());
        output = new TextArea();
        output.setEditable(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 128;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addComponent(new JLabel("Resource"), constraints, 0, 0);
        addComponent(new JLabel("Amount"), constraints, 1, 0);
        addComponent(nameField, constraints, 0, 1);
        addComponent(amountField, constraints, 1, 1);
        addComponent(button, constraints, 2, 1);
        addComponent(output, constraints, 0, 2, 3);

        setVisible(true);
    }

    private void addComponent(Component component, GridBagConstraints constraints, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }

    private void addComponent(Component component, GridBagConstraints constraints, int x, int y, int gridwidth) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = gridwidth;
        add(component, constraints);
    }

    private void calculate() {
        String name = nameField.getText();
        if (!model.isResource(name)) {
            error("invalid resource name");
            return;
        }
        long amount;
        try {
            amount = Long.parseLong(amountField.getText());
        }
        catch (NumberFormatException e) {
            error("amount is not a number");
            return;
        }
        if (amount < 1) {
            error("amount must be greater than 0");
            return;
        }
        Resource resource = new Resource(nameField.getText(), amount);
        AggregatedCost aggregatedCost = model.getAggregatedCost(resource);
        write(aggregatedCost.toString());
    }

    private void write(String text) {
        output.replaceRange("", 0, lastOutputLength);
        output.insert(text, 0);
        lastOutputLength = text.length();
        nameField.setText("");
        amountField.setText("");
    }

    private void error(String text) {
        write(String.format("ERROR: %s", text));
    }
}
