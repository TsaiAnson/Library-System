
/**
 * The Faculty object that will represent faculty of a school.
 * @author Yuan-Cheng Tsai
 */

public class Faculty 
{
    /** String NAME and CLASSES that hold the FACULTY's name and classes. */
	private String name, classes;

    /** Constructor for FACULTY object. */
	public Faculty(String nm, String cl)
	{
		name = nm;
		classes = cl;
	}

    /** Returns the string NAME. */
	public String returnN()
	{
		return name;
	}

    /** Returns the string CLASSES. */
	public String returnC()
	{
		return classes;
	}
}