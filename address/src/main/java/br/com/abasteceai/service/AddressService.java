package br.com.abasteceai.service;

import br.com.abasteceai.model.dto.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    AddressDTO create(AddressDTO addressDTO);

    void update(AddressDTO addressDTO, String addressId);

    void delete(String addressId);

    List<AddressDTO> findByZipCode(String zipCode);
}