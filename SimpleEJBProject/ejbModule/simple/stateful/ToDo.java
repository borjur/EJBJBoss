package simple.stateful;

import java.util.Vector;

import javax.ejb.Stateful;

/**
 * Session Bean implementation class ToDo
 */
@Stateful(mappedName = "ToDo")
public class ToDo implements ToDoRemote {
	Vector<String> items = new Vector<String>();
	
	public void addItem(String item) {
    	items.add(item);
	}
	
	public void clearItems() {
		items.clear();
	}
	
	public Vector<String> listItems() {
		return items;
	}
	
	

    /**
     * Default constructor. 
     */
    public ToDo() {
        // TODO Auto-generated constructor stub
    }

}
