package com.rs.kafka.entity;

import javax.persistence.*;
import java.time.*;
import java.util.*;

/**
 * created by rs 5/6/2022.
 */
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    private int orderId;

    @Column
    private String orderNumber;

    @Column
    private String orderLocation;

    @Column
    private LocalDateTime orderDateTime;

    @Column
    private String creditCardNumber;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderLocation() {
        return orderLocation;
    }

    public void setOrderLocation(String orderLocation) {
        this.orderLocation = orderLocation;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderLocation='" + orderLocation + '\'' +
                ", orderDateTime=" + orderDateTime +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", items=" + items +
                '}';
    }
}
