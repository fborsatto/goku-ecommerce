package br.com.abasteceai.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
