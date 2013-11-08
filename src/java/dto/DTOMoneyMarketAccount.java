/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 * Pt. kan kontoen ikke gå under minimumsbalancen. 
 * Man kan lave det således at der kan gives en bøde såfremt man når under. 
 * Bliver måske implementeret. 
 * @author Mads
 */
public class DTOMoneyMarketAccount extends DTOAccount {

    private double minimumBalance;

    public DTOMoneyMarketAccount(double minimumBalance, String accountType, int accountnumber, double interest, long balance, Date created) {
        super(accountType, accountnumber, interest, balance, created);
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    
}
