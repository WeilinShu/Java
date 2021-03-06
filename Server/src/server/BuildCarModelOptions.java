package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.io.StringReader;

import adapter.*;
import model.Auto;
import util.FileIO;

public class BuildCarModelOptions extends ProxyAutomobile {

	////////// PROPERTIES //////////

	private static final int WAITING = 0;
	private static final int REQUEST_BUILD_AUTO = 1;
	private static final int REQUEST_CONFIGURE_AUTO = 2;
	

	private int state = WAITING;

	////////// CONSTRUCTORS //////////

	public BuildCarModelOptions() {

	}

	////////// INSTANCE METHODS //////////

	public Object processRequest(Object obj) {
		Object toClient = null;

		System.out.print("I got the file here");
		
		if (state == REQUEST_BUILD_AUTO) {
			FileIO io = new FileIO();
			Properties props = new Properties();
			//CreateAuto a1 = new BuildAuto();
			try {			
				Auto ato = new Auto();
				props.load(new StringReader(obj.toString()));
				ato = io.ReadPropertiesFileUsingObject(props);
				AutoServer as1 = new BuildAuto();
				as1.AddtoHashMap(ato);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			toClient = "Automobile object successfully added to database\n"
					+ "Press any key to return to main menu";
		}
		else if (state == REQUEST_CONFIGURE_AUTO) {
			AutoServer as1 = new BuildAuto();
			System.out.println(Integer.valueOf(obj.toString()));
			toClient = as1.sendObject();
			System.out.print(toClient);
			
		}
		else {

		}

		this.state = WAITING;

		return toClient;
	}

	public String setRequest(int i) {
		String output = null;

		if (i == 1) {
			this.state = REQUEST_BUILD_AUTO;
			output = "Upload a file to create an Automobile";
		}
		else if (i == 2) {
			this.state = REQUEST_CONFIGURE_AUTO;
			output = "Select an Automobile from the following list to configure: \n" +
					super.getAllModels();
		}
		else {
			output = "Invalid request";
		}

		return output;
	}

}
