package br.com.abasteceai.controller;

import br.com.abasteceai.model.dto.AddressDTO;
import br.com.abasteceai.model.entity.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressMocks {
    public static List<Address> generateAddresses(int size) {
        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            addresses.add(generateAddress());
        }
        return addresses;
    }

    public static Address generateAddress() {
        return new Address(null, "av. Ipiranga", "643", "apto 101", "Porto Alegre", "RS", "Brasil", "91755040");
    }

    public static AddressDTO generateAddressDTO(){
        return new AddressDTO( "av. Ipiranga", "643", "apto 101", "Porto Alegre", "RS", "Brasil", "91755040", null);
    }
}
