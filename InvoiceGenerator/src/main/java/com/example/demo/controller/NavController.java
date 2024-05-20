
package com.example.demo.controller;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.User;
import com.example.demo.service.InvoiceServiceImplementation;
import com.example.demo.service.UsersserviceImplementation;

@RestController
@CrossOrigin
public class NavController {
    private final InvoiceServiceImplementation invoiceService;
    private final UsersserviceImplementation userService;

    @Autowired
    public NavController(InvoiceServiceImplementation invoiceService, UsersserviceImplementation userService) {
        this.invoiceService = invoiceService;
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        String username = user.getUsername();
        if (userService.checkUsername(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        user.setRegistrationDate(new Date());
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }

    @PostMapping("/validate")
    public ResponseEntity<Object> validate(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        String username = user.getUsername();
        String password = user.getPassword();

        if (!userService.checkUsername(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
        }

        if (!userService.validate(username, password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials, try again!");
        }

        User loggedInUser = userService.getByUsername(username);
        if (loggedInUser == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve user information");
        }

        List<Invoice> userInvoices = invoiceService.getInvoicesByUsername(username);
        Map<String, Object> response = new HashMap<>();
        response.put("userId", loggedInUser.getUserId());
        response.put("username", loggedInUser.getUsername());
        response.put("invoices", userInvoices);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}








