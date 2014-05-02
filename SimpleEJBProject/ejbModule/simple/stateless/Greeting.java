package simple.stateless;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class Greeting
 */
@Stateless(mappedName="GreetingBean")
public class Greeting implements GreetingRemote {

    /**
     * Default constructor. 
     */
    public Greeting() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String sayHello() {
		return "Hello World!";
	}

}
