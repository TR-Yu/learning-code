package cn.prinf.demos.junit.spring.controller;

import cn.prinf.demos.junit.spring.entity.User;
import cn.prinf.demos.junit.spring.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 测试 controller层 的
 * 1. spring mvc相关的bean需要初始化，采用 @WebMvcTest(需要测试的类)
 * 2. 对内部调用的server类需要使用@MockBean注入
 * 3. 模拟的mvc 通过字段注入获取到MockMvc进行模拟请求
 * 4. 主要是测试返回的
 */

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_list_users() throws Exception {
        given(userService.listAll()).willReturn(
                Arrays.asList(new User() {{
                    setId(01L);
                    setUsername("Test user");
                    setPassword("123456");
                    setCreateAt(Instant.now());
                    setUpdateAt(Instant.now());
                }})
        );

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON);
        ResultActions perform = this.mvc.perform(builder);
        ResultMatcher okMatcher = status().isOk();
        perform.andExpect(okMatcher);
    }

    @Test
    public void should_insert_users() throws Exception {
        // 构造参数
        User user = new User();
        user.setId(03L);
        user.setUsername("Test user03");
        user.setPassword("123456");
        user.setCreateAt(Instant.now());
        user.setUpdateAt(Instant.now());

        given(userService.add(isA(User.class))).willReturn(user);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions perform = this.mvc.perform(requestBuilder);
        ResultMatcher okMatcher = status().isOk();
        perform.andExpect(okMatcher);
    }
}
