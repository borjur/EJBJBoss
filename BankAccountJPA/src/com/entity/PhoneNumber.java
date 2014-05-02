package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: PhoneNumber
 *
 */
@Entity
@Table(name="PHONENO")
public class PhoneNumber implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
		
	@Column(name="AREACODE")
	int areaCode;
		
	@Column(name="NUMBER")
	int number;
	
	public String toString() {
	    return "(" + this.areaCode + ")" + this.number;
	}
	
	private static final long serialVersionUID = 1L;

	public PhoneNumber() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@ManyToOne
	@JoinColumn(name="OWNERID")
	Owner owner;

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
}
