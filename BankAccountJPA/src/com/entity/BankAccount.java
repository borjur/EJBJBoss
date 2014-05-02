package com.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: BankAccount
 * 
 */
@Entity
@Table(name = "BANKACCOUNT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType =  	DiscriminatorType.STRING)
@DiscriminatorValue("ACCOUNT")
public class BankAccount implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "BALANCE")
	int balance;

	// @Column(name="OWNERNAME")
	// String ownerName;
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "OWNER_ID")
	Owner owner;

	public String toString() {
		return "Bank account " + this.id + " with balance " + this.balance
				+ " owned by " + this.owner;
	}

	public void deposit(int amount) {
		this.setBalance(getBalance() + amount);
	}

	public void withdraw(int amount) {
		this.setBalance(getBalance() - amount);
	}

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

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

}
