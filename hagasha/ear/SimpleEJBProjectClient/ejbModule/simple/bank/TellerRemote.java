package simple.bank;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;

import com.entity.BankAccount;
import com.entity.Owner;

@Remote
@EJB(beanName="Teller",name="TellerRemote",beanInterface=TellerRemote.class)
public interface TellerRemote {

	public int createAccount(Owner owner, int balance);

	public BankAccount deposit(int id, int amount);

	public BankAccount withdraw(int id, int amount);

	public void closeAccount(int id);
	
	public BankAccount findAccount(int id);
	public List<BankAccount> listAllAccounts();
	public List<BankAccount> findWithBalance(int amount);
}
