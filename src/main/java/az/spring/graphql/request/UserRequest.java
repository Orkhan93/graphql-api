package az.spring.graphql.request;

import az.spring.graphql.enums.Role;
import lombok.Data;

@Data
public class UserRequest {

    private Long id;
    private String username;
    private String mail;
    private Role role;

}