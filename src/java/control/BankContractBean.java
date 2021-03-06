/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import contract.BankInterface;
import dto.DTOAccount;
import dto.DTOPerson;
import dto.DTOPersonDetail;
import entities.Account;
import entities.AccountType;
import entities.Assembler;
import entities.Person;
import entities.Role;
import entities.Transaction;
import entities.User;
import exceptions.CustomException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Thomas
 */
@Stateless
public class BankContractBean implements BankInterface {

    @PersistenceContext(unitName = "BankBackendPU")
    private EntityManager em;

    @Override
//    @RolesAllowed({"Customer", "Employees", "BankTeller", "Manager"})
    public DTOPerson getPerson(int id) {
        Person p = em.find(Person.class, id);
        DTOPerson pdto = Assembler.personObjectToDtoPerson(p);
        return pdto;
    }

    @Override
    @RolesAllowed({"Customer", "BankTeller", "Manager"})
    public DTOPersonDetail getPersonDetail(int id) {
        Person p = em.find(Person.class, id);
        DTOPersonDetail pddto = Assembler.PersonObjectToDTOPersonDetail(p);
        return pddto;
    }

    @Override
    @RolesAllowed({"Customer", "BankTeller", "Manager"})
    public ArrayList<String> getRoles() {
        Query q = em.createNamedQuery("Roles.findAll");
        ArrayList<String> roles = new ArrayList();
        roles.add(q.getSingleResult() + "");
        return roles;
    }

    @Override
    @RolesAllowed({"Customer", "BankTeller", "Manager"})
    public ArrayList<DTOAccount> getAccounts() {
        Query q = em.createNamedQuery("Account.findAll");
        List<Account> account = q.getResultList();
        return Assembler.accountObjectsToDTOAccounts(account);
    }

    @Override
    @RolesAllowed({"Customer", "BankTeller", "Manager"})
    public ArrayList<DTOPerson> getPersonsByRole(String title) {
        ArrayList<User> users = new ArrayList<>(em.find(Role.class, title).getUserCollection());
        ArrayList<Person> person = new ArrayList<>();
        for (User u : users) {
            ArrayList<Person> tmp = new ArrayList<>(u.getPersonCollection());
            Person p = tmp.get(0);
            person.add(p);
        }
        return Assembler.PersonObjectsToDTOPerson(person);
    }

    @Override
    @RolesAllowed({"Customer", "BankTeller", "Manager"})
    public DTOAccount getAccountByAccountnumber(int accountnumber) {
        if (accountnumber != 0) {
            Account a = em.find(Account.class, accountnumber);
            if (a != null) {
                DTOAccount adto = Assembler.AccountObjectToDTOAccountDetail(a);
                return adto;
            }
        }
        return null;
    }

    @Override
    public DTOPersonDetail getPersonByUserId(String userId) {
        User user = em.find(User.class, userId);
        if (user == null) { // Could not find the user
            return null;
        }

        ArrayList<Person> persons = new ArrayList<>(user.getPersonCollection());
        Person p = persons.get(0);
        return Assembler.PersonObjectToDTOPersonDetail(p);
    }

    @Override
    @RolesAllowed({"Customer", "BankTeller", "Manager"})
    public DTOPerson getPersonByAccountNumber(int accountNumber) {
        Person p = em.find(Account.class, accountNumber).getPersonId();
        DTOPerson pdto = Assembler.personObjectToDtoPerson(p);
        return pdto;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        User user = em.find(User.class, username);
        if (user == null) {
            return false;
        }
        return (password.equalsIgnoreCase(user.getPassword()));
    }

    @Override
    @RolesAllowed({"Customer", "BankTeller", "Manager"})
    public void saveTransaction(int fromAccountNumber, int toAccountNumber, long amount, String comment) throws CustomException{
        Transaction t = new Transaction();
        t.setTransactionDate(new Date());
        t.setFromAccountNumber(null);
        t.setToAccountNumber(null);
        if (fromAccountNumber != 0) {
            t.setFromAccountNumber(em.find(Account.class, fromAccountNumber));
        }
        if (toAccountNumber != 0) {
            t.setToAccountNumber(em.find(Account.class, toAccountNumber));
        }
        t.setAmount(amount);
        t.setComment(comment);

        // Gem transaction i hver account
        if (fromAccountNumber != 0) {
            Account acc1 = em.find(Account.class, fromAccountNumber);
            acc1.addFromTransaction(t);
        }

        if (toAccountNumber != 0) {
            Account acc2 = em.find(Account.class, toAccountNumber);
            acc2.addToTransaction(t);
        }

        em.persist(t);
    }

    @Override
    @RolesAllowed({"BankTeller", "Manager"})
    public int getNextPersonId() {
        Query q = em.createQuery("SELECT NEXT VALUE FOR person_id_sequence");
        return (int) q.getSingleResult();
    }

    @Override
    @RolesAllowed({"Customer", "BankTeller", "Manager"})
    public ArrayList<String> getAccountTypes() {
        Query q = em.createNamedQuery("AccountType.findAll");
        ArrayList<String> result = new ArrayList<>();
        List<AccountType> returned = q.getResultList();
        for (int i = 0; i < returned.size(); i++) {
            result.add(returned.get(i).getAccountType());
        }

        return result;
    }

    @Override
    public void saveAccount(int userId, int accountnumber, String type, double intrest) {

        Person p = em.find(Person.class, userId);
        Account acc = new Account();
        AccountType act = em.find(AccountType.class, type);
        if (accountnumber == 0) {
            acc.setPersonId(p);
            acc.setAccountType(act);
            acc.setInterest(intrest);
            acc.setBalance(0);
            acc.setCreated(new Date());
            p.addAccount(acc);
            act.getAccountCollection().add(acc);
        } else {
            acc = em.find(Account.class, accountnumber);
            acc.setAccountType(new AccountType(type));
            acc.setInterest(intrest);
        }
        persist(acc);
    }

    @Override
//    @RolesAllowed({"BankTeller", "Manager"})
    public void savePerson(String username, String role, String password, DTOPerson person) {

        if (person.getId() == 0) {
            User newUser = new User();
            Role r = em.find(Role.class, role);
            Person p = Assembler.DTOPersonObjectToDtoPerson(person);
            newUser.setTitle(r);
            newUser.setPassword(password);
            newUser.setUsername(username);
            p.addUser(newUser);
            newUser.addPerson(p);
            r.getUserCollection().add(newUser);
            persist(r);
            persist(p);
            persist(newUser);
        } else {
            Person p = em.find(Person.class, person.getId());
            Assembler.savePersonObjectToDtoPerson(person, p);
            persist(p);
        }
    }

    @Override
    @RolesAllowed({"Customer", "BankTeller", "Manager"})
    public String sayHello(String name) {

        return "Hello from " + name + " in the Bean";
    }

    @RolesAllowed({"Customer", "BankTeller", "Manager"})
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    @RolesAllowed({"Customer", "BankTeller", "Manager"})
    public DTOPerson getAddressFromPhoneNumber(int phonenumber) {
        Query q = em.createNamedQuery("Person.findByPhonenumber");
        q.setParameter("phonenumber", phonenumber);
        List<Person> r = q.getResultList();
        if (r.size() > 0) {
            return Assembler.personObjectToDtoPerson(r.get(0));
        } else {
            return null;
        }
    }
}
