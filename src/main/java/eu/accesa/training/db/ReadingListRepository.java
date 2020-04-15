package eu.accesa.training.db;

import eu.accesa.training.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ReadingListRepository  {

    List<Book> findByReader(String reader);
}
