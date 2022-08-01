package ar.gob.inti.argentinaprograma.miportfolio.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.dto.request.RequestAddress;
import ar.gob.inti.argentinaprograma.miportfolio.dto.response.ResponseAddress;
import ar.gob.inti.argentinaprograma.miportfolio.model.Address;

@Component
public class MapperAddress {
    
    public List<ResponseAddress> toResponseList(List<Address> addresses){
        List<ResponseAddress> addressesResponse = new ArrayList<>();
        
        if(addresses != null)
        {
            addressesResponse = addresses.stream().map(address -> { 
                return new ResponseAddress(
                    address.getId(), 
                    address.getStreet(), 
                    address.getNumber(), 
                    address.getZipCode(), 
                    address.getFloor(), 
                    address.getApartment()
                ); 
            }).collect(Collectors.toList());
        }
        return addressesResponse;
    }
    
    public Address toEntity(RequestAddress addressRequest){
        Address address = new Address();
        if (addressRequest != null){
                address.setStreet(addressRequest.getStreet());
                address.setNumber(addressRequest.getNumber());
                address.setZipCode(addressRequest.getZipCode());
                address.setFloor(addressRequest.getFloor());
                address.setApartment(addressRequest.getApartment());
        }
        return address;
    }

    public ResponseAddress toResponse(Address address){
        return new ResponseAddress(
            address.getId(),
            address.getStreet(),
            address.getNumber(),
            address.getZipCode(),
            address.getFloor(),
            address.getApartment()
        );
    }
}