package simple.stateful;
import java.util.Vector;

import javax.ejb.Remote;

@Remote
public interface ToDoRemote {

	public abstract Vector<String> listItems();

	public abstract void clearItems();

	public abstract void addItem(String item);

}
