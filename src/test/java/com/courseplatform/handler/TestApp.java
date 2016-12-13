package com.courseplatform.handler;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

/**
 * @Author chen cy
 * Created by ye on 2016/12/12.
 */
public class TestApp {

    @Test
    public void testDir() {
        String path = "G:\\ATM\\github\\swagger-ui\\dist\\images";
        File dir = new File(path);
        String[] paths = dir.list();
        for (String s : paths) {
            System.out.println("s = " + s);
        }
    }
}
