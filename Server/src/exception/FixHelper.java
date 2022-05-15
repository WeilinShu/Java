//Weilin Shu
//35B Project 2

package exception;

public class FixHelper {

	
	protected void fix(int errorno)
	{
		if (errorno == 2)
				fixProblem2();
		
		else if (errorno == 3)
				fixProblem3();
	}

	
	private String line2;
	protected void fixProblem2 ()
	{
		System.out.println("Fixing problem 2");
		line2 = "Unknown Option";
	}
	public String getline2() {
		return line2;
	}
	
	private String line3;
	protected void fixProblem3 ()
	{
		System.out.println("Fixing problem 3... setting unknown price to 0");
		line3 = "0";
	}
	public String getline3() {
		return line3;
	}
	
	
}
