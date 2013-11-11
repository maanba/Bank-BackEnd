/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.DTOAccount;
import dto.DTOPerson;
import dto.DTOPersonDetail;
import dto.DTOUser;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Mads
 */
public class Assembler {

    public static DTOAccount AccountObjectToDTOAccount(Account acc) {
        DTOAccount newAcc =
                new DTOAccount(
                acc.getAccountType(),
                acc.getAccountNumber(),
                acc.getInterest(),
                acc.getBalance().longValue(),
                acc.getCreated());

        return newAcc;
    }

    public static ArrayList<DTOAccount> accountObjectsToDTOAccounts(Collection<Account> accounts) {
        ArrayList<DTOAccount> result = new ArrayList<>();
        for (Account acc : accounts) {
            result.add(Assembler.AccountObjectToDTOAccount(acc));
        }
        return result;
    }

    public static DTOUser userObjectToDTOUser(User user) {
        DTOUser result = new DTOUser(user.getUsername(), user.getPassword(), user.getTitle().getTitle());
        return result;
    }

    public static ArrayList<DTOUser> userObjectsToDTOUsers(Collection<User> users) {
        ArrayList<DTOUser> result = new ArrayList<>();
        for (User user : users) {
            result.add(Assembler.userObjectToDTOUser(user));
        }
        return result;
    }
    
    public static DTOPerson PersonObjectsToDTOPerson(Collection<Person> persons){
        ArrayList<DTOPerson> result = new ArrayList<>();
        for (Person person : persons) {
            result.add(Assembler.userObjectToDTOUser(user));
        }
        return result;
    }
    
    public static DTOPersonDetail PersonObjectToDTOPersonDetail(Person person) {
        DTOPersonDetail result = new DTOPersonDetail(
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getStreet(),
                person.getZip(),
                person.getCity(),
                person.getPhonenumber(),
                Assembler.accountObjectsToDTOAccounts(person.getAccountCollection()),
                Assembler.userObjectsToDTOUsers(person.getUserCollection()));
        result.setId(person.getPersonId());
        return result;
    }
}

