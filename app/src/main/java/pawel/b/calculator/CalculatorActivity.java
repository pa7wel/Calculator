package pawel.b.calculator;

import android.graphics.Path;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    // zmienna przechowująca stan wyświatlacza
    private String display = "0";
    // zmienna przechowująca pierszą wprowadzona liczbe
    private double accumulator = 0.0;
    // ty enum przechowujący oktualnie wykonywana operację
    private Operation currentOperation = Operation.NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void onClicked(View view){

        Button button = (Button) view;
        String key = button.getText().toString();
        TextView displayTextView = (TextView)findViewById(R.id.displayTextView);

        switch (key){
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (display.equals("0")){
                    display = "";
                }
                display += key;
                break;
            case ".":
                if(!display.contains(".")) {
                    display += ".";
                }
                break;
            case "+":
            case "-":
                calculateOperation(key);
                break;
            case "=":
                calculateResult();
                break;
            case "CE":
                clearOne();
                break;
            case "C":
                clearAll();
                break;
        }
        displayTextView.setText(display);
    }

    private void clearAll() {
        display = "0";
        accumulator = 0.0;
        currentOperation = Operation.NONE;
    }

    private void clearOne() {
        if(display.length() > 1){
            display = display.substring(0, display.length() - 1);
        }else{
            display = "0";
        }
    }

    private void calculateResult() {

        double displayValues = Double.parseDouble(display);
        switch (currentOperation) {
            case ADD:
                displayResult(accumulator + displayValues);
                break;
            case SUB:
                displayResult(accumulator - displayValues);
                break;
        }
            currentOperation = Operation.NONE;
            accumulator = 0.0;

    }

    public void displayResult(double result) {
        if(result == (long)result){
            display = String.format("%d", (long)result);
        }else{
            display = String.format("%s", result);
        }
    }

    private void calculateOperation(String key) {

        accumulator = Double.parseDouble(display);
        currentOperation = Operation.operationFromKey(key);
        display = "0";
    }
}
