package com.waters.controller;

import com.waters.pojo.Book;
import com.waters.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/allBook")
    public String allBook(Model model){
        List<Book> books = bookService.queryAll();
        model.addAttribute("books",books);

        return "allBook";
    }


    @RequestMapping("/toAddBook")
    public String toAddBook(){
        return "addBook";
    }

    @RequestMapping("/addBook")
    public String addBook(Book book, Model model){
        bookService.insert(book);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int bookID){
        Book book = bookService.query(bookID);
        model.addAttribute("book",book);

        return "updateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Book book){
        bookService.update(book);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/deleteBook/{bookID}")
    public String deleteBook(@PathVariable("bookID") int bookID){
        bookService.delete(bookID);

        return "redirect:/book/allBook";
    }

    @RequestMapping("/queryByName")
    public String queryByName(Model model, String name){
        List<Book> books = bookService.queryByName(name);
        model.addAttribute("books",books);
        if (books.size() == 0) {
           model.addAttribute("error","未查到相关结果！");
        }
        return "allBook";
    }
}
