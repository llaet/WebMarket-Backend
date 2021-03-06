package com.saleshub.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saleshub.domain.enums.ClientType;
import com.saleshub.domain.enums.CustomerProfile;

@Entity(name = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(unique = true)
	private String email;
	//CPF or CNPJ in Brazil
	private String document;
	private Integer type;
	@JsonIgnore
	private String password;

	@OneToMany(mappedBy ="customer", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "phone_number")
	private Set<String> phones = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy ="customer")
	private List<SaleOrder> orders = new ArrayList<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "customer_profiles")
	private Set<Integer> profiles = new HashSet<>();

	public Customer() {
		addProfile(CustomerProfile.CLIENTE);
	}
	
	public Customer(Integer id, String name, String email, String document, ClientType type,
					String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.document = document;
		this.type = type == null ? null : type.getClientNumber();
		this.password = password;
		addProfile(CustomerProfile.CLIENTE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public ClientType getType() {
		return ClientType.toEnum(type);
	}

	public void setType(ClientType type) {
		this.type = type.getClientNumber();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<CustomerProfile> getCustomerProfiles(){
		return profiles.stream()
				.map(profile -> CustomerProfile.toEnum(profile))
				.collect(Collectors.toSet());
	}

	public void addProfile(CustomerProfile customerProfile){
		profiles.add(customerProfile.getRoleCode());
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}
	
	public List<SaleOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<SaleOrder> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
