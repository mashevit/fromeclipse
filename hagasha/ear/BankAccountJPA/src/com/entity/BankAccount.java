package com.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: BankAccount
 *
 */
@Entity
@Table(name = "BANKACCOUNT")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

@DiscriminatorColumn(name="DISCRIMINATOR", discriminatorType=DiscriminatorType.STRING ,columnDefinition = "varchar(45) default 'NULL'")
@DiscriminatorValue("ACCOUNT")
public class BankAccount implements Serializable {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "BALANCE")
	int balance;
	// @Column(name="OWNER")
	// String ownerName;
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "OWNER_ID")
	Owner owner;
	private static final long serialVersionUID = 1L;

	public BankAccount() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void deposit(int amount) {
		this.setBalance(getBalance() + amount);
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public void withdraw(int amount) {
		this.setBalance(getBalance() - amount);
	}
public String toString() {
	return "Bank Account "+this.id+" with balance "+this.balance+" Owned by "+this.owner;
}
}
