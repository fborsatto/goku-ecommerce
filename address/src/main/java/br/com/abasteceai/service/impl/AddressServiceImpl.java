package br.com.abasteceai.service.impl;

import br.com.abasteceai.common.exception.NotFoundException;
import br.com.abasteceai.model.dto.AddressDTO;
import br.com.abasteceai.model.entity.Address;
import br.com.abasteceai.repository.AddressRepository;
import br.com.abasteceai.mapper.AddressMapper;
import br.com.abasteceai.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final AddressMapper mapper;

    @Override
    public AddressDTO create(AddressDTO addressDTO) {
        return mapper.entityToDto(addressRepository.save(mapper.dtoToEntity(addressDTO)));
    }

    @Override
    public void update(AddressDTO addressDTO, String addressId) {

        Optional<Address> address = addressRepository.findById(addressId);

        if (!address.isPresent()) {
            throw new NotFoundException("Address not found for update");
        }

        Address addressUpdated = mapper.dtoToEntity(addressDTO);
        addressUpdated.setAddressId(addressId);

        addressRepository.save(addressUpdated);
    }

    @Override
    public void delete(String addressId) {
        Optional<Address> address = addressRepository.findById(addressId);

        if (!address.isPresent()) {
            throw new NotFoundException("Address not found for delete");
        }

        addressRepository.delete(address.get());
    }

    @Override
    public List<AddressDTO> findByZipCode(String zipCode) {
        return addressRepository.findAllByZipCode(zipCode).stream().map(mapper::entityToDto).collect(Collectors.toList());
    }
}