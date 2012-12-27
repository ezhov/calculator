package com.ezhov.calculator;

import java.util.LinkedList;
import java.util.ListIterator;


public class CalcModel {
	
	private static class OperationHelper {

		private enum Operation {
			kOperationMult,
			kOperationDiv,
			kOperationAdd,
			kOperationSub, 
			kOperationUnknown
		};
		
		private enum OperationPriority {
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

		public static Long processOperation(
				CalcModel.OperationHelper.Operation op,
				Long lValue, Long rValue) {
			long result = rValue;
			switch (op) {
			case kOperationMult:
				result = Long.valueOf(rValue.longValue()*lValue.longValue());
				break;
				
			case kOperationDiv:
				result = Long.valueOf(rValue.longValue()/lValue.longValue());
				break;

			case kOperationAdd:
				result = Long.valueOf(rValue.longValue()+lValue.longValue());
				break;

			case kOperationSub:
				result = Long.valueOf(rValue.longValue()-lValue.longValue());
				break;


			default:
				break;
			}
		return result;
		}

	}

	LinkedList <Long> values;
	LinkedList <OperationHelper.Operation> operations;
	
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
	public Long calculate ()
	{
		/*
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
		*/
		
		ListIterator<Long> valueIterator = values.listIterator();
		ListIterator<OperationHelper.Operation> operationIterator= operations.listIterator();

		while (operationIterator.hasNext())
		{
			Long lValue = valueIterator.next();
			Long rValue = valueIterator.next();
			OperationHelper.Operation op = operationIterator.next();
			if (OperationHelper.priorityOfOperation(op) == OperationHelper.OperationPriority.kOperationPriority1)
			{
				
				lValue = OperationHelper.processOperation(op,lValue,rValue);
				//		lValue.longValue() * rValue.longValue();
				valueIterator.set(lValue);

				valueIterator.previous();
				valueIterator.previous();
				valueIterator.remove();

				operationIterator.remove();
			}
			else
				valueIterator.previous();
		}

		valueIterator = values.listIterator();
		operationIterator= operations.listIterator();

		while (operationIterator.hasNext())
		{
			Long lValue = valueIterator.next();
			Long rValue = valueIterator.next();
			OperationHelper.Operation op = operationIterator.next();
			if (OperationHelper.priorityOfOperation(op) == OperationHelper.OperationPriority.kOperationPriority2)
			{
				
				lValue = OperationHelper.processOperation(op,lValue,rValue);
				//		lValue.longValue() * rValue.longValue();
				valueIterator.set(lValue);

				valueIterator.previous();
				valueIterator.previous();
				valueIterator.remove();

				operationIterator.remove();
			}
			else
				valueIterator.previous();
		}

		
		
		assert(values.size() == 1);
		assert(operations.size() == 0);
		
		return values.get(0);
		
	}


	public void reset() {
		// TODO Auto-generated method stub
		operations.clear();
		values.clear();
	}
	
}
