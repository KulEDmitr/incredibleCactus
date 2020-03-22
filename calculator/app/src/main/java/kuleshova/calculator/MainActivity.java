package kuleshova.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import expression.parser.ExpressionParser;


public class MainActivity extends AppCompatActivity {

    private static String EXPR = MainActivity.class.getName() + ".expr";
    private static String PREV = MainActivity.class.getName() + ".prev";
    private static String RET = MainActivity.class.getName() + ".ret";
    private static String RES = MainActivity.class.getName() + ".res";
    private static String DEL = MainActivity.class.getName() + ".del";

    private String expression = "", previous = "", format = "#0.0000000";
    private TextView display;
    private Button result, returner, deleter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        display.setMovementMethod(new ScrollingMovementMethod());

        Button space = findViewById(R.id.space);
        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression += " ";
                fixedChanges(returner.isEnabled(), result.isEnabled(), true);
            }
        });

        Button clearer = findViewById(R.id.clear);
        clearer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous = expression;
                expression = "";
                fixedChanges(true, false, false);
            }
        });

        deleter = findViewById(R.id.delete);
        deleter.setEnabled(false);
        deleter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression.substring(0, expression.length() - 1);
                fixedChanges(false, !expression.isEmpty(), !expression.isEmpty());
            }
        });

        result = findViewById(R.id.result);
        result.setEnabled(false);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous = expression;
                ExpressionParser parser = new ExpressionParser();
                String sol;
                Boolean isError = true;
                try {
                    sol = new DecimalFormat(format).format(parser.parse(expression).evaluate());
                    int i = sol.length() - 1;
                    while (sol.charAt(i) != '.') {
                        if (sol.charAt(i) == '0') {
                            --i;
                        } else break;
                    }
                    if (sol.charAt(i) != '.') ++i;
                    sol = sol.substring(0, i);
                } catch (Exception e) {
                    isError = false;
                    sol = e.toString();
                    sol = sol.substring(sol.indexOf(':') + 2, sol.length());
                }
                expression = sol;
                fixedChanges(true, false, isError);
            }
        });

        returner = findViewById(R.id.returner);
        returner.setEnabled(false);
        returner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = previous;
                fixedChanges(false, true, true);
            }
        });

        if (savedInstanceState != null) {
            expression = savedInstanceState.getString(EXPR);
            previous = savedInstanceState.getString(PREV);
            returner.setEnabled(savedInstanceState.getBoolean(RET));
            result.setEnabled(savedInstanceState.getBoolean(RES));
            deleter.setEnabled(savedInstanceState.getBoolean(DEL));
            fixedChanges(returner.isEnabled(), result.isEnabled(), deleter.isEnabled());
        }
    }

    public void addSymbol(View view) {
        Button tmp = (Button) view;
        fixedChanges(tmp.getText().toString());
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(PREV, previous);
        savedInstanceState.putString(EXPR, expression);
        savedInstanceState.putBoolean(RES, result.isEnabled());
        savedInstanceState.putBoolean(RET, returner.isEnabled());
        savedInstanceState.putBoolean(DEL, deleter.isEnabled());
        super.onSaveInstanceState(savedInstanceState);
    }

    private void fixedChanges(String add) {
        expression += add;
        fixedChanges(returner.isEnabled(), true, true);
    }

    private void fixedChanges(Boolean ret, Boolean res, Boolean del) {
        returner.setEnabled(ret);
        result.setEnabled(res);
        deleter.setEnabled(del);
        display.setText(expression);
    }
}
