package apa.ap_simplecalculator;

public class Calculator {
    private double firstOperand;
    private double secondOperand;
    private char operator;

    public Calculator() {
        this.firstOperand = 0;
        this.secondOperand = 0;
        this.operator = ' ';
    }

    public void setFirstOperand(double operand) {
        this.firstOperand = operand;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public void setSecondOperand(double operand) {
        this.secondOperand = operand;
    }

    public double calculate() {
        double result = 0;
        switch (operator) {
            case '+':
                result = firstOperand + secondOperand;
                break;
            case '-':
                result = firstOperand - secondOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '/':
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    result = Double.NaN;
                }
                break;
            default:
                System.out.println("Invalid operator!");
        }
        return result;
    }
}
