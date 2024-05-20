package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.User;
import com.example.demo.service.InvoiceServiceImplementation;
import com.example.demo.service.UsersserviceImplementation;

import java.util.List;

@RestController
@CrossOrigin // Enable Cross-Origin requests
public class InvoiceController {

    private final InvoiceServiceImplementation invoiceService;
    private final UsersserviceImplementation userService;

    @Autowired
    public InvoiceController(InvoiceServiceImplementation invoiceService, UsersserviceImplementation userService) {
        this.invoiceService = invoiceService;
        this.userService = userService;
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok().body(invoices);
    }

    @PostMapping("/invoices")
    public ResponseEntity<String> addInvoice(@RequestBody Invoice invoice, @RequestParam Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            invoice.setUser(user);
            invoiceService.addInvoice(invoice);
            return ResponseEntity.status(HttpStatus.CREATED).body("Invoice added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping("/invoices/update/{clientId}")
    public ResponseEntity<String> updateInvoice(@RequestBody Invoice invoice, @PathVariable("clientId") Long clientId) {
        Invoice existingInvoice = invoiceService.getInvoiceByClientId(clientId);
        if (existingInvoice != null) {
            existingInvoice.setClientName(invoice.getClientName());
            existingInvoice.setAmount(invoice.getAmount());
            existingInvoice.setInvoiceDate(invoice.getInvoiceDate());
            existingInvoice.setDescription(invoice.getDescription());
            invoiceService.updateInvoice(existingInvoice);
            return ResponseEntity.status(HttpStatus.OK).body("Invoice updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
        }
    }

    @DeleteMapping("/invoices/delete/{clientId}")
    public ResponseEntity<String> deleteInvoice(@PathVariable("clientId") Long clientId) {
        Invoice existingInvoice = invoiceService.getInvoiceByClientId(clientId);
        if (existingInvoice != null) {
            invoiceService.deleteInvoice(clientId);
            return ResponseEntity.status(HttpStatus.OK).body("Invoice deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
        }
    }

    @GetMapping("/invoices/user/{userId}")
    public ResponseEntity<List<Invoice>> getInvoicesByUserId(@PathVariable Long userId) {
        List<Invoice> invoices = invoiceService.getInvoicesByUserId(userId);
        if (invoices != null && !invoices.isEmpty()) {
            return ResponseEntity.ok().body(invoices);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

























///////////////////////////USING DTO>>/////////////////////////////////


//package com.example.demo.controller;
//
//import com.example.demo.dto.InvoiceDto;
//import com.example.demo.entity.Invoices;
//import com.example.demo.service.InvoiceServiceImplementation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class InvoiceController {
//
//private final InvoiceServiceImplementation invoiceService;
//
//@Autowired
//public InvoiceController(InvoiceServiceImplementation invoiceService) {
//    this.invoiceService = invoiceService;
//}
//
//@GetMapping("/invoices")
//public ResponseEntity<List<Invoices>> getAllInvoices(@RequestParam("username") String username) {
//    List<Invoices> invoices = invoiceService.getInvoicesByUsername(username);
//    return ResponseEntity.ok().body(invoices);
//}
//
////@PostMapping("/invoices/add/{userId}")
////public ResponseEntity<String> addInvoice(@RequestBody InvoiceDto invoiceDto, @PathVariable("userId") Long userId) {
////    invoiceService.addInvoice(invoiceDto, userId);
////    return ResponseEntity.status(HttpStatus.OK).body("Invoice added successfully");
////}
//@PostMapping("/invoices")
//public ResponseEntity<String> saveInvoice(@RequestParam Long userId, @RequestBody InvoiceDto invoicesto) {
//    try {
//        // Save invoice with the associated user ID
//        invoiceService.addInvoice(userId, invoicesto);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Invoice added successfully");
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add invoice");
//    }}
//
//@PutMapping("/invoices/update/{clientId}")
//public ResponseEntity<String> updateInvoice(@RequestBody InvoiceDto invoiceDto, @PathVariable("clientId") Long clientId) {
//    invoiceService.updateInvoice(invoiceDto, clientId);
//    return ResponseEntity.status(HttpStatus.OK).body("Invoice updated successfully");
//}
//
//@DeleteMapping("/invoices/delete/{clientId}")
//public ResponseEntity<String> deleteInvoice(@PathVariable("clientId") Long clientId) {
//    if (invoiceService.getInvoiceByClientId(clientId) != null) {
//        invoiceService.deleteInvoice(clientId);
//        return ResponseEntity.status(HttpStatus.OK).body("Invoice deleted successfully");
//    } else {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
//    }
//}
//}



