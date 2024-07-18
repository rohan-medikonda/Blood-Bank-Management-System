package com.pace.bloodbank.controller;

import com.pace.bloodbank.model.Recipient;
import com.pace.bloodbank.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipients")
public class RecipientController {

    private final RecipientService recipientService;

    @Autowired
    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @GetMapping
    public ResponseEntity<List<Recipient>> getAllRecipients() {
        List<Recipient> recipients = recipientService.getAllRecipients();
        return ResponseEntity.ok(recipients);
    }

    @PostMapping
    public ResponseEntity<Recipient> addRecipient(@RequestBody Recipient recipient) {
        Recipient savedRecipient = recipientService.addRecipient(recipient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipient> getRecipientById(@PathVariable Long id) {
        Recipient recipient = recipientService.getRecipientById(id);
        if (recipient != null) {
            return ResponseEntity.ok(recipient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipient> updateRecipient(@PathVariable Long id, @RequestBody Recipient recipient) {
        recipient.setId(id); // Ensure the ID from path variable is set in the recipient object
        Recipient updatedRecipient = recipientService.updateRecipient(recipient);
        if (updatedRecipient != null) {
            return ResponseEntity.ok(updatedRecipient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipient(@PathVariable Long id) {
        boolean deleted = recipientService.deleteRecipient(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}