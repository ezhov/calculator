package com.ezhov.calculator;

import java.util.LinkedList;

enum Operation {
	kOperationMult,
	kOperationDiv,
	kOperationAdd,
	kOperationSub
};

public class CalcModel {
	
	LinkedList <Long> values;
	LinkedList <Operation> operations;
	
	CalcModel()
	{
		values = new LinkedList<Long>();
		operations = new LinkedList<Operation>();
	}
	
	
	public void pushValue (Long value)
	{
		values.add(value);
	}
	public void pushOperation (Operation op)
	{
		operations.add(op);
	}
	public Long calculate ()
	{
		Long rValue = values.removeFirst();
		while (!operations.isEmpty())
		{
			Operation op = operations.removeFirst();
			Long lValue = values.removeFirst();
			switch (op) {
			case kOperationMult:
				rValue = Long.valueOf(rValue.longValue()*lValue.longValue());
				break;
				
			case kOperationDiv:
				rValue = Long.valueOf(rValue.longValue()/lValue.longValue());
				break;

			case kOperationAdd:
				rValue = Long.valueOf(rValue.longValue()+lValue.longValue());
				break;

			case kOperationSub:
				rValue = Long.valueOf(rValue.longValue()-lValue.longValue());
				break;


			default:
				break;
			}
		}
		return rValue;
	}


	public void reset() {
		// TODO Auto-generated method stub
		operations.clear();
		values.clear();
	}
	
}
