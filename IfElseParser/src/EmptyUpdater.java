/* Purpose: 
 * --------------------------------------------------
 * This class implements the behavior for an empty
 * string input in updating the state and transitioning
 * the state if applicable.
 */

public class EmptyUpdater implements StateStackUpdater 
{
	@Override
	public void update(Transition transitionInfo, Alphabet upcomingInput) throws StateStackUpdaterException
	{
		if(transitionInfo.getStack().isEmpty())
			throw new StateStackUpdaterException("Stack is empty. It must at least contain z");	
		
		/* Determine how to update state and stack based on current state. */
		switch(transitionInfo.getState())
		{
			case QO:
				transitionInfo.setState(State.Q1);
				transitionInfo.getStack().push(StackItem.IFSTMT);
				break;
			case Q1:
				updateSequenceInfo(transitionInfo);
				break;
			default:
				throw new StateStackUpdaterException("Sequence is in an invalid state: " + transitionInfo.getState());
		}
	}
	
	private void updateSequenceInfo(Transition sequence) throws StateStackUpdaterException
	{
		/* Determine how to update stack based on current top of stack. */
		switch(sequence.getStack().peek())
		{
			case Z:
				sequence.setState(State.QF);
				break;
			case IFSTMT:
				sequence.getStack().pop();
				break;
			default:
				throw new StateStackUpdaterException("Transition not defined for empty input with current stack of " + sequence.getStack().peek());
			
		}
	}
}
