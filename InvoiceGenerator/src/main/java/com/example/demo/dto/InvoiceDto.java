package com.example.demo.dto;

import java.util.Date;

public class InvoiceDto {

    private String clientName;
    private Double amount;
    private Date invoiceDate;
    private String description;

    // Constructors, getters, and setters
    // Constructor
    public InvoiceDto(String clientName, Double amount, Date invoiceDate, String description) {
        this.clientName = clientName;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
        this.description = description;
    }

    public InvoiceDto() {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
