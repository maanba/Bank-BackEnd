package dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Mads
 */
public class DTOTimeDepositAccount extends DTOAccount implements Serializable {

    private Date lockPeriodStart;
    private Date lockPeriodEnd;
    private boolean locked;
    private long penalty;

    public DTOTimeDepositAccount(Date lockPeriodStart, Date lockPeriodEnd, boolean locked, long penalty, String accountType, int accountnumber, double interest, long balance, Date created) {
        super(accountType, accountnumber, interest, balance, created);
        this.lockPeriodStart = lockPeriodStart;
        this.lockPeriodEnd = lockPeriodEnd;
        this.locked = locked;
        this.penalty = penalty;
    }

    public Date getLockPeriodStart() {
        return lockPeriodStart;
    }

    public void setLockPeriodStart(Date lockPeriodStart) {
        this.lockPeriodStart = lockPeriodStart;
    }

    public Date getLockPeriodEnd() {
        return lockPeriodEnd;
    }

    public void setLockPeriodEnd(Date lockPeriodEnd) {
        this.lockPeriodEnd = lockPeriodEnd;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public long getPenalty() {
        return penalty;
    }

    public void setPenalty(long penalty) {
        this.penalty = penalty;
    }
}
