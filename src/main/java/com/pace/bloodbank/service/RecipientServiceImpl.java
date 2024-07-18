package com.pace.bloodbank.service;

import com.pace.bloodbank.model.Recipient;
import com.pace.bloodbank.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipientServiceImpl implements RecipientService {

    private final RecipientRepository recipientRepository;

    @Autowired
    public RecipientServiceImpl(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    @Override
    public List<Recipient> getAllRecipients() {
        return recipientRepository.findAll();
    }

    @Override
    public Recipient getRecipientById(Long id) {
        Optional<Recipient> recipient = recipientRepository.findById(id);
        return recipient.orElse(null);
    }

    @Override
    public Recipient addRecipient(Recipient recipient) {
        return recipientRepository.save(recipient);
    }

    @Override
    public Recipient updateRecipient(Recipient recipient) {
        // Check if recipient with given ID exists
        Optional<Recipient> existingRecipientOptional = recipientRepository.findById(recipient.getId());
        if (existingRecipientOptional.isPresent()) {
            Recipient existingRecipient = existingRecipientOptional.get();
            // Update existing recipient with new data
            existingRecipient.setName(recipient.getName());
            existingRecipient.setBloodGroup(recipient.getBloodGroup());
            existingRecipient.setPhoneNumber(recipient.getPhoneNumber());
            // Save and return updated recipient
            return recipientRepository.save(existingRecipient);
        } else {
            return null; // Recipient not found
        }
    }

    @Override
    public boolean deleteRecipient(Long id) {
        Optional<Recipient> recipient = recipientRepository.findById(id);
        if (recipient.isPresent()) {
            recipientRepository.delete(recipient.get());
            return true;
        } else {
            return false; // Recipient not found
        }
    }

    

}