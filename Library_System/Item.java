import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The ITEM object that represents each item in the library. Holds information pertaining to
 * it's checkout.
 *@author Yuan-Cheng Tsai
 */

public class Item
{
    /** Strings NAME, PERSON, TYPE,  BARCODE, CKOUT that holds the name of the item,
     * name of the person checking out the book (if any), type of item, the barcode,
     * and the checkout status. */
	private String name, person, type, barcode, ckOut;

    /** Integers FEE and LFEE that represent the fee and late fee of checking out
     * the item. */
	private int fee, lfee;

    /** DateFormat DATEFORM which holds the format MM-dd-yyyy for the checkout/in dates. */
	private DateFormat dateform = new SimpleDateFormat("MM-dd-yyyy");

    /** Date DDATE holding the due date. */
	private Date ddate;

    /** Constructor for ITEM object. */
	public Item(String nm, String p, String tp, String bc, int f, int lf, String ck, String dd)
            throws ParseException
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

    /** Returns the string NAME. */
	public String returnN()
	{
		return name;
	}

    /** Returns the string TYPE. */
	public String returnT()
	{
		return type;
	}


    /** Sets the string PERSON to the string P. */
	public void setP(String p)
	{
		person = p;
	}

    /** Returns the string PERSON. */
	public String returnP()
	{
		return person;
	}

	/** Returns the string BARCODE. */
	public String returnBC()
	{
		return barcode;
	}

    /** Returns the integer FEE. */
	public int returnF()
	{
		return fee;
	}

    /** Returns the integer LFEE. */
	public int returnLF()
	{
		return lfee;
	}

    /** Toggles the checkout status. */
	public void toggleCK()
	{
		if (ckOut.equals("true"))
			ckOut = "false";
		else
			ckOut = "true";
	}

    /** Returns the string CKOUT. */
	public String returnCK()
	{
		return ckOut;
	}

    /** Sets the date DD to the new date specified by YEAR, MONTH, and DAY. */
	public void setDD(int year, int month, int day) throws ParseException
	{
		ddate = dateform.parse(""+month+"-"+day+"-"+year+"");
	}

    /** Returns the date DD. */
	public String returnDD()
	{
		String ddateS = "null";
		if (ddate != null)
			ddateS = dateform.format(ddate);
		return ddateS;
	}
}