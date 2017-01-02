import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item
{
	private String name, person, type, barcode, ckOut;
	private int fee, lfee;
	private DateFormat dateform = new SimpleDateFormat("MM-dd-yyyy");
	private Date ddate;
	public Item(String nm, String p, String tp, String bc, int f, int lf, String ck, String dd) throws ParseException
	{
		name = nm;
		person = p;
		type = tp;
		barcode = bc;
		fee = f;
		lfee = lf;
		ckOut = ck;
		if (!dd.equals("null"))
			ddate = dateform.parse(dd);
	}
	
	public String returnN()
	{
		return name;
	}
	
	public String returnT()
	{
		return type;
	}
	
	public void setP(String p)
	{
		person = p;
	}
	
	public String returnP()
	{
		return person;
	}
	
	public String returnBC()
	{
		return barcode;
	}
	
	public int returnF()
	{
		return fee;
	}
	
	public int returnLF()
	{
		return lfee;
	}
	
	public void toggleCK()
	{
		if (ckOut.equals("true"))
			ckOut = "false";
		else
			ckOut = "true";
	}
	
	public String returnCK()
	{
		return ckOut;
	}
	
	public void setDD(int year, int month, int day) throws ParseException
	{
		ddate = dateform.parse(""+month+"-"+day+"-"+year+"");
	}
	
	public String returnDD()
	{
		String ddateS = "null";
		if (ddate != null)
			ddateS = dateform.format(ddate);
		return ddateS;
	}
}