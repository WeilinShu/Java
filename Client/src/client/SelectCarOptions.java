
package client;

import model.*;

import java.util.*;

public class SelectCarOptions {

	////////// PROPERTIES //////////
	private Scanner in = new Scanner(System.in);
	

	////////// CONSTRUCTORS //////////

	public void SelectCarOptions() {

	}

	////////// INSTANCE METHODS //////////

	public void configureAuto(Object obj) {
		Auto a1 = (Auto) obj;
		
		a1.Print();
		a1.GetSelection();
		a1.Print();
	}
	
	public String getModel(Object obj){
		StringJoiner joiner = new StringJoiner(" ");
		ArrayList<Auto> a1 = (ArrayList<Auto>) obj;
		for (int i=0; i < ((ArrayList<Auto>) obj).size();i++){
			joiner.add(((ArrayList<Auto>) obj).get(i).getName());
		}
		return joiner.toString();
	}

	public boolean isAutomobile(Object obj) {
		boolean isAutomobile = false;

		try {
			ArrayList<Auto> a1 = (ArrayList<Auto>) obj;
			isAutomobile = true;
		}
		catch (ClassCastException e) {
			isAutomobile = false;
		}

		return isAutomobile;
	}

}
