/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
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
 * @author Thomas
 */
@Entity
@Table(name = "TRANSACTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findByTransactionDate", query = "SELECT t FROM Transaction t WHERE t.transactionDate = :transactionDate"),
    @NamedQuery(name = "Transaction.findByTransactionNumber", query = "SELECT t FROM Transaction t WHERE t.transactionNumber = :transactionNumber"),
    @NamedQuery(name = "Transaction.findByAmount", query = "SELECT t FROM Transaction t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transaction.findByComment", query = "SELECT t FROM Transaction t WHERE t.comment = :comment")})
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_NUMBER")
    private Integer transactionNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private double amount;
    @Size(max = 30)
    @Column(name = "COMMENT")
    private String comment;
    @JoinColumn(name = "FROM_ACCOUNT_NUMBER", referencedColumnName = "ACCOUNT_NUMBER")
    @ManyToOne
    private Account fromAccountNumber;
    @JoinColumn(name = "TO_ACCOUNT_NUMBER", referencedColumnName = "ACCOUNT_NUMBER")
    @ManyToOne
    private Account toAccountNumber;

    public Transaction() {
    }

    public Transaction(Integer transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Transaction(Integer transactionNumber, double amount) {
        this.transactionNumber = transactionNumber;
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Integer transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Account getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Account fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Account getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Account toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionNumber != null ? transactionNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.transactionNumber == null && other.transactionNumber != null) || (this.transactionNumber != null && !this.transactionNumber.equals(other.transactionNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Transaction[ transactionNumber=" + transactionNumber + " ]";
    }
    
}
