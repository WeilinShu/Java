//Weilin Shu
//35B Project 2

package model;

import java.io.Serializable;

import exception.AutoException;
import model.Option;
import model.OptionSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Auto implements Serializable {

	private String make = "";
	private String model ="";
	private String name = "";
	private float price = 0;
	private ArrayList<Option> choice = new ArrayList<Option>(); 
	private ArrayList<OptionSet> opset = new ArrayList<OptionSet>();
	
	public Auto() {
	}
	

	public Auto(String n) {
		name = n;
	}

	public Auto(String n, int size) {
		opset = new ArrayList<OptionSet>();
		choice = new ArrayList<Option>(opset.size());
		name = n;
	}

	public synchronized String getName() {
		notifyAll();
		return name;
	}
	
	public synchronized String getMake() {
		notifyAll();
		return make;
	}
	
	public synchronized String getModel() {
		notifyAll();
		return model;
	}

	public synchronized float getPrice() {
		notifyAll();
		return price;
	}
	
	public synchronized float getTotalChoicePrice() {
		float sum=price;
		for (int i=0; i< choice.size(); i++) {
			sum += opset.get(i).getChoiceprice();
		}
		notifyAll();
		return sum;
	}
	
	public synchronized String[] getChoicename() {
		String[] array= new String[choice.size()];
		for (int i=0; i< choice.size(); i++) {
			array[i] = opset.get(i).getChoicename();
		}
		notifyAll();
		return array;
	}

	public synchronized ArrayList<OptionSet> getOpset() {
		notifyAll();
		return opset;
	}

	public synchronized OptionSet findOptionSet(String name) {
		for (int i = 0; i < opset.size(); i++) {
			if (name.equals(opset.get(i).getName()))
				return opset.get(i);
				notifyAll();
		}
		System.out.println("Cannot find the item");
		notifyAll();
		try {
			throw new AutoException(4, "Cannot Find OptionSet");
		} catch (AutoException e4) {
			e4.printmyproblem();
	  }
		notifyAll();
		return null;
	}

	public synchronized Option findOption(String OptionSetname, String Optionname) {
		for (int i = 0; i < opset.size(); i++) {
			if (OptionSetname.equals(opset.get(i).getName()))
				for (int j = 0; j < opset.get(i).getOptlist().size(); j++) {
					if (Optionname.equals(opset.get(i).getOptlist().get(j).getOptionname()))
						return opset.get(i).getOptlist().get(j);
						notifyAll();
				}
		}
		System.out.println("Cannot find the item");
		notifyAll();
		try {
			throw new AutoException(5, "Cannot Find Option");
		} catch (AutoException e5) {
			e5.printmyproblem();
	  }
		return null;
	}

	public synchronized void setName(String name) {
		this.name = name;
		notifyAll();
	}
	
	public synchronized void setMake(String make) {
		this.make = make;
		notifyAll();
	}
	
	public synchronized void setModel(String model) {
		Option temp;
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(setName)) {
				opset.get(i).setOptionChoice(optionName);
				temp = opset.get(i).getOptionChoice();
				choice.add(temp);
			}
		}
		notifyAll();
	}

	public synchronized void setPrice(float price) {
		this.price = price;
		notifyAll();
	}
	
	public synchronized void setOptionChoice(String optionsetName, String optionName) {
		findOptionSet(optionsetName).setChoice(findOption(optionsetName,optionName ));
		choice.add(findOption(optionsetName, optionName));
		notifyAll();
	}

	public synchronized void setOpset(ArrayList<OptionSet> opset) {
		this.opset = opset;
		notifyAll();
	}

	public synchronized void updateOptionSetName(String name, String UpdateName) {
		findOptionSet(name).setName(UpdateName);
		notifyAll();
	}

	public synchronized void updateOptionPrice(String Modelname, String OptionSet, String Option, float newprice) {
		findOption(OptionSet, Option).setPriceAdjustment(newprice);
		notifyAll();
	}
	
	public synchronized void updateOptionName(String Modelname, String OptionSet, String Option, String NewName){
		try {
		findOption(OptionSet, Option).setOptionname(NewName);
		}catch(Exception e) {
			System.out.println(" ");
		}
		notifyAll();
	}

	public synchronized void setOptionArray(ArrayList<Option> optionset, int i) {
		opset.get(i).setOptlist(optionset);
		notifyAll();
	}

	public synchronized void setOptionInAOptionSet(Option opt, int iOptionSet, int iOption) {
		opset.get(iOptionSet).setIndexOptioninOptlist(opt, iOption);
		notifyAll();
	}
	
	public String[] GetSelection() {
		Scanner reader = new Scanner(System.in);
		for (int i=0; i<opset.size(); i++) {
			System.out.println(opset.get(i).getName());
			opset.get(i).getAllOption();
			System.out.println("Get a Choice: ");
			int n = reader.nextInt();
			setOptionChoice(opset.get(i).getName(),opset.get(i).getOptlist().get(n-1).getOptionname());
		}
		reader.close();
		System.out.print("You selection is "+ Arrays.toString(getChoicename()));
		String[] a = getChoicename();
		return a;
	}
	
	public synchronized void Print() {
		System.out.println(getName());
		System.out.println("Choice of OptionSet:");
		int i = 0;
		for (i = 0; i < opset.size() - 1; ++i) {
			System.out.print(opset.get(i).getName());
			System.out.print(" : ");
			for (int j = 0; j < opset.get(i).getOptlist().size(); j++) {
				opset.get(i).getOptlist().get(j).PrintOptionName();
				opset.get(i).getOptlist().get(j).PrintPrice();
				System.out.print(" ");
			}
			System.out.println(" ");
		}
		System.out.println("This is the current Choice: ");
		System.out.println(Arrays.toString(getChoicename()));
		System.out.print("Total Price: ");
		System.out.println(getTotalChoicePrice());
		notifyAll();
		
	}

}