package br.com.abasteceai.mapper;

import br.com.abasteceai.controller.AddressMocks;
import br.com.abasteceai.model.dto.AddressDTO;
import br.com.abasteceai.model.entity.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddressMapperTest {

    @Test
    void shouldMappEntityToDto(){
        AddressMapper mapper = new AddressMapperImpl();

        final Address address = AddressMocks.generateAddress();
        AddressDTO dto = mapper.entityToDto(address);

        assertEquals(address.getAddressId(), dto.getAddressId());
        assertEquals(address.getCity(), dto.getCity());
        assertEquals(address.getComplement(), dto.getComplement());
        assertEquals(address.getCountry(), dto.getCountry());
        assertEquals(address.getNumber(), dto.getNumber());
        assertEquals(address.getState(), dto.getState());
        assertEquals(address.getStreet(), dto.getStreet());
        assertEquals(address.getZipCode(), dto.getZipCode());
    }

    @Test
    void shouldMapDtoToEntity(){
        AddressMapper mapper = new AddressMapperImpl();

        final AddressDTO dto = AddressMocks.generateAddressDTO();
        Address address = mapper.dtoToEntity(dto);

        assertEquals(dto.getAddressId(), address.getAddressId());
        assertEquals(dto.getCity(), address.getCity());
        assertEquals(dto.getComplement(), address.getComplement());
        assertEquals(dto.getCountry(), address.getCountry());
        assertEquals(dto.getNumber(), address.getNumber());
        assertEquals(dto.getState(), address.getState());
        assertEquals(dto.getStreet(), address.getStreet());
        assertEquals(dto.getZipCode(), address.getZipCode());
    }

    @Test
    void whenAddressDtoIsNullShouldReturnNull() {
        AddressMapper mapper = new AddressMapperImpl();

        final AddressDTO dto = null;
        Address address = mapper.dtoToEntity(dto);

        assertNull(address);
    }

    @Test
    void whenAddressIsNullShouldReturnNull() {
        AddressMapper mapper = new AddressMapperImpl();

        final Address address = null;
        AddressDTO dto = mapper.entityToDto(address);

        assertNull(dto);
    }

}