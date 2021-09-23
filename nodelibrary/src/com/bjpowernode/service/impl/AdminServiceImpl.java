package com.bjpowernode.service.impl;

import com.bjpowernode.bean.Admin;
import com.bjpowernode.service.AdminService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 管理员注册和登录
 */
public class AdminServiceImpl implements AdminService {

    /*
        根据用户名获取用户数据
     */
    public Admin get(String name) {
        try {
            File file = new File("admin/" + name + ".properties");//找配置文件
            Properties properties = new Properties();

            FileInputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);
            Admin admin = new Admin();//创建对象
            admin.setUserName(name);//将用户名赋值
            admin.setPassword(properties.getProperty("password"));//将密码赋值
            return admin;//返回该对象
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
        保存用户
     */
    public void save(Admin admin) {
        try {
            File file = new File("admin/" + admin.getUserName() + ".properties");
            Properties properties = new Properties();

            FileInputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);

            FileOutputStream outputStream = new FileOutputStream(file);

            properties.setProperty("password", admin.getPassword());
            properties.store(outputStream, "Update Section");
            properties.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
