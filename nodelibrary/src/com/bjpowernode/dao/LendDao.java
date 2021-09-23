package com.bjpowernode.dao;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Lend;
import com.bjpowernode.bean.User;

import java.util.List;

public interface LendDao {
    List<Lend> select(Lend lend);

    void add(Lend lend);

    void delete(String id);

    void update(Lend lend);

}
