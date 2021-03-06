
package client;

import java.net.*;
import java.io.*;
import java.util.*;

import model.Auto;
import adapter.BuildAuto;
import adapter.Debuggable;
import java.util.*;

public class DefaultSocketClient extends Thread implements Debuggable {

	////////// PROPERTIES //////////
	private boolean DEBUG = true;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private BufferedReader stdIn;
	private Socket sock;
	private String strHost;
	private int iPort;
	private CarModelOptionsIO clientFTP;
	static SelectCarOptions clientProtocol;
	private ArrayList<String> Models = new ArrayList<String>();
	private clientserver c1 = new BuildAuto();

	////////// CONSTRUCTORS //////////

	public DefaultSocketClient(String strHost, int iPort) {
		this.strHost = strHost;
		this.iPort = iPort;
	}

	////////// INSTANCE METHODS //////////

	public void establishConnection() {
		try {
			if (DEBUG)
				System.out.println("Connecting to host ... ");
			this.sock = new Socket(strHost, iPort);

			if (DEBUG)
				System.out.println("Connected to host, creating object streams ... ");
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			stdIn = new BufferedReader(new InputStreamReader (System.in));

			clientFTP = new CarModelOptionsIO();
			clientProtocol = new SelectCarOptions();
		}
		catch (IOException e) {
			System.err.println("Error obtaining I/O for connection to host ... ");
			System.exit(1);
		}
	}

	public void handleConnection() {
		Object fromServer, toServer = null;
		

		try {
			if (DEBUG)
				System.out.println("Communicating with host ... ");

			while ((fromServer = in.readObject()) != null) {
				if (DEBUG)
					System.out.println("Received server response ... ");
				System.out.println(fromServer.toString());

				System.out.println("Response to server: ");
				
				if (clientProtocol.isAutomobile(fromServer))
				{		
					System.out.print("Im at this location "+ clientProtocol.getModel(fromServer));
					for (int i = 0; i < ((ArrayList<Auto>)fromServer).size(); i++) {
						c1.AddtoHashMap(((ArrayList<Auto>)fromServer).get(i));
					}
					//clientProtocol.configureAuto(fromServer);	
				}
				
				toServer = stdIn.readLine();
				
				if (toServer.toString().contains(".prop")) {
					toServer = clientFTP.loadPropsFile(toServer.toString());
				}
				if (toServer.toString().contains(".txt")) {
					toServer = clientFTP.loadTextFile(toServer.toString());
				}


				if (DEBUG)
					System.out.println("Sending " + toServer + " to server ... ");
				sendOutput(toServer);
				System.out.println("");

				if (toServer.equals("0")) {
					break;
				}
			}

			if (DEBUG)
				System.out.println("Closing client input stream ... ");
			in.close();

		}
		catch (ClassNotFoundException e) {
			System.err.println("Error in downloaded object, object may be corrupted ... ");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Error in I/O stream ... ");
			System.exit(1);
		}

	}

	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		}
		catch (IOException e) {
			System.err.println("Error in I/O stream while sending object to host ... ");
			System.exit(1);
		}
	}
	
	public String getModels(){
		String listString = String.join(", ", Models);
		System.out.print("This is the string of list "+ listString);
		return listString;
	}

	@Override
	public void run() {
		establishConnection();
		handleConnection();
		try {
			if (DEBUG)
				System.out.println("Closing client output stream ... ");
			out.close();

			if (DEBUG)
				System.out.println("Closing client console input stream ... ");
			stdIn.close();

			if (DEBUG)
				System.out.println("Closing client socket ... ");
			sock.close();
		}
		catch (IOException e) {
			System.err.println("Error ending client connection ... ");
			System.exit(1);
		}
	}

}
