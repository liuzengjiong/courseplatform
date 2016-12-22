package com.courseplatform.app;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ye
 * @dete 2016年 12月6日 1:24:53
 */
public class AppTest {

    @Test
    public void testMain() throws IOException {
        Map<String, String> data = new HashMap<>();
        data.put("number", "131544215");
        data.put("passwd", "131544215ccy");
        Response response = Jsoup.connect("http://localhost:8080/jwxt/confirmTeach").data(data).method(Method.POST).execute();
        System.out.println(response.contentType());
        System.out.println(response.charset());
        System.out.println(response.body());
    }
}
