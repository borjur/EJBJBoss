package simple.bank;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entity.BankAccount;
import com.entity.Owner;
import com.entity.PhoneNumber;
import com.entity.SavingsAccount;
import com.entity.exceptions.OwnerNotFoundException;

/**
 * Session Bean implementation class Teller
 */
@Stateless(mappedName = "Teller")
public class Teller implements TellerRemote, TellerLocal {

	@Resource javax.ejb.TimerService timerService;
	
	@Override
	public void closeAccount(int id) {
		BankAccount ba = em.find(BankAccount.class, id);
		em.remove(ba);
	}

	@Override
	public BankAccount deposit(int id, int amount) {
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

	@PersistenceContext(unitName = "BankAccountJPA")
	EntityManager em;

	public int createAccount(Owner owner, int balance) {
		BankAccount account = new BankAccount();
		account.setBalance(balance);
		account.setOwner(owner);
		em.persist(account);
		timerService.createTimer(10000, owner);
		return account.getId();
	}

	/**
	 * Default constructor.
	 */
	public Teller() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BankAccount findAccount(int id) {
		return em.find(BankAccount.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<BankAccount> findWithBalance(int amount) {
		String statement = "SELECT ba FROM BankAccount ba WHERE ba.balance "
				+ ">= :amt ORDER BY ba.owner.name ASC";
		Query query = em.createQuery(statement).setParameter("amt", amount);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<BankAccount> listAllAccounts() {
		Query query = em.createQuery("SELECT a FROM BankAccount a");
		return query.getResultList();
	}

	@Override
	public Owner addNumber(int id, PhoneNumber phoneNumber)
			throws OwnerNotFoundException {
		Owner owner = em.find(Owner.class, id);
		if (owner == null) {
			throw new OwnerNotFoundException();
		}
		owner.addNumber(phoneNumber);
		em.persist(owner);
		return owner;
	}

	@SuppressWarnings("unchecked")
	@Interceptors ({BankAccountInterceptor.class})
	public List<PhoneNumber> findNumbersForOwner(int id) {
		String statement = "SELECT pn " + "FROM Owner o "
				+ "JOIN o.phoneNumbers pn " + "WHERE o.id = :id";
		Query query = em.createQuery(statement).setParameter("id", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<BankAccount> findAccountsForAreaCode(int areaCode) {
		String statement = "SELECT ba " + "FROM BankAccount ba "
				+ "JOIN ba.owner o " + "JOIN o.phoneNumbers pn "
				+ "WHERE pn.areaCode = :areaCode";
		Query query = em.createQuery(statement).setParameter("areaCode",
				areaCode);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PhoneNumber> findNumbersForAmount(int amount) {
		String statement = "SELECT pn " + "FROM BankAccount ba "
				+ "JOIN ba.owner o " + "JOIN o.phoneNumbers pn "
				+ "WHERE ba.balance >= :amt";
		Query query = em.createQuery(statement).setParameter("amt", amount);
		return query.getResultList();
	}

	@Override
	public Owner findOwner(int id) {
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
	public List<SavingsAccount> listAllSavingsAccounts() {
		Query query = em.createQuery("SELECT a from SavingsAccount a");
		return query.getResultList();
	}

	@Override
	public long getTotalFunds() {
		String statement = "SELECT SUM(ba.balance) FROM BankAccount ba";
		Query query = em.createQuery(statement);
		Long result = (Long) query.getSingleResult();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result.longValue();
	}

	@Timeout
	public void alarm(javax.ejb.Timer timer) {
	  Owner o = (Owner) timer.getInfo();
	  System.out.println("A new bank account was created belonging to " + o);
	  //Notify manager via e-mail...
	}
		
}
