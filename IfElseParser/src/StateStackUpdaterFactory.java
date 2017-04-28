/* Purpose: 
 * --------------------------------------------------
 * This determines which StateStackUpdater implementor
 * is used based on the input alphabet.
 */


public class StateStackUpdaterFactory 
{
	public static StateStackUpdater getStackUpdater(Alphabet alphabet) throws StateStackUpdaterException
	{
		switch(alphabet)
		{
			case EMPTY:
				return new EmptyUpdater();
			case IF:
				return new IfUpdater();
			case ELSE:
				return new ElseUpdater();
			case LEFTBRACKET:
				return new LeftBracketUpdater();
			case RIGHTBRACKET:
				return new RightBracketUpdater();
			default:
				throw new StateStackUpdaterException("Invalid input alphabet: " + alphabet);
		}
	}
}
