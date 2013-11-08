/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel Krarup Knudsen
 */
@Entity
@Table(name = "PERSONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persons.findAll", query = "SELECT p FROM Persons p"),
    @NamedQuery(name = "Persons.findByPersonId", query = "SELECT p FROM Persons p WHERE p.personId = :personId"),
    @NamedQuery(name = "Persons.findByFirstName", query = "SELECT p FROM Persons p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Persons.findByLastName", query = "SELECT p FROM Persons p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Persons.findByEmail", query = "SELECT p FROM Persons p WHERE p.email = :email"),
    @NamedQuery(name = "Persons.findByStreet", query = "SELECT p FROM Persons p WHERE p.street = :street"),
    @NamedQuery(name = "Persons.findByZip", query = "SELECT p FROM Persons p WHERE p.zip = :zip"),
    @NamedQuery(name = "Persons.findByCity", query = "SELECT p FROM Persons p WHERE p.city = :city"),
    @NamedQuery(name = "Persons.findByPhonenumber", query = "SELECT p FROM Persons p WHERE p.phonenumber = :phonenumber"),
})
public class Persons implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERSON_ID")
    private Integer personId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LAST_NAME")
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "STREET")
    private String street;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZIP")
    private int zip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CITY")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PHONENUMBER")
    private int phonenumber;
    @JoinTable(name = "PERSON_USERS", joinColumns = {
        @JoinColumn(name = "PERSON_ID", referencedColumnName = "PERSON_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")})
    @ManyToMany
    private Collection<Users> usersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId")
    private Collection<Accounts> accountsCollection;

    public Persons() {
    }

    public Persons(Integer personId) {
        this.personId = personId;
    }

    public Persons(Integer personId, String firstName, String lastName, String email, String street, int zip, String city, int phonenumber) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.phonenumber = phonenumber;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
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

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<Accounts> getAccountsCollection() {
        return accountsCollection;
    }

    public void setAccountsCollection(Collection<Accounts> accountsCollection) {
        this.accountsCollection = accountsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persons)) {
            return false;
        }
        Persons other = (Persons) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Persons[ personId=" + personId + " ]";
    }
}
