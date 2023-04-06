package az.spring.graphql.model;

import az.spring.graphql.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "'user'")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity {

    private String username;
    private String mail;

    @Enumerated(EnumType.STRING)
    private Role role;

}