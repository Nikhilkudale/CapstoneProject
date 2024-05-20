package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.User;

@SpringBootTest
public class InvoicesTest {

    @Test
    public void testInvoiceCreation() {
        User user = new User();
        user.setUsername("john_doe");
        
        String invoiceDate = "2023-05-20";
        
        Invoice invoice = new Invoice("Client ABC", 1000.0, invoiceDate, "Invoice for services", user);
        
        assertNotNull(invoice);
        assertEquals("Client ABC", invoice.getClientName());
        assertEquals(1000.0, invoice.getAmount());
        assertEquals(invoiceDate, invoice.getInvoiceDate());
        assertEquals("Invoice for services", invoice.getDescription());
        assertEquals(user, invoice.getUser());
    }
    
    @Test
    public void testInvoiceSettersAndGetters() {
        Invoice invoice = new Invoice();
        
        invoice.setClientName("Client XYZ");
        invoice.setAmount(2000.0);
        String invoiceDate = "2023-05-21";
        invoice.setInvoiceDate(invoiceDate);
        invoice.setDescription("Invoice for products");
        
        assertNotNull(invoice);
        assertEquals("Client XYZ", invoice.getClientName());
        assertEquals(2000.0, invoice.getAmount());
        assertEquals(invoiceDate, invoice.getInvoiceDate());
        assertEquals("Invoice for products", invoice.getDescription());
        
        // Testing user association
        User user = new User();
        user.setUsername("jane_doe");
        invoice.setUser(user);
        assertEquals(user, invoice.getUser());
    }
    
    @Test
    public void testInvoiceUserAssociation() {
        User user = new User();
        user.setUsername("john_doe");
        
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        
        assertNotNull(invoice.getUser());
        assertEquals("john_doe", invoice.getUser().getUsername());
    }
    
    @Test
    public void testInvoiceDefaultConstructor() {
        Invoice invoice = new Invoice();
        
        assertNull(invoice.getClientName());
        assertNull(invoice.getAmount());
        assertNull(invoice.getInvoiceDate());
        assertNull(invoice.getDescription());
        assertNull(invoice.getUser());
    }
}
