package br.com.abasteceai.controller;

import br.com.abasteceai.model.dto.AddressDTO;
import br.com.abasteceai.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/address")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    @ResponseStatus(CREATED)
    public AddressDTO post(@Valid @RequestBody AddressDTO addressDTO) {
        return addressService.create(addressDTO);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<AddressDTO> get(@RequestParam("zipCode") String zipCode) {
        return addressService.findByZipCode(zipCode);
    }

    @PutMapping("/{addressId}")
    @ResponseStatus(NO_CONTENT)
    public void update(@Valid @RequestBody AddressDTO addressDTO, @PathVariable("addressId") String addressId) {
        addressService.update(addressDTO, addressId);
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable("addressId") String addressId) {
        addressService.delete(addressId);
    }
}
