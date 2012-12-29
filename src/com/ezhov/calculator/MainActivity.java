package com.ezhov.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private boolean newValue;
	
	private EditText editText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        final CalcModel calcModel = new CalcModel();
        newValue = false;
        final View.OnClickListener numberListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Button button = (Button)v;
				long val = 0;
				if (!newValue)
					val = getValue();
				val = val*10+Long.parseLong(button.getText().toString());
				setValue(val);
				newValue = false;
			}
		};
		findViewById(R.id.button1).setOnClickListener(numberListener);
		findViewById(R.id.button2).setOnClickListener(numberListener);
		findViewById(R.id.button3).setOnClickListener(numberListener);
		findViewById(R.id.button4).setOnClickListener(numberListener);
		findViewById(R.id.button5).setOnClickListener(numberListener);
		findViewById(R.id.button6).setOnClickListener(numberListener);
		findViewById(R.id.button7).setOnClickListener(numberListener);
		findViewById(R.id.button8).setOnClickListener(numberListener);
		findViewById(R.id.button9).setOnClickListener(numberListener);
		findViewById(R.id.button0).setOnClickListener(numberListener);
		
		final View.OnClickListener operationListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				newValue = true;
				Button b = (Button)v;
				calcModel.pushValue(Long.valueOf(getValue()));
				calcModel.pushOperation(b.getText().toString().charAt(0));
			}
		};
		
		findViewById(R.id.buttonMult).setOnClickListener(operationListener);
		findViewById(R.id.buttonDiv).setOnClickListener(operationListener);
		findViewById(R.id.buttonAdd).setOnClickListener(operationListener);
		findViewById(R.id.buttonSub).setOnClickListener(operationListener);
		
		
		findViewById(R.id.buttonCalc).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				newValue = true;
				calcModel.pushValue(getValue());
				setValue(calcModel.calculate());
			}
		});
		
		findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				long val = getValue();
				val = val / 10;
				setValue(val);
			}
		});
		
		findViewById(R.id.buttonC).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setValue(0);
				calcModel.reset();
			}
		});
    }
    
    private long getValue()
    {
    	return Long.parseLong(editText.getText().toString());
    }
    
    private void setValue(long value)
    {
    	editText.setText(Long.toString(value));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
