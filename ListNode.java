
/**
 * The class that represents a Linked-List.
 * @author Yuan-Cheng Tsai
 */

public class ListNode
{
	/** Object VALUE that holds the node's object. */
	private Object value;

	/** ListNode NEXT that points to the next node in the list. */
	private ListNode next;

	/** Constructor for ListNode. */
	public ListNode (Object initValue, ListNode initNext)
	{
		value = initValue;
		next = initNext;
	}

	/** Returns the object VALUE of the node. */
	public Object getValue()
	{
		return value;
	}

	/** Returns the ListNode NEXT of the node. */
	public ListNode getNext()
	{
		return next;
	}

	/** Sets the object VALUE to the given theNewValue object. */
	public void setValue(Object theNewValue)
	{
		value = theNewValue;
	}

	/** Sets the ListNode NEXT to the given theNewNext ListNode. */
	public void setNext(ListNode theNewNext)
	{
		next = theNewNext;
	}
}
