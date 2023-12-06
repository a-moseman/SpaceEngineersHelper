package org.amoseman.spaceengineershelper.ui;

import org.amoseman.spaceengineershelper.AggregatedCost;
import org.amoseman.spaceengineershelper.Model;
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

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.ipadx = 128;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel("Resource"), constraints);

        constraints.gridx = 1;
        add(new JLabel("Amount"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        nameField = new TextField();
        add(nameField, constraints);

        constraints.gridx = 1;
        amountField = new TextField();
        add(amountField, constraints);

        constraints.gridx = 2;
        JButton button = new JButton("Calculate");
        button.addActionListener(e -> calculate());
        add(button, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        output = new TextArea();
        output.setEditable(false);
        add(output, constraints);

        setVisible(true);
    }

    private void calculate() {
        String name = nameField.getText();
        if (!model.isResource(name)) {
            error("invalid resource name");
            return;
        }
        int amount;
        try {
            amount = Integer.parseInt(amountField.getText());
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
