package com.pace.bloodbank.controller;

import com.pace.bloodbank.model.Donor;
import com.pace.bloodbank.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors") // Base URL mapping for this controller
public class DonorController {

    private final DonorService donorService;

    @Autowired
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    // GET endpoint to fetch all donors
    @GetMapping
    public ResponseEntity<List<Donor>> getAllDonors() {
        List<Donor> donors = donorService.getAllDonors();
        return new ResponseEntity<>(donors, HttpStatus.OK);
    }

    // GET endpoint to fetch donor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable("id") Long id) {
        Donor donor = donorService.getDonorById(id);
        if (donor != null) {
            return new ResponseEntity<>(donor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST endpoint to add a new donor
    @PostMapping
    public ResponseEntity<Donor> addDonor(@RequestBody Donor donor) {
        Donor savedDonor = donorService.addDonor(donor);
        return new ResponseEntity<>(savedDonor, HttpStatus.CREATED);
    }

    // PUT endpoint to update an existing donor
    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable("id") Long id, @RequestBody Donor donor) {
        donor.setId(id);
        Donor updatedDonor = donorService.updateDonor(donor);
        if (updatedDonor != null) {
            return new ResponseEntity<>(updatedDonor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE endpoint to delete a donor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable("id") Long id) {
        boolean deleted = donorService.deleteDonor(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}