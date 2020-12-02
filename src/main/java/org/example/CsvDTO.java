package org.example;

import java.math.BigDecimal;

/**
 * To create a DTO like this one, it is required to know in advance the structure of the CSV file
 */
public class CsvDTO{
    private String item;
    private int quantity;
    private BigDecimal unitPrice;

    public CsvDTO() {
    }

    public CsvDTO(String item, int quantity, BigDecimal unitPrice) {
        this.item = item;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "csvDTO{" +
                "item='" + item + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
