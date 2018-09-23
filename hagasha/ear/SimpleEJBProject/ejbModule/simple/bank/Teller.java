package simple.bank;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entity.BankAccount;
import com.entity.Owner;
import com.entity.PhoneNumber;
import com.entity.SavingsAccount;
import com.entity.exceptions.OwnerNotFoundException;
//import javax.ejb.TimerService;

/**
 * Session Bean implementation class Teller
// */

@Stateless(mappedName = "Teller",name="Teller"/*,beanInterface="TellerLocal.class"*/)

@LocalBean
//@EJB()
public class Teller implements TellerRemote, TellerLocal {
@Resource javax.ejb.TimerService timerService;
	@PersistenceContext(unitName = "BankAccountJPA")
	EntityManager em;

	/**
	 * Default constructor.
	 */

	public Teller() {
		// TODO Auto-generated constructor stub
	}

	public int createAccount(Owner owner, int balance) {
		BankAccount account = new BankAccount();
		account.setOwner(owner);
		account.setBalance(balance);
		em.persist(account);
		timerService.createTimer(10000, owner);
		return account.getId();

	}
@Timeout
public void Alarm(javax.ejb.Timer timer) {
	Owner o= (Owner) timer.getInfo();
	System.out.println("A new bank account was created belonging to "+o);
	// do notify via email
}
	@Override
	public BankAccount deposit(int id, int amount) {
		// TODO Auto-generated method stub
		BankAccount ba = em.find(BankAccount.class, id);
		ba.deposit(amount);
		return ba;
	}

	@Override
	public BankAccount withdraw(int id, int amount) {
		BankAccount ba = em.find(BankAccount.class, id);
		ba.withdraw(amount);
		return ba;
	}

	@Override
	public void closeAccount(int id) {
		BankAccount ba = em.find(BankAccount.class, id);
		em.remove(ba);
	}

	@Override
	public BankAccount findAccount(int id) {
		// TODO Auto-generated method stub
		return em.find(BankAccount.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BankAccount> listAllAccounts() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT a FROM BankAccount a");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BankAccount> findWithBalance(int amount) {
		// TODO Auto-generated method stub
		String statement = "SELECT ba FROM BankAccount ba WHERE ba.balance >= :amt ORDER BY ba.owner.name ASC";
		Query query = em.createQuery(statement).setParameter("amt", amount);
		return query.getResultList();
	}

	@Override
	public Owner addNumber(int id, PhoneNumber phoneNumber) throws OwnerNotFoundException {
		// TODO Auto-generated method stub
		Owner owner = em.find(Owner.class, id);
		if (owner == null) {
			throw new OwnerNotFoundException();
		}
		owner.addNumber(phoneNumber);
		em.persist(owner);
		return owner;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Interceptors({BankAccountInterceptor.class})
	public List<PhoneNumber> findNumbersForOwner(int id) {
		// TODO Auto-generated method stub
		String statement = "SELECT pn FROM Owner o Join o.phoneNumbers pn WHERE o.id = :id";
		Query query = em.createQuery(statement).setParameter("id", id);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BankAccount> findAccountsForAreaCode(int areaCode) {
		// TODO Auto-generated method stub
		String statement = "SELECT ba FROM BankAccount ba JOIN ba.owner o JOIN o.phoneNumbers pn WHERE pn.areaCode = :areaCode";
		Query query = em.createQuery(statement).setParameter("areaCode", areaCode);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhoneNumber> findNumbersForAmount(int amount) {
		// TODO Auto-generated method stub
		String statement = "SELECT pn FROM BankAccount ba JOIN ba.owner o JOIN o.phoneNumbers pn WHERE ba.balance>=:amt";
		Query query = em.createQuery(statement).setParameter("amt", amount);
		return query.getResultList();

	}

	@Override
	public Owner findOwner(int id) {
		// TODO Auto-generated method stub
		return em.find(BankAccount.class, id).getOwner();
	}

	@Override
	public int createSavingsAccount(Owner owner, int balance, int interestRate) {
		SavingsAccount account = new SavingsAccount();
		account.setBalance(balance);
		account.setInterestRate(interestRate);
		account.setOwner(owner);
		em.persist(account);
		return account.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SavingsAccount> listAllsAvingAccounts() {
		Query query = em.createQuery("SELECT a from SavingsAccount a");
		return query.getResultList();
	}

	@Override
	public long getTotalFunds() {
	String statement = "SELECT SUM(ba.balance) FROM BankAccount ba";
	Query query = em.createQuery(statement);
	Long result = (Long)query.getSingleResult();
	try {
		Thread.sleep(30000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result.longValue();
	}
	


}
