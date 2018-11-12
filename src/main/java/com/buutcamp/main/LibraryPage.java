package com.buutcamp.main;

import com.buutcamp.dao.BookDAO;
import com.buutcamp.dao.UserDAO;
import com.buutcamp.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping(value="/library")
public class LibraryPage {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private UserDAO userDAO;


    @RequestMapping(value = "/")
    public String libraryPageGET(Model model) {
        List<Book> books = bookDAO.getBooks();
        model.addAttribute("books", books);
        model.addAttribute("book", new Book());
        return "library";
    }


    @GetMapping("/logout.done")
    private String logout() {
        return "redirect:/showLoginPage";
    }


    @GetMapping("/borrowBook")
    public String borrowPost(@RequestParam("bookId") int book_id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        int user_id = userDAO.getUserId(name);
        bookDAO.borrowBook(book_id, user_id);
        return "redirect:/library/";
    }


    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book, Model model) {
        bookDAO.saveBook(book);
        return "redirect:/library/";
    }


    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("bookId") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/library/";
    }
}