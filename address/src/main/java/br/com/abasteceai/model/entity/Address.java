package br.com.abasteceai.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
@Data
public class Address {

    @Id
    private String addressId;

    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
