package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.entity.Invoice;
import com.example.demo.repo.Invoicerepo;

@Service
public class InvoiceServiceImplementation {

    @Autowired
    private Invoicerepo invoiceRepository;

    public void addInvoice(Invoice invoice) {
        try {
            invoiceRepository.save(invoice);
            
        } catch (Exception e) {
            // Log the exception or perform some error handling
            e.printStackTrace();
            throw new RuntimeException("Failed to add invoice");
        }
    }

    public List<Invoice> getAllInvoices() {
        try {
            return invoiceRepository.findAll();
        } catch (Exception e) {
            // Log the exception or perform some error handling
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve all invoices");
        }
    }    
    
    public void updateInvoice(Invoice updatedInvoice) {
        try {
            invoiceRepository.save(updatedInvoice);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update invoice");
        }
    }
    public List<Invoice> getInvoicesByUsername(String username) {
        try {
            return invoiceRepository.findByUserUsername(username);
        } catch (Exception e) {
            // Log the exception or perform some error handling
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve invoices by username");
        }
    }

    public void deleteInvoice(Long clientId) {
        try {
            invoiceRepository.deleteById(clientId);
        } catch (Exception e) {
            // Log the exception or perform some error handling
            e.printStackTrace();
            throw new RuntimeException("Failed to delete invoice");
        }
    }

    public Invoice getInvoiceByClientId(Long clientId) {
        try {
            Optional<Invoice> optionalInvoice = invoiceRepository.findById(clientId);
            return optionalInvoice.orElse(null);
        } catch (Exception e) {
            // Log the exception or perform some error handling
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve invoice by client ID");
        }
    }
    
    
    
    
    public List<Invoice> getInvoicesByUserId(Long userId) {
        try {
            return invoiceRepository.findByUserUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve invoices by user ID");
        }
    }


}









































//package com.example.demo.dto;
//
//
//import com.example.demo.dto.InvoiceDto;
//import com.example.demo.entity.Invoice;
//import com.example.demo.entity.Invoices;
//import com.example.demo.entity.Users;
//import com.example.demo.repo.Invoicerepo;
//import com.example.demo.service.UsersserviceImplementation;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class InvoiceServiceDto {
//
//@Autowired
//private Invoicerepo invoiceRepository;
//
//@Autowired
//private UsersserviceImplementation userService;
//
//public void addInvoice(InvoiceDto invoiceDto, Long userId) {
//    User user = userService.getUserById(userId);
//    Invoice invoice = new Invoice(invoiceDto.getClientName(), invoiceDto.getAmount(), invoiceDto.getInvoiceDate(), invoiceDto.getDescription(), user);
//    invoiceRepository.save(invoice);
//}
//
//public void updateInvoice(InvoiceDto updatedInvoice, Long clientId) {
//    Invoice invoice = invoiceRepository.findById(clientId).orElse(null);
//    if (invoice != null) {
//        invoice.setClientName(updatedInvoice.getClientName());
//        invoice.setAmount(updatedInvoice.getAmount());
////        invoice.setInvoiceDate(updatedInvoice.getInvoiceDate());
//        invoice.setDescription(updatedInvoice.getDescription());
//        invoiceRepository.save(invoice);
//    }
//}
//
//// Other methods remain the same
//
//public void deleteInvoice(Long clientId) {
//    invoiceRepository.deleteById(clientId);
//}
//
//public InvoiceDto getInvoiceByClientId(Long clientId) {
//    Invoices invoice = invoiceRepository.findById(clientId).orElse(null);
//    if (invoice != null) {
//        return new InvoiceDto(
//            invoice.getClientName(),
//            invoice.getAmount(),
//            invoice.getInvoiceDate(),
//            invoice.getDescription()
//        );
//    }
//    return null;
//}
//public List<Invoice> getInvoicesByUsername(String username) {
//    return invoiceRepository.findByUserUsername(username);
//}
//
//	public void addInvoice(Long userId, InvoiceDto invoiceDto) {
//		
//		  User user = userService.getUserById(userId);
//	        Invoice invoice = new Invoice(invoiceDto.getClientName(), invoiceDto.getAmount(), invoiceDto.getInvoiceDate(), invoiceDto.getDescription(), user);
//	        invoiceRepository.save(invoice);
//		// TODO Auto-generated method stub
//		
//	}
//}
