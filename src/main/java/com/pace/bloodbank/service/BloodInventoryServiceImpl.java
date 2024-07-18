package com.pace.bloodbank.service;

import com.pace.bloodbank.model.BloodInventory;
import com.pace.bloodbank.repository.BloodInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodInventoryServiceImpl implements BloodInventoryService {

    private final BloodInventoryRepository bloodInventoryRepository;

    @Autowired
    public BloodInventoryServiceImpl(BloodInventoryRepository bloodInventoryRepository) {
        this.bloodInventoryRepository = bloodInventoryRepository;
    }

    @Override
    public List<BloodInventory> getAllBloodInventory() {
        return bloodInventoryRepository.findAll();
    }

    @Override
    public BloodInventory getBloodInventoryById(Long id) {
        Optional<BloodInventory> bloodInventory = bloodInventoryRepository.findById(id);
        return bloodInventory.orElse(null);
    }

    @Override
    public BloodInventory addBloodInventory(BloodInventory bloodInventory) {
        return bloodInventoryRepository.save(bloodInventory);
    }

    @Override
    public BloodInventory updateBloodInventory(BloodInventory bloodInventory) {
        // Check if blood inventory with given ID exists
        Optional<BloodInventory> existingBloodInventoryOptional = bloodInventoryRepository.findById(bloodInventory.getId());
        if (existingBloodInventoryOptional.isPresent()) {
            BloodInventory existingBloodInventory = existingBloodInventoryOptional.get();
            // Update existing blood inventory with new data
            existingBloodInventory.setBloodGroup(bloodInventory.getBloodGroup());
            existingBloodInventory.setUnitsAvailable(bloodInventory.getUnitsAvailable());
            // Save and return updated blood inventory
            return bloodInventoryRepository.save(existingBloodInventory);
        } else {
            return null; // Blood inventory not found
        }
    }

    @Override
    public boolean deleteBloodInventory(Long id) {
        Optional<BloodInventory> bloodInventory = bloodInventoryRepository.findById(id);
        if (bloodInventory.isPresent()) {
            bloodInventoryRepository.delete(bloodInventory.get());
            return true;
        } else {
            return false; // Blood inventory not found
        }
    }

    

}