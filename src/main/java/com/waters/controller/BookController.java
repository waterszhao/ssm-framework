package com.waters.controller;

import com.waters.pojo.Book;
import com.waters.pojo.User;
import com.waters.service.BookService;
import com.waters.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/*
* 书籍控制器，主要用于接受前端的请求
* 完成操作后返回相应的视图
*
* 功能包括增删改查
* */
@Controller
@RequestMapping("/book")
// 原型模式（prototype），每次请求开启新的线程处理，防止阻塞
@RequestScope
public class BookController {

    //使用mybatis框架获得数据库服务接口
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;


    // 显示所有书本并返回主页视图
    @RequestMapping("/allBook")
    public String allBook(Model model, HttpServletRequest httpServletRequest, HttpSession session){

        for (Cookie cookie : httpServletRequest.getCookies()) {
            if(cookie.getName().equals("userName")){
                User user = userService.queryByName(cookie.getValue());
                session.setAttribute("control",user.getControlLevel());
            }
        }

        List<Book> books = bookService.queryAll();
        model.addAttribute("books",books);
        return "/book/allBook";
    }

    //转到添加书本视图
    @RequestMapping("/toAddBook")
    public String toAddBook(){
        return "book/addBook";
    }

    //添加一本书
    @RequestMapping("/addBook")
    public String addBook(Book book, Model model){
        bookService.insert(book);
        return "redirect:/book/allBook";
    }

    //转到更新书本页面
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int bookID){
        Book book = bookService.query(bookID);
        model.addAttribute("book",book);
        return "/book/updateBook";
    }

    //更新一本书
    @RequestMapping("/updateBook")
    public String updateBook(Book book){
        bookService.update(book);
        return "redirect:/book/allBook";
    }

    //删除一本书
    @RequestMapping("/deleteBook/{bookID}")
    public String deleteBook(@PathVariable("bookID") int bookID, HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("control") && cookie.getValue().equals("2")){
                bookService.delete(bookID);
            }
        }
        return "redirect:/book/allBook";
    }

    //按书名模糊搜索
    @RequestMapping("/queryByName")
    public String queryByName(Model model, String name){
        List<Book> books = bookService.queryByName(name);
        model.addAttribute("books",books);
        if (books.size() == 0) {
           model.addAttribute("error","未查到相关结果！");
        }
        return "/book/allBook";
    }
}
