package com.bjpowernode.dao.impl;


import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.dao.BookDao;
import com.bjpowernode.util.BeanUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookDaoImpl implements BookDao {
    /**
     * ��ѯ
     * @param book
     * @return
     */
    @Override
    public List<Book> select(Book book){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            //��ȡlist����
            List<Book> list = (List<Book>) ois.readObject();
            if(list != null) {
                if(book == null || ("".equals(book.getBookName()) && "".equals(book.getIsbn()))){
                    //��ѯȫ������
                    return list;
                }else {
                    List<Book> conditionList = new ArrayList<>();

                    //����id��ѯ
                    if(!(0 == book.getId())) {
                        conditionList = list.stream().filter(b -> b.getId() == book.getId()).collect(Collectors.toList());
                        return conditionList;
                    }

                    //������ѯ
                    //�ж�������ѯ����ͬʱ����
                    if(!"".equals(book.getBookName()) && !"".equals(book.getIsbn())){
                        conditionList = list.stream().filter(b -> b.getBookName().equals(book.getBookName())).collect(Collectors.toList());
                        //���ڵ�һ����ѯ������в�ѯ
                        conditionList = conditionList.stream().filter(b -> b.getIsbn().equals(book.getIsbn())).collect(Collectors.toList());
                    } else {
                        //����������ѯ
                        if (!"".equals(book.getBookName())) {
                            //����ͼ�����Ʋ�ѯ
                            conditionList = list.stream().filter(b -> b.getBookName().equals(book.getBookName())).collect(Collectors.toList());
                        }
                        if(!"".equals(book.getIsbn())) {
                            conditionList = list.stream().filter(b -> b.getIsbn().equals(book.getIsbn())).collect(Collectors.toList());
                        }
                    }

                    return conditionList;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ois != null){
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    /**
     * ͼ������
     * @param book
     */
    @Override
    public void add(Book book) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            //��ȡlist����
            List<Book> list = (List<Book>) ois.readObject();
            if(list != null) {
                Book lastbook = list.get(list.size() - 1);
                book.setId(lastbook.getId() + 1);
                list.add(book);
                //��ͼ������д����Ӳ����
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if(ois != null){
                    ois.close();
                }
                if(oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ɾ��
     * @param id
     */
    @Override
    public void delete(int id) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            //��ȡlist����
            List<Book> list = (List<Book>) ois.readObject();
            if(list != null) {
                //�Ӽ����в���Ҫɾ���ļ��϶���
                Book book = list.stream().filter(b -> b.getId() == id).findFirst().get();
                list.remove(book);//����д
                //��ͼ������д����Ӳ����
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if(ois != null){
                    ois.close();
                }
                if(oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * �޸�
     * @param book
     */
    @Override
    public void update(Book book) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            //��ȡlist����
            List<Book> list = (List<Book>) ois.readObject();
            if(list != null) {
                //�Ӽ����в���Ҫ�޸ĵļ��϶���
                Book originBook = list.stream().filter(b -> b.getId() == book.getId()).findFirst().get();
                BeanUtil.populate(originBook, book);
                //��ͼ������д����Ӳ����
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if(ois != null){
                    ois.close();
                }
                if(oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}