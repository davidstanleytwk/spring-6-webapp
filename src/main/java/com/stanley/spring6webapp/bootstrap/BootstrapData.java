package com.stanley.spring6webapp.bootstrap;

import com.stanley.spring6webapp.domain.Author;
import com.stanley.spring6webapp.domain.Book;
import com.stanley.spring6webapp.domain.Publisher;
import com.stanley.spring6webapp.repositories.AuthorRepository;
import com.stanley.spring6webapp.repositories.BookRepository;
import com.stanley.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository=publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Development");
        ddd.setIsbn("1234");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("113344");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);


        Publisher oct = new Publisher();
        oct.setPublisherName("Octopus International");
        oct.setAddress("41 Holburn, London");
        oct.setWebsite("www.ocotopus.comm");

        Publisher octSavaed = publisherRepository.save(oct);

        Publisher house = new Publisher();
        house.setPublisherName("House International");
        house.setAddress("45 Holburn, London");
        house.setWebsite("www.house.comm");

        Publisher houseSavaed = publisherRepository.save(house);


        System.out.println("In Bootstrap");

        System.out.println("#authors: "+ authorRepository.count());
        System.out.println("#books: "+ bookRepository.count());
        System.out.println("#publishers: "+publisherRepository.count());
    }
}
