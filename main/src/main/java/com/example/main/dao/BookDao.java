package com.example.main.dao;

import com.example.main.domain.BookVO;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public long totalCount(String keyword) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("keyword", keyword);

        return sqlSession.selectOne(NAMESPACE + "totalCount", paramMap);
    }

    public List<BookVO> list(int offset, int limit, boolean asc, String keyword) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("asc", asc);
        paramMap.put("keyword", keyword);

        return sqlSession.selectList(NAMESPACE + "list", paramMap, new RowBounds(offset, limit));
    }

    public boolean update(BookVO bookVO) {
        return sqlSession.update(NAMESPACE + "update", bookVO) > 0;
    }

    public boolean delete(int id) {
        return sqlSession.delete(NAMESPACE + "delete", id) > 0;
    }

}
