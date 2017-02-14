// Allen Boynton

// JAV1 - 1702

// MainActivity.java

package edu.fullsail.aboynton.boyntonallen_ce01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static edu.fullsail.aboynton.boyntonallen_ce01.R.string.e;

public class MainActivity extends AppCompatActivity {

    // Reports/Prints to the console
    private static final String TAG = "MyActivity";

    // TextView used to display the output
    private TextView textView;

    // Using arrays to loop through the buttons in numbers and operators
    private int[] numberButtons;
    private int[] operatorButtons;

    // Integers used to make the calculations
    private int number1 = 0, number2 = 0, equation = -1;

    // Variable determines which operator key is tapped and forwards to the function
    private String operator;

    // A check if the last key entered is a number
    private boolean lastNumber;

    // A check to make sure there are no errors
    private boolean isEmpty;

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
            public void onClick(View sender) {

                if (equation != -1 && !isEmpty) {
                    textView.setText("");
//                    textView.setText(button.getText().toString());
                    isEmpty = true;
                } else if (!isEmpty) {
                    textView.setText("");
//                    textView.append(button.getText());
//                    number1 = Integer.parseInt(button.getText().toString());
                    isEmpty = true;
                }
                Button button = (Button) sender;
                textView.append(button.getText());
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
            public void onClick(View sender) {
                // Check that an operator can only be entered if a number is present & no errors
                if (lastNumber) {
                    Button button = (Button) sender;
                    if (button.getText().toString().equals("+")) {
                        equation = 0;
                    } else if (button.getText().toString().equals("-")) {
                        equation = 1;
                    } else if (button.getText().toString().equals("*")) {
                        equation = 2;
                    } else if (button.getText().toString().equals("/")) {
                        equation = 3;
                    }

                    //get value of number in display and set to firstNum
                    number1 = Integer.parseInt(textView.getText().toString());
//                    number1 = Integer.parseInt(button.getText().toString());

                    //add operand to function
                    operator = button.getText().toString();
                    textView.append(button.getText());
                    isEmpty = false;
//                    textView.append(button.getText());
//                    lastNumber = false;
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
            public void onClick(View sender) {
                String screen = textView.getText().toString();

                if(!isValidEquation(sender)){
                    toastMessage(sender);
                    return;
                }

                //get the second num
                number2 = Integer.parseInt(textView.getText().toString());

                double result = 0d;

                // Iterate through to correct function
                switch (equation) {
                    case 0:
                        result = number1 + number2;
                        break;
                    case 1:
                        result = number1 - number2;
                        break;
                    case 2:
                        result = number1 * number2;
                        break;
                    case 3:
                        if (number2 == 0) {
                            textView.setText(e);
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
        });
        // Clear button
        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                // Need to reset the setting from each equation
                lastNumber = false;
                isEmpty = false;
            }
        });
    }

    public boolean isValidEquation(View sender) {
        String currDisp = textView.getText().toString();
        return !currDisp.equals("") && !operator.equals("") && !currDisp.contains(" ");
    }

    //displayed if an operator or equal sign is used out of order
    public void toastMessage(View v) {
        Toast warning = Toast.makeText(getApplicationContext(), R.string.numInOrderToast, Toast.LENGTH_SHORT);
        warning.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, -250);
        warning.show();
    }
}
