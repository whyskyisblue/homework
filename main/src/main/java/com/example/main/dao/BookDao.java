package com.example.main.dao;

import com.example.main.domain.BookVO;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {

    protected static final String NAMESPACE = "com.example.main.book.";

    @Autowired
    private SqlSession sqlSession;

    public boolean create(BookVO bookVO) {
        return sqlSession.insert(NAMESPACE + "create", bookVO) > 0;
    }

    public BookVO read(int id) {
        return sqlSession.selectOne(NAMESPACE + "read", id);
    }

    public long totalCount() {
        return sqlSession.selectOne(NAMESPACE + "totalCount");
    }

    public List<BookVO> list(int offset, int limit, boolean asc) {
        return sqlSession.selectList(NAMESPACE + "list", asc, new RowBounds(offset, limit));
    }

    public boolean update(BookVO bookVO) {
        return sqlSession.update(NAMESPACE + "update", bookVO) > 0;
    }

    public boolean delete(int id) {
        return sqlSession.delete(NAMESPACE + "delete", id) > 0;
    }

}
