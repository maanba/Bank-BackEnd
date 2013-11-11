/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.DTOAccount;
import dto.DTOPersonDetail;
import dto.DTOUser;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Mads
 */
public class Assembler {

    public static DTOAccount AccountObjectToDTOAccount(Accounts acc) {
        DTOAccount newAcc =
                new DTOAccount(
                acc.getAccountType(),
                acc.getAccountNumber(),
                acc.getInterest(),
                acc.getBalance().longValue(),
                acc.getCreated());

        return newAcc;
    }

    public static ArrayList<DTOAccount> accountObjectsToDTOAccounts(Collection<Accounts> accounts) {
        ArrayList<DTOAccount> result = new ArrayList<>();
        for (Accounts acc : accounts) {
            result.add(Assembler.AccountObjectToDTOAccount(acc));
        }
        return result;
    }

    public static DTOUser userObjectToDTOUser(Users user) {
        DTOUser result = new DTOUser(user.getUsername(), user.getPassword(), user.getTitle().getTitle());
        return result;
    }

    public static ArrayList<DTOUser> userObjectsToDTOUsers(Collection<Users> users) {
        ArrayList<DTOUser> result = new ArrayList<>();
        for (Users user : users) {
            result.add(Assembler.userObjectToDTOUser(user));
        }
        return result;
    }

    public static DTOPersonDetail PersonObjectToDTOPersonDetail(Persons person) {
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String email = person.getEmail();
        String street = person.getStreet();
        int zip = person.getZip();
        String city = person.getCity();
        int phoneNumber = person.getPhonenumber();
        System.out.println("Getting accounts from Persons object");
        Collection<DTOAccount> accounts = Assembler.accountObjectsToDTOAccounts(person.getAccountsCollection());
        System.out.println("Getting users from Persons object");
        Collection<DTOUser> users = Assembler.userObjectsToDTOUsers(person.getUsersCollection());

        System.out.println("Returnerer nyt objekt");

        DTOPersonDetail result = new DTOPersonDetail(
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getStreet(),
                person.getZip(),
                person.getCity(),
                person.getPhonenumber(),
                Assembler.accountObjectsToDTOAccounts(person.getAccountsCollection()),
                Assembler.userObjectsToDTOUsers(person.getUsersCollection()));
        System.out.println("NU RETURNERER JEG !!!");
        return result;
    }
}
