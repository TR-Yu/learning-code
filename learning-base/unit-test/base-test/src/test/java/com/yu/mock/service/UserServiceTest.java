package com.yu.mock.service;

import com.yu.mock.entity.User;
import com.yu.mock.reposity.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * 只使用mockito 测试 与参数化行为联系在一起使用，注意此时的
 *
 * @author YU
 * @date 2022-06-18 10:08
 */
@RunWith(Parameterized.class)
public class UserServiceTest {
    private static final String STR_FMT = "Register Account successful! your username is %s";

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    /**
     * 相当于 使用Mock new 出的新对象
     */
    @Mock
    UserRepository mockedUserRepository;

    @Mock
    EmailService mockedEmailService;

    @Spy
    EncryptionService mockedEncryptionService;

    /**
     * 需要写构造方法，
     * 功能：通过注解的方式
     * 会自动将已经mock好的对象放入到对应的内部并构造出对象，减少了样板代码的使用
     */
    @InjectMocks
    UserService userService;

    // 页面输入的传参
    private String inputUser;
    private String inputPassword;
    private String inputEmail;

    // 传递给其它方法的参数
    private String sendEmailContent;
    private String sendEmailSubject = "Register Notification";

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"admin","1234567","keep364@163.com",String.format(STR_FMT,"admin")},
                {"user01","12werpassword","admin@test.com",String.format(STR_FMT,"user01")}
        });
    }

    public UserServiceTest(String inputUser, String inputPassword,
                           String inputEmail, String sendEmailContent) {
        this.inputUser = inputUser;
        this.inputPassword = inputPassword;
        this.inputEmail = inputEmail;
        this.sendEmailContent = sendEmailContent;
    }

    @Test
    public void registerTestByMockito() {

        // 页面输入的传参
        String inputUser = "admin";
        String inputPassword = "123456";
        String inputEmail = "keep364@163.com";

        // 传递给其它方法的参数
        String encryptPassword = "cd2eb0837c9b4c962c22d2ff8b5441b7b45805887f051d39bf133b583baf6860";
        String sendEmailSubject = "Register Notification";
        String sendEmailContent = "Register Account successful! your username is admin";

        // 构造mock对象 并对对应的方法返回值做行为修改
        Mockito.when(mockedEncryptionService.sha256(Mockito.anyString()))
                .thenReturn(encryptPassword);

        // 入参构造
        User user = new User();
        user.setName("admin");
        user.setPassword("123456");
        user.setEmail("keep364@163.com");
        // 方法调用
        userService.register(user);

        /**
         * 传递给其它的入参结果校验
         * 1. 保存入库入参数据校验
         * 2. 加密数据校验
         * 3. 发送邮件数据验证
         *
         */
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(mockedUserRepository).saveUser(argument.capture());
        assertEquals(inputEmail,argument.getValue().getEmail());
        assertEquals(encryptPassword,argument.getValue().getPassword());
        assertEquals(inputUser,argument.getValue().getName());

        Mockito.verify(mockedEncryptionService).sha256(
                ArgumentMatchers.eq(inputPassword)
        );

        Mockito.verify(mockedEmailService).sendEmail(
                ArgumentMatchers.eq(inputEmail),
                ArgumentMatchers.eq(sendEmailSubject),
                ArgumentMatchers.eq(sendEmailContent)
        );

    }

    /**
     * 通过代理对象来测试，前提是 B --> A
     * 若是A本身是通过单元测试的，则可以直接用于测试，则不需要去构造 A，
     * 而是通过代理的方式来实现，也可以在不改变原来的逻辑下，对实现它的行为进行修改
     *
     */

    @Test
    public void registerTestBySpy() {
        // 入参构造
        User user = new User();
        user.setName(inputUser);
        user.setPassword(inputPassword);
        user.setEmail(inputEmail);

        Instant moment = Instant.ofEpochMilli(12345667778L);

        // 方法调用
        userService.register(user);

        /**
         * 传递给其它的入参结果校验
         * 1. 保存入库入参数据校验
         * 2. 加密数据校验
         * 3. 发送邮件数据验证
         *
         */
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(mockedUserRepository).saveUser(argument.capture());
        assertEquals(inputEmail,argument.getValue().getEmail());
        assertEquals(inputUser,argument.getValue().getName());

        Mockito.verify(mockedEncryptionService).sha256(
                ArgumentMatchers.anyString()
        );

        Mockito.verify(mockedEmailService).sendEmail(
                ArgumentMatchers.eq(inputEmail),
                ArgumentMatchers.eq(sendEmailSubject),
                ArgumentMatchers.eq(sendEmailContent)
        );

    }
}