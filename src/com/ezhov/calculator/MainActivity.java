package com.ezhov.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	protected boolean newValue;
	
	protected EditText editText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        final CalcModel calcModel = new CalcModel();
        newValue = false;
        final View.OnClickListener listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Button button = (Button)v;
				long val = 0;
				if (!newValue)
					val = getValue();
				val = val*10+Long.parseLong(button.getText().toString());
				setValue(val);
				newValue = false;
			}
		};
		findViewById(R.id.button1).setOnClickListener(listener);
		findViewById(R.id.button2).setOnClickListener(listener);
		findViewById(R.id.button3).setOnClickListener(listener);
		findViewById(R.id.button4).setOnClickListener(listener);
		findViewById(R.id.button5).setOnClickListener(listener);
		findViewById(R.id.button6).setOnClickListener(listener);
		findViewById(R.id.button7).setOnClickListener(listener);
		findViewById(R.id.button8).setOnClickListener(listener);
		findViewById(R.id.button9).setOnClickListener(listener);
		findViewById(R.id.button0).setOnClickListener(listener);
		
		findViewById(R.id.buttonMult).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newValue = true;
				calcModel.pushValue(Long.valueOf(getValue()));
				calcModel.pushOperation(Operation.kOperationMult);
			}
		});
		
		findViewById(R.id.buttonDiv).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newValue = true;
				calcModel.pushValue(Long.valueOf(getValue()));
				calcModel.pushOperation(Operation.kOperationDiv);
			}
		});
		
		findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newValue = true;
				calcModel.pushValue(Long.valueOf(getValue()));
				calcModel.pushOperation(Operation.kOperationAdd);
			}
		});
		
		findViewById(R.id.buttonSub).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newValue = true;
				calcModel.pushValue(Long.valueOf(getValue()));
				calcModel.pushOperation(Operation.kOperationSub);
			}
		});
		
		
		
		findViewById(R.id.buttonCalc).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newValue = true;
				calcModel.pushValue(getValue());
				setValue(calcModel.calculate());
			}
		});
		
		findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    protected long getValue()
    {
    	return Long.parseLong(editText.getText().toString());
    }
    
    protected void setValue(long value)
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
