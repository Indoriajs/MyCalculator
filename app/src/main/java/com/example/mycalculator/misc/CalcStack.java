package com.example.mycalculator.misc;

import java.util.ArrayList;
import java.util.List;

public class CalcStack {
    private List<String> main_stack;
    private int top;

    public CalcStack(){
        main_stack = new ArrayList<>();
        top = -1;
    }
    public boolean isEmpty(){
        return top == -1;
    }
    public void push(String string){
        top++;
        main_stack.add(string);
    }
    public String pop(){
        if(!isEmpty()){
            String temp = main_stack.get(top);
            main_stack.remove(top);
            top--;
            return temp;
        }else{
            System.err.println("Error! Stack is empty");
            return null;
        }
    }
    public String peek(){
        int size = main_stack.size();
        if(size == 0) return null;
        if(top == 0) return "0";
        return main_stack.get(top-1);
    }
    public void clearStack(){
        main_stack.clear();
    }
}
