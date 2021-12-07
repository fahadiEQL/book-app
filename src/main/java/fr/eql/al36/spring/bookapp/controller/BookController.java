package fr.eql.al36.spring.bookapp.controller;

import fr.eql.al36.spring.bookapp.domain.Book;
import fr.eql.al36.spring.bookapp.repository.AuthorRepository;
import fr.eql.al36.spring.bookapp.repository.BookRepository;
import fr.eql.al36.spring.bookapp.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.WebParam;

@Controller
@Slf4j
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    //Injection de dépendances via le constructeur :
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/")
    public String getIndexPage(Model model) {
        log.info("Getting index page");
        return "index";
    }

    @GetMapping("book/list") //mapping, notamment thymeleaf
    public String displayBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book/list"; //arborescence dans templates et nom du fichier html
    }

    @GetMapping("book/new")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "book/form";
    }

    @PostMapping("addbook")
    public String saveBook(@ModelAttribute Book book) {
        authorRepository.save(book.getAuthor());
        publisherRepository.save(book.getPublisher());
        bookRepository.save(book);
        return "redirect:/book/list";
    }

    @GetMapping("book/{id}/show")
    public String displayBook(Model model, @PathVariable String id) {
        model.addAttribute("book", bookRepository.findById(Integer.parseInt(id)).get());
        //findById(Integer.parseInt(id)).orElse(null);
        return "book/show";
    }

    //TP update et delete :
    @GetMapping("book/{id}/delete")
    public String deleteBook(Model model, @PathVariable String id) {
        bookRepository.deleteById(Integer.parseInt(id));
        return "redirect:/book/list";
    }

    @GetMapping("book/{id}/update")
    public String updateBook(Model model, @PathVariable String id) {
        model.addAttribute("book", bookRepository.findById(Integer.parseInt(id)).orElse(null));
        return "book/form";
    }
}
