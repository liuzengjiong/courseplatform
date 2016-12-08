package com.courseplatform.app;

import com.mysql.jdbc.Driver;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
*@author ye
*@dete 2016年 12月6日 1:24:53
*/
public class AppTest {

    @Test
    public void testMain(){
        System.out.println("true = " + true);
        try {
            Logger logger = LoggerFactory.getLogger(AppTest.class);
            Driver driver = new Driver();
            System.out.println("hello --------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
