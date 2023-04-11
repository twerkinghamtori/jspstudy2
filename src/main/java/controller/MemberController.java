package controller;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.Member;
import model.MemberDao;

@WebServlet(urlPatterns= {"/member/*"}, //http://localhost:8080/jspstudy2/member/이후의 어떤 요청이 들어와도 MemberController 서블릿이 호출됨.(URL 경로를 매핑)
              initParams= {@WebInitParam(name="view", value="/view/")}) //view="/view/" 요청정보 (초기 파라미터) 상위 폴더를 설정. view가 model1으로 바뀌면 model1이 됨. 무조건 view밑에 있는게 실행되나? 그럼 이건 return값이랑 관련된거임? 그럼 응답정보 아닌가..
public class MemberController extends MskimRequestMapping { //MskimRequestMapping = 서블릿
	private MemberDao dao = new MemberDao();
	
   @RequestMapping("loginForm") //MskimRequestMapping에 있는 annotation => loginForm 메서드 실행
   //http://localhost:8080/jspstudy2/member/loginForm이 요청으로 들어오면 아래 loginForm함수를 실행
   public String loginForm(HttpServletRequest request, HttpServletResponse response) {
	   return "member/loginForm"; 
	   //view/member/loginForm.jsp가 view로 선택됨 
   }
   
   @RequestMapping("login")
   public String login(HttpServletRequest request, HttpServletResponse response) {
	   //1.파라미터를 변수에 저장하기
	   String id = request.getParameter("id");
	   String pass = request.getParameter("pass");
	   //2.비밀번호 검증
	   Member mem = dao.selectOne(id);
	   String msg = null;
	   String url = null;
	   if(mem == null) {
		   msg="아이디를 확인하세요";
		   url="loginForm";
	   } else if(!pass.equals(mem.getPass())) {
		   msg="비밀번호가 틀립니다.";
		   url="loginForm";
	   } else {
		   request.getSession().setAttribute("login", id);
		   msg="반갑습니다." + mem.getName() + "님";
		   url="main";
	   }
	   request.setAttribute("msg", msg);
	   request.setAttribute("url", url);
	   return "alert"; //view 이름 : /view/alert.jsp (jar파일에 설정되어있음 .jsp를 호출하도록)
   }
   //main 메서드가 없으면 mskimrequest가 그냥 /member/main.jsp를 호출하도록 설정되어잇음
   
   @RequestMapping("main") //main이라는 요청이 들어온다면, 아래 main 메서드를 실행
   public String main(HttpServletRequest request, HttpServletResponse response) {
	   String login = (String)request.getSession().getAttribute("login");
	   if(login==null) {
		   request.setAttribute("msg", "로그인하세요");
		   request.setAttribute("url", "loginForm");
		   return "alert";
	   }
	   return "member/main"; // /view/member/main.jsp로 forward 시킴. 이 메서드에 있는 request객체와 같은 영역.
   }
   
   @RequestMapping("logout")
   public String logout(HttpServletRequest request, HttpServletResponse response) {
	   /*
	    * 1.session에 등록된 login 정보 삭제 session.invalidate
	    * 2.loginForm으로 이동
	    */
	   request.getSession().invalidate();
	   return "redirect:loginForm"; //이렇게 쓰면 mskimrequest에서 주소로 forward가 아니라 redirect(response.sendRedirect)해주는 걸로 설정되어있음
   }
   
   @RequestMapping("info")
   public String info(HttpServletRequest request, HttpServletResponse response) {
  /* 1. 아이디 파라미터값을 조회
     2. login 상태 검증
        - 로그아웃 상태 : "로그인하세요" 메세지 출력후에 loginForm.jsp로 이동
        - admin으로 로그인된 상태 : 모든 회원정보를 볼 수 있음.
        - 로그인 된 상태 : 다른 id를 조회하려고 하면 "내 정보 조회만 가능합니다." 메세지 출력후에 main.jsp로 이동
     3. DB에서 id에 해당하는 데이터를 조회
     4. view로 데이터를 전송 => request 객체에 속성 등록
     */
	   String id = request.getParameter("id");
	   String login = (String)request.getSession().getAttribute("login");
	   if(login==null || login.equals("")) {
		   request.setAttribute("msg", "로그인하세요.");
		   request.setAttribute("url", "loginForm");
		   return "alert";
	   } else if(!login.equals("admin") && !id.equals(login)) {
		   request.setAttribute("msg" , "본인 정보만 조회가능합니다.");
		   request.setAttribute("url", "main");
		   return "alert";
	   }
	   Member mem = dao.selectOne(id);
	   request.setAttribute("mem",mem);
	   return "member/info";
   }
   
}
