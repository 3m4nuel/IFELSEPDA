/* Purpose: 
 * --------------------------------------------------
 * This program parses a file that contains a combination
 * of if, else, {, }, symbols. It simulates a PDA that
 * determines whether the input sequence is valid for the
 * defined PDA and CFG.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class Program 
{	
	private static final String DEFAULT_FILE_PATH = "sequence.txt";
	
	public static void main(String[] args) throws Exception 
	{
		String fileToParsePath = DEFAULT_FILE_PATH;
		
		/* If an input file is provided as the first argument, it will be used as the file that will be parsed */
		if(args.length > 0) {
			fileToParsePath = args[0];
		}
		
		/* Access file if it exist */
		File fileToParse = new File(fileToParsePath);
		if(!fileToParse.exists()) {
			throw new FileNotFoundException("File " + fileToParsePath + " does not exist");
		}
		
		InputStream inputStream = null;
		Reader reader = null;
		
		try
		{	
			/* Initialize Transition object that handles the transitions among states and stack updates	*
			 * when an input is parsed from the sequence file.											    */
			Transition pushdownAutomata = new Transition();
			AlphabetExtractor sequenceAlphabetAnalyzer = new AlphabetExtractor();
			
			inputStream = new FileInputStream(fileToParse);
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));

			int input;
			
			/* Initialize PDA by transition into the start symbol */
			pushdownAutomata.addInput(Alphabet.EMPTY, null);
			
			/* Read file until end of file, reading through each email block */
			while((input = reader.read()) != -1)
			{
				/* Set input into PDA after a full alphabet is extracted during the file parsing */
				if(sequenceAlphabetAnalyzer.isFullAlphabetObtained((char) input))
					pushdownAutomata.addInput(sequenceAlphabetAnalyzer.getAlphabet(), getFutureAlphabet(reader));
			}
			
			/* Input empty string to clean up stack. Errors out if invalid transition is detected */
			while(pushdownAutomata.getState() != State.QF)
				pushdownAutomata.addInput(Alphabet.EMPTY, null);
			
			System.out.println("Correct sequence");
		}
		/* If a transition is not defined for a state and alphabet input combination an exception occurs *
		 * which implies that the sequence is not valid													 */
		catch(Exception e)
		{
			System.out.println("Sequence is syntactically incorrect");
		}
		/* Close I/O resources */
		finally
		{
			if(inputStream != null)
				inputStream.close();
			
			if(reader != null)
				reader.close();
		}
	}
	
	/* The future input alphabet is obtained below so that a transition can be defined for the case when *
	 * an input for a specific state can update the stack in different ways								 */
	public static Alphabet getFutureAlphabet(Reader reader) throws Exception
	{
		reader.mark(Alphabet.MAX_LENGTH);
		
		AlphabetExtractor futureAlphabetExtractor = new AlphabetExtractor();
		int input;
		int readBytes = 0;
		Alphabet alphabet = Alphabet.EMPTY;
		
		/* Read file until end of file, reading through each email block */
		while(((input = reader.read()) != -1) && readBytes < Alphabet.MAX_LENGTH)
		{
			if(futureAlphabetExtractor.isFullAlphabetObtained((char) input))
			{
				alphabet = futureAlphabetExtractor.getAlphabet();
				break;
			}
			readBytes++;
		}

		reader.reset();
		return alphabet;
	}
}
