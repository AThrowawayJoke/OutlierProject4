package com.example.ada.tucanocaffe;

/**
 * Created by ada on 8/17/16.
 */
public class Order {
    private String name;
    private int id;
    private int tableNum;
    private String clientMessage;

    // array of orders
    public static final Order[] orders = {
        new Order("Order 1",1, 1, "Please more sugar"),
        new Order("Order 2",2, 1, "No more sugar"), new Order("Order 3",3, 1, "A glass of water beside."),

    };

    // constructor of Order class
    private Order(String name, int id, int tableNum, String clientMessage){
        this.name = name;
        this.clientMessage  = clientMessage;
        this.id = id;
        this.tableNum = tableNum;

    }

    public int getId(){
        return id;
    }

    public int getTableNum(){
        return this.tableNum;
    }

    public String getClientMessage(){
        return clientMessage;
    }

    public String toString() {
        return this.name;
    }

}
