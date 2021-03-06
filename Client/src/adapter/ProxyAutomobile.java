//Weilin Shu
//35B Project 4


package adapter;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import exception.AutoException;
import model.Auto;
import util.FileIO;

import java.util.Iterator;


public abstract class ProxyAutomobile {
	
	private static LinkedHashMap<String,Auto>  carmap= new LinkedHashMap<String,Auto>();
	private static Auto a1 = new Auto( );


	private AutoException e1;
	
	public ProxyAutomobile(){
		
	}

	public synchronized void UpdateOptionSetName(String Modelname, String OptionSetname, String newName) {	
		carmap.get(Modelname).updateOptionSetName(OptionSetname, newName);
		notifyAll();
	}

	public synchronized void UpdateOptionPrice(String Modelname, String OptionSetname, String Option, float newprice) {
		carmap.get(Modelname).updateOptionPrice(Modelname, OptionSetname, Option, newprice);
		notifyAll();
	}

	public synchronized void BuildAuto(Properties props) {
		FileIO IO = new FileIO();
		//if (fileType == "CSV file")
		//a1 = IO.ReadFile(filename, a1, carname);
		//else if (fileType == "Properties鈥� 鈥媐ile")
		a1 = IO.ReadPropertiesFileUsingObject(props);
		System.out.println(a1.getName());
		carmap.put(a1.getName(),a1);
		System.out.println("Putting the things");
		notifyAll();

	}

	public synchronized void printAuto(String Modelname) {
		carmap.get(Modelname).Print();
		notifyAll();
	}
	
	public void fix(int error) {
		e1.fix(error);
		
	}
	
	public synchronized void setChoice(String Modelname,String OptionSet, String Option) {
		carmap.get(Modelname).setOptionChoice(OptionSet, Option);
		notifyAll();
	}
	
	public synchronized void EditOption(String Modelname,String OptionSet, String OldOption, String NewOption) {
		carmap.get(Modelname).updateOptionName(Modelname, OptionSet, OldOption, NewOption);
		notifyAll();
	}
	
	public synchronized LinkedHashMap<String, Auto> getcarmap() {
		notifyAll();
		return carmap;
	}

	public String getAllModels() {
		System.out.print(carmap.size());
		System.out.print(carmap.keySet());
		return carmap.keySet().toString();
	}
	
	public void AddtoHashMap(Auto a) {
		a1 = a;
		carmap.put(a1.getName(),a1);
	}
	
	public void serve(int port) {
		
		
	}
	
	public String getModel(){
		Set<String> model = carmap.keySet();
		System.out.print("Im at here location 1 "+model.toString());
		String joined = String.join(",", model);
		System.out.print("Im at here location 2 "+joined);
		return joined;
	}
	
	public String getOption(){
		return null;
	}
	
}
