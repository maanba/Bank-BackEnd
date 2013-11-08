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
import entities.Persons;
import entities.Users;
import java.util.ArrayList;
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
        DTOPersonDetail pddto = new DTOPersonDetail(p.getFirstName(), p.getLastName(), p.getEmail(), p.getStreet(), p.getZip(), p.getCity(), p.getPhonenumber(), p.getAccountsCollection(), p.getUsersCollection());
        pddto.setId(p.getPersonId());
        return pddto;
    }

    @Override
    public ArrayList<String> getRoles() {
        Query q = em.createNamedQuery("Users.findAll");
        Users u = (Users) q.getSingleResult();
        DTOUser udto = new DTOUser(u.getUsername(), u.getPassword(), u.getTitle());
        udto.setId(u.getPersonId());
        return udto;
    }

    @Override
    public ArrayList<DTOAccount> getAccounts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<DTOPerson> getPersonsByRole(String role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DTOAccount getAccountByAccountnumber(int accountnumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DTOPersonDetail getPersonByUserId(String userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveAccount(int userId, String type, double intrest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void savePerson(String role, String password, DTOPerson person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String sayHello(String name) {
    return "Hello from " + name + " in the Bean";
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
}
