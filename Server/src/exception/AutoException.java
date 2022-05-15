//Weilin Shu
//35B Project 2

package exception;

public class AutoException extends Exception {
	
	private int error;
	private String errormsg;
	
	
	public AutoException() {
		super();
		printmyproblem();
	}
	
	public AutoException(String errormsg) {
		super();
		this.errormsg = errormsg;
		printmyproblem();
	}

	public AutoException(int error) {
		super();
		this.error = error;
		printmyproblem();
	}
	
	public AutoException(int error, String errormsg) {
		super();
		this.error = error;
		this.errormsg = errormsg;
		printmyproblem();
	}
	
	public int getError() {
		return error;
	}
	
	public void setError(int errorno) {
		this.error = errorno;
	}
	
	public String getErrormsg() {
		return errormsg;
	}
	
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	public void printmyproblem() {
		System.out.println("FixProblems [error= #" + error + ", errormsg=" + errormsg); 
	}
	
	
	public FixHelper fix(int error)
	{
		FixHelper f1 = new FixHelper();
		switch(error)
		{
		    case 1:
			f1.fix(1);
			break;
			
		    case 2:
		    f1.fix(2);
		    break;
		    
		    case 3:
			f1.fix(3);
			break;
			
		    case 4:
			f1.fix(4);
			break;
			
		    case 5:
			f1.fix(5);
			break;
				
				
		    
		}
		return f1;
	}
}
