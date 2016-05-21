package org.cdoremus.jpa_sandbox.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="CustomerOrder")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long orderId;
	
	@Column()
	private long customerId;
		
	@Column
	@NotNull
	private String name;
	
    @ManyToOne(optional=false)
    @JoinColumn(name="userId",referencedColumnName="userId")
    private Customer customer;
	
    @OneToMany(mappedBy = "order", targetEntity=Item.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)	
	private List<Item> items = new ArrayList<>();
	
	public Order() {
	}
	
	public Order(String name) {
		this.name = name;
	}
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void addItem(Item item) {
		item.setOrder(this);
		this.items.add(item);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

}
