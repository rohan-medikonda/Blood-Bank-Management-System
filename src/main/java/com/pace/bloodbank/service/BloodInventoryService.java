package com.pace.bloodbank.service;

import com.pace.bloodbank.model.BloodInventory;

import java.util.List;

public interface BloodInventoryService {

    List<BloodInventory> getAllBloodInventory();

    BloodInventory getBloodInventoryById(Long id);

    BloodInventory addBloodInventory(BloodInventory bloodInventory);

    BloodInventory updateBloodInventory(BloodInventory bloodInventory);

    boolean deleteBloodInventory(Long id);

    

}