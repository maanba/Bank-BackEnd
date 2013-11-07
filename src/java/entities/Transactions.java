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
@Table(name = "TRANSACTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t"),
    @NamedQuery(name = "Transactions.findByTransactionDate", query = "SELECT t FROM Transactions t WHERE t.transactionDate = :transactionDate"),
    @NamedQuery(name = "Transactions.findByTransactionNumber", query = "SELECT t FROM Transactions t WHERE t.transactionNumber = :transactionNumber"),
    @NamedQuery(name = "Transactions.findByAmount", query = "SELECT t FROM Transactions t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transactions.findByToAmount", query = "SELECT t FROM Transactions t WHERE t.toAmount = :toAmount"),
    @NamedQuery(name = "Transactions.findByFromAmount", query = "SELECT t FROM Transactions t WHERE t.fromAmount = :fromAmount"),
    @NamedQuery(name = "Transactions.findByComment", query = "SELECT t FROM Transactions t WHERE t.comment = :comment")})
public class Transactions implements Serializable {
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
    private int amount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TO_AMOUNT")
    private BigDecimal toAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FROM_AMOUNT")
    private BigDecimal fromAmount;
    @Size(max = 30)
    @Column(name = "COMMENT")
    private String comment;
    @JoinColumn(name = "FROM_ACCOUNT_NUMBER", referencedColumnName = "ACCOUNT_NUMBER")
    @ManyToOne(optional = false)
    private Accounts fromAccountNumber;
    @JoinColumn(name = "TO_ACCOUNT_NUMBER", referencedColumnName = "ACCOUNT_NUMBER")
    @ManyToOne(optional = false)
    private Accounts toAccountNumber;

    public Transactions() {
    }

    public Transactions(Integer transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Transactions(Integer transactionNumber, int amount, BigDecimal toAmount, BigDecimal fromAmount) {
        this.transactionNumber = transactionNumber;
        this.amount = amount;
        this.toAmount = toAmount;
        this.fromAmount = fromAmount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getToAmount() {
        return toAmount;
    }

    public void setToAmount(BigDecimal toAmount) {
        this.toAmount = toAmount;
    }

    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Accounts getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Accounts fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Accounts getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Accounts toAccountNumber) {
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
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactionNumber == null && other.transactionNumber != null) || (this.transactionNumber != null && !this.transactionNumber.equals(other.transactionNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Transactions[ transactionNumber=" + transactionNumber + " ]";
    }
    
}
