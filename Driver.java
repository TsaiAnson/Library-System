import java.io.IOException;
import java.text.ParseException;

/**
 * Driver class for the Library System.
 * @author Yuan-Cheng Tsai
 */

public class Driver 
{
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException
	{
		Library l1 = new Library();
		l1.Start();
	}
}