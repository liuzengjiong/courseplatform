package com.courseplatform.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
            }
            filepath = path + "/" + file.getOriginalFilename();
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
            FileUtils.copyInputStreamToFile(file.getInputStream(), file1);
        }
        return filepath;
    }
}
