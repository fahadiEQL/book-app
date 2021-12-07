package fr.eql.al36.spring.bookapp.controller;

import fr.eql.al36.spring.bookapp.domain.Publisher;
import fr.eql.al36.spring.bookapp.repository.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PublisherController {

    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("publisher/list")
    public String displayPublishers(Model model) {
        model.addAttribute("publishers", publisherRepository.findAll());
        return "publisher/list";
    }

    @GetMapping("publisher/{id}/books")
    public String displayBooks(Model model, @PathVariable String id) {
        Publisher publisher = publisherRepository.findById(Integer.parseInt(id)).orElse(null);
        if (publisher != null) {
            model.addAttribute("publisher", publisher);
            model.addAttribute("books", publisher.getBooks());
        }
        return "publisher/books";
    }
}
