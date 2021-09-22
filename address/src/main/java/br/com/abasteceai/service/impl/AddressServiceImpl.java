package br.com.abasteceai.service.impl;

import br.com.abasteceai.common.exception.model.NotFoundException;
import br.com.abasteceai.model.dto.AddressDTO;
import br.com.abasteceai.model.entity.Address;
import br.com.abasteceai.repository.AddressRepository;
import br.com.abasteceai.mapper.AddressMapper;
import br.com.abasteceai.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CachePut(value = "addresses", key = "#addressDTO.zipCode")
    public AddressDTO create(AddressDTO addressDTO) {
        return mapper.entityToDto(addressRepository.save(mapper.dtoToEntity(addressDTO)));
    }

    @Override
    @CachePut(value = "addresses", key = "#addressDTO.zipCode")
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
    @CacheEvict(value = "addresses", allEntries = true)
    public void delete(String addressId) {
        Optional<Address> address = addressRepository.findById(addressId);

        if (!address.isPresent()) {
            throw new NotFoundException("Address not found for delete");
        }

        addressRepository.delete(address.get());
    }

    @Override
    @Cacheable(value = "addresses", key = "#zipCode")
    public List<AddressDTO> findByZipCode(String zipCode) {
        log.info("Getting addresses from mongoDB ---");
        return addressRepository.findAllByZipCode(zipCode).stream().map(mapper::entityToDto).collect(Collectors.toList());
    }
}