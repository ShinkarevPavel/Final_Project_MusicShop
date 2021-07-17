package com.shinkarev.musicshop.entity;

import java.util.Date;
import java.util.List;

public class Order extends Entity {
    private long id;
    private long userId;
    private Date orderDate;
    private double price;
    private OderType status;
    private String address;
    private List<Long> items;

    public Order(long id, long userId, Date orderDate, List<Long> items, double price, String address, OderType status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.items = items;
        this.price = price;
        this.address = address;
        this.status = status;
    }

    public Order(long userId, Date orderDate, List<Long> items, double price, String address, OderType status) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.items = items;
        this.price = price;
        this.address = address;
        this.status = status;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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
        if (!(this.items.containsAll(((Order) o).getItems()) && ((Order) o).getItems().containsAll(this.items))) {
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
        for (Long item : this.items) {
            result = 31 * result + ((int) (item ^ (item >>> 32)));
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append(", address='").append(address).append('\'');
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
