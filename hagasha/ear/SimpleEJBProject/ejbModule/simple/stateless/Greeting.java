package simple.stateless;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Greeting
 */
@Stateless(mappedName="GreetingBean")
@LocalBean
public class Greeting implements GreetingRemote {

    /**
     * Default constructor. 
     */
    public Greeting() {
        // 
    }

	@Override
	public String sayHello() {
		// TODO Auto-generated method stub
		return "Hello World!";
	}

}
