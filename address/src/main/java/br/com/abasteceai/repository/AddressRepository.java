package br.com.abasteceai.repository;

import br.com.abasteceai.model.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

    List<Address> findAllByZipCode(String zipCode);

}
