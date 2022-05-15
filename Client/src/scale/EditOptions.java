//Weilin Shu

package scale;
import adapter.ProxyAutomobile;

public class EditOptions extends ProxyAutomobile implements Runnable { //create a  helper class
	private Thread t;
	private EditHelper h; //lhm
	
	private String [] auto;
	
	private int operationNo;  // # operation
	private int threadNo; // synchronized or not sync-d thread
	
	public EditOptions (int threadNo, int operationNo, String [] auto) {
		this.threadNo = threadNo;
		this.operationNo = operationNo;
		this.auto=auto.clone();
		h = new EditHelper();
		t = new Thread(this);
		//System.out.print(this.auto[0] + " " + this.auto[1] + "  <====== Checking array here\n");
		//run();
	}
	
	
	public void start () {
		t.start();
	}
	
	public void run () {
		// convert thr code so the setter adds the value and getter gets a new value. Work with 2 thread at a time (increase the sleep time)
				
				System.out.println("Start thread #" + threadNo + " Operation #" + operationNo);
					
				ops(); // calls certain operation
				System.out.println("Stopping thread " + threadNo);
	}
	
	
	public void ops() {
		switch (operationNo%2) {
		case 1: 
			System.out.printf("Switch statement for synch \n");
			h.updateOptionSetNameSync(this.auto);
			break;
		case 0:
			System.out.printf("Switch statement for unsynch. OperationNo: "+ operationNo + "\n");
		
			if (operationNo==4) { // this if-statement is for one of the unsynchronized methods to be put to sleep
				try 	{
					System.out.printf("Sleeping. . .  \n");
					Thread.sleep(1000);  
				} 
				catch (InterruptedException e) 	{
					System.out.printf("Get done sleeping \n");
				}
			}
			
			h.updateOptionSetNameUnsync(this.auto);	// no sleep
			break;

		}
	}
}
