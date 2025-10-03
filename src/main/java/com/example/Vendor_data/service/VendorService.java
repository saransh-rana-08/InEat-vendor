package com.example.Vendor_data.service;

import com.example.Vendor_data.model.Vendor;
import com.example.Vendor_data.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    // Get all vendors
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    // Get vendor by ID
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
    }

    // Create new vendor
    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    // Update vendor
    public Vendor updateVendor(Long id, Vendor vendorDetails) {
        Vendor vendor = getVendorById(id);
        if (vendorDetails.getName() != null) vendor.setName(vendorDetails.getName());
        if (vendorDetails.getContactNumber() != null) vendor.setContactNumber(vendorDetails.getContactNumber());
        if (vendorDetails.getUniversity() != null) vendor.setUniversity(vendorDetails.getUniversity());
        return vendorRepository.save(vendor);
    }

    // Delete vendor
    public void deleteVendor(Long id) {
        Vendor vendor = getVendorById(id);
        vendorRepository.delete(vendor);
    }
}
