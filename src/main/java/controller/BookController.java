package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.Book;
import model.BookDao;

@WebServlet(urlPatterns= {"/book/*"}, initParams= {@WebInitParam(name="view", value="/view/")})
public class BookController extends MskimRequestMapping {
   private BookDao dao = new BookDao();
   
   @RequestMapping("testForm")
   public String testForm(HttpServletRequest request, HttpServletResponse response) {
	   return "/book/testForm";
   }
   
   @RequestMapping("testlist")
   public String testlist(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	   request.setCharacterEncoding("UTF-8");
	   Book book = new Book();
	   book.setWriter(request.getParameter("writer"));
	   book.setTitle(request.getParameter("title"));
	   book.setContent(request.getParameter("content"));
	   dao.insert(book);
	   List<Book> list = dao.list();
	   request.setAttribute("list", list);
	   return "/book/testlist";
   }
}
