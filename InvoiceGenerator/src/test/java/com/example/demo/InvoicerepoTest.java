package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Invoice;
import com.example.demo.repo.Invoicerepo;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class InvoicerepoTest {

    @Autowired
    private Invoicerepo invoicerepo;

    @Test
    public void testSave() {
        // Given
        Invoice invoice = new Invoice();
        invoice.setClientName("Client ABC"); // Providing clientName
        invoice.setAmount(1000.0);
        invoice.setDescription("Testing App"); // Providing description
        invoice.setInvoiceDate("2023-05-20"); // Providing invoiceDate as String

        // When
        Invoice savedInvoice = invoicerepo.save(invoice);

        // Then
        assertNotNull(savedInvoice.getClientId());
        assertEquals("Client ABC", savedInvoice.getClientName());
    }

    @Test
    public void testFindById() {
        // Given
        Invoice invoice = new Invoice();
        invoice.setClientName("Client XYZ");
        invoice.setAmount(2000.0);
        invoice.setDescription("Testing App");
        invoice.setInvoiceDate("2023-05-21"); // Providing invoiceDate as String
        Invoice savedInvoice = invoicerepo.save(invoice);
        Long invoiceId = savedInvoice.getClientId();

        // When
        Invoice foundInvoice = invoicerepo.findById(invoiceId).orElse(null);

        // Then
        assertNotNull(foundInvoice);
        assertEquals("Client XYZ", foundInvoice.getClientName());
    }

    @Test
    public void testUpdate() {
        // Given
        Invoice invoice = new Invoice();
        invoice.setClientName("Client XYZ");
        invoice.setAmount(2000.0);
        invoice.setDescription("Testing App");
        invoice.setInvoiceDate("2023-05-21"); // Providing invoiceDate as String
        Invoice savedInvoice = invoicerepo.save(invoice);
        Long invoiceId = savedInvoice.getClientId();

        // When
        Invoice foundInvoice = invoicerepo.findById(invoiceId).orElse(null);
        assertNotNull(foundInvoice);
        foundInvoice.setClientName("Updated Client XYZ");
        invoicerepo.save(foundInvoice);

        // Then
        Invoice updatedInvoice = invoicerepo.findById(invoiceId).orElse(null);
        assertNotNull(updatedInvoice);
        assertEquals("Updated Client XYZ", updatedInvoice.getClientName());
    }

    @Test
    public void testDelete() {
        // Given
        Invoice invoice = new Invoice();
        invoice.setClientName("Client XYZ");
        invoice.setAmount(2000.0);
        invoice.setDescription("Testing App");
        invoice.setInvoiceDate("2023-05-21"); // Providing invoiceDate as String
        Invoice savedInvoice = invoicerepo.save(invoice);
        Long invoiceId = savedInvoice.getClientId();

        // When
        invoicerepo.deleteById(invoiceId);

        // Then
        assertNull(invoicerepo.findById(invoiceId).orElse(null));
    }

}
