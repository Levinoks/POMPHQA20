package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
public class ContactDTO {
    String name;
    String lastName;
    String email;
    String phone;
    String address;
    String description;

}
