package com.entity;

import com.entity.BankAccount;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SavingsAccount
 *
 */
@Entity
@DiscriminatorValue("SAVINGS")
public class SavingsAccount extends BankAccount implements Serializable {
	private int interestRate;

	private static final long serialVersionUID = 1L;

	public SavingsAccount() {
		super();
	}

	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	public String toString() {
		return super.toString() + " with interest rate " + this.getInterestRate();
	}

}
