package br.com.abasteceai.service.impl;

import br.com.abasteceai.common.exception.model.NotFoundException;
import br.com.abasteceai.controller.AddressMocks;
import br.com.abasteceai.mapper.AddressMapper;
import br.com.abasteceai.mapper.AddressMapperImpl;
import br.com.abasteceai.model.dto.AddressDTO;
import br.com.abasteceai.repository.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.abasteceai.controller.AddressMocks.generateAddress;
import static br.com.abasteceai.controller.AddressMocks.generateAddressDTO;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    AddressRepository repository;

    @Spy
    AddressMapper mapper = new AddressMapperImpl();

    @InjectMocks
    AddressServiceImpl addressService;

    @Test
    void shouldSaveAddress() {
        when(repository.save(any())).thenReturn(generateAddress());
        addressService.create(generateAddressDTO());
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("When passed a id that exists, should update address")
    void shouldUpdateAddress() {
        AddressDTO dtoToUpdate = generateAddressDTO();
        dtoToUpdate.setAddressId("id");
        when(repository.findById(any())).thenReturn(Optional.of(generateAddress()));
        when(repository.save(any())).thenReturn(generateAddress());
        addressService.update(dtoToUpdate, dtoToUpdate.getAddressId());
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("When passed a id that not exists, should throw not found exception")
    void shouldNotUpdateAddress() {
        AddressDTO dtoToUpdate = generateAddressDTO();
        when(repository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class , () -> addressService.update(dtoToUpdate, "id"));
    }

    @Test
    void shoudReturnAddressList() {
        when(repository.findAllByZipCode(any())).thenReturn(AddressMocks.generateAddresses(3));
        List<AddressDTO> returnedList = addressService.findByZipCode(any());
        Assertions.assertEquals(3, returnedList.size());
    }

    @Test
    @DisplayName("When passed a id that exists, should delete address")
    void shouldDeleteAddress() {
        when(repository.findById(any())).thenReturn(Optional.of(generateAddress()));
        doNothing().when(repository).delete(any());
        addressService.delete("id");
        verify(repository, times(1)).delete(any());
    }

    @Test
    @DisplayName("When passed a id that not exists, should throw not found exception")
    void shouldNotDeleteAddress() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class , () -> addressService.delete("id"));
    }
}