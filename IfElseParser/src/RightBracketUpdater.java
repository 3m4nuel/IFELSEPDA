/* Purpose: 
 * --------------------------------------------------
 * This class implements the behavior for a '}'
 * input in updating the state and transitioning
 * the state if applicable.
 */

import java.util.Stack;

public class RightBracketUpdater implements StateStackUpdater 
{
	@Override
	public void update(Transition transitionInfo, Alphabet upcomingInput) throws StateStackUpdaterException
	{
		if(transitionInfo.getStack().isEmpty())
			throw new StateStackUpdaterException("Stack is empty. It must at least contain z");
		
		/* Determine how to update state and stack based on current state. */
		switch(transitionInfo.getState())
		{
			case Q1:
				updateSequenceInfo(transitionInfo.getStack(), upcomingInput);
				break;
			default:
				throw new StateStackUpdaterException("Sequence is in an invalid state: " + transitionInfo);
		}
	}
	
	private void updateSequenceInfo(Stack<StackItem> stack, Alphabet upcomingInput) throws StateStackUpdaterException
	{
		/* Determine how to update stack based on current top of stack. */
		switch(stack.peek())
		{
			case RIGHTBRACKET:
				updateStackForRightBracket(stack, upcomingInput);
				break;
			default:
				throw new StateStackUpdaterException("Transition not defined for } input with current stack of " + stack.peek());
			
		}
	}
	
	private void updateStackForRightBracket(Stack<StackItem> stack, Alphabet upcomingInput) throws StateStackUpdaterException
	{
		switch(upcomingInput)
		{
			case IF:
				stack.pop();
				stack.push(StackItem.IFSTMT);
				break;
			case ELSE:
				stack.pop();
				stack.push(StackItem.ELSESTMT);
				break;
			default:
				stack.pop();
		}
	}
}
