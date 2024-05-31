package hellojpa;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {
    private String city;
    private String street;
    //@Column(name="ZIPCODE")
    private String zipcode;

    public Address(){

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    // setter를 만들지 않음 -> 값 변경 방지 || private 으로 만들기
    public String getCity() {
        return city;
    }
    public String getStreet() {
        return street;
    }
    public String getZipcode() {
        return zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
