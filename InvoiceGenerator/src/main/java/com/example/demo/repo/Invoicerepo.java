package com.example.demo.repo;

import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.InvoiceDto;
import com.example.demo.entity.Invoice;

public interface Invoicerepo extends JpaRepository<Invoice, Long> { // Change Long to match the type of clientId
    List<Invoice> findByUserUsername(String username); // Adjusted method name to reflect the change from Users to User

    List<Invoice> findByUserUserId(Long userId);  // Method to find invoices by userId



//	public void addInvoice(Invoices invoice, Long userId);


}
