package simple.stateless;

import javax.ejb.Remote;

@Remote
public interface GreetingRemote {
	public String sayHello();
}
