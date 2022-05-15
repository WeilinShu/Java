//Message.java
package testPackage;

import javax.servlet.annotation.WebServlet;

@WebServlet("/P16")
public class Message
{
  public String msg() {
    return "Hello from JSP!";
  }

  public Message() 
  {
  }
}
