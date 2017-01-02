
public class ItemList 
{
	private ListNode list;
	
	public ItemList()
	{
		list = null;
	}
	
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
	
	public ListNode getNode()
	{
		return list;
	}
	
	public boolean isEmpty()
	{
		if (list == null)
			return true;
		else 
			return false;
	}
	
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
	
	public void removeL()
	{
		ListNode current = list;
		while (current.getNext().getNext() != null)
			current = current.getNext();
		current.setNext(null);
	}
}