package com.courseplatform.handler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @Author chen cy
 * Created by ye on 2016/12/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:/applicationContext.xml"})
//当然 你可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class TeacherHandlerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testInfo() throws Exception {
        Cookie cookie1 = new Cookie("account", "131544215");
        Cookie cookie2 = new Cookie("userCode", "5a66f14e226173b3eb135e65484f165f");
        mockMvc.perform((get("/teacher/info").cookie(cookie1, cookie2)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testAddCourse() throws Exception {
        String url = "/teacher/addCourse";
        Map<String,String> data = new HashMap<>();
        data.put("courseName","java课程设计");
        data.put("courseIntroduction","java课程设计的");
        test(url,data);
    }

    public void test(String url, Map<String, String> data) throws Exception {
        Cookie cookie1 = new Cookie("account", "131544215");
        Cookie cookie2 = new Cookie("userCode", "5a66f14e226173b3eb135e65484f165f");
        // post
        MockHttpServletRequestBuilder builder = post(url);
        // 参数
        for (Map.Entry<String, String> entry : data.entrySet()) {
            builder.param(entry.getKey(), entry.getValue());
        }
        // cookie
        builder.cookie(cookie1, cookie2);
        // 请求
        mockMvc.perform((builder))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
