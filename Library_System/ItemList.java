
/**
 * The object ITEMLIST that uses a LISTNODE object to create
 * a linked list for ITEM objects.
 * @author Yuan-Cheng Tsai
 */

public class ItemList 
{
    /** ListNode LIST that serves a the head of the ItemList. */
	private ListNode list;

    /** Constructor for ItemList. */
	public ItemList()
	{
		list = null;
	}

    /** Adds Object OBJ to the end of ListNode LIST. */
	public void add(Object obj)
	{
		ListNode node = new ListNode (obj, null);
		ListNode current;
		
		if (list == null)
			list = node;
		else
		{
			current = list;
			while (current.getNext() != null)
				current = current.getNext();
			current.setNext(node);
		}
	}

    /** Returns the head of ListNode LIST. */
	public ListNode getNode()
	{
		return list;
	}

    /** Returns TRUE if ListNode LIST is empty,
     * false otherwise. */
	public boolean isEmpty()
	{
		if (list == null)
			return true;
		else 
			return false;
	}

    /** Removes the ListNode object at the specific INDEX of LIST. */
	public void remove(int index)
	{
		if (index == 0)
			list = list.getNext();
		else
		{
			ListNode current = list;
			while (index > 1)
			{
				current = current.getNext();
				index --;
			}
			current.setNext(current.getNext().getNext());
		}
	}

    /** Removes the last ListNode object of LIST. */
	public void removeL()
	{
		ListNode current = list;
		while (current.getNext().getNext() != null)
			current = current.getNext();
		current.setNext(null);
	}
}