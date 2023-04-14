package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.Board;
import model.BoardDao;

@WebServlet(urlPatterns= {"/board/*"}, initParams= {@WebInitParam(name="view", value="/view/")})
public class BoardController extends MskimRequestMapping {
	private BoardDao dao = new BoardDao();
	
	@RequestMapping("writeForm")
	public String writeForm(HttpServletRequest request, HttpServletResponse response) {
		String boardid = (String)request.getSession().getAttribute("boardid");
		if(boardid==null) boardid="1";
		String login = (String)request.getSession().getAttribute("login");
		if(boardid.equals("1")) {
			if(login==null || !login.equals("admin")) {
				request.setAttribute("msg", "관리자만 공지사항에 글쓰기가 가능합니다.");
				request.setAttribute("url", request.getContextPath()+"/board/list?boardid="+boardid); //"list?boardid="+boardid 하면 안되나? 됨. 저건 절대경로로 접근하는 방식ㅇㅇ. 
				return "alert";
			}
		}
		return "board/writeForm";
	}
	
	@RequestMapping("write")
	public String write(HttpServletRequest request, HttpServletResponse response) throws IOException {
		   String path = request.getServletContext().getRealPath("/") +"view/board/file";
		   File f = new File(path);
		   if(!f.exists()) f.mkdirs();
		   MultipartRequest multi = new MultipartRequest(request, path, 10*1024*1024, "UTF-8");
		   Board board = new Board();
		   board.setWriter(multi.getParameter("writer")); //view 에서 enctype 잘 보고 파라미터 받을 것.
		   board.setPass(multi.getParameter("pass"));
		   board.setTitle(multi.getParameter("title"));
		   board.setContent(multi.getParameter("content"));
		   board.setFile1(multi.getFilesystemName("file1"));
		   String boardid = (String)request.getSession().getAttribute("boardid");
		   if(boardid==null) boardid = "1";
		   board.setBoardid(boardid);
		   
		   int num = dao.maxnum(); //최대 num 값.
		   board.setNum(++num); //등록할 때마다 num이 하나씩 커짐
		   board.setGrp(num); //grp==num
		   
		   if(dao.insert(board)) {
			   return "redirect:list?boardid="+boardid;
		   } else {
			    request.setAttribute("msg", "게시물 등록 실패");
				request.setAttribute("url", "writeForm"); 
				return "alert";
		   }
	}

}
