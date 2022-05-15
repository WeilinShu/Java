package server;

import model.Auto;
import java.util.*;

public interface AutoServer {
	
	//public ArrayList<String> ProvideList();
	
	public ArrayList<Auto> sendObject ();
	
	public void AddtoHashMap(Auto a);
	
	public String getModel();
	
	public String getOption();

}
