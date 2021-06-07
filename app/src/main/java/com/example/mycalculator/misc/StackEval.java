package com.example.mycalculator.misc;

import java.util.ArrayList;
import java.util.List;

public class StackEval {
    private final CalcStack stack;
    /**
     *
     */
    private final List<String> INFIX_EXPR;

    public StackEval(){
        stack = new CalcStack();
        INFIX_EXPR = new ArrayList<>();
    }

    private int Prec(String c){
        if(c.equals("+") || c.equals("-")) return 1;
        else if (c.equals("/") || c.equals("*")) return 2;
        else return -1;
    }
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

            /*int j = i;
            StringBuilder stringBuilder = new StringBuilder();
            while(str.charAt(j) != '+' && str.charAt(j) != '-' && str.charAt(j) !='*' && str.charAt(j) !='/'){
                stringBuilder.append(str.charAt(j));
                j++;
            }
            if(stringBuilder.equals(""))
            INFIX_EXPR.add(String.valueOf(str.charAt(j)));
            else
                INFIX_EXPR.add(stringBuilder.toString()); */
        }
    }
    public List<String> infixToPostfix(){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < INFIX_EXPR.size(); i++){
            String s = INFIX_EXPR.get(i);

            if(!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")){
                result.add(s);
            }
            else {
                while (!stack.isEmpty() && Prec(s) < Prec(stack.peek())){
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
