package com.courseplatform.util;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于文件的上传下载
 *
 * @Author chen cy
 * Created by ye on 2016/12/10.
 */
public class FileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 多文件上传
     *
     * @param files
     *         文件数组
     * @param path
     *         存放文件目录
     * @return 存放文件的路径数组
     * @throws IOException
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 20:08
     */
    public static String[] uploadFile(MultipartFile[] files, String path) throws IOException {
        // 文件保存路径数组
        String[] filepathes = new String[files.length];
        //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
        //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
        //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
        for (int i = 0; i < files.length; i++) {
            filepathes[i] = uploadFile(files[i], path);
        }
        return filepathes;
    }

    /**
     * 上传文件
     *
     * @param file
     *         文件
     * @param path
     *         文件目录
     * @return 存放文件的路径
     * @throws IOException
     * @author ye [15622797401@163.com]
     * @date 2016/12/12 20:08
     */
    public static String uploadFile(MultipartFile file, String path) throws IOException {
        String filepath = "";
        if (file.isEmpty()) {
            LOG.info("文件未上传");
        } else {
            LOG.info("文件长度: " + file.getSize());
            LOG.info("文件类型: " + file.getContentType());
            LOG.info("文件名称: " + file.getName());
            LOG.info("文件原名: " + file.getOriginalFilename());
            LOG.info("========================================");

            // 判定目录中是否存在同一名字的文件
            File file1 = new File(path, file.getOriginalFilename());
            if (file1.exists()) {
//                if (MD5Util.getMD5(file1).equals(MD5Util.getMD5(file.getBytes()))) {
//
//                }
                filepath = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                file1 = new File(path, filepath);
            } else {
                filepath = file.getOriginalFilename();
            }
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
            FileUtils.copyInputStreamToFile(file.getInputStream(), file1);
        }
        return filepath;
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> data = new HashMap<>();
        data.put("number", "131544215");
        data.put("passwd", "131544215ccy");
//        String url = "http://localhost:8080/teacher/getCourse?courseId=29ea3721-8a66-4145-85ba-a6183b2b0cad";
//        String url = "http://localhost:8080/teacher/getCourses";
        String url = "http://localhost:8080/jwxt/confirmTeach?number=131544215&passwd=131544215ccy";

//        Cookie cookie1 = new Cookie("account", "131544200");
//        Cookie cookie2 = new Cookie("userCode", "9a047e8f07f11f917fa9f75812613c5c");

//        Connection.Response response = Jsoup.connect(url).cookie("account", "131544200").cookie("userCode", "9a047e8f07f11f917fa9f75812613c5c")
//                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36")
////                .header("Accept","application/json, text/javascript, */*; q=0.01")
////                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//                .method(Connection.Method.GET).execute();
//
//        System.out.println(response.contentType());
//        System.out.println(response.charset());
//        System.out.println(response.body());

        Document document = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
        System.out.println(document.body());
    }

    public static String readHtml(String myurl) {
        StringBuffer sb = new StringBuffer("");
        URL url;
        try {
            url = new URL(myurl);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "ISO"));
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
