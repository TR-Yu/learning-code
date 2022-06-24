package com.yu.mock.service;

import com.yu.mock.entity.User;
import com.yu.mock.entity.service.EmailService;
import com.yu.mock.entity.service.EncryptionService;
import com.yu.mock.entity.service.UserService;
import com.yu.mock.reposity.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

/**
 * @author YU
 * @date 2022-06-18 15:43
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class})
public class UserServiceTest {
    @Mock
    UserRepository mockedUserRepository;
    @Mock
    EmailService mockedEmailService;
    @Spy
    EncryptionService mockedEncryptionService = new EncryptionService();

    @InjectMocks
    UserService userService;

    @Test
    public void register() {
        // mock 前生成一个 Instant 实例
        Instant moment = Instant.ofEpochSecond(1596494464);

        // 将公用的静态方法
        // Instant.now()包装好，然后mock 并设定期望返回值
        PowerMockito.mockStatic(Instant.class);
        PowerMockito.when(Instant.now()).thenReturn(moment);
        Mockito.when(mockedEncryptionService.sha256("xxx"))
                .thenReturn("cd2eb0837c9b4c962c22d2ff8b5441b7b45805887f051d39bf133b583baf6860");

        // given
        User user = new User("admin@test.com", "xxx", "admin", Instant.now());

        // when
        userService.register(user);

        // then
        Mockito.verify(mockedEmailService).sendEmail(
                ArgumentMatchers.eq("admin@test.com"),
                ArgumentMatchers.eq("Register Notification"),
                ArgumentMatchers.eq("Register Account successful! your username is admin"));

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(mockedUserRepository).saveUser(argument.capture());

        assertEquals("admin@test.com", argument.getValue().getEmail());
        assertEquals("admin", argument.getValue().getName());
        assertEquals("cd2eb0837c9b4c962c22d2ff8b5441b7b45805887f051d39bf133b583baf6860", argument.getValue().getPassword());
        assertEquals(moment, argument.getValue().getCreateAt());

    }
}