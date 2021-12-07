package fr.eql.al36.spring.bookapp.bootstrap;

import fr.eql.al36.spring.bookapp.domain.Author;
import fr.eql.al36.spring.bookapp.domain.Book;
import fr.eql.al36.spring.bookapp.domain.Publisher;
import fr.eql.al36.spring.bookapp.repository.AuthorRepository;
import fr.eql.al36.spring.bookapp.repository.BookRepository;
import fr.eql.al36.spring.bookapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    //Injection de dépendance via le constructeur :
    public BootStrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initData();
    }

    private void initData() {
        //le code executé au lancement de l'application :

        Author dan = authorRepository.save(Author.builder()
                .firstName("Dan")
                .name("Brown")
                .build());

        Author jk = authorRepository.save(Author.builder()
                .firstName("JK")
                .name("Rowling")
                .build());

        Author tolkien = authorRepository.save(Author.builder()
                .firstName("JRR")
                .name("Tolkien")
                .build());

        Publisher bloomsbury = publisherRepository.save(Publisher.builder()
                .name("Bloomsbury")
                .address("31 Bedford Avenue")
                .city("London")
                .zipCode("WC1B 3AT").build());

        Publisher doubleday = publisherRepository.save(Publisher.builder()
                .name("DoubleDay")
                .address("1745 Broadway")
                .city("New York")
                .zipCode("10019").build());

        Book lotr = bookRepository.save(Book.builder()
                .title("Le seigneur des anneaux")
                .isbn("123-456")
                .author(tolkien)
                .publisher(bloomsbury)
                .build());

        Book hp1 = bookRepository.save(Book.builder()
                .title("Harry Potter à l'école des sorciers")
                .isbn("235-854")
                .author(jk)
                .publisher(bloomsbury)
                .build());

        Book hp2 = bookRepository.save(Book.builder()
                .title("Harry Potter et la chambre des secrets")
                .isbn("845-521")
                .author(jk)
                .publisher(bloomsbury)
                .build());

        Book daVinciCode = bookRepository.save(Book.builder()
                .title("Le Da Vinci Code")
                .isbn("854-695")
                .author(dan)
                .publisher(doubleday)
                .build());

    }
}
