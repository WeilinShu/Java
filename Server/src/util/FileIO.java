//Weilin Shu
//35B Project 2

package util;

import model.OptionSet;
import model.Option;
import model.Auto;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import exception.AutoException;

public class FileIO {

	private final String COMMA_DELIMITER = ",";

	// This method read the csv file and create Auto object
	public Auto ReadFile(String filename, Auto ato, String carname) {
		Auto FordWagonZTW = new Auto(carname);
		OptionSet[] Optlist = new OptionSet[1];
		Optlist[0] = new OptionSet();
		Option[][] Opt = new Option[1][1];
		Opt[0][0] = new Option();

		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			String lastOptionSetname = " ";
			int rowNumber = 0;
			int columnNumber = 1;
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					String[] stringline = line.split(COMMA_DELIMITER);
					if (stringline.length > 0) {
						OptionSet optset1 = new OptionSet(stringline[0]);
						if (stringline[0].equals("")) {
							try {
								throw new AutoException(1, "OptionSetName is empty!");
							} catch (AutoException e1) {
								e1.printmyproblem();
							}
						}

						if (stringline[1].equals("")) {
							try {
								throw new AutoException(2, "Missing Option name");
							} catch (AutoException e2) {
								stringline[1] = e2.fix(2).getline2();
							}
						}

						else if (stringline.length < 3) {
							try {
								throw new AutoException(3, "Missing Option price");
							} catch (AutoException e3) {
								stringline = addArray(stringline);
								stringline[2] = e3.fix(3).getline3();
							}
						}

						Option option1 = new Option(stringline[1], Float.parseFloat(stringline[2]));

						if (!stringline[0].equals(lastOptionSetname)) {
							Optlist = addOptionSet(Optlist, optset1);
							columnNumber = 1;
							Opt = addToColumn(Opt, rowNumber, columnNumber, option1, Optlist[rowNumber]);
							rowNumber++;
						} else {

							Opt = addToRow(Opt, rowNumber, columnNumber, option1, Optlist[rowNumber]);
							columnNumber++;
						}
						lastOptionSetname = stringline[0];
					} else {
						System.out.println("Cannot parse the line");
					}
				}
			}
			buff.close();
		} catch (FileNotFoundException f) {
			System.out.println("Custom exception: " + f.getMessage());
		} catch (IOException e) {
			System.out.println("Error --" + e.toString());
		}

		ArrayList<OptionSet> arrayList = new ArrayList<OptionSet>(Arrays.asList(Optlist));
		FordWagonZTW.setOpset(arrayList);

		for (int i = 0; i < Opt.length; ++i) {
			ArrayList<Option> array1 = new ArrayList<Option>(Arrays.asList(Opt[i]));
			FordWagonZTW.setOptionArray(array1, i);
		}

		return FordWagonZTW;
	}

	private String[] addArray(String[] array) {
		String[] newarray = new String[array.length + 1];
		for (int i = 0; i < array.length; i++)
			newarray[i] = new String();
		System.arraycopy(array, 0, newarray, 0, array.length);
		return newarray;

	}

	// This method increments the OptionSet array as more OptionSet gets add in
	private OptionSet[] addOptionSet(OptionSet[] oplist, OptionSet OptionToAdd) {
		OptionSet[] newOptionSet = new OptionSet[oplist.length + 1];
		for (int i = 0; i < newOptionSet.length; i++)
			newOptionSet[i] = new OptionSet();
		System.arraycopy(oplist, 0, newOptionSet, 0, oplist.length);
		newOptionSet[oplist.length - 1] = OptionToAdd;
		return newOptionSet;
	}

	// This method increments the number of row of a 2D Option array
	private Option[][] addToRow(Option[][] oparray, int rowNumber, int columnNumber, Option p, OptionSet plist) {
		int Largerstrow = 1;
		int i = 0;
		for (i = 0; i < rowNumber; i++) {
			int number = oparray[i].length;
			if (number > Largerstrow)
				Largerstrow = number;
		}
		Option[][] newOptionTable = new Option[rowNumber][Largerstrow + 1];

		for (int index = 0; index < rowNumber; index++) {
			for (int jndex = 0; jndex < Largerstrow + 1; jndex++)
				newOptionTable[index][jndex] = new Option();
		}

		int j = 0;
		for (j = 0; j < rowNumber; j++)
			System.arraycopy(oparray[j], 0, newOptionTable[j], 0, oparray[j].length);
		newOptionTable[rowNumber - 1][columnNumber] = p;
		return newOptionTable;
	}

	// This method increments the number of Column of a 2D Option array
	private Option[][] addToColumn(Option[][] oparray, int rowNumber, int columnNumber, Option p, OptionSet plist) {
		int Largerstrow = 1;
		int i = 0;
		for (i = 0; i < rowNumber; i++) {

			int number = oparray[i].length;
			if (number > Largerstrow)
				Largerstrow = number;
		}
		Option[][] newOptionTable = new Option[rowNumber + 1][Largerstrow];

		for (int index = 0; index < rowNumber + 1; index++) {
			for (int jndex = 0; jndex < Largerstrow; ++jndex)
				newOptionTable[index][jndex] = new Option();
		}

		int j = 0;
		for (j = 0; j < rowNumber; j++)
			System.arraycopy(oparray[j], 0, newOptionTable[j], 0, oparray[j].length);
		newOptionTable[rowNumber][0] = p;
		return newOptionTable;
	}

	// This method is used for serialization
	public void writeToFile(Auto Ato) {
		System.out.println("Serializating Object....");
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Auto.bin"));
			out.writeObject(Ato);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// This method is used for deserialization
	public Auto readToFile(String filename) {
		System.out.println("Deserializating Object....");
		System.out.println("Printing out new Object..");
		Auto Ato = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			try {
				Ato = (Auto) in.readObject();
				in.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Ato;

	}

	public Auto ReadPropertiesFile(String filename, Auto ato, String carname) {
		String fileType = "Property file";
		Auto A1 = new Auto(carname);
		try {
			Properties props = new Properties();
			
			FileInputStream in = new FileInputStream(filename);
			try {
				props.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String CarMake = props.getProperty("CarMake");
			if(!CarMake.equals(null))
			{
				String CarModel = props.getProperty("CarModel");
				float baseprice = Float.parseFloat(props.getProperty("CarBasePrice"));
				ArrayList<OptionSet> Optarray = new ArrayList<OptionSet>();	
				OptionSet OptionSet1 = new OptionSet(props.getProperty("Option1"));
				Optarray.add(OptionSet1);
				ArrayList<Option> array1 = new ArrayList<Option>();		
				Option OptionValue1a = new Option(props.getProperty("OptionValue1a"),Float.parseFloat(props.getProperty("OptionValue1aPrice")));
				Option OptionValue1b = new Option(props.getProperty("OptionValue1b"),Float.parseFloat(props.getProperty("OptionValue1bPrice")));
				Option OptionValue1c = new Option(props.getProperty("OptionValue1c"),Float.parseFloat(props.getProperty("OptionValue1cPrice")));
				Option OptionValue1d = new Option(props.getProperty("OptionValue1d"),Float.parseFloat(props.getProperty("OptionValue1dPrice")));
				Option OptionValue1e = new Option(props.getProperty("OptionValue1e"),Float.parseFloat(props.getProperty("OptionValue1ePrice")));
				Option OptionValue1f = new Option(props.getProperty("OptionValue1f"),Float.parseFloat(props.getProperty("OptionValue1fPrice")));
				Option OptionValue1g = new Option(props.getProperty("OptionValue1g"),Float.parseFloat(props.getProperty("OptionValue1gPrice")));
				Option OptionValue1h = new Option(props.getProperty("OptionValue1h"),Float.parseFloat(props.getProperty("OptionValue1hPrice")));
				Option OptionValue1i = new Option(props.getProperty("OptionValue1i"),Float.parseFloat(props.getProperty("OptionValue1iPrice")));
				Option OptionValue1j = new Option(props.getProperty("OptionValue1j"),Float.parseFloat(props.getProperty("OptionValue1jPrice")));
				array1.addAll(Arrays.asList(OptionValue1a,OptionValue1b,OptionValue1c,OptionValue1d,OptionValue1e,OptionValue1f,OptionValue1g,OptionValue1h,OptionValue1i,OptionValue1j)); 
				
				OptionSet OptionSet2 = new OptionSet(props.getProperty("Option2"));
				Optarray.add(OptionSet2);
				ArrayList<Option> array2 = new ArrayList<Option>();	
				Option OptionValue2a = new Option(props.getProperty("OptionValue2a"),Float.parseFloat(props.getProperty("OptionValue2aPrice")));
				Option OptionValue2b = new Option(props.getProperty("OptionValue2b"),Float.parseFloat(props.getProperty("OptionValue2bPrice")));
				array2.add(OptionValue2a);
				array2.add(OptionValue2b);
				
				OptionSet OptionSet3 = new OptionSet(props.getProperty("Option3"));
				Optarray.add(OptionSet3);
				ArrayList<Option> array3 = new ArrayList<Option>();	
				Option OptionValue3a = new Option(props.getProperty("OptionValue3a"),Float.parseFloat(props.getProperty("OptionValue3aPrice")));
				Option OptionValue3b = new Option(props.getProperty("OptionValue3b"),Float.parseFloat(props.getProperty("OptionValue3bPrice")));
				Option OptionValue3c = new Option(props.getProperty("OptionValue3c"),Float.parseFloat(props.getProperty("OptionValue3cPrice")));
				array3.add(OptionValue3a);
				array3.add(OptionValue3b);
				array3.add(OptionValue3c);
				
				OptionSet OptionSet4 = new OptionSet(props.getProperty("Option4"));
				Optarray.add(OptionSet4);
				ArrayList<Option> array4 = new ArrayList<Option>();	
				Option OptionValue4a = new Option(props.getProperty("OptionValue4a"),Float.parseFloat(props.getProperty("OptionValue4aPrice")));
				Option OptionValue4b = new Option(props.getProperty("OptionValue4b"),Float.parseFloat(props.getProperty("OptionValue4bPrice")));
				array4.add(OptionValue4a);
				array4.add(OptionValue4b);
				
				OptionSet OptionSet5 = new OptionSet(props.getProperty("Option5"));
				Optarray.add(OptionSet5);
				ArrayList<Option> array5 = new ArrayList<Option>();	
				Option OptionValue5a = new Option(props.getProperty("OptionValue5a"),Float.parseFloat(props.getProperty("OptionValue5aPrice")));
				Option OptionValue5b = new Option(props.getProperty("OptionValue5b"),Float.parseFloat(props.getProperty("OptionValue5bPrice")));
				array5.add(OptionValue5a);
				array5.add(OptionValue5b);
				
				A1.setOpset(Optarray);
				A1.setOptionArray(array1, 0);
				A1.setOptionArray(array2, 1);
				A1.setOptionArray(array3, 2);
				A1.setOptionArray(array4, 3);
				A1.setOptionArray(array5, 4);
     			A1.setMake(CarMake);
				A1.setModel(CarModel);
				A1.setPrice(baseprice);
				A1.setName(CarMake+CarModel);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		return A1;
	}
	
	public Auto ReadPropertiesFileUsingObject(Properties props) {
		String fileType = "Property file";
		Auto A1 = new Auto();
		String CarMake = props.getProperty("CarMake");
		//if(!CarMake.equals(null))
		//{
			String CarModel = props.getProperty("CarModel");
			float baseprice = Float.parseFloat(props.getProperty("CarBasePrice"));
			ArrayList<OptionSet> Optarray = new ArrayList<OptionSet>();	
			OptionSet OptionSet1 = new OptionSet(props.getProperty("Option1"));
			Optarray.add(OptionSet1);
			ArrayList<Option> array1 = new ArrayList<Option>();		
			Option OptionValue1a = new Option(props.getProperty("OptionValue1a"),Float.parseFloat(props.getProperty("OptionValue1aPrice")));
			Option OptionValue1b = new Option(props.getProperty("OptionValue1b"),Float.parseFloat(props.getProperty("OptionValue1bPrice")));
			Option OptionValue1c = new Option(props.getProperty("OptionValue1c"),Float.parseFloat(props.getProperty("OptionValue1cPrice")));
			Option OptionValue1d = new Option(props.getProperty("OptionValue1d"),Float.parseFloat(props.getProperty("OptionValue1dPrice")));
			Option OptionValue1e = new Option(props.getProperty("OptionValue1e"),Float.parseFloat(props.getProperty("OptionValue1ePrice")));
			Option OptionValue1f = new Option(props.getProperty("OptionValue1f"),Float.parseFloat(props.getProperty("OptionValue1fPrice")));
			Option OptionValue1g = new Option(props.getProperty("OptionValue1g"),Float.parseFloat(props.getProperty("OptionValue1gPrice")));
			Option OptionValue1h = new Option(props.getProperty("OptionValue1h"),Float.parseFloat(props.getProperty("OptionValue1hPrice")));
			Option OptionValue1i = new Option(props.getProperty("OptionValue1i"),Float.parseFloat(props.getProperty("OptionValue1iPrice")));
			Option OptionValue1j = new Option(props.getProperty("OptionValue1j"),Float.parseFloat(props.getProperty("OptionValue1jPrice")));
			array1.addAll(Arrays.asList(OptionValue1a,OptionValue1b,OptionValue1c,OptionValue1d,OptionValue1e,OptionValue1f,OptionValue1g,OptionValue1h,OptionValue1i,OptionValue1j)); 
			
			OptionSet OptionSet2 = new OptionSet(props.getProperty("Option2"));
			Optarray.add(OptionSet2);
			ArrayList<Option> array2 = new ArrayList<Option>();	
			Option OptionValue2a = new Option(props.getProperty("OptionValue2a"),Float.parseFloat(props.getProperty("OptionValue2aPrice")));
			Option OptionValue2b = new Option(props.getProperty("OptionValue2b"),Float.parseFloat(props.getProperty("OptionValue2bPrice")));
			array2.add(OptionValue2a);
			array2.add(OptionValue2b);
			
			OptionSet OptionSet3 = new OptionSet(props.getProperty("Option3"));
			Optarray.add(OptionSet3);
			ArrayList<Option> array3 = new ArrayList<Option>();	
			Option OptionValue3a = new Option(props.getProperty("OptionValue3a"),Float.parseFloat(props.getProperty("OptionValue3aPrice")));
			Option OptionValue3b = new Option(props.getProperty("OptionValue3b"),Float.parseFloat(props.getProperty("OptionValue3bPrice")));
			Option OptionValue3c = new Option(props.getProperty("OptionValue3c"),Float.parseFloat(props.getProperty("OptionValue3cPrice")));
			array3.add(OptionValue3a);
			array3.add(OptionValue3b);
			array3.add(OptionValue3c);
			
			OptionSet OptionSet4 = new OptionSet(props.getProperty("Option4"));
			Optarray.add(OptionSet4);
			ArrayList<Option> array4 = new ArrayList<Option>();	
			Option OptionValue4a = new Option(props.getProperty("OptionValue4a"),Float.parseFloat(props.getProperty("OptionValue4aPrice")));
			Option OptionValue4b = new Option(props.getProperty("OptionValue4b"),Float.parseFloat(props.getProperty("OptionValue4bPrice")));
			array4.add(OptionValue4a);
			array4.add(OptionValue4b);
			
			OptionSet OptionSet5 = new OptionSet(props.getProperty("Option5"));
			Optarray.add(OptionSet5);
			ArrayList<Option> array5 = new ArrayList<Option>();	
			Option OptionValue5a = new Option(props.getProperty("OptionValue5a"),Float.parseFloat(props.getProperty("OptionValue5aPrice")));
			Option OptionValue5b = new Option(props.getProperty("OptionValue5b"),Float.parseFloat(props.getProperty("OptionValue5bPrice")));
			array5.add(OptionValue5a);
			array5.add(OptionValue5b);
			
			A1.setOpset(Optarray);
			A1.setOptionArray(array1, 0);
			A1.setOptionArray(array2, 1);
			A1.setOptionArray(array3, 2);
			A1.setOptionArray(array4, 3);
			A1.setOptionArray(array5, 4);
			
			A1.setMake(CarMake);
			A1.setModel(CarModel);
			A1.setPrice(baseprice);
			A1.setName(CarMake+CarModel);
		//}
		return A1;
	}
}
