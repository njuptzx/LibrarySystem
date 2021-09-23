package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Lend;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.dao.LendDao;
import com.bjpowernode.util.BeanUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LendDaoImpl implements LendDao {
    /**
     * ��ѯ
     * @param lend
     * @return
     */
    @Override
    public List<Lend> select(Lend lend) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH))) {
            List<Lend> list = (List<Lend>) ois.readObject();
            if (lend == null || "".equals(lend.getId())) {
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return new ArrayList<>();
    }

    /**
     * ͼ�����
     * @param lend
     */
    @Override
    public void add(Lend lend) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            //��ȡlist����
            List<Lend> list = (List<Lend>) ois.readObject();
            list.add(lend);
            //��ͼ������д����Ӳ����
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
            oos.writeObject(list);
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

    @Override
    public void delete(String id) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            //��ȡlist����
            List<Lend> list = (List<Lend>) ois.readObject();
            if(list != null) {
                //�Ӽ����в���Ҫɾ���ļ��϶���
                Lend originLend = list.stream().filter(r -> Objects.equals(id, r.getId())).findFirst().get();
                list.remove(originLend);//����д
                //��ͼ������д����Ӳ����
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ɾ�����ݳ�����");
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

    @Override
    public void update(Lend lend) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            //��ȡlist����
            List<Lend> list = (List<Lend>) ois.readObject();
            if(list != null) {
                //�Ӽ����в���Ҫ�޸ĵļ��϶���
                Lend originLend = list.stream().filter(u -> u.getId().equals(lend.getId())).findFirst().get();
                //�޸�����
                BeanUtil.populate(originLend, lend);
                //�����ݳ־û����ļ���
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
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
