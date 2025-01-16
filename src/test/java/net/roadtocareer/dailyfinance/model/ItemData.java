package net.roadtocareer.dailyfinance.model;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.*;

/*
 ** 2025, January 14, Tuesday, 11:57 AM
 */
public class ItemData {
    private static Integer itemCounter = 0;

    private String itemName;
    private int quantity;
    private String amount;
    private String purchaseDate;
    private String month;
    private String remarks;

    public ItemData() {
    }

    public ItemData(String itemName, int quantity, String amount, String purchaseDate, String month, String remarks) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.month = month;
        this.remarks = remarks;
    }

    public ItemData generate() {
        ItemData itemData = new ItemData();
        Faker faker = new Faker();
        long time = new Date().getTime();

        itemData.setItemName("Item " + ++itemCounter);
        itemData.setQuantity(new Random().nextInt(100));
        itemData.setAmount(((int) (Math.random() * (9999 - 1000) + 1000)) + "");
        itemData.setPurchaseDate(new SimpleDateFormat("YYYY-MM-dd").format(time));
        itemData.setMonth(new SimpleDateFormat("MMMM").format(time));
        itemData.setRemarks(faker.lorem().paragraph(3));

        return itemData;
    }

    public int generateRandom8Digit() {
        return (int) (Math.random() * (99999999 - 10000000) + 10000000);
    }

    public static Integer getItemCounter() {
        return itemCounter;
    }

    public static void setItemCounter(Integer itemCounter) {
        ItemData.itemCounter = itemCounter;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
