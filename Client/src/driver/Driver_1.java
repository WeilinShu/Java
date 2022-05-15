//Weilin Shu
//Assignment6


package driver;
import adapter.*;
import exception.AutoException;

public class Driver_1 {

	public static void main(String[] args) throws AutoException{
		// TODO Auto-generated method stub
		System.out.printf("\n     ============= ASSIGNMENT 2 =============\n");
		System.out.printf("\n     =============    Part 1    =============\n");
		
		String file = ("FordWagonZTW.txt");
		String modelname = "Ford Wagon ZTW";	// Automobile name
		CreateAuto automobile = new BuildAuto();
		automobile.BuildAuto(file);
		automobile.printAuto(modelname);
		
		// we cant call updateOptionSetName for automobile because this method
		// is undefined for type CreateAuto.
		// we create a new automobile object of the type UpdateAuto
		// but its going to be the same because we made it static.
		
		UpdateAuto automobile1 = new BuildAuto();
		
		System.out.printf("\n=====================================\n");
		System.out.printf("\nUpdating the names of some options...\n");
		automobile1.updateOptionSetName(modelname, "Color", "Color Options");
		automobile1.updateOptionSetName(modelname, "Brakes", "Brake Options");
		automobile1.updateOptionSetName(modelname, "Power Moonroof", "Power Moonroof Options");
		
		System.out.printf("\n=====================================\n");
		
		System.out.printf("\nUpdating the price of some options...\n");
		System.out.printf("\n=====================================\n");
		automobile1.updateOptionPrice(modelname, "Color Options", "Gold Clearcoat Metallic", 300);
		automobile1.updateOptionPrice(modelname, "Color Options", "Infra-Red Clearcoat", 200);
		automobile1.updateOptionPrice(modelname, "Color Options", "Twilight Blue Clearcoat Metallic", 150);
		// print everything again to see if all 
		// the methods for UpdateAuto worked on automobile
		automobile.printAuto(modelname);
		
		// FixAuto a1 = new fix(0, "Something");
		
		
		
		System.out.printf("\n     ============= Part 2 =============\n");
		
		System.out.printf("\n This part of the program is created only for testing purposes!\n");
		
		/// This part of the program is created only for testing purposes! 
		
		System.out.printf("\n Reading an array of files with mistakes. . . \n");
		String [] filename = {"1.txt",
					"2.txt",
					"3.txt",
					"4.txt",
					"5.txt"};
		
		for (int i =0; i<5; i++) {
			System.out.printf("\n=====================================");
			System.out.printf("\n Reading file # "+ (i+1) + "\n");
			System.out.printf("=====================================\n");
			CreateAuto a1 = new BuildAuto();
			a1.BuildAuto(filename[i]);
			a1.printAuto(modelname);
		}
		
		
		
		System.out.printf("\n     ============= End of program =============\n");
		
	}

}
