package org.cdoremus.jpa_sandbox.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Customer extends User {

	@Column
	@NotNull
	private String address1;
	@Column
	@NotNull
	private String city;
	@Column
	@NotNull
	private String state;
	@Column(name="zip_code")
	@NotNull
	private String zipCode;

	@OneToMany(mappedBy="customer",targetEntity=Order.class,
    fetch=FetchType.EAGER, cascade=CascadeType.ALL)	
	List<Order> orders = new ArrayList<>();
	
	
	public Customer() {
	}
	
	public Customer(String firstName, String lastName, String email, String address1, String city, String state, String zipCode) {
		super(firstName, lastName, email);
		this.address1 = address1;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void addOrder(Order order) {
		order.setCustomer(this);
		this.orders.add(order);
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
