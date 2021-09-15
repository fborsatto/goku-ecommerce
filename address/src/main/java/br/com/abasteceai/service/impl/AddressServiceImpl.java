package br.com.abasteceai.service.impl;

import br.com.abasteceai.model.dto.AddressDTO;
import br.com.abasteceai.repository.AddressRepository;
import br.com.abasteceai.mapper.AddressMapper;
import br.com.abasteceai.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final AddressMapper mapper;

    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        return mapper.entityToDto(addressRepository.save(mapper.dtoToEntity(addressDTO)));
    }
}