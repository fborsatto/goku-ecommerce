package br.com.abasteceai.model.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
