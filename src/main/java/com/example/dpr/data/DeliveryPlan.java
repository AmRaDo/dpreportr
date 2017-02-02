package com.example.dpr.data;

import java.math.BigDecimal;
import java.sql.Date;

public class DeliveryPlan {
	long deliveryNo;
	String part;
	String depot;
	String customer;
	long quantity;
	Date dueDate;
	Date deliveryDate;
	BigDecimal margin;

	public DeliveryPlan(long deliveryNo) {

		super();
		this.deliveryNo = deliveryNo;
	}

	public long getDeleveryNo() {
		return deliveryNo;
	}

	public void setDeleveryNo(long deleveryNo) {
		this.deliveryNo = deleveryNo;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}
}
