package com.shinkarev.musicshop.entity;

import java.time.LocalDateTime;
import java.util.Map;

public class Order extends Entity {
    private long id;
    private LocalDateTime orderDate;
    private Map<Instrument, Integer> items;
    private long userId;
    private double price;
    private OderType status;
    private String address;
    private String payment;

    public Order(long userId, LocalDateTime orderDate, double price, String address, OderType status, String payment) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.price = price;
        this.address = address;
        this.status = status;
        this.payment = payment;
    }

    public Order(long id, LocalDateTime orderDate, Map<Instrument, Integer> items, long userId, double price, OderType status, String address, String payment) {
        this.id = id;
        this.orderDate = orderDate;
        this.items = items;
        this.userId = userId;
        this.price = price;
        this.status = status;
        this.address = address;
        this.payment = payment;
    }


    public Order() {
    }

    public Map<Instrument, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Instrument, Integer> items) {
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OderType getStatus() {
        return status;
    }

    public void setStatus(OderType status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        if (this.id != order.id) {
            return false;
        }
        if (this.userId != order.getUserId()) {
            return false;
        }
        if (this.items.equals(((Order) o).items)) {
            return false;
        }

        if (this.price != order.getPrice()) {
            return false;
        }
        if (this.orderDate != order.getOrderDate()) {
            return false;
        }
        if (!this.address.equals(order.getAddress())) {
            return false;
        }
        if (!this.payment.equals(order.payment)) {
            return false;
        }
        return this.status != order.getStatus();
    }

    @Override
    public int hashCode() {
        int result = (int) (this.id ^ (this.id >>> 32));
        result = 31 * result + (int) this.userId;
        result = 31 * result + (this.orderDate != null ? this.orderDate.hashCode() : 0);
        result = 31 * result + Double.hashCode(this.price);
        result = 31 * result + (this.address != null ? this.address.hashCode() : 0);
        result = 31 * result + (this.status != null ? this.status.hashCode() : 0);
        result = 31 * result + (this.payment != null ? this.payment.hashCode() : 0);
        for (Map.Entry<Instrument, Integer> item : this.items.entrySet()) {
            result += (31 * item.getKey().hashCode() + item.getValue());
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", items=").append(items);
        sb.append(", userId=").append(userId);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append(", address='").append(address).append('\'');
        sb.append(", payment='").append(payment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
