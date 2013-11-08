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
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a"),
    @NamedQuery(name = "Accounts.findByAccountType", query = "SELECT a FROM Accounts a WHERE a.accountType = :accountType"),
    @NamedQuery(name = "Accounts.findByAccountNumber", query = "SELECT a FROM Accounts a WHERE a.accountNumber = :accountNumber"),
    @NamedQuery(name = "Accounts.findByInterest", query = "SELECT a FROM Accounts a WHERE a.interest = :interest"),
    @NamedQuery(name = "Accounts.findByBalance", query = "SELECT a FROM Accounts a WHERE a.balance = :balance"),
    @NamedQuery(name = "Accounts.findByCreated", query = "SELECT a FROM Accounts a WHERE a.created = :created")})
public class Accounts implements Serializable {
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
    private BigDecimal balance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED")
    @Temporal(TemporalType.DATE)
    private Date created;
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "PERSON_ID")
    @ManyToOne(optional = false)
    private Persons personId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromAccountNumber")
    private Collection<Transactions> transactionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toAccountNumber")
    private Collection<Transactions> transactionsCollection1;

    public Accounts() {
    }

    public Accounts(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Accounts(Integer accountNumber, String accountType, double interest, BigDecimal balance, Date created) {
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Persons getPersonId() {
        return personId;
    }

    public void setPersonId(Persons personId) {
        this.personId = personId;
    }

    @XmlTransient
    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    @XmlTransient
    public Collection<Transactions> getTransactionsCollection1() {
        return transactionsCollection1;
    }

    public void setTransactionsCollection1(Collection<Transactions> transactionsCollection1) {
        this.transactionsCollection1 = transactionsCollection1;
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
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.accountNumber == null && other.accountNumber != null) || (this.accountNumber != null && !this.accountNumber.equals(other.accountNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Accounts[ accountNumber=" + accountNumber + " ]";
    }
    
}
