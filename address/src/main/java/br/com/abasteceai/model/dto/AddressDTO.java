package br.com.abasteceai.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddressDTO {
    @NotNull @NotEmpty
    private String street;
    @NotNull @NotEmpty
    private String number;
    private String complement;
    @NotNull @NotEmpty
    private String city;
    @NotNull @NotEmpty
    private String state;
    @NotNull @NotEmpty
    private String country;
    @NotNull @NotEmpty
    private String zipCode;

    private String addressId;
}
