package com.milypol.security.address;

import java.util.List;

public class AddressServiceImpl implements AddressService{

    private final AddressRepo addressRepo;

    public AddressServiceImpl(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Override
    public Address getAddressById(Integer id) {
        return addressRepo.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepo.save(address);
    }

    @Override
    public void deleteAddress(Integer id) {
        addressRepo.deleteById(id);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepo.findAll();
    }
}
