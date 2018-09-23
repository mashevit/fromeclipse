package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "owner", fetch=FetchType.EAGER)
	List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
	// @JoinTable(name = "OWNERPHONE_REL", joinColumns = @JoinColumn(name =
		// "OWNER_ID"), inverseJoinColumns = @JoinColumn(name = "PHONE_ID"))
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
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

	public String toString() {
		return "Owner " + this.name + " born on " + this.dateOfBirth + " lives at " + this.address;
	}

	public void addNumber(PhoneNumber number) {
		this.phoneNumbers.add(number);
		number.setOwner(this);
	}

	private static final long serialVersionUID = 1L;

	public Owner() {
		super();
	}

}
