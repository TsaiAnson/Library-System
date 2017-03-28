# Library-System
The Library System is designed to digitally manage a library. The program handles all check-outs and check-ins, keeps track of all overdue items, and adds any new items that the User specifies. All data is stored in text files to allow offline storage, and the User interacts with the program through a basic GUI interface. Written in Java.

#### Let’s Get Started!
To compile, go into the Library_System folder and run `javac Item.java Faculty.java ListNode.java Student.java ItemList.java Library.java Driver.java`

To run, simply run `java Driver`

#### Home Screen
On the home screen, you will find buttons on the left and two menues in the middle and to the right. All the actions will be performed using the buttons to the left, and all the statuses of checked-out items will be shown in the menues.

#### Checking-Out
To check out an item, go into the Check-Out window by pressing the button and you will see three columns. In the first column, select the faculty of the student who is checking out an item. Next, select the student. Note: There are sample faculty and students loaded, and you can modify them later). After selecting the category and clicking next, you will be brought to a window that lists all the available items of that category. Simply select the item and enter in the desired due date. After clicking check-out, you should be brought back to the home screen, and now the checked-out item should be in the Check Out menu! If the item is not returned by the due date, it will then be transeferred to the over-due menu.

#### Returning an Item / Reporting a Lost Item
To check-in an item, simply select the corresponding item in the menus to the right (either the Checked-Out or the Over-due column). A smaller window will ask you to confirm that the item has been checked in. Similarly, for reporting a lost item, follow the same steps as above except click the Lost button in the smaller window. After doing so, the item will then be removed from the lists.

#### Adding an Item
To add an item, faculty, or student into the database, click on the Add Item button and select the proper item in the menu to the left of the newly opened window. Then, fill in the corresponding fields that relate to that object. For example, for objects that can be checked-out, enter the Name, the Over-Due fee, and the lost fee. As for students, enter the name and the grade. Lastly, for faculty, enter the name and the class they teach. 

#### Notes
All data stored in this database is stored in external text files. That said, all changes made in the program will continue to exist once the program is closed, and upon re-opening the program will load that data back into memory.

#### Feedback!
How is my program? Do you have any questions, comments, and/or improvements? Feel free to let me know!



