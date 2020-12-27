package cc.dividebyzero.spring.postal;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.*;

@Data
@NoArgsConstructor
@SuperBuilder
public class PostalAddress {

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String houseNumber;

}
