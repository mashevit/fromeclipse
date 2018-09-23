package simple.bank;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;

import com.entity.BankAccount;
import com.entity.Owner;
import com.entity.PhoneNumber;
import com.entity.SavingsAccount;
import com.entity.exceptions.OwnerNotFoundException;

@Local
@EJB(beanName = "Teller",name="TellerLocal",beanInterface=TellerLocal.class)
public interface TellerLocal {

	List<BankAccount> findWithBalance(int amount);

	List<BankAccount> listAllAccounts();

	BankAccount findAccount(int id);

	void closeAccount(int id);

	BankAccount withdraw(int id, int amount);

	BankAccount deposit(int id, int amount);

	int createAccount(Owner owner, int balance);

	public Owner addNumber(int id, PhoneNumber phoneNumber) throws OwnerNotFoundException;

	public List<PhoneNumber> findNumbersForOwner(int id);

	public List<BankAccount> findAccountsForAreaCode(int areaCode);

	public List<PhoneNumber> findNumbersForAmount(int amount);

	public Owner findOwner(int id);

	public int createSavingsAccount(Owner owner, int balance, int interestRate);

	public List<SavingsAccount> listAllsAvingAccounts();

	public long getTotalFunds();
}
