package com.pace.bloodbank.service;

import com.pace.bloodbank.model.Donor;
import com.pace.bloodbank.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService {

    private final DonorRepository donorRepository;

    @Autowired
    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @Override
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    @Override
    public Donor getDonorById(Long id) {
        Optional<Donor> donor = donorRepository.findById(id);
        return donor.orElse(null);
    }

    @Override
    public Donor addDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    @Override
    public Donor updateDonor(Donor donor) {
        // Check if donor with given ID exists
        Optional<Donor> existingDonorOptional = donorRepository.findById(donor.getId());
        if (existingDonorOptional.isPresent()) {
            Donor existingDonor = existingDonorOptional.get();
            // Update existing donor with new data
            existingDonor.setName(donor.getName());
            existingDonor.setBloodGroup(donor.getBloodGroup());
            existingDonor.setPhoneNumber(donor.getPhoneNumber());
            // Save and return updated donor
            return donorRepository.save(existingDonor);
        } else {
            return null; // Donor not found
        }
    }

    @Override
    public boolean deleteDonor(Long id) {
        Optional<Donor> donor = donorRepository.findById(id);
        if (donor.isPresent()) {
            donorRepository.delete(donor.get());
            return true;
        } else {
            return false; // Donor not found
        }
    }

    

}