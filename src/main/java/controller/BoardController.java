package controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import gdu.mskim.MSLogin;
import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.Board;
import model.BoardDao;
import model.Comment;
import model.CommentDao;
//http://localhost:8080/jspstudy2/board/info?num=1 
@WebServlet(urlPatterns= {"/board/*"}, initParams= {@WebInitParam(name="view", value="/view/")})
public class BoardController extends MskimRequestMapping {
	private BoardDao dao = new BoardDao();
	private CommentDao cdao = new CommentDao();
	
	public String loginAdminCheck(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String login = (String)request.getSession().getAttribute("login");
		if(login==null || login.equals("")) {
			request.setAttribute("msg", "로그인하세요.");
			request.setAttribute("url", "loginForm");
			return "alert";
		} else if(!login.equals("admin")) {
			request.setAttribute("msg" , "관리자만 접근 가능합니다.");
			request.setAttribute("url", "main");
			return "alert";
		}
		return null;
	}
	
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
		//파일 업로드
		   String path = request.getServletContext().getRealPath("/") +"/upload/board/";
		   File f = new File(path);
		   if(!f.exists()) f.mkdirs();
		   MultipartRequest multi = new MultipartRequest(request, path, 10*1024*1024, "UTF-8");
		   //객체 생성 & 파라미터(입력값) 등록
		   Board board = new Board();
		   board.setWriter(multi.getParameter("writer")); //view 에서 enctype 잘 보고 파라미터 받을 것.
		   board.setPass(multi.getParameter("pass"));
		   board.setTitle(multi.getParameter("title"));
		   board.setContent(multi.getParameter("content"));
		   board.setFile1(multi.getFilesystemName("file1"));
		   String boardid = (String)request.getSession().getAttribute("boardid"); //boardid 언제 session에 등록햇지? list에서 등록하지 않나?
		   if(boardid==null) boardid = "1";
		   board.setBoardid(boardid);
		   
		   if(board.getFile1()==null) board.setFile1(""); //업로드할 파일이 없는 경우 빈문자열로 대체
		   
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
	
	@RequestMapping("list")
	public String list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 1.한페이지당 10건의 게시물을 출력 pageNum 파라미터값을 저장 => 없는 경우는 1로 설정
	     2.최근 등록된 게시물이 가장 위에 배치.
	     3.db에서 해당 페이지에 출력될 내용을 조회하여 화면에 출력. 
	               게시물을 출력하는 부분. 
	               페이지 구분 출력 부분.*/
		 if(request.getParameter("boardid") != null) { 
			   request.getSession().setAttribute("boardid", request.getParameter("boardid"));
			   request.getSession().setAttribute("pageNum","1");
		   }
		   String boardid = (String)request.getSession().getAttribute("boardid");
		   if(boardid == null) boardid="1"; //parameter가 안들어오면(null 이면) boardid=1, 공지사항.
		   
		   int pageNum=1;
		   try {
			   pageNum = Integer.parseInt(request.getParameter("pageNum")); //pageNum에 오류가 나도(null or "a" 등등) 1로 인식하겠음.
		   } catch(NumberFormatException e) {}
		   
		   int limit=10;
		   BoardDao dao = new BoardDao();
		   int boardcount = dao.boardCount(boardid); //boardCount = "게시판 종류별" 전체 등록 게시물 건수 => maxNum()은 삭제되거나 다른 boardid일떄 안되니까.
		   List<Board> list = dao.list(boardid, pageNum, limit);
		   
		   //페이징 부분 시작.
		   int maxpage = (int)((double)boardcount/limit + 0.95);
		   int startpage = ((int)(pageNum/10.0 + 0.9) -1)*10 +1; //화면에 출력될 시작 페이지 ex. [21]...[30] 에서 21
		   int endpage = startpage + 9; //ex. [21]...[30] 에서 30
		   if(endpage > maxpage) endpage = maxpage;
		   
		   String boardName = "공지사항";
		   switch(boardid) {
		   case "2" : boardName = "자유게시판"; break;
		   case "3" : boardName = "QnA"; break;
		   }
		   
		   int boardnum = boardcount - (pageNum -1)*limit;
		   
		   request.setAttribute("boardName", boardName);
		   request.setAttribute("boardcount", boardcount);
		   request.setAttribute("boardid", boardid);
		   request.setAttribute("boardnum", boardnum);
		   request.setAttribute("pageNum", pageNum);
		   request.setAttribute("list", list);
		   request.setAttribute("startpage", startpage);
		   request.setAttribute("endpage", endpage);
		   request.setAttribute("maxpage", maxpage);
		   request.setAttribute("today", new Date());
		   return "board/list";
	}
	
	@RequestMapping("info")
	public String info(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
	 1. num 파라미터 저장. session에서 boardid 조회하기
     2. num값의 게시물을 db에서 조회 BoardDao.selectOne(num)
     3. num값의 게시물의 조회수 증가시키기 void BoardDao.readcntAdd(num)
     4. 조회된 게시물 화면에 출력*/
		   int num = Integer.parseInt(request.getParameter("num")); 
		   String boardid = (String)request.getSession().getAttribute("boardid");
		   if(boardid==null) boardid="1";
		   
		   Board b = dao.selectOne(num);
		   String readcnt = request.getParameter("readcnt");
		   
		   if(readcnt==null || !readcnt.equals("f")) {
			   dao.readcntAdd(num);
		   }		   
		   
		   request.setAttribute("b", b);
		   request.setAttribute("boardid", boardid);
		   
		   List<Comment> commlist = cdao.list(num);
		   request.setAttribute("commlist", commlist);
		return "board/info";
	}
	
	@RequestMapping("replyForm")
	public String replyForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int num = Integer.parseInt(request.getParameter("num")); 
		String boardid = (String)request.getSession().getAttribute("boardid");
		if(boardid==null) boardid="1";
		Board b = dao.selectOne(num); 
		request.setAttribute("b", b);
		request.setAttribute("boardid", boardid);
		return "board/replyForm";
	}
	
	@RequestMapping("reply")
	public String reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		1. 파라미터 값을 Board 객체에 저장하기
    		원글정보 : num, grp, grplevel, grpstep, boardid (hidden 태그로 들어옴) '페이지 소스보기'로 확인가능.
    		답글정보 : writer, pass, title, content => 등록정보
		2. 같은 grp 값을 사용하는 게시물들의 grpstep 값을 1 증가하기.
    		void BoardDao.grpStepAdd(grp,grpstep)
		3. Board 객체를 db에 insert 하기.
    		num : maxnum+1
    		grp : 원글과 동일.
    		grplevel : 원글 +1
    		grpstep : 원글 +1
    		boardid : 원글과 동일
		4. 등록 성공시 list.jsp 로 이동
       		실패시 "답변 등록 시 오류 발생" 메세지 출력 후 replyForm.jsp로 이동 */
		request.setCharacterEncoding("UTF-8");
		
		Board b = new Board();
		b.setWriter(request.getParameter("writer"));
		b.setPass(request.getParameter("pass"));
	    b.setTitle(request.getParameter("title"));
		b.setContent(request.getParameter("content"));
	    b.setBoardid(request.getParameter("boardid"));
		b.setGrp(Integer.parseInt(request.getParameter("grp")));
		b.setGrplevel(Integer.parseInt(request.getParameter("grplevel")));
		b.setGrpstep(Integer.parseInt(request.getParameter("grpstep")));
		
		dao.grpStepAdd(b.getGrp(), b.getGrpstep());
		 
		int grplevel = b.getGrplevel();
		int grpstep = b.getGrpstep();
		int num = dao.maxnum();
		b.setNum(++num);
		b.setGrplevel(grplevel + 1);
		b.setGrpstep(grpstep + 1);
		if(dao.insert(b)) {
			return "redirect:list?boardid="+b.getBoardid();
		} else {
			request.setAttribute("msg", "답변 등록시 오류 발생.");
			request.setAttribute("url", "replyForm?num="+b.getNum());
			return "alert";
		}
	}
	
	@RequestMapping("updateForm")
	public String updateForm(HttpServletRequest request, HttpServletResponse response) {
		/*
		1. 공지사항인 경우 관리자만 수정 가능
		2. num값에 해당하는 게시물을 조회
		3. 조회된 게시물을 화면에 출력 
		*/
		int num = Integer.parseInt(request.getParameter("num"));
		Board dbboard = dao.selectOne(num);
		String boardid = (String)request.getSession().getAttribute("boardid");
		if(boardid==null) boardid="1";
		String login = (String)request.getSession().getAttribute("login");
		if(dbboard.getBoardid().equals("1")) {	
			if(login==null || login.equals("")) {
				request.setAttribute("msg", "로그인하세요.");
				request.setAttribute("url", "/jspstudy2/member/loginForm"); //맨앞에 / 해야 절대경로로 인식. location.href="member/loginForm" 으로 들어가니까 jspstudy2/board/member/loginForm이 됨.
				return "alert";
			} else if(!login.equals("admin")) {
				request.setAttribute("msg" , "관리자만 접근 가능합니다.");
				request.setAttribute("url", "/jspstudy2/member/main");
				return "alert";
			}
		}	    
		Board b = dao.selectOne(num);
		String boardName="공지사항";
		switch(boardid) {
		case "2" : boardName="자유게시판"; break;
		case "3" : boardName="QnA"; break;
		}
		request.setAttribute("boardName", boardName);
		request.setAttribute("b",b);
		return "board/updateForm";
	}
	
	@RequestMapping("update")
	public String update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		1. 파라미터 정보들을 Board 객체에 저장
		2. 비밀번호 검증
      		비밀번호 오류 메세지 출력 후 updateForm.jsp로 이동
		3. 첨부파일의 변경이 없는 경우 file2 파라미터 내용을 다시 저장하기
   			파라미터의 내용으로 해당 게시물의 내용을 수정하기.
      		boolean BoardDao,update(Board)	
         	수정성공 : info.jsp 페이지로 이동
         	수정실패 : 수정실패 메세지 출력 후 updateForm.jsp로 이동*/
		Board b = new Board();
		String path = request.getServletContext().getRealPath("/") + "/upload/board";
		File f = new File(path);
		if(!f.exists()) f.mkdirs();
		System.out.println(path);
		
		MultipartRequest multi = new MultipartRequest(request, path, 10*1024*1024, "UTF-8");
		b.setNum(Integer.parseInt(multi.getParameter("num")));
		b.setFile1(multi.getFilesystemName("file1")); //file1 : 변경하려는 파일명, 변경하려는 파일명으로 board객체에 file1을 set
		b.setWriter(multi.getParameter("writer"));
		b.setPass(multi.getParameter("pass"));
		b.setTitle(multi.getParameter("title"));
		b.setContent(multi.getParameter("content"));
		if(b.getFile1()==null || b.getFile1().equals("")) { //만약 변경하려는 파일명이 null이거나 공백이라면, board 객체에 file1을 변경 전 파일명으로. 
			b.setFile1(multi.getParameter("file2"));
		}
		
		Board dbboard = dao.selectOne(b.getNum());  
		if(!b.getPass().equals(dbboard.getPass())) {
			request.setAttribute("msg" , "비밀번호가 다릅니다.");
			request.setAttribute("url", "updateForm?num="+multi.getParameter("num"));			
			return "alert";
		} else {
			if(dao.update(b)) {
				request.setAttribute("msg" , "수정 성공");
				request.setAttribute("url", "info?num="+multi.getParameter("num"));			
				return "alert";
			} else {
				request.setAttribute("msg" , "수정 실패");
				request.setAttribute("url", "updateForm?num="+multi.getParameter("num"));			
				return "alert";
			}
		}
	}
	
	@RequestMapping("deleteForm")
	public String deleteForm(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		Board dbboard = dao.selectOne(num);
		String login = (String)request.getSession().getAttribute("login");
//		String boardid = (String)request.getSession().getAttribute("boardid");
//		if(boardid==null) boardid="1";
		if(dbboard==null) {
			request.setAttribute("msg" , "이미 삭제된 게시물 입니다.");
			request.setAttribute("url", "list?boardid=1");
			return "alert";
		} else {
			if(dbboard.getBoardid().equals("1")) {	
				if(login==null || !login.equals("admin")) {
					request.setAttribute("msg" , "관리자만 접근 가능합니다.");
					request.setAttribute("url", "list?boardid="+dbboard.getBoardid());
					return "alert";
			    } 
			}	
		}			
		request.setAttribute("num", num);
		return "board/deleteForm";
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		/*
		1. num, pass 파라미터를 변수에 저장.
		2. 비밀번호 검증
     		틀린경우 : 비밀번호 오류 메세지 출력, deleteForm.jsp로 이동
		3. 해당게시물이 공지사항 게시물인 경우 관리자만 삭제 가능
		4. 게시물 삭제
     		boolean BoardDao.delete(num)
     		삭제 성공 : list.jsp 페이지로 이동
     		삭제 실패 : 실패 메세지 출력 후 info.jsp 페이지로 이동*/
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		String login = (String)request.getSession().getAttribute("login");
		Board dbboard = dao.selectOne(num);
		if(!dbboard.getPass().equals(pass)) {
			request.setAttribute("msg" , "비밀번호가 틀립니다.");
			request.setAttribute("url", "deleteForm?num="+num);
			return "alert";
		}
		if(dbboard.getBoardid().equals("1")) {	
			 if(login==null || !login.equals("admin")) {
				request.setAttribute("msg" , "관리자만 접근 가능합니다.");
				request.setAttribute("url", "list?boardid="+dbboard.getBoardid());
				return "alert";
		    } 
		}
		if(dao.delete(num)) {
			request.setAttribute("msg" , "삭제 성공");
			request.setAttribute("url", "list?boardid="+dbboard.getBoardid());
			return "alert";
		} else {
			request.setAttribute("msg" , "삭제 실패");
			request.setAttribute("url", "info?num="+num);
			return "alert";
		}
	}
	
	@RequestMapping("imgupload")
	public String imgupload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getServletContext().getRealPath("/") + "/upload/imgfile";
		File f = new File(path);
		if(!f.exists()) f.mkdirs();
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request, path, 10*1024*1024, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String fileName = multi.getFilesystemName("upload"); //ckeditor가 전달해줌 upload라는 이름으로. 파일 전송할 때 파라미터 이름이 upload인거임
//		System.out.println(fileName); //dochi.jsp
		request.setAttribute("fileName", fileName);
		return "ckeditor"; //view밑에
	}
	
	@RequestMapping("comment")
	public String comment(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int num = Integer.parseInt(request.getParameter("num"));
		String url = "info?num="+num+"&readcnt=f";
		Comment cmt = new Comment();
		cmt.setNum(num);
		cmt.setContent(request.getParameter("content"));
		cmt.setWriter(request.getParameter("writer"));
		int seq = cdao.maxseq(num);
//		cmt.setSeq(++seq); //url 실행될 때마다 증가. dao에서 처리하는 거 아님. controller에서 해야함.
		cmt.setSeq(seq+1); 
		if(cdao.insert(cmt)) {
			return "redirect:"+url;
		}
		request.setAttribute("msg", "답글 등록시 오류 발생");
		request.setAttribute("url", url);
		return "alert";
	}
	
	@RequestMapping("commdel")
	public String commdel(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		String url = "info?num="+num + "&readcnt=f";
		if(cdao.delete(num,seq)) {
			return "redirect:"+url;
		}
		request.setAttribute("msg", "답글 삭제 시 오류 발생");
		request.setAttribute("url", url);
		return "alert";
	}
	
}
