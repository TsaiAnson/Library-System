
public class Student 
{
	private String name;
	private int grade;
	public Student(String nm, int gr)
	{
		name = nm;
		grade = gr;
	}
	
	public String returnN()
	{
		return name;
	}
	
	public int returnG()
	{
		return grade;
	}
}