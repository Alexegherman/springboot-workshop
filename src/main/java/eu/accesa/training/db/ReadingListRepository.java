package eu.accesa.training.db;

import eu.accesa.training.model.Book;

import java.util.List;


public interface ReadingListRepository  {

    List<Book> findByReader(String reader);
}
