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
import dto.DTOUser;
import entities.Account;
import entities.Assembler;
import entities.Person;
import entities.Person;
import entities.Role;
import entities.User;
import java.util.ArrayList;
import java.util.List;
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
        Query q = em.createNamedQuery("Accounts.findAll");
        List<Account> account = q.getResultList();
        return Assembler.accountObjectsToDTOAccounts(account);
    }

    @Override
    public ArrayList<DTOPerson> getPersonsByRole(String title) {
        User u = em.find(User.class, title);
        return Assembler.PersonObjectsToDTOPerson(u.getPersonCollection());
        }

    @Override
    public DTOAccount getAccountByAccountnumber(int accountnumber) {
        Account a = em.find(Account.class, accountnumber);
        DTOAccount adto = Assembler.AccountObjectToDTOAccount(a);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextPersonId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
