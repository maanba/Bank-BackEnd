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
import entities.Assembler;
import entities.Person;
import entities.Transaction;
import entities.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
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
    public DTOPerson getPerson(int id) {
        Person p = em.find(Person.class, id);
        DTOPerson pdto = Assembler.personObjectToDtoPerson(p);
        return pdto;
    }

    @Override
    public DTOPersonDetail getPersonDetail(int id) {
        Person p = em.find(Person.class, id);
        DTOPersonDetail pddto = Assembler.PersonObjectToDTOPersonDetail(p);
        return pddto;
    }

    @Override
    public ArrayList<String> getRoles() {
        Query q = em.createNamedQuery("Roles.findAll");
        ArrayList<String> roles = new ArrayList();
        roles.add(q.getSingleResult() + "");
        return roles;
    }

    @Override
    public ArrayList<DTOAccount> getAccounts() {
        Query q = em.createNamedQuery("Account.findAll");
        List<Account> account = q.getResultList();
        return Assembler.accountObjectsToDTOAccounts(account);
    }

    @Override
    public ArrayList<DTOPerson> getPersonsByRole(String title) {
        ArrayList<User> users = new ArrayList<>(em.find(Role.class, title).getUserCollection());
        ArrayList<Person> person = new ArrayList<>();
        for (User u : users){
           ArrayList<Person> tmp = new ArrayList<>(u.getPersonCollection());
           Person p = tmp.get(0);
            person.add(p);
        }
        return Assembler.PersonObjectsToDTOPerson(person);
    }

    @Override
    public DTOAccount getAccountByAccountnumber(int accountnumber) {
        Account a = em.find(Account.class, accountnumber);
        System.out.println("Account coll1: " + a.getTransactionCollection());
        DTOAccount adto = Assembler.AccountObjectToDTOAccountDetail(a);
        return adto;
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
    public DTOPerson getPersonByAccountNumber(int accountNumber) {
//      MANGLER RIGTIG QUERY
        Query q = em.createNamedQuery("Person.findByAccounNumber");
        q.setParameter("accountNumber", accountNumber);
        //Handle exception for unkown id
        Person p = (Person) q.getSingleResult();
        DTOPerson pdto = new DTOPerson(p.getFirstName(), p.getLastName(), p.getEmail(), p.getStreet(), p.getZip(), p.getCity(), p.getPhonenumber());
        pdto.setId(p.getPersonId());
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
    public void saveTransaction(int fromAccountNumber, int toAccountNumber, long amount, String comment) {
        Transaction t = new Transaction(new Random().nextInt());
        t.setFromAccountNumber(Assembler.dtoAccountToAccount(getAccountByAccountnumber(fromAccountNumber)));
        t.setToAccountNumber(Assembler.dtoAccountToAccount(getAccountByAccountnumber(toAccountNumber)));

        System.out.println("From: " + t.getFromAccountNumber());
        System.out.println("To: " + t.getToAccountNumber());
        t.setTransactionNumber(new Random().nextInt());
        t.setAmount(amount);
        t.setComment(comment);
        persist(t);

    }

    @Override
    public int getNextPersonId() {
        Query q = em.createQuery("SELECT NEXT VALUE FOR person_id_sequence");
        return (int) q.getSingleResult();
    }

    @Override
    public ArrayList<String> getAccountTypes() {
        Query q = em.createNamedQuery("Roles.findAll");
        ArrayList<String> result = new ArrayList<>();

        List<String> returned = q.getResultList();
        for (String value : returned) {
            result.add(value);
        }

        return result;
    }

    @Override
    public void saveAccount(int userId, String type, double intrest) {
        DTOAccount acc = getAccountByAccountnumber(userId);
        acc.setAccountType(type);
        acc.setInterest(intrest);

        persist(acc);
    }

    @Override
    public void savePerson(String role, String password, DTOPerson person) {
        // Generate userpassword
        String username = person.getLastName() + person.getId() + role;
        User newUser = new User(username, password);

        // Persist the objects
        persist(newUser);
        persist(person);
    }

    @Override
    public String sayHello(String name) {
        return "Hello from " + name + " in the Bean";
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
