package com.milypol.security.address;

import java.util.List;

public interface AddressService {
    Address getAddressById(Integer id);
    Address saveAddress(Address address);
    void deleteAddress(Integer id);
    List<Address> getAllAddresses();
}
