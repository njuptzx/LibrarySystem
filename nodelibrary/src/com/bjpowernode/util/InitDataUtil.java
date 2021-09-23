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

//��ʼ�����ݵĹ�����
public class InitDataUtil {
    public static void main(String[] args) {
        //��ʼ���û�����
        List<User> userList = new ArrayList<>();
        userList.add(new User(1001,"����", Constant.USER_OK, BigDecimal.TEN,false));//��ʼ�����10
        initData(PathConstant.USER_PATH,userList);
        //��ʼ��ͼ������
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1,"java����","����",Constant.TYPE_COMPUTER,"123-1","��е��ҵ������",Constant.STATUS_STORAGE));//��ʼ�����10
        initData(PathConstant.BOOK_PATH,bookList);
        //��ʼ����������
        List<Lend> lendList = new ArrayList<>();
        User user = new User(1001,"����", Constant.USER_OK, BigDecimal.TEN,false);//��ʼ�����10
        Book book = new Book(1,"java����","����",Constant.TYPE_COMPUTER,"123-1","��е��ҵ������",Constant.STATUS_STORAGE);//��ʼ�����10

        Lend lend = new Lend();

        //ʹ��UUID���ɱ��
        lend.setId(UUID.randomUUID().toString());

        lend.setUser(user);
        lend.setBook(book);
        lend.setStatus(Constant.STATUS_LEND);
        LocalDate begin = LocalDate.now();
        lend.setLendDate(begin);
        //���ù黹����
        lend.setReturnDate(begin.plusDays(30));

        lendList.add(lend);

        initData(PathConstant.LEND_PATH,lendList);

    }




    /**
     * ��ʼ������
     *
     */
    public static void initData(String path, List<?> list){
        ObjectOutputStream oos = null;
        //��������ļ���
        try {
            File directory = new File(path.split("/")[0] + "/");
            File file = new File(path);
            //�ж��ļ����Ƿ����
            if(!directory.exists()) {
                directory.mkdir();
            }
            //�ж��ļ��Ƿ����
            if(!file.exists()) {
                file.createNewFile();
                //���ö����������list����д�����ļ���
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //�ر���
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
//        //��������ļ���
//        try {
//            File directory = new File("user/");
//            File file = new File(PathConstant.USER_PATH);
//            //�ж��ļ����Ƿ����
//            if(!directory.exists()) {
//                directory.mkdir();
//            }
//            //�ж��ļ��Ƿ����
//            if(!file.exists()) {
//                file.createNewFile();
//                List<User> list = new ArrayList<>();
//                //userList�����û���������Լ�����
//                if(userList == null) {
//                    list.add(new User(1001,"����", Constant.USER_OK, BigDecimal.TEN));//��ʼ�����10
//                }else {
//                    list = userList;
//                }
//                //���ö����������list����д�����ļ���
//                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
//                oos.writeObject(list);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //�ر���
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
//        //��������ļ���
//        try {
//            File directory = new File("book/");
//            File file = new File(PathConstant.BOOK_PATH);
//            //�ж��ļ����Ƿ����
//            if(!directory.exists()) {
//                directory.mkdir();
//            }
//            //�ж��ļ��Ƿ����
//            if(!file.exists()) {
//                file.createNewFile();
//                List<Book> list = new ArrayList<>();
//                //userList�����û���������Լ�����
//                if(bookList == null) {
//                    list.add(new Book(1,"java����","����",Constant.TYPE_COMPUTER,"123-1","��е��ҵ������",Constant.STATUS_STORAGE));//��ʼ�����10
//                }else {
//                    list = bookList;
//                }
//                //���ö����������list����д�����ļ���
//                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
//                oos.writeObject(list);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //�ر���
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
