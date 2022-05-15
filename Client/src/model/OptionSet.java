//Weilin Shu
//35B Project 2


package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;

public class OptionSet implements Serializable {
	private String optionSetName = " ";
	//private Option optlist[] = new Option[0];
	private ArrayList<Option> optlist = new ArrayList<Option>();
	private Option choice = new Option(); 

	public OptionSet() {
	}

	public OptionSet(String n) {
		optionSetName = n;
	}

	public OptionSet(String n, int size) {
		optionSetName = n;
		optlist = new ArrayList<Option>();
	}

	
	protected void Print() {
		System.out.println(optionSetName);
	}
	
	protected String getName() {
		return optionSetName;
	}

	protected Option getChoice() {
		return choice;
	}
	
	protected String getChoicename() {
		return choice.getOptionname();
	}
	
	protected float getChoiceprice() {
		return choice.getPriceAdjustment();
	}
	
	protected void getAllOption() {
		for (int i =0; i< optlist.size(); i++)
		{
			System.out.print(String.valueOf(i+1)+optlist.get(i).getOptionname());
		}
	}
	
	protected void setName(String name) {
		optionSetName = name;
	}

	protected ArrayList<Option> getOptlist() {
		return optlist;
	}

	protected void setChoice(Option choice) {
		this.choice = choice;
	}
	
	protected void setOptlist(ArrayList<Option> optlist) {
		this.optlist = optlist;
	}
	
	protected void setIndexOptioninOptlist(Option option, int i) {
		optlist.set(1,option);
	}
	
	protected Option findOption(String name) {
		for (int i = 0; i < optlist.size(); i++) {
			if (name.equals(optlist.get(i).getOptionname()))
				return optlist.get(i);
		}
		System.out.println("Cannot find the item");
		try {
			throw new AutoException(4, "Cannot Find OptionSet");
		} catch (AutoException e4) {
			e4.printmyproblem();
	  }
		return null;
	}

	protected void updateOption(String name, float price, int i) {
		if (i >= 0 && i < opt.size()) { // make sure the index is within the range
			opt.get(i).setName(name);
			opt.get(i).setPrice(price);
		}
	}

	protected void updateOptionPrice(String name, float price) {
		Option option = findOption(name);
		if (option != null) {
			option.setPrice(price);
		}
	}

	protected void updateOptionName(float price, String name) {
		Option option = findOption(name);
		if (option != null) {
			option.setName(name);
		}
	}


	protected void printOptionSet() {

		if (DEBUG)
			System.out.print("Print method in OptionSet:");

		System.out.print("\nName of the set: " + name);
		System.out.print("\n\tName (Price)\n");
		for (int i = 0; i < opt.size(); i++) {
			opt.get(i).optionPrintMethod();
		}
	}

	

}
