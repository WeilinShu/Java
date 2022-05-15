//Weilin Shu
//35B Project 4

package driver;
import scale.EditOptions;
import adapter.*;
import client.DefaultSocketClient;

public class Project5ClientDriver {
	static DefaultSocketClient C1 = new DefaultSocketClient("192.168.0.108",4444);
	public static void main(String[] args) {
		
		C1.start();
	}
	
	public static DefaultSocketClient getC1(){
		return C1;
	}

	public static void main(String[] args) {
	System.out.printf("\n     ============= ASSIGNMENT 1 =============\n");

	String file = ("FordWagonZTW.txt");
	
	try {	
		Automobile auto = new Automobile();
		FileIO f1 = new FileIO(file);
		auto = f1.readFile(auto);
		f1.serialize(auto);
		auto = f1.deserialize();
	} catch (Exception e) {
		System.out.println("Error -- " + e.toString());
	}	
		System.out.printf("\n\n   ~~~~~~~~~~~~~~ End of the program! ~~~~~~~~~~~~~\n");
	}
		
	}

