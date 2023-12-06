package org.amoseman.spaceengineershelper.ui;

import org.amoseman.spaceengineershelper.AggregatedCost;
import org.amoseman.spaceengineershelper.Model;
import org.amoseman.spaceengineershelper.io.Reader;
import org.amoseman.spaceengineershelper.resource.Resource;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Model model;
    private TextField nameField;
    private TextField amountField;
    private TextArea output;
    private JButton button;

    public View(Model model) {
        this.model = model;
        setSize(640, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        nameField = new TextField();
        add(nameField);
        amountField = new TextField();
        add(amountField);
        button = new JButton("Calculate");
        button.addActionListener(e -> {
            String name = nameField.getText();
            int amount = Integer.parseInt(amountField.getText());
            Resource resource = new Resource(name, amount);
            AggregatedCost aggregatedCost = model.getAggregatedCost(resource);
            output.insert(aggregatedCost.toString(), 0);
            nameField.setText("");
            amountField.setText("");
        });
        add(button);
        output = new TextArea();
        output.setEditable(false);
        output.setColumns(128);
        output.setRows(32);
        add(output);
        setVisible(true);
    }
}
