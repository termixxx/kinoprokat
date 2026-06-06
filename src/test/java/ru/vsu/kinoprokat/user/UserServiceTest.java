package ru.vsu.kinoprokat.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    CreateUserDto createUserDto;

    @BeforeEach
    void setUp() {
        createUserDto = new CreateUserDto(
                "Ivan",
                "Ivanov",
                "ivan@test.ru"
        );
    }

    @AfterEach
    void tearDown() {
        createUserDto = null;
    }

    @Test
    void shouldCreateUser() {

        UserEntity savedUser = UserEntity.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ivan@test.ru")
                .build();

        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(savedUser);

        UserDto result = userService.create(createUserDto);

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.firstName()).isEqualTo("Ivan");
        assertThat(result.email()).isEqualTo("ivan@test.ru");

        verify(userRepository).save(any(UserEntity.class));
    }
}