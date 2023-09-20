package edu.trinity;

import java.util.Stack;
import java.util.EmptyStackException;

public class RPNCalculator {

    public static double evaluate(String expr) {
        String[] intStr = expr.split(" ");
        Stack<Double> numStack = new Stack<>();
        double num1 = 0;
        double num2 = 0;
        double finVal = 0;

        for(String member:intStr){
            if(checkOperator(member) == false){
                numStack.push(Double.parseDouble(member));
            }
            else{
                if(numStack.size()< 2){
                    throw new EmptyStackException();
                }
                num2 = numStack.pop();
                num1 = numStack.pop();
                finVal = analyzeOperation(member, num1, num2);
                numStack.push(finVal);
            }
        }

        if(numStack.size() != 1){
            numStack.pop();
        }

        return numStack.pop();

    }


    public static boolean checkOperator(String operat){
        switch(operat){
            case "+": case "-": case "*": case "/":
                return true;
            default:
                return false;
        }

    }


    public static double analyzeOperation(String operation, double num1, double num2){
        switch(operation){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if(num2 == 0){
                    throw new IllegalArgumentException();
                }
                return num1/num2;
            default:
                throw new IllegalArgumentException();
    }

        }
        
}
