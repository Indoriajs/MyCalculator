package com.example.mycalculator.activities;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.mycalculator.R;
import com.example.mycalculator.misc.StackEval;

import java.util.List;

public class ScreenActivity extends Activity {
    Button[] Buttons;
    TextView calc_text;
    StackEval stackEval;
    List<String> postFixExpr;
    Float result;
    private final String[] NUM_TO_FUNC = {"0","1","2","3","4","5","6","7","8","9",".","+","-","*","/","=","CLR"};



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_screen);
        Buttons = new Button[17];
        Buttons[0] = findViewById(R.id.num_0);
        Buttons[1] = findViewById(R.id.num_1);
        Buttons[2] = findViewById(R.id.num_2);
        Buttons[3] = findViewById(R.id.num_3);
        Buttons[4] = findViewById(R.id.num_4);
        Buttons[5] = findViewById(R.id.num_5);
        Buttons[6] = findViewById(R.id.num_6);
        Buttons[7] = findViewById(R.id.num_7);
        Buttons[8] = findViewById(R.id.num_8);
        Buttons[9] = findViewById(R.id.num_9);
        Buttons[10] = findViewById(R.id.decimal);
        Buttons[11] = findViewById(R.id.plus);
        Buttons[12] = findViewById(R.id.minus);
        Buttons[13] = findViewById(R.id.multiply);
        Buttons[14] = findViewById(R.id.divide);
        Buttons[15] = findViewById(R.id.equal);
        Buttons[16] = findViewById(R.id.clear);

        ToggleButton toggleButton = findViewById(R.id.theme_mode);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton.isChecked()){
                    findViewById(R.id.background).setBackgroundResource(R.drawable.calc_bg_night_);
                }else{
                    findViewById(R.id.background).setBackgroundResource(R.drawable.calc_bg);
                }
            }
        });
        calc_text = findViewById(R.id.textView);

        stackEval = new StackEval();

        setListeners();
    }

    private void setListeners(){
        for(int i = 0; i<NUM_TO_FUNC.length; i++){
            int finalI = i;
            Buttons[finalI].setOnClickListener(v -> func(NUM_TO_FUNC[finalI]));
        }
    }
    private void func(String string){
        if(string.equals("=")){
            textFunc();
        }else if(string.equals("CLR")){
            calc_text.setText("");
            result = 0.0f;
        }else {
            String temp = calc_text.getText().toString() + string;
            calc_text.setText(temp);
        }
    }
    private void textFunc(){
        stackEval.setINFIX_EXPR(calc_text.getText().toString());
        postFixExpr = stackEval.infixToPostfix();
        result = stackEval.evalPostfix(postFixExpr);
        calc_text.setText(" ");
        calc_text.setText(String.valueOf(result));
    }

}
