package br.com.abasteceai.service;

import br.com.abasteceai.model.dto.AddressDTO;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    AddressDTO save(AddressDTO addressDTO);
}