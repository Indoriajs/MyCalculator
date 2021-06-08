package com.example.mycalculator.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to convert and calculate expressions using stack.<p>
 * Uses the {@link CalcStack custom stack} class to perform operations for <i>MyCalculator</i>.
 *
 * @author Harsh Indoria
 *
 * @see java.util.Stack
 */
public class StackEval {
    private final CalcStack stack;
    /**
     * A {@link List} is used to store the Infix expression.
     * Using a list allows us to store numbers that have more than 1 digit and allow for larger
     * calculations.
     */
    private final List<String> INFIX_EXPR;

    /**
     * Constructor that takes no arguments.<p>
     * Initializes the {@link CalcStack stack} and {@link List INFIX_EXPR} variable. <p>
     *
     */
    public StackEval(){
        stack = new CalcStack();
        INFIX_EXPR = new ArrayList<>();
    }

    /**
     * Private method that checks for the precedence of the operators.
     * Takes in a {@code String} that checks for the precedence of the operators in accordance with standard
     * mathematics.<p>
     *
     * @param c String that ideally contains operands or operators.
     * @return The value of precedence. Higher the value, the more priority the operator is given.
     */
    private int Precedence(String c){
        if(c.equals("+") || c.equals("-")) return 1;
        else if (c.equals("/") || c.equals("*")) return 2;
        else return -1;
    }

    /**
     * Converts the standard {@code String} to {@link List list} styled expression.<p>
     * @param str Ideally contains the expression that has to be converted
     */
    public void setINFIX_EXPR(String str){
        INFIX_EXPR.clear();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++){
           if(str.charAt(i) != '+' && str.charAt(i) != '-' && str.charAt(i) !='*' && str.charAt(i) !='/'){
               stringBuilder.append(str.charAt(i));
               if(i == str.length() -1){
                   INFIX_EXPR.add(stringBuilder.toString());
               }
           }else {
               INFIX_EXPR.add(stringBuilder.toString());
               stringBuilder.delete(0,stringBuilder.length());
               INFIX_EXPR.add(String.valueOf(str.charAt(i)));
           }
        }
    }

    /**
     * Converts the stored Infix expression to Postfix expression.<p>
     * Uses the class variable {@link #INFIX_EXPR INFIX_EXPR} and converts it to Postfix.
     * <p>
     * @return {@code List} that contains the Postfix expression
     */
    public List<String> infixToPostfix(){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < INFIX_EXPR.size(); i++){
            String s = INFIX_EXPR.get(i);

            if(!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")){
                result.add(s);
            }
            else {
                while (!stack.isEmpty() && Precedence(s) < Precedence(stack.peek())){
                    result.add(stack.pop());
                }
                stack.push(s);
            }
        }
        while (!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }

    /**
     * Evaluates the passed Postfix expression and returns it as a float.
     * @param result The postfix expression that has to be calculated.
     * @return evaluated postfix expression as {@code float}
     */
    public float evalPostfix(List<String> result){
        stack.clearStack();
        for(int i = 0; i < result.size(); i++){
            String s = String.valueOf(result.get(i));

            if(!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")){
                stack.push(s);
            }
            else{
                float val1 = Float.parseFloat(stack.pop());
                float val2 = Float.parseFloat(stack.pop());
                float temp;
                switch (s){
                    case "+":
                        temp = val1 + val2;
                        stack.push(Float.toString(temp));
                        break;
                    case "-":
                        temp = val2 - val1;
                        stack.push(Float.toString(temp));
                        break;
                    case "/":
                        temp = val2/val1;
                        stack.push(Float.toString(temp));
                        break;
                    case "*":
                        temp = val1 * val2;
                        stack.push(Float.toString(temp));
                        break;
                }
            }
        }
        return Float.parseFloat(stack.pop());
    }
}
