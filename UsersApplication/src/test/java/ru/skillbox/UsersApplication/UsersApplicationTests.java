package ru.skillbox.UsersApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.ArrayEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.skillbox.UsersApplication.dto.UserDto;
import ru.skillbox.UsersApplication.model.User;
import ru.skillbox.UsersApplication.repository.UserRepository;
import ru.skillbox.UsersApplication.service.UserService;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {UsersApplicationTests.Initializer.class})
@Testcontainers
class UsersApplicationTests {
    @LocalServerPort
    private Integer port;
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine")
            .withDatabaseName("users")
            .withUsername("postgres")
            .withPassword("postgres");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgres.getJdbcUrl(),
                    "spring.datasource.username=" + postgres.getUsername(),
                    "spring.datasource.password=" + postgres.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    UserRepository repository;
    @Autowired
    UserService userService;

    @BeforeEach
    @Transactional
    void setUp() {
        repository.deleteAll();
        repository.save(new User(null, "UserLogin1", "User LastName 1", "User FirstName 1",
                "User MiddleName 1", 'M',
                new Date(), "User AvatarLink 1", "User Email 1", "Phone 123123", false));
        repository.save(new User(null, "UserLogin2", "User LastName 2", "User FirstName 2",
                "User MiddleName 2", 'M',
                new Date(), "User AvatarLink 2", "User Email 2", "Phone 234234", false));
        repository.save(new User(null, "UserLogin3", "User LastName 3", "User FirstName 3",
                "User MiddleName 3", 'F',
                new Date(), "User AvatarLink 3", "User Email 3", "Phone 345345", true));

    }

    @Test
    void shouldGetCountUsers() {
        assertThat(repository.findAll()).hasSize(3);
    }

    @Test
    public void listUsersShouldBeEquals() {
        List<UserDto> users = userService.getUsers();
        String[] staticUsers = {"UserLogin1", "UserLogin2", "UserLogin3"};
        String[] actualUsers = users.stream().map(UserDto::getLogin).toList().toArray(new String[0]);
        var arrayEquals = new ArrayEquals(actualUsers);
        arrayEquals.matches(staticUsers);
    }

    @Test
    public void userShouldBeEquals() {
        UserDto actualUser = userService.getUser(1L);
        String expected = "UserLogin1";
        Assertions.assertEquals(actualUser.getLogin(), expected);
    }

    @Test
    @Transactional
    public void userShouldBeSaved() {
        UserDto expectedUser = userService.getUser(1L);
        expectedUser.setId(null);
        expectedUser.setLogin("UserLogin4");
        expectedUser.setFirstname("User FirstName 4");
        UserDto actualUser = userService.saveUser(expectedUser);
        Assertions.assertNotNull(actualUser);
    }

    @Test
    @Transactional
    public void userShouldBeUpdated() {
        UserDto expectedUser = userService.getUser(1L);
        expectedUser.setLogin("UserLoginNot");
        expectedUser.setFirstname("User FirstName not 1");
        UserDto actualUser = userService.updateUser(1L, expectedUser);
        Assertions.assertEquals(actualUser, expectedUser);
    }

    @Test
    @Transactional
    public void userShouldBeDeleted() {
        List<UserDto> usersBeforeDeleting = userService.getUsers();
        userService.deleteUser(1L);
        userService.deleteUser(2L);
        userService.deleteUser(3L);
        List<UserDto> usersAfterDeleting = userService.getUsers();
        Assertions.assertNotEquals(usersBeforeDeleting, usersAfterDeleting);
    }
}
