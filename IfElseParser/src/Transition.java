/* Purpose: 
 * --------------------------------------------------
 * This class defines the state and stack while a 
 * sequence is being parsed and is updated every time
 * an alphabet input is added.
 */


import java.util.Stack;

public class Transition
{
	private State state;
	private Stack<StackItem> stack;
	private StringBuilder input;
	
	public Transition()
	{
		state = State.QO;
		input = new StringBuilder("");
		stack = new Stack<StackItem>();
		stack.push(StackItem.Z);
	}
	
	public Stack<StackItem> getStack()
	{
		return this.stack;
	}
	
	public State getState()
	{
		return this.state;
	}
	
	public void setState(State state)
	{
		this.state = state;
	}
	
	public void addInput(Alphabet inputAlphabet, Alphabet upcomingInput) throws StateStackUpdaterException
	{
		this.input.append(inputAlphabet.getAlphabet());
		StateStackUpdater stackUpdater = StateStackUpdaterFactory.getStackUpdater(inputAlphabet);
		stackUpdater.update(this, upcomingInput);
	}
}
