package br.com.abasteceai.mapper;


import br.com.abasteceai.model.dto.AddressDTO;
import br.com.abasteceai.model.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address dtoToEntity(AddressDTO dto);

    AddressDTO entityToDto(Address address);
}
