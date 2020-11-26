package com.example.main.controller;

import com.example.main.domain.BookVO;
import com.example.main.service.BookService;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    //booklist
    @RequestMapping(value = "bookList", method = RequestMethod.GET)
    public String bookList(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Boolean asc,
            Model model
    ) {
        String loginId = userService.loginId();

        if (loginId == null)
            return "redirect:/login";

        model.addAttribute("loginId", loginId);

        int dataPerPage = 10; // 한 페이지에 보여질 데이터 수
        int pageBlockCount = 10; // 페이지 구간 페이지 수
        long totalCount = bookService.totalCount(); // 전체 데이터 수
        int efPage; // 0 >, totalPage <=
        int totalPage; // 전체 페이지 수
        int pageBlockStart; // 페이지 구간 시작 번호
        int pageBlockEnd; // 페이지 구간 종료 번호
        Integer pageBlockPrev; // 페이지 이전 구간 번호
        Integer pageBlockNext; // 페이지 다음 구간 번호
        Integer pageStart; // 페이지 처음 번호 1 or null
        Integer pageEnd; // 페이지 끝 번호 totalPage or null
        boolean efAsc = asc == null || asc; // 오름차순 여부
        List<BookVO> bookVOList; // 현재 페이지 데이터 목록

        if (totalCount > 0) {
            totalPage = (int) Math.ceil((double) totalCount / dataPerPage);

            if (page == null || page <= 0)
                efPage = 1;
            else if (page > totalPage)
                efPage = totalPage;
            else
                efPage = page;

            // 1 : 1
            // 2 : 1
            // 10 : 1
            // 11 : 11
            // 12 : 11
            pageBlockStart = efPage - (efPage - 1) % pageBlockCount;

            // 1 : 10
            // 2 : 10
            // 10 : 10
            // 11 : 20
            // 12 : 20
            pageBlockEnd = pageBlockStart + pageBlockCount - 1;

            if (pageBlockEnd > totalPage)
                pageBlockEnd = totalPage;

            pageBlockPrev = pageBlockStart <= 1 ? null : pageBlockStart - 1;
            pageBlockNext = pageBlockEnd >= totalPage ? null : pageBlockEnd + 1;
            pageStart = 1; // TODO
            pageEnd = 1; // TODO
            bookVOList = bookService.list(dataPerPage, efPage, efAsc);
        } else {
            efPage = 1;
            totalPage = 0;
            pageBlockStart = 1;
            pageBlockEnd = 1;
            pageBlockPrev = null;
            pageBlockNext = null;
            pageStart = 1; // TODO
            pageEnd = 1; // TODO
            bookVOList = new ArrayList<>();
        }

        model.addAttribute("asc", efAsc);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("page", efPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageBlockStart", pageBlockStart);
        model.addAttribute("pageBlockEnd", pageBlockEnd);
        model.addAttribute("pageBlockPrev", pageBlockPrev);
        model.addAttribute("pageBlockNext", pageBlockNext);
        model.addAttribute("pageStart", pageStart);
        model.addAttribute("pageEnd", pageEnd);
        model.addAttribute("bookVOList", bookVOList);

        return "bookList";
    }


    //bookForm
    @RequestMapping(value = "bookCreate", method = RequestMethod.GET)
    public String bookCreate(Model model) {
        String loginId = userService.loginId();

        if (loginId == null)
            return "redirect:/login";

        model.addAttribute("action", "create");
        model.addAttribute("actionUrl", "/bookCreate");

        return "bookForm";
    }

    //bookCreateComplete
    @RequestMapping(value = "bookCreate", method = RequestMethod.POST)
    public String bookCreateProcess(@ModelAttribute BookVO bookVO) {
        String loginId = userService.loginId();

        if (loginId == null)
            return "redirect:/login";

        String errorMsg = bookService.create(bookVO);

        if (errorMsg != null) {
            throw new RuntimeException(errorMsg);
        }

        return "redirect:/bookCreateComplete";
    }

    //bookFormComplete
    @RequestMapping(value = "bookCreateComplete", method = RequestMethod.GET)
    public String bookCreateComplete(Model model) {
        String loginId = userService.loginId();

        if (loginId == null)
            return "redirect:/login";

        model.addAttribute("action", "create");

        return "bookFormComplete";
    }

    //bookFormError
    @RequestMapping(value = "bookUpdate", method = RequestMethod.GET)
    public String bookUpdate(
            @RequestParam("id") int bookId,
            Model model
    ) {
        String loginId = userService.loginId();

        if (loginId == null)
            return "redirect:/login";

        BookVO bookVO = bookService.read(bookId);

        if (bookVO == null)
            throw new RuntimeException("存在していない本です");

        model.addAttribute("action", "update");
        model.addAttribute("actionUrl", "/bookUpdate?id=" + bookId);
        model.addAttribute("bookVO", bookVO);

        return "bookForm";
    }

    //bookUpdate
    @RequestMapping(value = "bookUpdate", method = RequestMethod.POST)
    public String bookUpdateProcess(@ModelAttribute BookVO bookVO) {
        String loginId = userService.loginId();

        if (loginId == null)
            return "redirect:/login";

        String errorMsg = bookService.update(bookVO);

        if (errorMsg != null) {
            throw new RuntimeException(errorMsg);
        }

        return "redirect:/bookUpdateComplete";
    }

    @RequestMapping(value = "bookUpdateComplete", method = RequestMethod.GET)
    public String bookUpdateComplete(Model model) {
        String loginId = userService.loginId();

        if (loginId == null)
            return "redirect:/login";

        model.addAttribute("action", "update");

        return "bookFormComplete";
    }

    //bookDelete
    @RequestMapping(value = "bookDelete", method = RequestMethod.GET)
    public String bookDelete(
            @RequestParam("id") int bookId,
            Model model
    ) {
        String loginId = userService.loginId();

        if (loginId == null)
            return "redirect:/login";

        bookService.delete(bookId);

        return "redirect:/";
    }

}
