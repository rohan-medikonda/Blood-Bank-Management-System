package com.pace.bloodbank.service;

import com.pace.bloodbank.model.Donor;

import java.util.List;

public interface DonorService {

    List<Donor> getAllDonors();

    Donor getDonorById(Long id);

    Donor addDonor(Donor donor);

    Donor updateDonor(Donor donor);

    boolean deleteDonor(Long id);

    

}