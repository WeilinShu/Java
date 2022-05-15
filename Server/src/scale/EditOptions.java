//Weilin Shu

package scale;
import adapter.BuildAuto;
import adapter.EditOptionsInterface;


public class EditOptions extends Thread  {
	
	BuildAuto ato;
	String Modelname;
	String OptionSetname;
	String OldOptionName;
	String NewOptionName;
	int threadnumber;
	EditOptionsInterface a1 = new BuildAuto();
	
	public EditOptions() {
	}
	
	public EditOptions(BuildAuto a1) {
		ato = a1;
	}
	
	public EditOptions(BuildAuto a1,int threadnumber, String Modelname, String OptionSetname, String OldOptionName, String NewOptionName) {
		ato = a1;
		this.Modelname = Modelname;
		this.OptionSetname= OptionSetname;
		this.OldOptionName=OldOptionName;
		this.NewOptionName=NewOptionName;
		this.threadnumber = threadnumber;
	}
	
	
	public void run()
	{
		System.out.println("Thread "+ threadnumber +" is updating...");
		a1.EditOption(Modelname,OptionSetname,OldOptionName,NewOptionName);
	}
}
