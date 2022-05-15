package scale;

import adapter.ProxyAutomobile;

public class EditHelper extends ProxyAutomobile {
	
	public synchronized void updateOptionSetNameSync(String [] auto) {
		System.out.printf("Entering synchronized method \n");


		try {
			updateOptionSetName(auto[0], auto[1], auto[2]);
			System.out.printf("Option set name changed in synchronized method \n");
		} catch (Exception e) {
			System.out.printf("Option set name to be changed, not found\n");
		}
	}

	public void updateOptionSetNameUnsync(String[] auto) {
		System.out.printf("Entering unsynchronized method \n");

		try {
			updateOptionSetName(auto[0], auto[1], auto[2]);
			System.out.printf("Option set name changed in unsynchronized method\n");
		} catch (Exception e) {
			System.out.printf("Option set name to be changed, not found\n");
		}
	}
	
	
