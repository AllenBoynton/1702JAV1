// Allen Boynton

// JAV1 - 1610

// BoyntonAllen_CE01

package com.fullsail.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
    OnClickListener listener;
    String operator;
    String value1;

    /* renamed from: com.fullsail.android.calculator.MainActivity.1 */
    class C00001 implements OnClickListener {
        C00001() {
        }

        public void onClick(View v) {
            TextView tv = (TextView) MainActivity.this.findViewById(C0001R.id.display);
            switch (v.getId()) {
                case C0001R.id.button7 /*2131034113*/:
                    MainActivity.this.enterNumber(7);
                case C0001R.id.button8 /*2131034114*/:
                    MainActivity.this.enterNumber(8);
                case C0001R.id.button9 /*2131034115*/:
                    MainActivity.this.enterNumber(9);
                case C0001R.id.button_divide /*2131034116*/:
                    MainActivity.this.enterOperator("/");
                case C0001R.id.button4 /*2131034117*/:
                    MainActivity.this.enterNumber(4);
                case C0001R.id.button5 /*2131034118*/:
                    MainActivity.this.enterNumber(5);
                case C0001R.id.button6 /*2131034119*/:
                    MainActivity.this.enterNumber(6);
                case C0001R.id.button_multiply /*2131034120*/:
                    MainActivity.this.enterOperator("*");
                case C0001R.id.button1 /*2131034121*/:
                    MainActivity.this.enterNumber(1);
                case C0001R.id.button2 /*2131034122*/:
                    MainActivity.this.enterNumber(2);
                case C0001R.id.button3 /*2131034123*/:
                    MainActivity.this.enterNumber(3);
                case C0001R.id.button_minus /*2131034124*/:
                    MainActivity.this.enterOperator("-");
                case C0001R.id.button_clear /*2131034125*/:
                    MainActivity.this.clear();
                case C0001R.id.button0 /*2131034126*/:
                    MainActivity.this.enterNumber(0);
                case C0001R.id.button_equals /*2131034127*/:
                    MainActivity.this.doMath(tv.getText().toString());
                case C0001R.id.button_plus /*2131034128*/:
                    MainActivity.this.enterOperator("+");
                default:
            }
        }
    }

    public MainActivity() {
        this.listener = new C00001();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0001R.layout.activity_main);
        findViewById(C0001R.id.button0).setOnClickListener(this.listener);
        findViewById(C0001R.id.button1).setOnClickListener(this.listener);
        findViewById(C0001R.id.button2).setOnClickListener(this.listener);
        findViewById(C0001R.id.button3).setOnClickListener(this.listener);
        findViewById(C0001R.id.button4).setOnClickListener(this.listener);
        findViewById(C0001R.id.button5).setOnClickListener(this.listener);
        findViewById(C0001R.id.button6).setOnClickListener(this.listener);
        findViewById(C0001R.id.button7).setOnClickListener(this.listener);
        findViewById(C0001R.id.button8).setOnClickListener(this.listener);
        findViewById(C0001R.id.button9).setOnClickListener(this.listener);
        findViewById(C0001R.id.button_clear).setOnClickListener(this.listener);
        findViewById(C0001R.id.button_plus).setOnClickListener(this.listener);
        findViewById(C0001R.id.button_minus).setOnClickListener(this.listener);
        findViewById(C0001R.id.button_multiply).setOnClickListener(this.listener);
        findViewById(C0001R.id.button_divide).setOnClickListener(this.listener);
        findViewById(C0001R.id.button_equals).setOnClickListener(this.listener);
        this.operator = null;
        this.value1 = null;
    }

    private void enterOperator(String op) {
        TextView tv = (TextView) findViewById(C0001R.id.display);
        String text = tv.getText().toString();
        if (text.length() != 0 || this.value1 != null) {
            if (text.length() != 0 || this.value1 == null) {
                if (this.value1 == null) {
                    this.value1 = text;
                    tv.setText(BuildConfig.VERSION_NAME);
                } else {
                    doMath(text);
                    this.value1 = tv.getText().toString();
                    tv.setText(BuildConfig.VERSION_NAME);
                }
                this.operator = op;
                return;
            }
            this.operator = op;
        }
    }

    private void doMath(String value2) {
        if (this.value1 != null && this.operator != null) {
            int val1 = Integer.parseInt(this.value1);
            int val2 = Integer.parseInt(value2);
            int total = 0;
            if (this.operator.equals("+")) {
                total = val1 + val2;
            } else if (this.operator.equals("-")) {
                total = val1 - val2;
            } else if (this.operator.equals("*")) {
                total = val1 * val2;
            } else if (this.operator.equals("/")) {
                total = val1 / val2;
            }
            TextView tv = (TextView) findViewById(C0001R.id.display);
            clear();
            tv.setText(BuildConfig.VERSION_NAME + total);
            this.operator = "=";
        }
    }

    private void enterNumber(int num) {
        TextView tv = (TextView) findViewById(C0001R.id.display);
        if (this.operator == null || !this.operator.equals("=")) {
            tv.setText(tv.getText() + BuildConfig.VERSION_NAME + num);
            return;
        }
        this.value1 = tv.getText().toString();
        this.operator = null;
        tv.setText(BuildConfig.VERSION_NAME + num);
    }

    private void clear() {
        this.value1 = null;
        this.operator = null;
        ((TextView) findViewById(C0001R.id.display)).setText(BuildConfig.VERSION_NAME);
    }
}
