package com.example.payme20.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Debt holds data about a Member who owes another Member and how much that debt is of
 */
public class Debt implements Serializable {
    private Member debtTo;
    private Member debtFrom;
    private int debtAmount;

    /**
     * Creates a debt
     * @param debtTo the Member which the debt is to
     * @param debtFrom the Member which the debt is from
     * @param debtAmount the amount of debt as an integer
     */
    public Debt(Member debtTo, Member debtFrom, Integer debtAmount){
        this.debtTo = Objects.requireNonNull(debtTo);
        this.debtFrom = Objects.requireNonNull(debtFrom);
        this.debtAmount = debtAmount;
    }
    public Debt() {}

    /**
     * Get the amount of debt between the Members
     * @return returns an integer
     */
    public int getDebtAmount(){
        return this.debtAmount;
    }

    /**
     * Get the member for whom the debt is to
     * @return return a Member
     */
    public Member getDebtTo() {
        return new Member(debtTo.getUserName(), debtTo.getPhoneNumber());
    }

    /**
     * Get the member for who the debt is from
     * @return return a Member
     */
    public Member getDebtFrom() {
        return new Member(debtFrom.getUserName(), debtFrom.getPhoneNumber());
    }
}
