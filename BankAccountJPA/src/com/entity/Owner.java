package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Owner
 * 
 */
@Entity
@Table(name = "OWNERS")
public class Owner implements Serializable {

	@Id
	@Column(name = "OWNERID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "NAME")
	String name;

	@Column(name = "ADDRESS")
	String address;

	@Column(name = "DATEOFBIRTH")
	String dateOfBirth;

	public String toString() {
		return "Owner " + this.name + " born on " + this.dateOfBirth
				+ " lives at " + this.address;
	}

	private static final long serialVersionUID = 1L;

	public Owner() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="owner", fetch=FetchType.EAGER)
	List <PhoneNumber>phoneNumbers = new ArrayList<PhoneNumber>();

	public void addNumber(PhoneNumber number) {
	    this.phoneNumbers.add(number);
	    number.setOwner(this);
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	
}
