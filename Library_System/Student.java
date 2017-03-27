
/**
 * The STUDENT object that represents a student. Holds the name
 * of the student as well as it's grade level.
 * @author Yuan-Cheng Tsai
 */

public class Student 
{
	/** String NAME that holds the name. */
	private String name;

	/** Integer GRADE that holds the grade level. */
	private int grade;

	/** Constructor for STUDENT. */
	public Student(String nm, int gr)
	{
		name = nm;
		grade = gr;
	}

	/** Returns the string NAME. */
	public String returnN()
	{
		return name;
	}

	/** Returns the integer GRADE. */
	public int returnG()
	{
		return grade;
	}
}