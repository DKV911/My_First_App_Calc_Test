package demo.calc.calc_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    EditText calculatorDisplay;
    static StringBuilder lineOperation = new StringBuilder();

    Button zero;
    Button nine;
    Button eight;
    Button seven;
    Button six;
    Button five;
    Button four;
    Button three;
    Button two;
    Button one;
    Button add;
    Button dot;
    Button equals;
    Button subtract;
    Button divide;
    Button correction;
    Button multiply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorDisplay = findViewById(R.id.editText);

        zero = findViewById(R.id.number0);
        nine = findViewById(R.id.number9);
        eight = findViewById(R.id.number8);
        seven = findViewById(R.id.number7);
        six = findViewById(R.id.number6);
        five = findViewById(R.id.number5);
        four = findViewById(R.id.number4);
        three = findViewById(R.id.number3);
        two = findViewById(R.id.number2);
        one = findViewById(R.id.number1);
        add = findViewById(R.id.add);
        dot = findViewById(R.id.dot);
        equals = findViewById(R.id.equals);
        subtract = findViewById(R.id.subtract);
        divide = findViewById(R.id.divide);
        correction = findViewById(R.id.correction);
        multiply = findViewById(R.id.multiply);

        zero.setOnClickListener(this);
        nine.setOnClickListener(this);
        eight.setOnClickListener(this);
        seven.setOnClickListener(this);
        six.setOnClickListener(this);
        five.setOnClickListener(this);
        four.setOnClickListener(this);
        three.setOnClickListener(this);
        two.setOnClickListener(this);
        one.setOnClickListener(this);
        add.setOnClickListener(this);
        dot.setOnClickListener(this);
        equals.setOnClickListener(this);
        subtract.setOnClickListener(this);
        divide.setOnClickListener(this);
        correction.setOnClickListener(this);
        multiply.setOnClickListener(this);
        correction.setOnLongClickListener(this);
    }

    public void clickOnCorrection() {
        if (!lineOperation.toString().isEmpty()) {
            lineOperation.delete(lineOperation.length() - 1, lineOperation.length());
            String completedString = lineOperation.toString();
            calculatorDisplay.setText(completedString);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.number0:
                if (!lineOperation.toString().isEmpty()) {
                    if (lineOperation.toString().substring(lineOperation.length() - 1, lineOperation.length()).equals("/")) {
                        setText("0.");
                        break;
                    } else
                        setText("0");
                    break;
                } else if (lineOperation.toString().isEmpty()) {
                    setText("0.");
                    break;
                }

            case R.id.number1:
                setText("1");
                break;
            case R.id.number2:
                setText("2");
                break;
            case R.id.number3:
                setText("3");
                break;
            case R.id.number4:
                setText("4");
                break;
            case R.id.number5:
                setText("5");
                break;
            case R.id.number6:
                setText("6");
                break;
            case R.id.number7:
                setText("7");
                break;
            case R.id.number8:
                setText("8");
                break;
            case R.id.number9:
                setText("9");
                break;
            case R.id.add:
                setText("+");
                break;
            case R.id.subtract:
                setText("-");
                break;
            case R.id.divide:
                setText("/");
                break;
            case R.id.multiply:
                setText("*");
                break;
            case R.id.correction:
                setText("<-");
                break;
            case R.id.equals:
                if(lineOperation.toString().isEmpty()){
                    break;
                }
                else if (lineOperation.substring(lineOperation.toString().length() - 1, lineOperation.toString().length()).equals(".") |
                        lineOperation.substring(lineOperation.toString().length() - 1, lineOperation.toString().length()).equals("/") |
                        lineOperation.substring(lineOperation.toString().length() - 1, lineOperation.toString().length()).equals("*") |
                        lineOperation.substring(lineOperation.toString().length() - 1, lineOperation.toString().length()).equals("-") |
                        lineOperation.substring(lineOperation.toString().length() - 1, lineOperation.toString().length()).equals("+")){
                    break;
                } else setText("=");
                break;
            case R.id.dot:
                if (lineOperation.toString().isEmpty()) {
                    setText("0.");
                    break;
                } else if (lineOperation.toString().length() == 1) {
                    setText(".");
                    break;
                } else if (lineOperation.toString().length() == 2) {
                    if (!lineOperation.toString().substring(0, 1).equals("0")) {
                        setText(".");
                        break;
                    }
                } else if (lineOperation.toString().length() > 2) {
                    for (int i = lineOperation.toString().length(); i > 1; i--) {
                        if (lineOperation.toString().substring(i - 1, i).equals(".")) {
                            break;
                        } else if (lineOperation.toString().substring(i - 1, i).equals("/") |
                                lineOperation.toString().substring(i - 1, i).equals("*") |
                                lineOperation.toString().substring(i - 1, i).equals("-") |
                                lineOperation.toString().substring(i - 1, i).equals("+")) {
                            setText(".");
                            break;
                        } else if (i == 2) {
                            setText(".");
                            break;
                        }
                    }
                }
        }
    }

    public void setText(String s) {
        if (s.equals("=")) {
            if(lineOperation.substring(lineOperation.toString().length()-1,lineOperation.toString().length()).equals("0")){
                if(!lineOperation.substring(lineOperation.toString().length()-2,lineOperation.toString().length()).equals("/0")){}
                Toast.makeText(getApplicationContext(),"НА 0 ДЕЛИТЬ НЕЛЬЗЯ",
                        Toast.LENGTH_LONG).show();
            }
            else if (!lineOperation.toString().isEmpty()) {
                String r = lineOperation.toString();
                Expression ex = new ExpressionBuilder(r).build();
                BigDecimal d = BigDecimal.valueOf(ex.evaluate());
                lineOperation.delete(0, lineOperation.toString().length());
                String completedString = d.toString();
                lineOperation.append(completedString);
                if (lineOperation.substring(lineOperation.toString().length() - 1, lineOperation.toString().length()).equals(".")) {
                    lineOperation.delete(lineOperation.toString().length() - 1, lineOperation.toString().length());
                    completedString = lineOperation.toString();
                } else if (lineOperation.substring(lineOperation.toString().length() - 1, lineOperation.toString().length()).equals("0") &
                        lineOperation.substring(lineOperation.toString().length() - 2, lineOperation.toString().length() - 1).equals(".")) {
                    lineOperation.delete(lineOperation.toString().length() - 2, lineOperation.toString().length());
                    completedString = lineOperation.toString();
                }
                calculatorDisplay.setText(completedString);
            }else {}
        } else if (s.equals("<-")) {
            clickOnCorrection();
        } else if (s.equals("*") | s.equals("/") | s.equals("-") | s.equals("+") | s.equals(".")) {
            if (!lineOperation.toString().isEmpty()) {
                if (lineOperation.toString().substring(lineOperation.toString().length() - 1).equals(s)) {
                } else if (lineOperation.toString().substring(lineOperation.toString().length() - 1).equals("/") |
                        lineOperation.toString().substring(lineOperation.toString().length() - 1).equals("*") |
                        lineOperation.toString().substring(lineOperation.toString().length() - 1).equals("-") |
                        lineOperation.toString().substring(lineOperation.toString().length() - 1).equals("+") |
                        lineOperation.toString().substring(lineOperation.toString().length() - 1).equals(".")) {
                    lineOperation.delete(lineOperation.toString().length() - 1, lineOperation.toString().length());
                    if (lineOperation.toString().isEmpty()) {
                        String completedString = lineOperation.toString();
                        calculatorDisplay.setText(completedString);
                    } else {
                        lineOperation.append(s);
                        String completedString = lineOperation.toString();
                        calculatorDisplay.setText(completedString);
                    }
                } else {
                    lineOperation.append(s);
                    String completedString = lineOperation.toString();
                    calculatorDisplay.setText(completedString);
                }
            } else if (lineOperation.toString().isEmpty() & s.equals("-")) {
                lineOperation.append(s);
                String completedString = lineOperation.toString();
                calculatorDisplay.setText(completedString);
            } else {
            }
        } else {
            lineOperation.append(s);
            String completedString = lineOperation.toString();
            calculatorDisplay.setText(completedString);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if(view.getId()==R.id.correction){
            lineOperation.delete(0, lineOperation.toString().length());
            calculatorDisplay.setText(lineOperation.toString());
            return true;}
        return false;
    }
}