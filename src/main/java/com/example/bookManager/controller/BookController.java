package com.example.bookManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookManager.model.Book;
import com.example.bookManager.service.BookService;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Book> books = bookService.searchByTitle(keyword);
        if (books.isEmpty()) {
            books = bookService.searchByAuthor(keyword);
        }
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/filter")
    public String filterByPrice(@RequestParam Double minPrice, @RequestParam Double maxPrice, Model model) {
        List<Book> books = bookService.filterByPriceRange(minPrice, maxPrice);
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("book", new Book());
        return "add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElse(new Book()));
        return "edit";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }
}