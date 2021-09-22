package br.com.abasteceai.controller;

import br.com.abasteceai.common.exception.config.GlobalExceptionHandler;
import br.com.abasteceai.mapper.AddressMapper;
import br.com.abasteceai.mapper.AddressMapperImpl;
import br.com.abasteceai.model.dto.AddressDTO;
import br.com.abasteceai.model.entity.Address;
import br.com.abasteceai.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {AddressController.class, GlobalExceptionHandler.class})
@WebMvcTest(controllers = AddressController.class)
class AddressControllerTest {

    @MockBean
    private AddressService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @WithMockUser
    @Test
    @DisplayName("When getting address, should return 200 and address list")
    void getAllAddresses() {
        List<Address> addresses = AddressMocks.generateAddresses(5);

        AddressMapper addressMapper = new AddressMapperImpl();

        List<AddressDTO> response = addresses.stream()
                .map(addressMapper::entityToDto)
                .collect(Collectors.toList());

        when(service.findByZipCode(any())).thenReturn(response);

        this.mockMvc.perform(get("/api/address")
                        .param("zipCode", "9999999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @SneakyThrows
    @WithMockUser
    @Test
    @DisplayName("When getting address and not send a zipCode param , should return 400")
    void getAllAddressesWIthoutParam() {
        this.mockMvc.perform(get("/api/address")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    @WithMockUser
    @Test
    @DisplayName("When post address, should return 201")
    void createAddressWithSuccess() {
        AddressMapper addressMapper = new AddressMapperImpl();
        when(service.create(any())).thenReturn(addressMapper.entityToDto(AddressMocks.generateAddress()));

        this.mockMvc.perform(post("/api/address")
                        .content(objectMapper.writeValueAsString(addressMapper.entityToDto(AddressMocks.generateAddress())))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @SneakyThrows
    @WithMockUser(password = "user")
    @Test
    @DisplayName("When update address, should return 204")
    void updateAddressWithSuccess() {
        AddressMapper addressMapper = new AddressMapperImpl();
        doNothing().when(service).update(any(), any());

        this.mockMvc.perform(put("/api/address/{addressId}", "1234asda1212")
                        .content(objectMapper.writeValueAsString(addressMapper.entityToDto(AddressMocks.generateAddress())))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @SneakyThrows
    @WithMockUser
    @Test
    @DisplayName("When delete address, should return 204")
    void deleteAddressWithSuccess() {
        AddressMapper addressMapper = new AddressMapperImpl();
        doNothing().when(service).delete(any());

        this.mockMvc.perform(delete("/api/address/{addressId}", "1234asda1212")
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
}