
package com.example.demo.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

//    @NotBlank(message = "ClientName is mandatory")
    private String clientName;
    
    @Positive(message = "Amount must be positive")
    private Double amount;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String invoiceDate;
    
    @NotBlank(message = "description is mandatory")
    private String description;

    @ManyToOne
    @JoinColumn(name = "userId") // referencing userId in Users entity
    private User user;


    public Invoice() {
        super();
    }

    public Invoice(String clientName, Double amount, String invoiceDate, String description, User user) {
        this.clientName = clientName;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
        this.description = description;
        this.user = user;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

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
        if (amount > 0) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Amount should be positive.");
        }
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


	

}
