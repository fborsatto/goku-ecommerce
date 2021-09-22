package br.com.abasteceai.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
