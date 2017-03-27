import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Object that represents the Library. Also contains the GUI interface.
 * @author Yuan-Cheng Tsai
 */

public class Library 
{
    /** ItemList BOOKS that holds the ITEM Books. */
	private ItemList books = new ItemList();

    /** ItemList VIDEOS that holds the ITEM videos. */
	private ItemList videos = new ItemList();

    /** ItemList DESKTOPS that holds the ITEM desktops. */
	private ItemList desktops = new ItemList();

    /** ItemList LAPTOPS that holds the ITEM laptops. */
	private ItemList laptops = new ItemList();

    /** ItemList IPADS that holds the ITEM ipads. */
	private ItemList ipads = new ItemList();

    /** ItemList PROJECTORS that holds the ITEM projectors. */
	private ItemList projectors = new ItemList();

    /** ItemList TEXTBOOKS that holds the ITEM textbooks. */
	private ItemList textbooks = new ItemList();

    /** ItemList ROOMS that holds the ITEM rooms. */
	private ItemList rooms = new ItemList();

    /** ItemList CKOUT that holds the ITEMS that are checked-out. */
	private ItemList ckout = new ItemList();

    /** ItemList OVDUE that holds the ITEMS that are overdue. */
	private ItemList ovdue = new ItemList();

    /** ItemList STUDENTS that holds the STUDENT objects. */
	private ItemList students = new ItemList();

    /** ItemList FACULTY that holds the FACULTY objects. */
	private ItemList faculty = new ItemList();

    /** ListNode TYPES that holds the regular selections in the main library interface. */
	private ListNode types = new ListNode("books", new ListNode("videos",
            new ListNode("desktops", new ListNode("laptops",
                    new ListNode("ipads", new ListNode("projectors",
                            new ListNode("textbooks", new ListNode("rooms", null))))))));

    /** ListNode TYPESA that holds the add selections in the main library interface. */
	private ListNode typesA = new ListNode("books", new ListNode("videos",
            new ListNode("desktops", new ListNode("laptops",
                    new ListNode("ipads", new ListNode("projectors",
                            new ListNode("textbooks", new ListNode("rooms",
                                    new ListNode("faculty",
                                            new ListNode("students", null))))))))));

    /** Method to start the Library GUI interface. */
	public void Start() throws NumberFormatException, IOException, ParseException
	{
		ReadFiles();
		Home();
		
	}

    /** Creates a HOME screen where the main controls for the library reside. */
	@SuppressWarnings("serial")
	public void Home()
	{
		Check();
		
		final JFrame H = new JFrame(); //H stands for Home
	    H.setSize(850, 600);
	    H.setTitle("Library System: Home");
	    H.setResizable(false);
	    H.setLocationRelativeTo(null); //Makes the window appear in the center of the screen
	    
	    //Creating checkout and overdue Jlists
	    DefaultListModel<String> modelCO = new DefaultListModel<>();
	    ListNode ckoutL = ckout.getNode();
	    while (ckoutL != null)
	    {
	    	modelCO.addElement("<html>"+((Item) ckoutL.getValue()).returnN()+" "+((Item) ckoutL.getValue()).returnBC()+" "+((Item) ckoutL.getValue()).returnT()+"<br>"+ ((Item) ckoutL.getValue()).returnP()+" "+((Item) ckoutL.getValue()).returnDD()+"</span><html>");
	    	ckoutL = ckoutL.getNext();
	    }
	    JList<String> ckoutLi = new JList<>(modelCO);
	    ckoutLi.setFont(new Font("Arial",Font.BOLD,20));
	    JScrollPane ckoutLiS = new JScrollPane(ckoutLi);
	    
	    DefaultListModel<String> modelOD = new DefaultListModel<>();
	    ListNode ovdueL = ovdue.getNode();
	    while (ovdueL != null)
	    {
	    	modelOD.addElement("<html>"+((Item) ovdueL.getValue()).returnN()+" "+((Item) ovdueL.getValue()).returnT()+"<br>"+((Item) ovdueL.getValue()).returnP()+" Late Fee: "+((Item) ovdueL.getValue()).returnF()*3+"<br>"+((Item) ovdueL.getValue()).returnDD()+"</span><html>");
	    	ovdueL = ovdueL.getNext();
	    }
	    JList<String> ovdueLi = new JList<>(modelOD);
	    ovdueLi.setFont(new Font("Arial",Font.BOLD,20));
	    JScrollPane ovdueLiS = new JScrollPane(ovdueLi);
	    
	    JPanel hCkoutP = new JPanel();
	    hCkoutP.setLayout(new GridLayout(1,1));
	    TitledBorder titleCkout = BorderFactory.createTitledBorder("Checked-Out");
	    hCkoutP.setBorder(titleCkout);
	    hCkoutP.add(ckoutLiS);
	    
	    JPanel hOvdueP = new JPanel();
	    hOvdueP.setLayout(new GridLayout(1,1));
	    TitledBorder titleOvdue = BorderFactory.createTitledBorder("Over-Due");
	    hOvdueP.setBorder(titleOvdue);
	    hOvdueP.add(ovdueLiS);
	    
	    JPanel hButtons = new JPanel();
	    hButtons.setLayout(new GridLayout(9,1));
	    
	    JLabel titleL = new JLabel("         ~~Library~~");
	    titleL.setFont(new Font("Italic", Font.BOLD, 25));
	    
	    JButton hCkoutB = new JButton("Check-Out");
	    hCkoutB.setFont(new Font("Arial",Font.BOLD,20));
	    Action acthCkoutB = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
                H.dispose();
                CheckOut1();
            }
        };
	    hCkoutB.addActionListener(acthCkoutB);
	    
	    JButton hReturnB = new JButton("Return/Lost");
	    hReturnB.setFont(new Font("Arial",Font.BOLD,20));
	    Action acthReturnB = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
                if (ckoutLi.getSelectedIndex() != -1)
                	Return(0, ckoutLi.getSelectedIndex());
                else
                	Return(1, ovdueLi.getSelectedIndex());
                H.dispose();	
            }
        };
	    hReturnB.addActionListener(acthReturnB);
	    
	    JButton hAddB = new JButton("Add Item");
	    hAddB.setFont(new Font("Arial",Font.BOLD,20));
	    Action acthAddB = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
                H.dispose();
                Add();
            }
        };
	    hAddB.addActionListener(acthAddB);
	    
	    JButton hCloseB = new JButton("Close");
	    hCloseB.setFont(new Font("Arial",Font.BOLD,20));
	    Action acthCloseB = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
                H.dispose();
                try {
					SaveFiles();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				System.exit(0);
            }
        };
	    hCloseB.addActionListener(acthCloseB);
	    
	    hButtons.add(titleL);
	    hButtons.add(new JLabel());
	    hButtons.add(hCkoutB);
	    hButtons.add(new JLabel());
	    hButtons.add(hReturnB);
	    hButtons.add(new JLabel());
	    hButtons.add(hAddB);
	    hButtons.add(new JLabel());
	    hButtons.add(hCloseB);
	    
	    JPanel hCol = new JPanel();
	    hCol.setLayout(new GridLayout(1,3));
	    hCol.add(hButtons);
	    hCol.add(hCkoutP);
	    hCol.add(hOvdueP);
	    
	    H.add(hCol);
	    
	    H.setVisible(true);
	}

    /** Creates and shows the frame that holds the first CHECKOUT stage. */
	@SuppressWarnings("serial")
	public void CheckOut1()
	{
		final JFrame CO1 = new JFrame(); //CO stands for checkout
	    CO1.setSize(850, 600);
	    CO1.setTitle("Check-out: Step 1");
	    CO1.setResizable(false);
	    CO1.setLocationRelativeTo(null); //Makes the window appear in the center of the screen
	    
	  //Creating Faculty and Student and Type Jlists
	    DefaultListModel<String> modelF = new DefaultListModel<>();
	    ListNode facultyL = faculty.getNode();
	    while (facultyL != null)
	    {
	    	modelF.addElement("<html>"+((Faculty) facultyL.getValue()).returnN()+"<br>"+ "-" + ((Faculty) facultyL.getValue()).returnC()+"</span><html>");
	    	facultyL = facultyL.getNext();
	    }
	    JList<String> facultyLi = new JList<>(modelF);
	    facultyLi.setFont(new Font("Arial",Font.BOLD,20));
	    JScrollPane facultyLiS = new JScrollPane(facultyLi);
	    
	    DefaultListModel<String> modelS = new DefaultListModel<>();
	    ListNode studentsL = students.getNode();
	    while (studentsL != null)
	    {
	    	modelS.addElement("<html>"+((Student) studentsL.getValue()).returnN()+" "+ ((Student) studentsL.getValue()).returnG()+"</span><html>");
	    	studentsL = studentsL.getNext();
	    }
	    JList<String> studentsLi = new JList<>(modelS);
	    studentsLi.setFont(new Font("Arial",Font.BOLD,20));
	    JScrollPane studentsLiS = new JScrollPane(studentsLi);
	    
	    DefaultListModel<String> modelT = new DefaultListModel<>();
	    ListNode typesL = types;
	    while (typesL != null)
	    {
	    	modelT.addElement("<html>"+typesL.getValue()+"</span><html>");
	    	typesL = typesL.getNext();
	    }
	    JList<String> typesLi = new JList<>(modelT);
	    typesLi.setFont(new Font("Arial",Font.BOLD,20));
	    JScrollPane typesLiS = new JScrollPane(typesLi);
	    
	    //Making bordered panels
	    JPanel FLiSP = new JPanel();
	    FLiSP.setLayout(new GridLayout(1,1));
	    TitledBorder titleFLiS = BorderFactory.createTitledBorder("Faculty");
	    FLiSP.setBorder(titleFLiS);
	    FLiSP.add(facultyLiS);
	    
	    JPanel SLiSP = new JPanel();
	    SLiSP.setLayout(new GridLayout(1,1));
	    TitledBorder titleSLiS = BorderFactory.createTitledBorder("Students");
	    SLiSP.setBorder(titleSLiS);
	    SLiSP.add(studentsLiS);
	    
	    JPanel TLiSP = new JPanel();
	    TLiSP.setLayout(new GridLayout(1,1));
	    TitledBorder titleTLiS = BorderFactory.createTitledBorder("Types");
	    TLiSP.setBorder(titleTLiS);
	    TLiSP.add(typesLiS);
	    
	    
	    //Creating Next-Panel
	    JButton nextB = new JButton("Next");
	    nextB.setFont(new Font("Arial",Font.BOLD,20));
	    Action actnextB = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
            	if (facultyLi.getSelectedIndex() == -1)
                	CO2(1,studentsLi.getSelectedIndex(), typesLi.getSelectedIndex());
            	else
            		CO2(0,facultyLi.getSelectedIndex(), typesLi.getSelectedIndex());
            	CO1.dispose();
            }
        };
	    nextB.addActionListener(actnextB);
	    
	    JPanel nextP = new JPanel();
	    nextP.setLayout(new GridLayout(3,1));
	    nextP.add(new JLabel());
	    nextP.add(new JLabel());
	    nextP.add(nextB);
	    
	    JPanel ckO1 = new JPanel();
	    ckO1.setLayout(new GridLayout(2,1));
	    ckO1.add(TLiSP);
	    ckO1.add(nextP);
	    
	    //Filling Window
	    JPanel ckout1P = new JPanel();
	    ckout1P.setLayout(new GridLayout(1,3));
	    ckout1P.add(FLiSP);
	    ckout1P.add(SLiSP);
	    ckout1P.add(ckO1);
	    
	    CO1.add(ckout1P);
	    
	    CO1.setVisible(true);
	}
	
	JTextField ddmonth = new JTextField(); /*I added the text fields here because I needed 
    the text fields to be static in order for the actionListener to be able to grab the text*/
    JTextField ddday = new JTextField();
    JTextField ddyear = new JTextField();


    /** Creates and shows the frame that holds the second CHECKOUT stage. */
	@SuppressWarnings({ "serial" })
	public void CO2(int FoS, int iP, int tp)
	{
		final JFrame CO2 = new JFrame(); //CO stands for checkout
	    CO2.setSize(850, 600);
	    CO2.setTitle("Check-out: Step 2");
	    CO2.setResizable(false);
	    CO2.setLocationRelativeTo(null); //Makes the window appear in the center of the screen
	    
	    ListNode listcontents;
	    
	    if (tp == 0)
	    	listcontents = books.getNode();
	    else if (tp == 1)
	    	listcontents = videos.getNode();
	    else if (tp == 2)
	    	listcontents = desktops.getNode();
	    else if (tp == 3)
	    	listcontents = laptops.getNode();
	    else if (tp == 4)
	    	listcontents = ipads.getNode();
	    else if (tp == 5)
	    	listcontents = projectors.getNode();
	    else if (tp == 6)
	    	listcontents = textbooks.getNode();
	    else
	    	listcontents = rooms.getNode();
	    
	    ListNode grabitem = listcontents;
	    
	    //Making JList of items
	    DefaultListModel<String> modelI = new DefaultListModel<>();
	    while (listcontents != null)
	    {	if (((Item) listcontents.getValue()).returnCK().equals("false"))
	    		modelI.addElement(""+((Item) listcontents.getValue()).returnN()+"");
	    	listcontents = listcontents.getNext();
	    }
	    JList<String> listcontentsL = new JList<>(modelI);
	    listcontentsL.setFont(new Font("Arial",Font.BOLD,20));
	    JScrollPane listcontentsLS = new JScrollPane(listcontentsL);
	    
	    JPanel itemLP = new JPanel();
	    itemLP.setLayout(new GridLayout(1,1));
	    TitledBorder titleTLiS = BorderFactory.createTitledBorder("Items");
	    itemLP.setBorder(titleTLiS);
	    itemLP.add(listcontentsLS);
	    
	    String iiname = ((Item) grabitem.getValue()).returnT();
	    int fee = ((Item) grabitem.getValue()).returnF();
	    
	    //Making the date panel
	    JLabel fFee = new JLabel("Attention: "+iiname+" fees are "+fee);
	    fFee.setFont(new Font("Arial",Font.BOLD,20));
	    
	    JLabel lFee = new JLabel("Late fees are 3x");
	    lFee.setFont(new Font("Arial",Font.BOLD,20));
	    
	    JLabel dateI = new JLabel("Today's Date:");
	    dateI.setFont(new Font("Arial",Font.BOLD,20));
	    
	    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	    Date curr = new Date();
	    JLabel dateC = new JLabel(dateFormat.format(curr));
	    dateC.setFont(new Font("Arial",Font.BOLD,20));
	    
	    JLabel ddateI = new JLabel("Due Date:");
	    ddateI.setFont(new Font("Arial",Font.BOLD,20));
	    
	    JPanel ddP = new JPanel();
	    ddP.setLayout(new GridLayout(1,5));
	    ddP.add(ddmonth);
	    JLabel d1 = new JLabel("     --");
	    d1.setFont(new Font("Arial",Font.BOLD,20));
	    ddP.add(d1);
	    ddP.add(ddday);
	    JLabel d2 = new JLabel("     --");
	    d2.setFont(new Font("Arial",Font.BOLD,20));
	    ddP.add(d2);
	    ddP.add(ddyear);
	    
	    //Grabbing Person
	    ListNode people;
	    if (FoS == 0)
	    	people = faculty.getNode();
	    else
	    	people = students.getNode();
	    while(iP != 0)
	    {
	    	people = people.getNext();
	    	iP--;
	    }
	    
	    JLabel name;
	    String pname;
	    
	    if (FoS == 0)
	    {
	    	name = new JLabel("Faculty: "+((Faculty) people.getValue()).returnN());
	    	pname = ((Faculty) people.getValue()).returnN();
	    }
	    else
	    {
	    	name = new JLabel("Student: "+((Student) people.getValue()).returnN());
	    	pname = ((Student) people.getValue()).returnN();
	    }
	    name.setFont(new Font("Arial",Font.BOLD,20));
	    
	    
	    
	    JPanel itemConf = new JPanel();
	    itemConf.setLayout(new GridLayout(7,1));
	    itemConf.add(fFee);
	    itemConf.add(lFee);
	    itemConf.add(name);
	    itemConf.add(dateI);
	    itemConf.add(dateC);
	    itemConf.add(ddateI);
	    itemConf.add(ddP);
	    TitledBorder titleitemConf = BorderFactory.createTitledBorder("Configure");
	    itemConf.setBorder(titleitemConf);
	    
	    //Making button panel
	    JButton ckoutB = new JButton("Check Out");
	    ckoutB.setFont(new Font("Arial",Font.BOLD,20));
	    Action actckoutB = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
            	String ppname = pname;
            	ListNode grabitemB = grabitem;
            	String iname = listcontentsL.getSelectedValue();
            	while (!iname.equals(((Item) grabitemB.getValue()).returnN()))
            		grabitemB = grabitemB.getNext();
            	((Item) grabitemB.getValue()).toggleCK();
            	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            	Date curr = new Date();
            	String currD = dateFormat.format(curr);
            	String ddate = ""+ddmonth.getText()+"-"+ddday.getText()+"-"+ddyear.getText();
            	try {
					ckout.add(new Item(((Item) grabitemB.getValue()).returnN(), ppname, ((Item) grabitemB.getValue()).returnT(), ((Item) grabitemB.getValue()).returnBC(), ((Item) grabitemB.getValue()).returnF(), ((Item) grabitemB.getValue()).returnLF(), currD, ddate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
            	CO2.dispose();
            	Home();
            }
        };
	    ckoutB.addActionListener(actckoutB);
	    
	    JButton canB = new JButton("Cancel");
	    canB.setFont(new Font("Arial",Font.BOLD,20));
	    Action actcanB = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
             CO2.dispose();
             Home();
            }
        };
	    canB.addActionListener(actcanB);
	    
	    JPanel buttons = new JPanel();
	    buttons.setLayout(new GridLayout(3, 1));
	    buttons.add(new JLabel());
	    buttons.add(ckoutB);
	    buttons.add(canB);
	    
	    //Combining to make window
	    JPanel CO2R = new JPanel();
	    CO2R.setLayout(new GridLayout(2,1));
	    CO2R.add(itemConf);
	    CO2R.add(buttons);
	    
	    JPanel CO2W = new JPanel();
	    CO2W.setLayout(new GridLayout(1,2));
	    CO2W.add(itemLP);
	    CO2W.add(CO2R);
	    
	    CO2.add(CO2W);
	    
	    CO2.setVisible(true);
	}

    /** Creates and shows the frame that holds the RETURN functions. */
	@SuppressWarnings("serial")
	public void Return(int CoO, int index)
	{
		final JFrame R = new JFrame(); //R stands for Return
	    R.setSize(450, 600);
	    R.setTitle("Return/Lost");
	    R.setResizable(false);
	    R.setLocationRelativeTo(null); //Makes the window appear in the center of the screen
	    
	    ListNode itemstatus;
	    
	    if (CoO == 0) //If the selected item is a regular ckout or overdue
	    	itemstatus = ckout.getNode();
	    else
	    	itemstatus = ovdue.getNode();
	    
	    int index1 = index;
	    
	    while (index != 0)
	    {
	    	itemstatus = itemstatus.getNext();
	    	index--;
	    }
	    
	    String typeperson = "null";
	    
	    String nameperson = ((Item) itemstatus.getValue()).returnP();
	    
	    if ((nameperson.substring(0, 1)).equals("M"))
	    		typeperson = "Faculty: ";
	    else
	    	typeperson = "Student: ";
	    
	    int ifee = ((Item) itemstatus.getValue()).returnF(); 
	    int ilfee = ((Item) itemstatus.getValue()).returnLF();
	    String inm = ((Item) itemstatus.getValue()).returnN();
	    String ity = ((Item) itemstatus.getValue()).returnT();
	    
	    //Creating InfoPanel
	    JLabel personL = new JLabel(typeperson+nameperson);
	    personL.setFont(new Font("Arial",Font.BOLD,20));
	    
	    JLabel itemL = new JLabel("Item: "+inm+" "+ity);
	    itemL.setFont(new Font("Arial",Font.BOLD,20));
	    
	    JPanel info = new JPanel();
	    info.setLayout(new GridLayout(4,1));
	    info.add(personL);
	    info.add(new JLabel());
	    info.add(itemL);
	    info.add(new JLabel());
	    
	    //Creating ButtonPanel
	    JButton rB = new JButton("Return");
	    rB.setFont(new Font("Arial",Font.BOLD,20));
	    Action rBa = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
            	final JFrame RR = new JFrame(); 
        	    RR.setSize(450, 200);
        	    RR.setTitle("Item Fee");
        	    RR.setResizable(false);
        	    RR.setLocationRelativeTo(null); //Makes the window appear in the center of the screen
        	    
        	    ListNode listcontents;
        	    
        	    if (ity.equals("book"))
        	    	listcontents = books.getNode();
        	    else if (ity.equals("video"))
        	    	listcontents = videos.getNode();
        	    else if (ity.equals("desktop"))
        	    	listcontents = desktops.getNode();
        	    else if (ity.equals("laptop"))
        	    	listcontents = laptops.getNode();
        	    else if (ity.equals("ipad"))
        	    	listcontents = ipads.getNode();
        	    else if (ity.equals("projector"))
        	    	listcontents = projectors.getNode();
        	    else if (ity.equals("textbook"))
        	    	listcontents = textbooks.getNode();
        	    else
        	    	listcontents = rooms.getNode();
        	    
        	    while (!((Item) listcontents.getValue()).returnN().equals(inm))
        	    	listcontents = listcontents.getNext();
        	    ((Item) listcontents.getValue()).toggleCK();
        	    
        	    JLabel fee;
        	    if ((nameperson.substring(0, 1)).equals("M"))
        	    {
            		fee = new JLabel("Fee for "+inm+": 0");
            		fee.setFont(new Font("Arial",Font.BOLD,20));
            		
            		if (CoO == 0)
            		{
            			//Removing item from list
                		ItemList ck = ckout;
                		ck.remove(index1);
            		}
            		else
            		{
            			//Removing item from list
                		ItemList cko = ovdue;
                		cko.remove(index1);
            		}
        	    }
        	    else
        	    {
        	    	if (CoO == 0)
        	    	{
        	    		fee = new JLabel("Fee for "+inm+": "+ifee);
                		fee.setFont(new Font("Arial",Font.BOLD,20));
                		
                		//Removing item from list
                		ItemList ck = ckout;
                		ck.remove(index1);
        	    	}
        	    	else
        	    	{
        	    		fee = new JLabel("Late Fee for "+inm+": "+ifee*3);
                		fee.setFont(new Font("Arial",Font.BOLD,20));
                		
                		//Removing item from list
                		ItemList cko = ovdue;
                		cko.remove(index1);
        	    	}
        	    }
             
            	JButton okL = new JButton("Ok");
            	okL.setFont(new Font("Arial",Font.BOLD,20));
            	Action okLa = new AbstractAction(){ //This is what is going to happen if the button is pressed
            		public void actionPerformed(ActionEvent evt){
            		RR.dispose();
            		R.dispose();
            		Home();
            		}
             };
             okL.addActionListener(okLa);
             
             JPanel RP = new JPanel();
             RP.setLayout(new GridLayout(3,1));
             RP.add(fee);
             RP.add(new JLabel());
             RP.add(okL);
             
             RR.add(RP);
             RR.setVisible(true);
            }
        };
        rB.addActionListener(rBa);
	    
	    JButton lB = new JButton("Lost");
	    lB.setFont(new Font("Arial",Font.BOLD,20));
	    Action lBa = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
            	final JFrame L = new JFrame(); //L Stands for Lost
        	    L.setSize(450, 200);
        	    L.setTitle("Lost Item Fee");
        	    L.setResizable(false);
        	    L.setLocationRelativeTo(null); //Makes the window appear in the center of the screen
            	
            	JLabel lfee = new JLabel("Lost Fee for "+inm+": "+ilfee);
            	lfee.setFont(new Font("Arial",Font.BOLD,20));
             
            	if (CoO == 0)
        		{
        			//Removing item from list
            		ItemList ck = ckout;
            		ck.remove(index1);
        		}
        		else
        		{
        			//Removing item from list
            		ItemList cko = ovdue;
            		cko.remove(index1);
        		}
            	
            	JButton okL = new JButton("Ok");
            	okL.setFont(new Font("Arial",Font.BOLD,20));
            	Action okLa = new AbstractAction(){ //This is what is going to happen if the button is pressed
            		public void actionPerformed(ActionEvent evt){
            		L.dispose();
            		R.dispose();
            		Home();
            		}
             };
             okL.addActionListener(okLa);
             
             JPanel LP = new JPanel();
             LP.setLayout(new GridLayout(3,1));
             LP.add(lfee);
             LP.add(new JLabel());
             LP.add(okL);
             
             L.add(LP);
             L.setVisible(true);
            }
        };
        lB.addActionListener(lBa);
	    
	    JButton canB = new JButton("Cancel");
	    canB.setFont(new Font("Arial",Font.BOLD,20));
	    Action actcanB = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
             R.dispose();
             Home();
            }
        };
	    canB.addActionListener(actcanB);
	    
	    JPanel buttons = new JPanel();
	    buttons.setLayout(new GridLayout(4,1));
	    buttons.add(new JLabel());
	    buttons.add(rB);
	    buttons.add(lB);
	    buttons.add(canB);
	    
	    //Creating whole window
	    JPanel wR = new JPanel();
	    wR.setLayout(new GridLayout(2,1));
	    wR.add(info);
	    wR.add(buttons);
	    
	    R.add(wR);
	    R.setVisible(true);
	}

    /** Method that checks if there are ITEMS that are overdue. */
	public void Check()
	{
		Date curr = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("MM-dd-yyyy");
		int index = 0;
		
		ListNode checkco = ckout.getNode();
		while (checkco != null)
		{ 
			Date ddi = null;
			try {
				ddi = simple.parse(((Item) checkco.getValue()).returnDD());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if (curr.compareTo(ddi) > 1)
			{
				ovdue.add(checkco.getValue());
				ckout.remove(index);
			}
			index++;
			checkco = checkco.getNext();
		}
	}
	
	JTextField nameA = new JTextField();
	JTextField gradeA = new JTextField();
	JTextField classA = new JTextField();
	JTextField feeA = new JTextField();
	JTextField lfeeA = new JTextField();


    /** Creates and shows a FRAME that holds the ADD functions. */
	@SuppressWarnings("serial")
	public void Add()
	{
		final JFrame A = new JFrame(); //R stands for Return
	    A.setSize(850, 600);
	    A.setTitle("Add");
	    A.setResizable(false);
	    A.setLocationRelativeTo(null); //Makes the window appear in the center of the screen
	    
	    //Making JList
	    DefaultListModel<String> modelA = new DefaultListModel<>();
	    ListNode tyL = typesA;
	    while (tyL != null)
	    {
	    	modelA.addElement((String) tyL.getValue());
	    	tyL = tyL.getNext();
	    }
	    JList<String> tyLi = new JList<>(modelA);
	    tyLi.setFont(new Font("Arial",Font.BOLD,20));
	    JScrollPane tyLiS = new JScrollPane(tyLi);
	    
	    //JList Panel
	    JPanel listP = new JPanel();
	    listP.setLayout(new GridLayout(1,1));
	    TitledBorder titleListP = BorderFactory.createTitledBorder("Types");
	    listP.setBorder(titleListP);
	    listP.add(tyLiS);
	    
	    //Making Panels
	    JPanel aName = new JPanel();
	    aName.setLayout(new GridLayout (1, 2));
	    JLabel aNameL = new JLabel("Name:");
	    aNameL.setFont(new Font("Arial",Font.BOLD,20));
	    aName.add(aNameL);
	    aName.add(nameA);
	    
	    JPanel aGrCl = new JPanel();
	    aGrCl.setLayout(new GridLayout(1,4));
	    JLabel aGrL = new JLabel("Grade:");
	    aGrL.setFont(new Font("Arial",Font.BOLD,20));
	    JLabel aClL = new JLabel("Class:");
	    aClL.setFont(new Font("Arial",Font.BOLD,20));
	    aGrCl.add(aGrL);
	    aGrCl.add(gradeA);
	    aGrCl.add(aClL);
	    aGrCl.add(classA);
	    
	    JPanel aLFee = new JPanel();
	    aLFee.setLayout(new GridLayout(1,4));
	    JLabel aFee = new JLabel("Fee:");
	    aFee.setFont(new Font("Arial",Font.BOLD,20));
	    JLabel aLfee = new JLabel("Lost:");
	    aLfee.setFont(new Font("Arial",Font.BOLD,20));
	    aLFee.add(aFee);
	    aLFee.add(feeA);
	    aLFee.add(aLfee);
	    aLFee.add(lfeeA);
	    
	    //Creating Buttons
	    JButton addB = new JButton("Add");
	    addB.setFont(new Font("Arial",Font.BOLD,20));
	    Action actaddB = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
            
            	String selected = tyLi.getSelectedValue();
            	ItemList typeA;
            	
            	if (selected.equals("books"))
            			typeA = books;
            	else if (selected.equals("videos"))
        			typeA = videos;
            	else if (selected.equals("desktops"))
        			typeA = desktops;
            	else if (selected.equals("laptops"))
        			typeA = laptops;
            	else if (selected.equals("ipads"))
        			typeA = ipads;
            	else if (selected.equals("projectors"))
        			typeA = projectors;
            	else if (selected.equals("textbooks"))
        			typeA = textbooks;
            	else if (selected.equals("rooms"))
        			typeA = rooms;
            	else if (selected.equals("faculty"))
        			typeA = faculty;
            	else
            		typeA = students;
            	
            	if (selected.equals("faculty"))
            		typeA.add(new Faculty(nameA.getText(), classA.getText()));
            	else if (selected.equals("students"))
            		typeA.add(new Student(nameA.getText(), Integer.parseInt(gradeA.getText())));
				else
					try {
						typeA.add(new Item(nameA.getText(), "null", ((Item) typeA.getNode().getValue()).returnT(), "e1", Integer.parseInt(feeA.getText()), 0, "false", "null"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	
            	nameA.setText("");
            	gradeA.setText("");
            	classA.setText("");
            	feeA.setText("");
            	lfeeA.setText("");
            	
            	A.dispose();
            	Home();
            }
        };
	    addB.addActionListener(actaddB);
	    
	    JButton canB = new JButton("Cancel");
	    canB.setFont(new Font("Arial",Font.BOLD,20));
	    Action actcanB = new AbstractAction(){ //This is what is going to happen if the button is pressed
            public void actionPerformed(ActionEvent evt){
            	A.dispose();
            	Home();
            }
        };
	    canB.addActionListener(actcanB);
	    
	    //Creating right panel
	    JPanel rA = new JPanel();
	    rA.setLayout(new GridLayout(6,1));
	    rA.add(aName);
	    rA.add(aGrCl);
	    rA.add(aLFee);
	    rA.add(new JLabel());
	    rA.add(addB);
	    rA.add(canB);
	    
	    //Creating whole panel
	    JPanel wA = new JPanel();
	    wA.setLayout(new GridLayout(1,2));
	    wA.add(listP);
	    wA.add(rA);
	    
	    //Adding to window
	    A.add(wA);
	    A.setVisible(true);
	    }

    /** Method that reads the TEXTFILES that store the Library's information. */
	public void  ReadFiles() throws NumberFormatException, IOException, ParseException
	{
		BufferedReader Breader = new BufferedReader(new FileReader("books.txt"));
		String Bline;
		while ((Bline = Breader.readLine()) != null) 
		{
			Scanner b = new Scanner(Bline);
		    books.add(new Item(b.next(), b.next(), b.next(), b.next(), Integer.parseInt(b.next()), Integer.parseInt(b.next()), b.next(), b.next()));
		    b.close();
		}
		Breader.close();
		
		BufferedReader Vreader = new BufferedReader(new FileReader("videos.txt"));
		String Vline;
		while ((Vline = Vreader.readLine()) != null) 
		{
			Scanner s = new Scanner(Vline);
		    videos.add(new Item(s.next(), s.next(), s.next(), s.next(), Integer.parseInt(s.next()), Integer.parseInt(s.next()), s.next(), s.next()));
		    s.close();
		}
		Vreader.close();
		
		BufferedReader Dreader = new BufferedReader(new FileReader("desktops.txt"));
		String Dline;
		while ((Dline = Dreader.readLine()) != null) 
		{
			Scanner s = new Scanner(Dline);
		    desktops.add(new Item(s.next(), s.next(), s.next(), s.next(), Integer.parseInt(s.next()), Integer.parseInt(s.next()), s.next(), s.next()));
		    s.close();
		}
		Dreader.close();
		
		BufferedReader Lreader = new BufferedReader(new FileReader("laptops.txt"));
		String Lline;
		while ((Lline = Lreader.readLine()) != null) 
		{
			Scanner s = new Scanner(Lline);
		    laptops.add(new Item(s.next(), s.next(), s.next(), s.next(), Integer.parseInt(s.next()), Integer.parseInt(s.next()), s.next(), s.next()));
		    s.close();
		}
		Lreader.close();
		
		BufferedReader Ireader = new BufferedReader(new FileReader("ipads.txt"));
		String Iline;
		while ((Iline = Ireader.readLine()) != null) 
		{
			Scanner s = new Scanner(Iline);
		    ipads.add(new Item(s.next(), s.next(), s.next(), s.next(), Integer.parseInt(s.next()), Integer.parseInt(s.next()), s.next(), s.next()));
		    s.close();
		}
		Ireader.close();
		
		BufferedReader Preader = new BufferedReader(new FileReader("projectors.txt"));
		String Pline;
		while ((Pline = Preader.readLine()) != null) 
		{
			Scanner s = new Scanner(Pline);
		    projectors.add(new Item(s.next(), s.next(), s.next(), s.next(), Integer.parseInt(s.next()), Integer.parseInt(s.next()), s.next(), s.next()));
		    s.close();
		}
		Preader.close();
		
		BufferedReader Treader = new BufferedReader(new FileReader("textbooks.txt"));
		String Tline;
		while ((Tline = Treader.readLine()) != null) 
		{
			Scanner s = new Scanner(Tline);
		    textbooks.add(new Item(s.next(), s.next(), s.next(), s.next(), Integer.parseInt(s.next()), Integer.parseInt(s.next()), s.next(), s.next()));
		    s.close();
		}
		Treader.close();
		
		BufferedReader Rreader = new BufferedReader(new FileReader("rooms.txt"));
		String Rline;
		while ((Rline = Rreader.readLine()) != null) 
		{
			Scanner s = new Scanner(Rline);
		    rooms.add(new Item(s.next(), s.next(), s.next(), s.next(), Integer.parseInt(s.next()), Integer.parseInt(s.next()), s.next(), s.next()));
		    s.close();
		}
		Rreader.close();
		
		BufferedReader COreader = new BufferedReader(new FileReader("checkout.txt"));
		String COline;
		while ((COline = COreader.readLine()) != null) 
		{
			Scanner s = new Scanner(COline);
		    ckout.add(new Item(s.next(), s.next(), s.next(), s.next(), Integer.parseInt(s.next()), Integer.parseInt(s.next()), s.next(), s.next()));
		    s.close();
		}
		COreader.close();
		
		BufferedReader ODreader = new BufferedReader(new FileReader("overdue.txt"));
		String ODline;
		while ((ODline = ODreader.readLine()) != null) 
		{
			Scanner s = new Scanner(ODline);
		    ovdue.add(new Item(s.next(), s.next(), s.next(), s.next(), Integer.parseInt(s.next()), Integer.parseInt(s.next()), s.next(), s.next()));
		    s.close();
		}
		ODreader.close();
		
		BufferedReader Freader = new BufferedReader(new FileReader("faculty.txt"));
		String Fline;
		while ((Fline = Freader.readLine()) != null) 
		{
			Scanner s = new Scanner(Fline);
		    faculty.add(new Faculty(s.next(), s.next()));
		    s.close();
		}
		Freader.close();
		
		BufferedReader Sreader = new BufferedReader(new FileReader("students.txt"));
		String Sline;
		while ((Sline = Sreader.readLine()) != null) 
		{
			Scanner s = new Scanner(Sline);
		    students.add(new Student(s.next(), Integer.parseInt(s.next())));
		    s.close();
		}
		Sreader.close();
	}

    /** Method that stores the Library's information on TEXTFILES. */
	public void SaveFiles() throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter Bwriter = new PrintWriter("books.txt", "UTF-8");
		ListNode booksL = books.getNode();	
		while (booksL != null)
		{
			Bwriter.println(""+((Item) booksL.getValue()).returnN()+" "+((Item) booksL.getValue()).returnP()+" "+((Item) booksL.getValue()).returnT()+" "+((Item) booksL.getValue()).returnBC()+" "+((Item) booksL.getValue()).returnF()+" "+((Item) booksL.getValue()).returnLF()+" "+((Item) booksL.getValue()).returnCK()+" "+((Item) booksL.getValue()).returnDD());
			booksL = booksL.getNext();
		}
		Bwriter.close();
		
		PrintWriter Vwriter = new PrintWriter("videos.txt", "UTF-8");
		ListNode videosL = videos.getNode();	
		while (videosL != null)
		{
			Vwriter.println(""+((Item) videosL.getValue()).returnN()+" "+((Item) videosL.getValue()).returnP()+" "+((Item) videosL.getValue()).returnT()+" "+((Item) videosL.getValue()).returnBC()+" "+((Item) videosL.getValue()).returnF()+" "+((Item) videosL.getValue()).returnLF()+" "+((Item) videosL.getValue()).returnCK()+" "+((Item) videosL.getValue()).returnDD());
			videosL = videosL.getNext();
		}
		Vwriter.close();
		
		PrintWriter Dwriter = new PrintWriter("desktops.txt", "UTF-8");
		ListNode desktopsL = desktops.getNode();	
		while (desktopsL != null)
		{
			Dwriter.println(""+((Item) desktopsL.getValue()).returnN()+" "+((Item) desktopsL.getValue()).returnP()+" "+((Item) desktopsL.getValue()).returnT()+" "+((Item) desktopsL.getValue()).returnBC()+" "+((Item) desktopsL.getValue()).returnF()+" "+((Item) desktopsL.getValue()).returnLF()+" "+((Item) desktopsL.getValue()).returnCK()+" "+((Item) desktopsL.getValue()).returnDD());
			desktopsL = desktopsL.getNext();
		}
		Dwriter.close();
		
		PrintWriter Lwriter = new PrintWriter("laptops.txt", "UTF-8");
		ListNode laptopsL = laptops.getNode();	
		while (laptopsL != null)
		{
			Lwriter.println(""+((Item) laptopsL.getValue()).returnN()+" "+((Item) laptopsL.getValue()).returnP()+" "+((Item) laptopsL.getValue()).returnT()+" "+((Item) laptopsL.getValue()).returnBC()+" "+((Item) laptopsL.getValue()).returnF()+" "+((Item) laptopsL.getValue()).returnLF()+" "+((Item) laptopsL.getValue()).returnCK()+" "+((Item) laptopsL.getValue()).returnDD());
			laptopsL = laptopsL.getNext();
		}
		Lwriter.close();
		
		PrintWriter Iwriter = new PrintWriter("ipads.txt", "UTF-8");
		ListNode ipadsL = ipads.getNode();	
		while (ipadsL != null)
		{
			Iwriter.println(""+((Item) ipadsL.getValue()).returnN()+" "+((Item) ipadsL.getValue()).returnP()+" "+((Item) ipadsL.getValue()).returnT()+" "+((Item) ipadsL.getValue()).returnBC()+" "+((Item) ipadsL.getValue()).returnF()+" "+((Item) ipadsL.getValue()).returnLF()+" "+((Item) ipadsL.getValue()).returnCK()+" "+((Item) ipadsL.getValue()).returnDD());
			ipadsL = ipadsL.getNext();
		}
		Iwriter.close();
		
		PrintWriter Pwriter = new PrintWriter("projectors.txt", "UTF-8");
		ListNode projectorsL = projectors.getNode();	
		while (projectorsL != null)
		{
			Pwriter.println(""+((Item) projectorsL.getValue()).returnN()+" "+((Item) projectorsL.getValue()).returnP()+" "+((Item) projectorsL.getValue()).returnT()+" "+((Item) projectorsL.getValue()).returnBC()+" "+((Item) projectorsL.getValue()).returnF()+" "+((Item) projectorsL.getValue()).returnLF()+" "+((Item) projectorsL.getValue()).returnCK()+" "+((Item) projectorsL.getValue()).returnDD());
			projectorsL = projectorsL.getNext();
		}
		Pwriter.close();
		
		PrintWriter Twriter = new PrintWriter("textbooks.txt", "UTF-8");
		ListNode textbooksL = textbooks.getNode();	
		while (textbooksL != null)
		{
			Twriter.println(""+((Item) textbooksL.getValue()).returnN()+" "+((Item) textbooksL.getValue()).returnP()+" "+((Item) textbooksL.getValue()).returnT()+" "+((Item) textbooksL.getValue()).returnBC()+" "+((Item) textbooksL.getValue()).returnF()+" "+((Item) textbooksL.getValue()).returnLF()+" "+((Item) textbooksL.getValue()).returnCK()+" "+((Item) textbooksL.getValue()).returnDD());
			textbooksL = textbooksL.getNext();
		}
		Twriter.close();
		
		PrintWriter Rwriter = new PrintWriter("rooms.txt", "UTF-8");
		ListNode roomsL = rooms.getNode();	
		while (roomsL != null)
		{
			Rwriter.println(""+((Item) roomsL.getValue()).returnN()+" "+((Item) roomsL.getValue()).returnP()+" "+((Item) roomsL.getValue()).returnT()+" "+((Item) roomsL.getValue()).returnBC()+" "+((Item) roomsL.getValue()).returnF()+" "+((Item) roomsL.getValue()).returnLF()+" "+((Item) roomsL.getValue()).returnCK()+" "+((Item) roomsL.getValue()).returnDD());
			roomsL = roomsL.getNext();
		}
		Rwriter.close();
		
		PrintWriter COwriter = new PrintWriter("checkout.txt", "UTF-8");
		ListNode ckoutL = ckout.getNode();	
		while (ckoutL != null)
		{
			COwriter.println(""+((Item) ckoutL.getValue()).returnN()+" "+((Item) ckoutL.getValue()).returnP()+" "+((Item) ckoutL.getValue()).returnT()+" "+((Item) ckoutL.getValue()).returnBC()+" "+((Item) ckoutL.getValue()).returnF()+" "+((Item) ckoutL.getValue()).returnLF()+" "+((Item) ckoutL.getValue()).returnCK()+" "+((Item) ckoutL.getValue()).returnDD());
			ckoutL = ckoutL.getNext();
		}
		COwriter.close();
		
		PrintWriter ODwriter = new PrintWriter("overdue.txt", "UTF-8");
		ListNode ovdueL = ovdue.getNode();	
		while (ovdueL != null)
		{
			ODwriter.println(""+((Item) ovdueL.getValue()).returnN()+" "+((Item) ovdueL.getValue()).returnP()+" "+((Item) ovdueL.getValue()).returnT()+" "+((Item) ovdueL.getValue()).returnBC()+" "+((Item) ovdueL.getValue()).returnF()+" "+((Item) ovdueL.getValue()).returnLF()+" "+((Item) ovdueL.getValue()).returnCK()+" "+((Item) ovdueL.getValue()).returnDD());
			ovdueL = ovdueL.getNext();
		}
		ODwriter.close();
		
		PrintWriter Fwriter = new PrintWriter("faculty.txt", "UTF-8");
		ListNode facultyL = faculty.getNode();	
		while (facultyL != null)
		{
			Fwriter.println(""+((Faculty) facultyL.getValue()).returnN()+" "+((Faculty) facultyL.getValue()).returnC()+"");
			facultyL = facultyL.getNext();
		}
		Fwriter.close();
		
		PrintWriter Swriter = new PrintWriter("students.txt", "UTF-8");
		ListNode studentsL = students.getNode();	
		while (studentsL != null)
		{
			Swriter.println(""+((Student) studentsL.getValue()).returnN()+" "+((Student) studentsL.getValue()).returnG()+"");
			studentsL = studentsL.getNext();
		}
		Swriter.close();
	}
	
}