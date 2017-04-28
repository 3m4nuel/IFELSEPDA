/* Purpose: 
 * --------------------------------------------------
 * This is a specific exception class that is used
 * when an exception occurs in any of the
 * implementors of the StateStackUpdator interface.
 */

public class StateStackUpdaterException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public StateStackUpdaterException(String msg)
	{
		super(msg);
	}
}
