/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel Krarup Knudsen
 */
@Entity
@Table(name = "ACCOUNTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccountType", query = "SELECT a FROM Account a WHERE a.accountType = :accountType"),
    @NamedQuery(name = "Account.findByAccountNumber", query = "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber"),
    @NamedQuery(name = "Account.findByInterest", query = "SELECT a FROM Account a WHERE a.interest = :interest"),
    @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance"),
    @NamedQuery(name = "Account.findByCreated", query = "SELECT a FROM Account a WHERE a.created = :created")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCOUNT_NUMBER")
    private Integer accountNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTEREST")
    private double interest;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALANCE")
    private double balance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED")
    @Temporal(TemporalType.DATE)
    private Date created;
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "PERSON_ID")
    @ManyToOne(optional = false)
    private Person personId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromAccountNumber")
    private Collection<Transaction> transactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toAccountNumber")
    private Collection<Transaction> transactionCollection1;

    public Account() {
    }

    public Account(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Account(Integer accountNumber, String accountType, double interest, double balance, Date created) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.interest = interest;
        this.balance = balance;
        this.created = created;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    @XmlTransient
    public Collection<Transaction> getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(Collection<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

    @XmlTransient
    public Collection<Transaction> getTransactionCollection1() {
        return transactionCollection1;
    }

    public void setTransactionCollection1(Collection<Transaction> transactionCollection1) {
        this.transactionCollection1 = transactionCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountNumber != null ? accountNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountNumber == null && other.accountNumber != null) || (this.accountNumber != null && !this.accountNumber.equals(other.accountNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Account[ accountNumber=" + accountNumber + " ]";
    }

    public void addFromTransaction(Transaction t) {
        transactionCollection.add(t);
        balance = (balance - t.getAmount());
    }

    public void addToTransaction(Transaction t) {
        transactionCollection1.add(t);
        balance = (balance + t.getAmount());
    }
}
