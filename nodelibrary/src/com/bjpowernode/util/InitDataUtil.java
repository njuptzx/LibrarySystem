package com.bjpowernode.util;

import com.bjpowernode.bean.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//初始化数据的工具类
public class InitDataUtil {
    public static void main(String[] args) {
        //初始化用户数据
        List<User> userList = new ArrayList<>();
        userList.add(new User(1001,"张旭", Constant.USER_OK, BigDecimal.TEN,false));//初始化余额10
        initData(PathConstant.USER_PATH,userList);
        //初始化图书数据
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1,"java入门","张旭",Constant.TYPE_COMPUTER,"123-1","机械工业出版社",Constant.STATUS_STORAGE));//初始化余额10
        initData(PathConstant.BOOK_PATH,bookList);
        //初始化借阅数据
        List<Lend> lendList = new ArrayList<>();
        User user = new User(1001,"张旭", Constant.USER_OK, BigDecimal.TEN,false);//初始化余额10
        Book book = new Book(1,"java入门","张旭",Constant.TYPE_COMPUTER,"123-1","机械工业出版社",Constant.STATUS_STORAGE);//初始化余额10

        Lend lend = new Lend();

        //使用UUID生成编号
        lend.setId(UUID.randomUUID().toString());

        lend.setUser(user);
        lend.setBook(book);
        lend.setStatus(Constant.STATUS_LEND);
        LocalDate begin = LocalDate.now();
        lend.setLendDate(begin);
        //设置归还日期
        lend.setReturnDate(begin.plusDays(30));

        lendList.add(lend);

        initData(PathConstant.LEND_PATH,lendList);

    }




    /**
     * 初始化数据
     *
     */
    public static void initData(String path, List<?> list){
        ObjectOutputStream oos = null;
        //创建相关文件夹
        try {
            File directory = new File(path.split("/")[0] + "/");
            File file = new File(path);
            //判断文件夹是否存在
            if(!directory.exists()) {
                directory.mkdir();
            }
            //判断文件是否存在
            if(!file.exists()) {
                file.createNewFile();
                //利用对象输出流将list数据写出到文件中
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (oos != null) {
                try {
                    oos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    public static void initUser(List<User> userList){
//        ObjectOutputStream oos = null;
//        //创建相关文件夹
//        try {
//            File directory = new File("user/");
//            File file = new File(PathConstant.USER_PATH);
//            //判断文件夹是否存在
//            if(!directory.exists()) {
//                directory.mkdir();
//            }
//            //判断文件是否存在
//            if(!file.exists()) {
//                file.createNewFile();
//                List<User> list = new ArrayList<>();
//                //userList中如果没有数据则自己创建
//                if(userList == null) {
//                    list.add(new User(1001,"张旭", Constant.USER_OK, BigDecimal.TEN));//初始化余额10
//                }else {
//                    list = userList;
//                }
//                //利用对象输出流将list数据写出到文件中
//                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
//                oos.writeObject(list);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //关闭流
//            if (oos != null) {
//                try {
//                    oos.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    public static void initBook(List<Book> bookList){
//        ObjectOutputStream oos = null;
//        //创建相关文件夹
//        try {
//            File directory = new File("book/");
//            File file = new File(PathConstant.BOOK_PATH);
//            //判断文件夹是否存在
//            if(!directory.exists()) {
//                directory.mkdir();
//            }
//            //判断文件是否存在
//            if(!file.exists()) {
//                file.createNewFile();
//                List<Book> list = new ArrayList<>();
//                //userList中如果没有数据则自己创建
//                if(bookList == null) {
//                    list.add(new Book(1,"java入门","张旭",Constant.TYPE_COMPUTER,"123-1","机械工业出版社",Constant.STATUS_STORAGE));//初始化余额10
//                }else {
//                    list = bookList;
//                }
//                //利用对象输出流将list数据写出到文件中
//                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
//                oos.writeObject(list);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //关闭流
//            if (oos != null) {
//                try {
//                    oos.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
