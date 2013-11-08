/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author DragønEye
 */
public class DTOTransaction {
    private Date date;
    private int transactionNumber;
    private int fromAccount;
    private int toAccount;
    private long amount;
    private long toAmount;
    private long fromAmount;
    private String comment;

    public DTOTransaction(Date date, int transactionNumber, int fromAccount, int toAccount, long amount, long toAmount, long fromAmount, String comment) {
        this.date = date;
        this.transactionNumber = transactionNumber;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.toAmount = toAmount;
        this.fromAmount = fromAmount;
        this.comment = comment;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public void setToAccount(int toAccount) {
        this.toAccount = toAccount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getToAmount() {
        return toAmount;
    }

    public void setToAmount(long toAmount) {
        this.toAmount = toAmount;
    }

    public long getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(long fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
