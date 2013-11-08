/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author Mads
 */
public class DTOPersonDetail {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private int zip;
    private String city;
    private int phonenumber;
    private ArrayList<DTOUser> users;
    private ArrayList<DTOAccount> accounts;

    public DTOPersonDetail() {
    }

    public DTOPersonDetail(String firstName, String lastName, String email, String street, int zip, String city, int phonenumber, ArrayList<DTOAccount> accounts, ArrayList<DTOUser> users) {
        this.users = users;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.phonenumber = phonenumber;
        this.accounts = accounts;
    }

    public ArrayList<DTOUser> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<DTOUser> users) {
        this.users = users;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public ArrayList<DTOAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<DTOAccount> accounts) {
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public DTOUser getRoleByUserId(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUserId())) {
                return users.get(i);
            }
        }
        return null;
    }
//</editor-fold>
}
