package builder;


import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
}
