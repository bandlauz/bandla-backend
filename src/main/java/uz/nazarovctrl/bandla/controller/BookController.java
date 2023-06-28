package uz.nazarovctrl.bandla.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/list")
    public String getBookList() {
        return "book_list";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String addBook() {
        return "book_add";
    }
}
