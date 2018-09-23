package simple.stateful;

import java.util.Vector;

import javax.ejb.Remote;

@Remote
public interface ToDoRemote {

	Vector<String> listItems();

	void clearItems();

	void addItem(String item);

}
