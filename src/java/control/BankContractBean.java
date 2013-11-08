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
import entities.Accounts;
import entities.Assembler;
import entities.Persons;
import entities.Users;
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
        Query q = em.createNamedQuery("Person.findById");
        q.setParameter("id", id);
        //Handle exception for unkown id
        Persons p = (Persons) q.getSingleResult();
        DTOPerson pdto = new DTOPerson(p.getFirstName(), p.getLastName(), p.getEmail(), p.getStreet(), p.getZip(), p.getCity(), p.getPhonenumber());
        pdto.setId(p.getPersonId());
        return pdto;
    }

    @Override
    public DTOPersonDetail getPersonDetail(int id) {
        Query q = em.createNamedQuery("Person.findById");
        q.setParameter("id", id);
        //Handle exception for unkown id
        Persons p = (Persons) q.getSingleResult();

        ArrayList<DTOAccount> accounts = Assembler.accountObjectsToDTOAccounts(new ArrayList<>(p.getAccountsCollection()));
        ArrayList<DTOUser> users = Assembler.userObjectsToDTOUsers(new ArrayList<>(p.getUsersCollection()));

        DTOPersonDetail pddto = new DTOPersonDetail(
                p.getFirstName(),
                p.getLastName(),
                p.getEmail(),
                p.getStreet(),
                p.getZip(),
                p.getCity(),
                p.getPhonenumber(),
                accounts,
                users);

        pddto.setId(p.getPersonId());
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
        List<Accounts> accounts = q.getResultList();
        return Assembler.accountObjectsToDTOAccounts(accounts);
    }

    @Override
    public ArrayList<DTOPerson> getPersonsByRole(String role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DTOAccount getAccountByAccountnumber(int accountnumber) {
        Query q = em.createNamedQuery("Accounts.findByAccountNumber");
        q.setParameter("accountnumber", accountnumber);
        Accounts a = (Accounts) q.getSingleResult();
        
        DTOAccount adto = new DTOAccount(
                a.getAccountType(), 
                a.getAccountNumber(), 
                a.getInterest(), 
                a.getBalance().longValue(), 
                a.getCreated());
        return adto;
    }

    @Override
    public DTOPersonDetail getPersonByUserId(String userId) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//
//        Query q = em.createNamedQuery("Person.findByUserId");
//        q.setParameter("userId", userId);
//        //Handle exception for unkown id
//        Persons p = (Persons) q.getSingleResult();
//        
//        DTOPersonDetail pddto = 
//                new DTOPersonDetail(
//                p.getFirstName(), 
//                p.getLastName(), 
//                p.getEmail(), 
//                p.getStreet(), 
//                p.getZip(), 
//                p.getCity(), 
//                p.getPhonenumber(), 
//                Assembler.accountObjectsToDTOAccounts(p.getAccountsCollection()), 
//                Assembler.userObjectsToDTOUsers(p.getUsersCollection()));
//        pddto.setId(p.getPersonId());
//        
//        return pddto;
    }

    @Override
    public DTOPerson getPersonByAccountNumber(int accountNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkLogin(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveTransaction(int fromAccountNumber, int toAccountNumber, long amount, String comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void savePhysicalTransaction(int AccountNumber, long amount, String comment) {
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
        Users newUser = new Users(username, password);

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
