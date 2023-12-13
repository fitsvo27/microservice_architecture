package ru.skillbox.UsersApplication;

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
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.skillbox.UsersApplication.model.User;
import ru.skillbox.UsersApplication.repository.UserRepository;

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
            .withPassword("postgres")
            .withInitScript("db.sql");

    //	@DynamicPropertySource
//	static void configureProperties(DynamicPropertyRegistry registry) {
//		registry.add("spring.datasource.url", postgres::getJdbcUrl);
//		registry.add("spring.datasource.username", postgres::getUsername);
//		registry.add("spring.datasource.password", postgres::getPassword);
//	}
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
    @Transactional
    public void animalsShouldBeCorrect() {
        List<User> users = repository.findAll();
        String[] staticUsers = {"UserLogin1", "UserLogin2", "UserLogin3"};
        String[] actualUsers = users.stream().map(User::getLogin).toList().toArray(new String[0]);
        var arrayEquals = new ArrayEquals(actualUsers);
        arrayEquals.matches(staticUsers);

    }
}
