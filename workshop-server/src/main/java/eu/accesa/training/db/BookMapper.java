package eu.accesa.training.db;

import eu.accesa.training.model.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface BookMapper {

    @Insert("INSERT INTO book (reader, isbn, title, author, description) " +
            "VALUES (#{book.reader}, #{book.isbn}, #{book.title}, #{book.author}, #{book.description})")
    public void insertBook(@Param("book") Book book);
}
