//	 Provide a list of available models to the client.
//	 2. Send the object (using Serialization) to the client, upon selection of an Automobile
package adapter;

public interface ServableAuto {

	public void serve(int port);

}