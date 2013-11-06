/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel Krarup Knudsen
 */
@Entity
@Table(name = "ACCOUNTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a"),
    @NamedQuery(name = "Accounts.findByAccounttype", query = "SELECT a FROM Accounts a WHERE a.accounttype = :accounttype"),
    @NamedQuery(name = "Accounts.findByAccountnumber", query = "SELECT a FROM Accounts a WHERE a.accountnumber = :accountnumber"),
    @NamedQuery(name = "Accounts.findByInterest", query = "SELECT a FROM Accounts a WHERE a.interest = :interest"),
    @NamedQuery(name = "Accounts.findByBalance", query = "SELECT a FROM Accounts a WHERE a.balance = :balance"),
    @NamedQuery(name = "Accounts.findByCreated", query = "SELECT a FROM Accounts a WHERE a.created = :created")})
public class Accounts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ACCOUNTTYPE")
    private String accounttype;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCOUNTNUMBER")
    private Integer accountnumber;
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
    @JoinColumn(name = "TRANSACTION_NUMBER", referencedColumnName = "TRANSACTION_NUMBER")
    @ManyToOne(optional = false)
    private Transactions transactionNumber;

    public Accounts() {
    }

    public Accounts(Integer accountnumber) {
        this.accountnumber = accountnumber;
    }

    public Accounts(Integer accountnumber, String accounttype, double interest, BigDecimal balance, Date created) {
        this.accountnumber = accountnumber;
        this.accounttype = accounttype;
        this.interest = interest;
        this.balance = balance;
        this.created = created;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public Integer getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(Integer accountnumber) {
        this.accountnumber = accountnumber;
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

    public Transactions getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Transactions transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountnumber != null ? accountnumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.accountnumber == null && other.accountnumber != null) || (this.accountnumber != null && !this.accountnumber.equals(other.accountnumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Accounts[ accountnumber=" + accountnumber + " ]";
    }
    
}
