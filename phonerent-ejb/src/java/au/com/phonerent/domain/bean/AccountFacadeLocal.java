package au.com.phonerent.domain.bean;

import au.com.phonerent.domain.Account;
import au.com.phonerent.domain.CreditCard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TOSHIBA
 */
@Local
public interface AccountFacadeLocal {

    /**
     * create an account entity and persist into database
     * @param account an account object 
     */
    void create(Account account);

    /**
     * update an account entity
     * @param account an account to be update
     */
    void edit(Account account);

    /**
     * remove an account entity
     * @param account an account to be removed
     */
    void remove(Account account);

    /**
     * find and account in the database
     * @param id the id of the account
     * @return the account object find, null if not found
     */
    Account find(Object id);

    /**
     * find all the accounts
     * @return a list of accounts
     */
    List<Account> findAll();

    /**
     * get a range of account
     * @param range indexes of the account to get
     * @return a list of account
     */
    List<Account> findRange(int[] range);

    /**
     * the number of accounts
     * @return count of the account
     */
    int count();
    
    /**
     * create a new sign up user, initial some require fields
     * @param account account object with initial user input
     */
    void signUp(Account account);    
    
    /**
     * find the account via user's email
     * @param email the email binded to the account
     * @return the account found, null if not found
     */
    Account findByEmail(String email);
    
    /**
     * find the account via the password reset id
     * @param resetId the password reset id binding to the account
     * @return the account found, null if not found
     */
    Account findByPasswordResetId(String resetId);
    
    /**
     * whether an account is activated or not
     * @param email the email of the account
     * @return is activate or not/ true false
     */
    boolean isActivate(String email);
    
    /**
     * send out the password recovery link via user's email and create a password reset id
     * @param email email to be sent to and the account's email
     */
    void sendPasswordRecovery(String email);
    
    /**
     * reset the account's password
     * @param account the account to be reset
     * @param newPassword the new password
     */
    void resetPassword(Account account, String newPassword);
    
    /**
     * confirm the registration of the account
     * @param email the email of the account
     * @return true if found the account being registered
     */
    boolean confirmRegistration(String email);
}
