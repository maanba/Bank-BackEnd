/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.DTOAccount;
import dto.DTOPerson;
import dto.DTOPersonDetail;
import dto.DTOTransaction;
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
                acc.getAccountType().getAccountType(),
                acc.getAccountNumber(),
                acc.getInterest(),
                acc.getBalance(),
                acc.getCreated());

        return newAcc;
    }

    public static DTOAccount AccountObjectToDTOAccountDetail(Account acc) {
        DTOAccount newAcc =
                new DTOAccount(
                acc.getAccountType().getAccountType(),
                acc.getAccountNumber(),
                acc.getInterest(),
                acc.getBalance(),
                acc.getCreated());


        // Her fejler der muligvis noget.. 
        newAcc.setTransactions(Assembler.transactionsToDTOTransactions(acc.getTransactionCollection(), acc));
        newAcc.getTransactions().addAll(Assembler.transactionsToDTOTransactions(acc.getTransactionCollection1(), acc));

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

    public static DTOPerson personObjectToDtoPerson(Person p) {
        DTOPerson dtop = new DTOPerson(
                p.getFirstName(),
                p.getLastName(),
                p.getEmail(),
                p.getStreet(),
                p.getZip(),
                p.getCity(),
                p.getPhonenumber());
        dtop.setId(p.getPersonId());
        return dtop;
    }
    public static Person DTOPersonObjectToDtoPerson(DTOPerson dtop) {
        Person p = new Person();
                p.setFirstName(dtop.getFirstName());
                p.setLastName(dtop.getLastName());
                p.setEmail(dtop.getEmail());
                p.setStreet(dtop.getStreet());
                p.setZip(dtop.getZip());
                p.setCity(dtop.getCity());
                p.setPhonenumber(dtop.getPhonenumber());
        return p;
    }
    
        public static Person savePersonObjectToDtoPerson(DTOPerson dtop, Person p) {
                p.setFirstName(dtop.getFirstName());
                p.setLastName(dtop.getLastName());
                p.setEmail(dtop.getEmail());
                p.setStreet(dtop.getStreet());
                p.setZip(dtop.getZip());
                p.setCity(dtop.getCity());
                p.setPhonenumber(dtop.getPhonenumber());
        return p;
    }


    public static ArrayList<DTOPerson> PersonObjectsToDTOPerson(Collection<Person> persons) {
        ArrayList<DTOPerson> result = new ArrayList<>();
        for (Person person : persons) {
            result.add(Assembler.personObjectToDtoPerson(person));
        }
        return result;
    }

    public static Account dtoAccountToAccount(DTOAccount acc) {
        Account result = new Account(
                acc.getAccountnumber(),
                acc.getInterest(),
                acc.getBalance(),
                acc.getCreated());
        return result;
    }

    public static DTOTransaction transactionToDTOTransaction(Transaction t, Account acc) {
        double amount = t.getAmount();
        if (t.getFromAccountNumber() != null && acc.getAccountNumber() == t.getFromAccountNumber().getAccountNumber()) {
            amount = t.getAmount() * -1;
        }
        DTOAccount dtoaFrom = null;
        DTOAccount dtoaTo = null;
        if (t.getFromAccountNumber() != null) {
            dtoaFrom = Assembler.AccountObjectToDTOAccount(t.getFromAccountNumber());
        }
        if (t.getToAccountNumber() != null) {
            dtoaTo = Assembler.AccountObjectToDTOAccount(t.getToAccountNumber());
        }
        DTOTransaction result = new DTOTransaction(
                t.getTransactionDate(),
                t.getTransactionNumber(),
                dtoaFrom,
                dtoaTo,
                amount,
                t.getComment());
        return result;
    }

    public static ArrayList<DTOTransaction> transactionsToDTOTransactions(Collection<Transaction> transactions, Account acc) {
        ArrayList<DTOTransaction> result = new ArrayList<>();
        for (Transaction value : transactions) {
            result.add(Assembler.transactionToDTOTransaction(value, acc));
        }
        return result;
    }
}
