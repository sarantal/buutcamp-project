package com.buutcamp.dao;

import com.buutcamp.entities.MyUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class UserDAO {


    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public int getUserId(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from MyUser where userName = :name ");
        query.setParameter("name", name);
        List<?> list = query.list();
        MyUser user = (MyUser) list.get(0);
        return user.getUserId();
    }
}
