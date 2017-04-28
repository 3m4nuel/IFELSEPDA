/* Purpose: 
 * --------------------------------------------------
 * This class implements the behavior for an if input 
 * in updating the state and transitioning
 * the state if applicable.
 */

import java.util.Stack;

public class IfUpdater implements StateStackUpdater 
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
				throw new StateStackUpdaterException("Sequence is in an invalid state: " + transitionInfo.getState());
		}
	}
	
	private void updateSequenceInfo(Stack<StackItem> stack, Alphabet upcomingInput) throws StateStackUpdaterException
	{
		/* Determine how to update stack based on current top of stack. */
		switch(stack.peek())
		{
			case IFSTMT:
				updateStackForIfStmt(stack, upcomingInput);
				break;
			case ELSESTMT:
				stack.pop();
				stack.push(StackItem.ELSESTMT);
				break;
			default:
				throw new StateStackUpdaterException("Transition not defined for if input with current stack of " + stack.peek());
			
		}
	}
	
	private void updateStackForIfStmt(Stack<StackItem> stack, Alphabet upcomingInput) throws StateStackUpdaterException
	{
		switch(upcomingInput)
		{
			case ELSE:
				stack.pop();
				stack.push(StackItem.ELSESTMT);
				break;
			case LEFTBRACKET:
				stack.pop();
				stack.push(StackItem.BRACKET);
				break;
			case IF:
				stack.pop();
				stack.push(StackItem.IFSTMT);
				break;
			default:
				stack.pop();
				break;
		}
	}
}
