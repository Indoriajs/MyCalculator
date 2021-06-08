package com.example.mycalculator.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * CalcStack - Custom stack created specifically for <em>MyCalculator</em>.
 * The stack uses {@link List} instead of the standard {@code Array[]} to perform
 * the basic operations of stack.
 *<p></p>
 * CalcStack provides basic stack operations like {@link #push(String) push(String)}, {@link #pop() pop()}, {@link #peek() peek()}.
 * Additionally, the user can also check whether the stack is {@code Empty} by using the
 * {@link #isEmpty() isEmpty()} method.
 *<p></p>
 * A constructor with no arguments is called to initialize the {@link List}
 * and {@code int} that acts as a placeholder for the stack's <b>TOP</b>.
 *<p></p>
 * @author Harsh Indoria
 * @see java.util.Stack
 */
public class CalcStack {
    private final List<String> main_stack;
    private int top;

    /**
     * Constructor for the Stack.
     * Initializes an {@link ArrayList, ArrayList<E>} and {@code int} top.
     *
     *
     */
    public CalcStack(){
        main_stack = new ArrayList<>();
        top = -1;
    }
    /**
    *   Returns {@code True} if the stack is empty.
     *
     * @return boolean
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * Pushes {@code String} into the stack by adding it to the list.
     * @param string Standard string. In the case of this application, a string of the expression is expected but not necessary for this stack to work.
     */
    public void push(String string){
        top++;
        main_stack.add(string);
    }

    /**
     * Pops out the {@code String} stored at the {@link #top TOP} of the stack.
     * Deletes the {@code String} from the {@link #top TOP}.
     *
     * @return string {@code String} at the top of the Stack.
     */
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

    /**
     * Returns the {@code String} at the {@link #top TOP} without deleting it.
     *
     * @return string {@code String} stored at the {@link #top TOP}.
     */
    public String peek(){
        int size = main_stack.size();
        if(size == 0) return null;
        if(top == 0) return "0";
        return main_stack.get(top-1);
    }

    /**
     * Clears the entire Stack.
     * The {@link ArrayList ArrayList<>} is emptied using the {@link ArrayList#clear() clear()} method
     */
    public void clearStack(){
        main_stack.clear();
    }
}
