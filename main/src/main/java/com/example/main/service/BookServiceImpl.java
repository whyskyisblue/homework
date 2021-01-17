package com.example.main.service;

import com.example.main.dao.BookDao;
import com.example.main.domain.BookSearchVO;
import com.example.main.domain.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    //validation
    private String validation(BookVO bookVO) {
        String title = bookVO.getTitle();
        String author = bookVO.getAuthor();
        String publisher = bookVO.getPublisher();
        Date date = bookVO.getDate();
        Long cost = bookVO.getCost();

        if (title == null || title.isEmpty())
            return "タイトルを書いてください";
        else if (title.length() > 100)
            return "最大100以内でお願いいたします。";

        if (author == null || author.isEmpty())
            return "著者を書いてください";
        else if (author.length() > 50)
            return "最大50以内でお願いいたします。";

        if (publisher != null && publisher.length() > 50)
            return "最大50以内でお願いいたします。";

        if (cost == null)
            return "価額を書いてください";
        else if (cost < 0)
            return "価額を０以上で書いてください";

        return null;
    }

    @Override
    public long totalCount(String keyword) {
        return bookDao.totalCount(keyword);
    }

    //ListRead
    @Override
    public List<BookVO> list(int dataPerPage, BookSearchVO search) {
        // 1페이지에서 10개 가져옴
        // page = 1, offset = 0, count = 10
        // page = 2, offset = 10, count = 10
        // page = 3, offset = 20, count = 10
        int page = search.getPage() == null ? 1 : search.getPage();
        int offset = (page - 1) * dataPerPage;

        return bookDao.list(offset, dataPerPage, search);
    }

    //create
    @Override
    public String create(BookVO bookVO) {
        String errorMsg = validation(bookVO);

        if (errorMsg != null)
            return errorMsg;

        if (!bookDao.create(bookVO))
            return "登録に失敗しました";

        return null;
    }

    //read
    @Override
    public BookVO read(int bookId) {
        return bookDao.read(bookId);
    }

    //update
    @Override
    public String update(BookVO bookVO) {
        if (bookVO.getId() == null)
            return "存在していない本です";

        if (read(bookVO.getId()) == null)
            return "存在していない本です";

        String errorMsg = validation(bookVO);

        if (errorMsg != null)
            return errorMsg;

        if (!bookDao.update(bookVO))
            return "修正失敗しました";

        return null;
    }

    //delete
    @Override
    public void delete(int bookId) {
        if (read(bookId) == null)
            throw new RuntimeException("存在していない本です");

        bookDao.delete(bookId);
    }

}
