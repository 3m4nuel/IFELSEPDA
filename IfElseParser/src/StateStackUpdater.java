/* Purpose: 
 * --------------------------------------------------
 * This is the interface used among its implementors
 * for updating a PDA for its state and stack.
 */

public interface StateStackUpdater
{
	public void update(Transition transitionInfo, Alphabet upcomingInput) throws StateStackUpdaterException;
}
