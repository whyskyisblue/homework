package com.example.main.service;

import com.example.main.domain.BookSearchVO;
import com.example.main.domain.BookVO;

import java.util.List;

public interface BookService {

    long totalCount(String keyword);

    List<BookVO> list(int dataPerPage, BookSearchVO search);

    String create(BookVO bookVO);

    BookVO read(int bookId);

    String update(BookVO bookVO);

    void delete(int bookId);

}
