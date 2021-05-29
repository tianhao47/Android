package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button one,two,three,four,five,six,seven,eight,nine,zero,reduce,add,mul,mod,div,point,del,ace,equal;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewAndListener();
    }

    private void initViewAndListener() {
        one = (Button)findViewById(R.id.one);
        one.setOnClickListener(this);

        two = (Button)findViewById(R.id.two);
        two.setOnClickListener(this);

        three = (Button)findViewById(R.id.three);
        three.setOnClickListener(this);

        four = (Button)findViewById(R.id.four);
        four.setOnClickListener(this);

        five = (Button)findViewById(R.id.five);
        five.setOnClickListener(this);

        six = (Button)findViewById(R.id.six);
        six.setOnClickListener(this);

        seven = (Button)findViewById(R.id.seven);
        seven.setOnClickListener(this);

        eight = (Button)findViewById(R.id.eight);
        eight.setOnClickListener(this);

        nine= (Button)findViewById(R.id.nine);
        nine.setOnClickListener(this);

        zero = (Button)findViewById(R.id.zero);
        zero.setOnClickListener(this);

        reduce = (Button)findViewById(R.id.reduce);
        reduce.setOnClickListener(this);

        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(this);

        mod = (Button)findViewById(R.id.mod);
        mod.setOnClickListener(this);

        div = (Button)findViewById(R.id.div);
        div.setOnClickListener(this);

        mul = (Button)findViewById(R.id.mul);
        mul.setOnClickListener(this);

        equal = (Button)findViewById(R.id.equal);
        equal.setOnClickListener(this);

        point = (Button)findViewById(R.id.point);
        point.setOnClickListener(this);

        del = (Button)findViewById(R.id.del);
        del.setOnClickListener(this);

        ace = (Button)findViewById(R.id.ace);
        ace.setOnClickListener(this);

        editText = findViewById(R.id.eidt);


    }
    private  boolean lastIsOperator;
    private  String lastOperators="";

    private  double firstNumber =0D;
    private  double secondNumber =0D;

    @Override
    public void onClick(View v) {
        String currentText=editText.getText().toString();
        String operatorNumber="";
        if(currentText.equals("0")){
            editText.setText("");
        }
        operatorNumber=editText.getText().toString();
        if(!lastOperators.equals(""))
        {
            int index=operatorNumber.lastIndexOf(lastOperators);
            operatorNumber=operatorNumber.substring(index+1);

        }

        switch (v.getId()){
            case R.id.zero:
                editText.setText(editText.getText()+"0");
                lastIsOperator=false;
                break;
            case R.id.one:
                editText.setText(editText.getText()+"1");
                lastIsOperator=false;
                break;
            case R.id.two:
                editText.setText(editText.getText()+"2");
                lastIsOperator=false;
                break;
            case R.id.three:
                editText.setText(editText.getText()+"3");
                lastIsOperator=false;
                break;
            case R.id.four:
                editText.setText(editText.getText()+"4");
                lastIsOperator=false;
                break;
            case R.id.five:
                editText.setText(editText.getText()+"5");
                lastIsOperator=false;
                break;
            case R.id.six:
                editText.setText(editText.getText()+"6");
                lastIsOperator=false;
                break;
            case R.id.seven:
                editText.setText(editText.getText()+"7");
                lastIsOperator=false;
                break;
            case R.id.eight:
                editText.setText(editText.getText()+"8");
                lastIsOperator=false;
                break;
            case R.id.nine:
                editText.setText(editText.getText()+"9");
                lastIsOperator=false;
                break;
            case R.id.point:
                editText.setText(editText.getText()+".");
                lastIsOperator=false;
                break;
            case R.id.ace:
                editText.setText("");
                lastIsOperator=false;
                firstNumber=0D;
                secondNumber=0D;
                lastOperators="=";
                break;
            case R.id.del:
                if(TextUtils.isEmpty(editText.getText())){
                    return;
                }
                lastIsOperator=false;

                editText.setText(currentText.substring(0,currentText.length()  -1).length()>0?currentText.substring(0,currentText.length()-1):"0");
                break;
            case R.id.div:
                if((TextUtils.isEmpty(editText.getText())) || lastIsOperator && !lastOperators.equals("=")){
                    return;
            }
                opratorCalc(operatorNumber,"÷");
                lastIsOperator=true;
                editText.setText(editText.getText()+"÷");
                lastOperators="÷";
                break;
            case R.id.mul:
                if((TextUtils.isEmpty(editText.getText())
                        || lastIsOperator)&&lastOperators.equals("=")){
                    return;}
                    opratorCalc(operatorNumber,"*");
                    lastIsOperator=true;
                    editText.setText(editText.getText()+"*");
                    lastOperators="*";
                    break;

            case R.id.reduce:
                if((TextUtils.isEmpty(editText.getText())
                        || lastIsOperator)&&lastOperators.equals("=")){
                    return;}
                    opratorCalc(operatorNumber,"-");
                    lastIsOperator=true;
                    editText.setText(editText.getText()+"-");
                    lastOperators="-";
                    break;

            case R.id.add:
                if((TextUtils.isEmpty(editText.getText())
                        || lastIsOperator)&&lastOperators.equals("=")){
                    return;}
                    opratorCalc(operatorNumber,"+");
                    lastIsOperator=true;
                    editText.setText(editText.getText()+"+");
                    lastOperators="+";
                    break;

            case R.id.equal:
                double result=0D;
                if(TextUtils.isEmpty(lastOperators)){
                    return;}

                opratorResult(operatorNumber);
                secondNumber=0D;
                lastOperators="=";
                lastIsOperator=true;
                editText.setText(editText.getText()+"\n="+String.valueOf(firstNumber));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

    }
    private  void operate(String operatorNumber)
    {
        if(secondNumber!=0D)
        {
            if(lastOperators.equals("÷"))
            {
                secondNumber=secondNumber/Double.parseDouble(operatorNumber);
                firstNumber=firstNumber/secondNumber;
            }
            else if(lastOperators.equals("*"))
            {
                secondNumber=secondNumber*Double.parseDouble(operatorNumber);
                firstNumber=firstNumber*secondNumber;
            }
            else if(lastOperators.equals("+"))
            {
                secondNumber=Double.parseDouble(operatorNumber);
                firstNumber=firstNumber+secondNumber;
            }
            else if(lastOperators.equals("-"))
            {
                secondNumber=Double.parseDouble(operatorNumber);
                firstNumber=firstNumber-secondNumber;
            }
        }
        else
            {
                if(lastOperators.equals("÷"))
                {
                    firstNumber = firstNumber / Double.parseDouble(operatorNumber);
                }
                else if(lastOperators.equals("*"))
                {
                    firstNumber = firstNumber * Double.parseDouble(operatorNumber);
                }
                else if(lastOperators.equals("+"))
                {
                    firstNumber = firstNumber + Double.parseDouble(operatorNumber);
                }
                else if(lastOperators.equals("-"))
                {
                    firstNumber= firstNumber - Double.parseDouble(operatorNumber);
                }

        }
    }
    public void opratorResult(String operatorNumber)
    {
        operate(operatorNumber);

    }

    public void  opratorCalc(String operatorNumber,String currentOprator)
    {
        if (TextUtils.isEmpty(lastOperators))
        {
            firstNumber=Double.parseDouble(operatorNumber);
            return;
        }
        if(lastOperators.equals(currentOprator))
        {
            if(lastOperators.equals("÷"))
            {
                firstNumber = firstNumber / Double.parseDouble(operatorNumber);
            }
            else if(lastOperators.equals("*"))
            {
                firstNumber = firstNumber * Double.parseDouble(operatorNumber);
            }
            else if(lastOperators.equals("+"))
            {
                firstNumber = firstNumber + Double.parseDouble(operatorNumber);
            }
            else if(lastOperators.equals("-"))
            {
                firstNumber = firstNumber - Double.parseDouble(operatorNumber);
            }

            return;
        }
        operate(operatorNumber);

    }
}