package apa.ap_simplecalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField displayField;
    private Calculator calculator;

    public CalculatorGUI() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 192, 203));

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setBackground(new Color(211, 121, 168));
        displayField.setForeground(Color.WHITE);
        displayField.setFont(new Font("Arial", Font.BOLD, 30));
        displayField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {
                "C", "", "", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "x", "="
        };

        for (String label : buttonLabels) {
            JButton button = createButton(label);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);

        calculator = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("=")) {
            String expression = displayField.getText();
            String[] parts = expression.split("(?<=[-+*/])|(?=[-+*/])");
            if (parts.length == 3) {
                double firstOperand = Double.parseDouble(parts[0]);
                char operator = parts[1].charAt(0);
                double secondOperand = Double.parseDouble(parts[2]);

                calculator.setFirstOperand(firstOperand);
                calculator.setOperator(operator);
                calculator.setSecondOperand(secondOperand);

                double result = calculator.calculate();
                DecimalFormat df = new DecimalFormat("#.##"); // Format to two decimal places
                displayField.setText(df.format(result));
            } else {
                displayField.setText("Syntax Error");
            }
        } else if (command.equals("C")) {
            displayField.setText("");
        } else {
            displayField.setText(displayField.getText() + command);
        }
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        button.setBackground(new Color(211, 121, 168));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBorder(BorderFactory.createLineBorder(new Color(84, 10, 43)));
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
