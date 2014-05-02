package simple.bank;

import java.util.List;

import javax.ejb.Local;

import com.entity.BankAccount;
import com.entity.Owner;
import com.entity.PhoneNumber;
import com.entity.SavingsAccount;
import com.entity.exceptions.OwnerNotFoundException;

@Local
public interface TellerLocal {

	public abstract List<BankAccount> listAllAccounts();

	public abstract List<BankAccount> findWithBalance(int amount);

	public abstract BankAccount findAccount(int id);

	public abstract int createAccount(Owner owner, int balance);

	public abstract BankAccount withdraw(int id, int amount);

	public abstract BankAccount deposit(int id, int amount);

	public abstract void closeAccount(int id);

	public Owner addNumber(int id, PhoneNumber phoneNumber)
			throws OwnerNotFoundException;

	public List<PhoneNumber> findNumbersForOwner(int id);

	public List<BankAccount> findAccountsForAreaCode(int areaCode);

	public List<PhoneNumber> findNumbersForAmount(int amount);

	public Owner findOwner(int id);

	public int createSavingsAccount(Owner owner, int balance, int interestRate);

	public List<SavingsAccount> listAllSavingsAccounts();

	public long getTotalFunds();
}
