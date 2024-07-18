package com.pace.bloodbank.service;

import com.pace.bloodbank.model.Recipient;

import java.util.List;

public interface RecipientService {

    List<Recipient> getAllRecipients();

    Recipient getRecipientById(Long id);

    Recipient addRecipient(Recipient recipient);

    Recipient updateRecipient(Recipient recipient);

    boolean deleteRecipient(Long id);

    

}