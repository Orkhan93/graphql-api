package az.spring.graphql.controller;

import az.spring.graphql.enums.Role;
import az.spring.graphql.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureGraphQlTester
public class UserControllerTest {

    @Autowired
    GraphQlTester graphQlTester;

    @BeforeEach
    void setUp() {
        createUser(new User("Orxan", "mustafa@mail.com", Role.ADMIN));
        createUser(new User("Rashad", "allahverdiyev@mail.com", Role.USER));
        createUser(new User("Kamal", "mammadov@mail.com", Role.ADMIN));
    }

    @Test
    void when_getAllUsers_should_return_userList() {

        // language=graphql
        String query =
                """
                        query {
                          getAllUsers{
                            id
                            username
                            role
                            created
                            updated
                          }
                        }
                        """;

        graphQlTester.document(query).execute().path("getAllUsers").entityList(User.class).hasSize(3);
    }

    @Test
    void when_createUser_shouldCreateNewUserAndReturnUser() {
        // language=graphql
        String mutation =
                """
                        mutation {
                          createUser(userRequest: {username: "Orxan", mail: "mustafa@mail.com", role: ADMIN}) {
                            id
                            username
                            mail
                            role
                            created
                            updated
                          }
                        }
                        """;
        graphQlTester
                .document(mutation)
                .execute()
                .path("createUser")
                .entity(User.class)
                .satisfies(
                        x -> {
                            assertEquals("Orxan", x.getUsername());
                            assertEquals("mustafa@mail.com", x.getMail());
                        });
    }

    @Test
    void when_getUserById_shouldReturnUser() {
        // language=graphql
        String query =
                """
                        query{
                            getUserById(id: 1){
                                id
                            }
                        }                       
                        """;
        graphQlTester.document(query)
                .execute()
                .path("getUserById")
                .entity(User.class)
                .satisfies(
                        x -> {
                            assertEquals(1L, x.getId());
                        }
                );
    }


    void createUser(User user) {
        String mutation =
                """
                        mutation {
                          createUser(userRequest: {username: "%s", mail: "%s", role: %s}) {
                            id
                            username
                            role
                            created
                            updated
                          }
                        }
                        """
                        .formatted(user.getUsername(), user.getMail(), user.getRole());
        graphQlTester.document(mutation).execute().path("createUser");
    }
}