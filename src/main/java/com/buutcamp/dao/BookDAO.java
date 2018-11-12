package com.buutcamp.dao;

import com.buutcamp.entities.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class BookDAO {

    //get a sessionFactory reference to access data (bean defined in config file)
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<Book> getBooks() {
        Session session = sessionFactory.getCurrentSession();
        Query<Book> query = session.createQuery("from Book", Book.class);

        return query.getResultList();
    }


    @Transactional
    public void borrowBook(int book_id, int user_id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Book set bookStatus = :client" + " where id = :bookId");
        query.setParameter("client", user_id);
        query.setParameter("bookId", book_id);
        query.executeUpdate();
    }


    @Transactional
    public void saveBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);      // .saveOrUpdate(book);
    }


    @Transactional
    public void deleteBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Book where id=:bookId");
        query.setParameter("bookId", id);
        query.executeUpdate();
    }
}
