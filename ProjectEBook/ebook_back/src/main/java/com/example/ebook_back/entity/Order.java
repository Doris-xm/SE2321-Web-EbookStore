package com.example.ebook_back.entity;

public class Order {

    private Long id;
    private String title;
    private float price;
    private int quantity;
    private String address;
    private State orderState;

    public enum State {
        PAID("已付款"),
        SHIPPED("已发货"),
        REACHED("已送达"),
        RECEIVED("已收货"),
        REVIEWED("已评价"),
        CANCELED("已取消");

        private String description;

        State(String description) {
            this.description = description;
        }
        public String getDescription() {
            return description;
        }
    }
    // getters and setters for id, title, price, quantity, and address
    public State getState() {
        return orderState;
    }
    public void setState(State orderState) {
        this.orderState = orderState;
    }
    public Order(Long id, String title,float price,  int quantity, String address,State orderState) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.orderState = orderState;
    }

    public Long getId() {   return id;   }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {   return price;    }
    public void setPrice(float price) {    this.price = price;    }

    public int getQuantity() {   return quantity;}

    public void setQuantity(int quantity) {    this.quantity = quantity;    }

    public String getAddress() {    return address;    }
    public void setAddress(String address) {    this.address = address;    }



    @Override
    public String toString() {
        return String.format(
                "Book[id=%d, title='%s', price='%f', quantity='%d', address='%s',orderState='%s']",
                id, title, price, quantity, address,orderState);
    }

}



//package com.example.ebook_back.entity;
//
//public class Order {
//
//    private Long id;
//    private String title;
//    private float price;
//    private int quantity;
//    private String address;
//    private State orderState;
//
//    public enum State {
//        PAID("已付款"),
//        SHIPPED("已发货"),
//        REACHED("已送达"),
//        RECEIVED("已收货"),
//        REVIEWED("已评价"),
//        CANCELED("已取消");
//
//        private String description;
//
//        State(String description) {
//            this.description = description;
//        }
//        public String getDescription() {
//            return description;
//        }
//    }
//    // getters and setters for id, title, price, quantity, and address
//    public State getState() {
//        return orderState;
//    }
//    public void setState(State orderState) {
//        this.orderState = orderState;
//    }
//    public Order(Long id, String title,float price,  int quantity, String address,State orderState) {
//        this.id = id;
//        this.title = title;
//        this.price = price;
//        this.quantity = quantity;
//        this.address = address;
//        this.orderState = orderState;
//    }
//
//    public Long getId() {   return id;   }
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public float getPrice() {   return price;    }
//    public void setPrice(float price) {    this.price = price;    }
//
//    public int getQuantity() {   return quantity;}
//
//    public void setQuantity(int quantity) {    this.quantity = quantity;    }
//
//    public String getAddress() {    return address;    }
//    public void setAddress(String address) {    this.address = address;    }
//
//
//
//    @Override
//    public String toString() {
//        return String.format(
//                "Book[id=%d, title='%s', price='%f', quantity='%d', address='%s',orderState='%s']",
//                id, title, price, quantity, address,orderState);
//    }
//
//}
