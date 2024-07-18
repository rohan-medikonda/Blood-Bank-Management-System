package com.pace.bloodbank.controller;

import com.pace.bloodbank.model.BloodInventory;
import com.pace.bloodbank.service.BloodInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloodInventory")
public class BloodInventoryController {

    private final BloodInventoryService bloodInventoryService;

    @Autowired
    public BloodInventoryController(BloodInventoryService bloodInventoryService) {
        this.bloodInventoryService = bloodInventoryService;
    }

    @GetMapping
    public ResponseEntity<List<BloodInventory>> getAllBloodInventory() {
        List<BloodInventory> bloodInventoryList = bloodInventoryService.getAllBloodInventory();
        return ResponseEntity.ok(bloodInventoryList);
    }

    @PostMapping
    public ResponseEntity<BloodInventory> addBloodInventory(@RequestBody BloodInventory bloodInventory) {
        BloodInventory addedBloodInventory = bloodInventoryService.addBloodInventory(bloodInventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBloodInventory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodInventory> getBloodInventoryById(@PathVariable Long id) {
        BloodInventory bloodInventory = bloodInventoryService.getBloodInventoryById(id);
        if (bloodInventory != null) {
            return ResponseEntity.ok(bloodInventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodInventory> updateBloodInventory(@PathVariable Long id, @RequestBody BloodInventory bloodInventory) {
        bloodInventory.setId(id); // Ensure the ID from path variable is set in the blood inventory object
        BloodInventory updatedBloodInventory = bloodInventoryService.updateBloodInventory(bloodInventory);
        if (updatedBloodInventory != null) {
            return ResponseEntity.ok(updatedBloodInventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodInventory(@PathVariable Long id) {
        boolean deleted = bloodInventoryService.deleteBloodInventory(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}