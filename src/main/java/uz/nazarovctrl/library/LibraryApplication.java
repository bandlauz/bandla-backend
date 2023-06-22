package uz.nazarovctrl.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.nazarovctrl.library.entity.Book;

import java.sql.Timestamp;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		Book book=new Book();
		SpringApplication.run(LibraryApplication.class, args);
	}

}
