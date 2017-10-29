package com.example.chimaeraqm.commcalculator;

import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView _screen;
    private String predis = "";
    private String display = "";
    private String currentOperator = "";
    RadioGroup radioGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        _screen = (TextView) findViewById(R.id.textView);
        _screen.setText(display);

        Button zero = (Button) findViewById(R.id.button0);
        zero.setOnClickListener(this);
        Button one = (Button) findViewById(R.id.button1);
        one.setOnClickListener(this);
        Button two = (Button) findViewById(R.id.button2);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.button3);
        three.setOnClickListener(this);
        Button four = (Button) findViewById(R.id.button4);
        four.setOnClickListener(this);
        Button five = (Button) findViewById(R.id.button5);
        five.setOnClickListener(this);
        Button six = (Button) findViewById(R.id.button6);
        six.setOnClickListener(this);
        Button seven = (Button) findViewById(R.id.button7);
        seven.setOnClickListener(this);
        Button eight = (Button) findViewById(R.id.button8);
        eight.setOnClickListener(this);
        Button nine = (Button) findViewById(R.id.button9);
        nine.setOnClickListener(this);
        Button btna = (Button) findViewById(R.id.buttonA);
        btna.setOnClickListener(this);
        Button btnb = (Button) findViewById(R.id.buttonB);
        btnb.setOnClickListener(this);
        Button btnc = (Button) findViewById(R.id.buttonC);
        btnc.setOnClickListener(this);
        Button btnd = (Button) findViewById(R.id.buttonD);
        btnd.setOnClickListener(this);
        Button btne = (Button) findViewById(R.id.buttonE);
        btne.setOnClickListener(this);
        Button btnf = (Button) findViewById(R.id.buttonF);
        btnf.setOnClickListener(this);
        Button btnadd = (Button) findViewById(R.id.buttonadd);
        btnadd.setOnClickListener(this);
        Button btnminus = (Button) findViewById(R.id.buttonminus);
        btnminus.setOnClickListener(this);
        Button btnmulti = (Button) findViewById(R.id.buttonmulti);
        btnmulti.setOnClickListener(this);
        Button btndiv = (Button) findViewById(R.id.buttondivide);
        btndiv.setOnClickListener(this);
        Button btnequal = (Button) findViewById(R.id.buttonequal);
        btnequal.setOnClickListener(this);
        Button btnclear = (Button) findViewById(R.id.buttonclean);
        btnclear.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.hexspinner);
        String[] mItems = getResources().getStringArray(R.array.commtypes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mItems);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] commtypes = getResources().getStringArray(R.array.commtypes);
                Toast.makeText(MainActivity.this, "你点击的是:"+commtypes[position], 2000).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.dechexRadioG);

    }


    public void onrbClick(View v){
        int radiobuttonid = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(radiobuttonid);
        Toast.makeText(getBaseContext(),radioButton.getText(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button0:
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
                onClickButton(v);
                break;
            case R.id.buttonadd:
            case R.id.buttonminus:
            case R.id.buttonmulti:
            case R.id.buttondivide:
                onClickOperator(v);
                break;
            case R.id.buttonequal:
                onClickEqual(v);
                break;
            case R.id.buttonclean:
                onClickClear(v);
                break;
            default:
                break;
        }
    }

    private void updateScreen(){
        _screen.setText(display);
    }

    private void onClickButton(View v){
        Button b = (Button) v;
        display += b.getText();
        updateScreen();
    }

    private void onClickOperator(View v){
        Button b = (Button) v;
        display += b.getText();
        currentOperator = b.getText().toString();
        updateScreen();
    }

    private double operateArithmetic(String a, String b, String op){
        double rtn = -1;
        switch (op) {
            case "+":
                rtn = Double.valueOf(a)+Double.valueOf(b);
                break;
            case "-":
                rtn = Double.valueOf(a)-Double.valueOf(b);
                break;
            case "*":
                rtn = Double.valueOf(a)*Double.valueOf(b);
                break;
            case "/":
                try {
                    rtn = Double.valueOf(a)/Double.valueOf(b);
                }
                catch (Exception e){
                    Log.d("Calc",e.getMessage());
                }
                break;
            default:
                rtn = -1;
                break;
        }
        return rtn;
    }

    private void onClickEqual(View v){
        String[] operation = display.split(Pattern.quote(currentOperator));
        Double res;
        if(operation.length < 2){
            return;
        }
        else{
            res = operateArithmetic(operation[0],operation[1],currentOperator);
            _screen.setText(display+"\n"+String.valueOf(res));
        }
    }

    private void clear(){
        display = "";
        currentOperator = "";
    }

    private void onClickClear(View v){
        clear();
        updateScreen();
    }
}
