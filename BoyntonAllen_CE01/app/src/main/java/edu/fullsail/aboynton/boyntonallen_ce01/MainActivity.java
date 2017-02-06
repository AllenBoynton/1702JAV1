// Allen Boynton

// JAV1 - 1702

// MainActivity.java

package edu.fullsail.aboynton.boyntonallen_ce01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TextView used to display the output
    private TextView textView;

    // Using arrays to loop through the buttons in numbers and operators
    private int[] numberButtons;
    private int[] operatorButtons;

    // Integers used to make the calculations
    private int number1 = 0, number2 = 0;

    // Variable determines which operator key is tapped and forwards to the function
    private String operator;

    // A check if the last key entered is a number
    private boolean lastNumber;

    // A check to make sure there are no errors
    private boolean hasError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Display where users entry is and entire expression remains
        this.textView = (TextView) findViewById(R.id.textView);

        // IDs of all the number buttons
        numberButtons = new int[]{R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        // IDs of all the operator buttons
        operatorButtons = new int[]{R.id.button_division, R.id.button_multiplication,
                R.id.button_subtraction, R.id.button_addition, R.id.button_equals, R.id.button_clear
        };

        // Sets the OnClickListener for number buttons
        setNumberOnClickListener();

        // Sets the OnClickListener to operator buttons
        setOperatorOnClickListener();
    }

    // Sets the number buttons to OnClickListeners
    private void setNumberOnClickListener() {
        // Create a common OnClickListener
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                if (hasError) {
                    textView.setText(button.getText().toString());
                    hasError = false;
                } else {
                    textView.append(button.getText());
                    number1 = Integer.parseInt(button.getText().toString());
                    System.out.println(number1);
                }
                lastNumber = true;
            }
        };
        // Assign the listener to the array of number buttons
        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    // Sets the operators OnClickListener
    private void setOperatorOnClickListener() {
        // Create an OnClickListener for operators
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check that an operator can only be entered if a number is present & no errors
                if (lastNumber) {
                    Button button = (Button) view;
                    textView.append(button.getText());
                    lastNumber = false;

                    // Defines what the operators are
                    switch (view.getId()) {
                        case R.id.button_addition:
                            operator = "+";
                            break;
                        case R.id.button_subtraction:
                            operator = "-";
                            break;
                        case R.id.button_multiplication:
                            operator = "*";
                            break;
                        case R.id.button_division:
                            operator = "/";
                            break;
                        default:
                            break;
                    }
                    System.out.println(operator);
                }
            }
        };
        // Assign the listener to all the operator buttons
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }

        // Equals button
        findViewById(R.id.button_equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String screen = textView.getText().toString();
                if (lastNumber) {
                    try {
                        number2 = Integer.parseInt(screen);
                        System.out.println(number2);
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                    }

                    double result = 0d;

                    switch (operator) {
                        case "+":
                            result = number1 + number2;
                            break;
                        case "-":
                            result = number1 - number2;
                            break;
                        case "*":
                            result = number1 * number2;
                            break;
                        case "/":
                            if (number2 == 0) {
                                textView.setText(R.string.e);
                            } else {
                                result = number1 / number2;
                            }
                            break;
                        default:
                            textView.setText(R.string.error);
                            break;
                    }
                    textView.setText(screen + "=" + result);
                }
            }
        });

        // Clear button
        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                // Need to reset the setting from each equation
                lastNumber = false;
                hasError = false;
            }
        });
    }
}

    /*Performs the operation of specific operator to
    get result and display it using switch statement*/
//    private float doMath() {
//        String screen = textView.getText().toString();
//        if (lastNumber && !hasError) {
//            try {
//                number2 = Integer.parseInt(screen);
//                System.out.println(number2);
//            }
//            catch (NumberFormatException ex) {
//                ex.printStackTrace();
//            }
//            float result = 0;
//
//            switch (operator) {
//                case "+":
//                    return number1 + number2;
////                    break;
//                case "-":
//                    return number1 - number2;
////                    break;
//                case "*":
//                    return number1 * number2;
////                    break;
//                case "/":
//                    if (number2 == 0) {
//                        textView.setText(e);
//                    } else {
//                        return number1 / number2;
//                    }
////                    break;
//                default:
//                    textView.setText(R.string.error);
//
//                    break;
//            }
//            textView.setText(screen + "=" + result);
//            System.out.println(operator);
//        }
//        return -1;
//    }
//}
