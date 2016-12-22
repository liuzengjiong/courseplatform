package com.courseplatform.services.impl;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 教务系统的爬虫 用于验证学生信息
 *
 * @author ye
 * @dete 2016年11月13日 上午10:37:13
 */
public class JWXTSpider {
    private static final Logger log = LoggerFactory.getLogger(JWXTSpider.class);
    private static final String EAGE_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586";
    private static final String CHROME_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36";
    private static final String FIREFOX_USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";
    private static final String IE_USER_AGENT = "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)";

    /**
     * 登录教务系统
     *
     * @param number
     * @param passwd
     * @author ye
     * @dete 2016年11月13日 上午10:39:27
     */
    private static String login(String number, String passwd) {
        String url = "http://jwxt.gduf.edu.cn/jsxsd/xk/LoginToXk";
        Map<String, String> data = new HashMap<>();
        data.put("userAccount", number);
        data.put("userPassword", passwd);
        data.put("jzmmid", "1");
        data.put("encoded", getEncoded(number, passwd));
        try {
            log.info("---登录教务系统---" + number + "-" + passwd);
            Response response = Jsoup.connect(url).data(data)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("User-Agent", EAGE_USER_AGENT).header("Accept-Language", "zh-CN,zh;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate").header("Cache-Control", "max-age=0")
                    .header("Content-Length", "45").header("Upgrade-Insecure-Requests", "1")
                    .header("Connection", "keep-alive").header("Referer", "http://jwxt.gduf.edu.cn/jsxsd/")
                    .header("Host", "jwxt.gduf.edu.cn").header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Origin", "http://jwxt.gduf.edu.cn").method(Method.POST).timeout(5000).execute();
            Map<String, String> cookies = response.cookies();
            url = "http://jwxt.gduf.edu.cn/jsxsd/framework/xsMain.jsp";
            log.info("---进入教务系统首页---" + cookies);
            response = Jsoup.connect(url).cookies(cookies)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("User-Agent", EAGE_USER_AGENT).header("Accept-Language", "zh-CN,zh;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, sdch").header("Cache-Control", "max-age=0")
                    .header("Upgrade-Insecure-Requests", "1").header("Connection", "keep-alive")
                    .header("Referer", "http://jwxt.gduf.edu.cn/jsxsd/").header("Host", "jwxt.gduf.edu.cn")
                    // .header("Content-Type",
                    // "application/x-www-form-urlencoded")
                    .method(Method.GET).timeout(5000).execute();
            Document document = response.parse();
//            System.out.println(document);
            Element element = document.getElementById("Top1_divLoginName");
            return element.text();
        } catch (IOException e) {
            log.info(e.getLocalizedMessage() + "--" + e.getMessage() + "--" + e.fillInStackTrace());
            // TODO Auto-generated catch block
//            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取encode字段
     *
     * @param number
     * @param passwd
     * @return
     * @author ye
     * @dete 2016年11月13日 上午11:53:43
     */
    private static String getEncoded(String number, String passwd) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            String jsFileName = new LoginServiceImpl().getClass().getResource("/").getFile() + "JWXT/test.js"; // 读取js文件
            FileReader reader = new FileReader(jsFileName); // 执行指定脚本
            engine.eval(reader);

            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine; // 调用encodeInp方法
                String account = (String) invocable.invokeFunction("encodeInp", number);
                String password = (String) invocable.invokeFunction("encodeInp", passwd);
                return account + "%%%" + password;
            }
        } catch (FileNotFoundException | ScriptException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String confirmTeach(String number, String passwd) {
        String name = login(number, passwd);
        if (name.length() > 0) {
            name = name.substring(0, name.indexOf("("));
        }
        log.info("---用户名---" + name);
        return name;
    }

    public static void main(String[] args) throws ScriptException {
        // System.out.println(confirmTeach("131544215", "131544215ccy"));
        System.out.println(confirmTeach("131544249", "iwyctmw1095"));
        // String name = new UserService().getClass().getResource("").getFile();
        // System.out.println(name.substring(1));
//        System.out.println(new LoginServiceImpl().getClass().getResource("").getFile());
    }
}

