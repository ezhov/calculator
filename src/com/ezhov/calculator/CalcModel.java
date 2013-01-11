package com.ezhov.calculator;

import java.util.LinkedList;
import java.util.ListIterator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


public class CalcModel implements Parcelable{
	
	private static final String CALC_MODEL_TAG = "CalcModel";

	private static class OperationHelper {

		enum Operation {
			kOperationMult,
			kOperationDiv,
			kOperationAdd,
			kOperationSub, 
			kOperationUnknown
		};
		
		enum OperationPriority {
			kOperationPriority1,
			kOperationPriority2,
			kOperationPriority3, 
			kOperationPriorityUnknown
		}
		
		static OperationPriority priorityOfOperation (Operation op)
		{
			switch (op) {
			case kOperationMult:
			case kOperationDiv:
				return OperationPriority.kOperationPriority1;
			case kOperationSub:
			case kOperationAdd:
				return OperationPriority.kOperationPriority2;
			}
			return OperationPriority.kOperationPriorityUnknown;
		}
		
		static OperationHelper.Operation parseOperation (Character ch)
		{
			switch (ch) {
			case '+':
				return Operation.kOperationAdd;
			case '-':
				return Operation.kOperationSub;
			case '*':
				return Operation.kOperationMult;
			case '/':
				return Operation.kOperationDiv;
			}
			return Operation.kOperationUnknown;
		}

		static Long processOperation(
				CalcModel.OperationHelper.Operation op,
				Long lValue, Long rValue) {
			long result = rValue;
			switch (op) {
			case kOperationMult:
				result = Long.valueOf(lValue.longValue()*rValue.longValue());
				break;
				
			case kOperationDiv:
				result = Long.valueOf(lValue.longValue()/rValue.longValue());
				break;

			case kOperationAdd:
				result = Long.valueOf(lValue.longValue()+rValue.longValue());
				break;

			case kOperationSub:
				result = Long.valueOf(lValue.longValue()-rValue.longValue());
				break;


			default:
				break;
			}
		return result;
		}
		
	}

	private LinkedList <Long> values;
	private LinkedList <OperationHelper.Operation> operations;
	
	CalcModel()
	{
		values = new LinkedList<Long>();
		operations = new LinkedList<OperationHelper.Operation>();
	}
	
	
	public void pushValue (Long value)
	{
		values.add(value);
	}
	public void pushOperation (Character op)
	{
		operations.add(OperationHelper.parseOperation(op));
	}
	
	private void doMath (OperationHelper.OperationPriority priority)
	{
		ListIterator<Long> valueIterator = values.listIterator();
		ListIterator<OperationHelper.Operation> operationIterator= operations.listIterator();

		
		while (operationIterator.hasNext())
		{
			Long lValue = valueIterator.next();

			OperationHelper.Operation op = operationIterator.next();
			if (OperationHelper.priorityOfOperation(op) == priority)
			{
				valueIterator.remove();
				Long rValue = valueIterator.next();
				lValue = OperationHelper.processOperation(op,lValue,rValue);
				valueIterator.set(lValue);
				valueIterator.previous();

				operationIterator.remove();
			}
		}
	}
	
	public Long calculate ()
	{
		doMath(OperationHelper.OperationPriority.kOperationPriority1);
		doMath(OperationHelper.OperationPriority.kOperationPriority2);
		
		assert(values.size() == 1);
		assert(operations.size() == 0);
		
		return values.get(0);
		
	}


	public void reset() {
		operations.clear();
		values.clear();
	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		Log.v(CALC_MODEL_TAG, "describeContents");
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.v(CALC_MODEL_TAG, "writeToParcel");
	}
	
	public static final Parcelable.Creator<CalcModel> CREATOR = new Parcelable.Creator<CalcModel>() {

		@Override
		public CalcModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			Log.v(CALC_MODEL_TAG, "createFromParcel");
			
			CalcModel result = new CalcModel();
			
			return result;
		}

		@Override
		public CalcModel[] newArray(int size) {
			// TODO Auto-generated method stub
			Log.v(CALC_MODEL_TAG, "newArray");
			return null;
		}
	};


}
