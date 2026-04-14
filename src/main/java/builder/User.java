package builder;

import lombok.Builder;
import lombok.Data;


@Data
@Builder(toBuilder = true)
public class User {
    private String name;
    private int age;
    private String email;
    private Address address;
}
