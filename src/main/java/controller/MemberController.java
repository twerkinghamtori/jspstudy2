package controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import gdu.mskim.MSLogin;
import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;
import model.Member;
import model.MemberDao;
//url에 pictureForm 들어오면 mskim에서 자동으로 view/pictureForm.jsp 로 맵핑해줌. pictureForm.jsp가 왜 자동으로 뜨지? mskim 이 해줌~

@WebServlet(urlPatterns= {"/member/*"}, //http://localhost:8080/jspstudy2/member/이후의 어떤 요청이 들어와도 MemberController 서블릿이 호출됨.(URL 경로를 매핑)
              initParams= {@WebInitParam(name="view", value="/view/")}) //view="/view/" 요청정보 (초기 파라미터) 상위 폴더를 설정. return 값 앞에 붙게됨. View의 위치를 설정.
public class MemberController extends MskimRequestMapping { //MskimRequestMapping = 서블릿(HttpServlet을 상속받은 클래스)
	private MemberDao dao = new MemberDao();
	//로그인 검증, id파라미터와 로그인 정보를 검증해주는 함수
	public String loginIdCheck(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException { // @MSLogin annotation에 있는 거랑 이름 똑같아야함.
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String login = (String)request.getSession().getAttribute("login");
		if(login==null || login.equals("")) {
			request.setAttribute("msg", "로그인하세요.");
			request.setAttribute("url", "loginForm");
			return "alert";
		} else if(!login.equals("admin") && !id.equals(login)) {
			request.setAttribute("msg" , "본인만 접근 가능합니다.");
			request.setAttribute("url", "main");
			return "alert";
		}
		return null;
	}
	
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
	
//   @RequestMapping("loginForm") //MskimRequestMapping에 있는 annotation => loginForm 메서드 실행
//   //http://localhost:8080/jspstudy2/member/loginForm이 요청으로 들어오면 아래 loginForm함수를 실행
//   public String loginForm(HttpServletRequest request, HttpServletResponse response) {
//	   return "member/loginForm"; 
//	   // /view/member/loginForm.jsp가 view로 선택됨 
//   }
   
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
   @MSLogin("loginIdCheck") //annotation이 붙어있으면 info메서드를 실행하기 전에 "loginIdCheck" 메서드를 먼저 실행
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
	   
	   Member mem = dao.selectOne(id);
	   request.setAttribute("mem",mem);
	   return "member/info";
   }

   @RequestMapping("join")
   public String join(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	   /*1. 파라미터 정보를 Member객체에 저장.
       2. Member객체를 이용하여 db에 insert (member 테이블)
       3. 가입성공 : db에 등록 => loginForm.jsp 페이지로 이동
          가입실패 : joinForm.jsp 페이지로 이동*/
	   request.setCharacterEncoding("UTF-8");
	   Member mem = new Member();
	   mem.setId((String)request.getParameter("id"));
	   mem.setPass((String)request.getParameter("pass"));
	   mem.setName((String)request.getParameter("name"));
	   mem.setGender(Integer.parseInt(request.getParameter("gender")));
	   mem.setTel((String)request.getParameter("tel"));
	   mem.setEmail((String)request.getParameter("email"));
	   mem.setPicture((String)request.getParameter("picture"));
	   if(dao.insert(mem)) {
		   request.setAttribute("msg","가입 성공");
		   request.setAttribute("url", "loginForm");
		   return "alert";
	   } else {
		   request.setAttribute("msg","가입 실패");
		   request.setAttribute("url", "joinForm");
		   return "alert";
	   }
   }
   
   @RequestMapping("updateForm")
   @MSLogin("loginIdCheck")
   public String updateForm(HttpServletRequest request, HttpServletResponse response) {
	   /*
	    *  1. 아이디 파라미터값을 조회
     2. login 상태 검증
        - 로그아웃 상태 : "로그인하세요" 메세지 출력후에 loginForm.jsp로 이동
        - 로그인 된 상태 : 다른 id를 수정하려고 하면 "내 정보만 수정이 가능합니다." 메세지 출력후에 main.jsp로 이동
     3. DB에서 id에 해당하는 데이터를 조회
     4. 조회된 내용을 화면에 출력(수정 전 화면)*/
	   String id = request.getParameter("id");
	   
	   Member mem = dao.selectOne(id);
	   request.setAttribute("mem", mem);
	  	   
	   return "member/updateForm";
   }
   
   @RequestMapping("update")
   @MSLogin("loginIdCheck")
   public String update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	   /*
	 1. 모든 파라미터를 Member Bean 객체에 저장.
     2. 입력된 비밀번호와 db에 저장된 비밀번호를 비교.(로그인 정보로 비교)
          관리자인 경우 관리자비밀번호와 비교.
          불일치 : '비밀번호 오류' 메세지 출력 후 updateForm.jsp로 페이지 이동
     3. 일치 : Member 객체의 내용으로 db를 수정.
              - 성공 : 회원정보 수정 완료 메세지 출력 후 info.jsp 페이지로 이동
              - 실패 : 회원정보 수정 실패 메세지 출력 후 updateForm.jsp 페이지로 이동*/
	   request.setCharacterEncoding("UTF-8");
	   String login = (String)request.getSession().getAttribute("login");
	   Member mem = new Member();   
	   mem.setId(request.getParameter("id"));
	   mem.setPass(request.getParameter("pass"));
	   mem.setName(request.getParameter("name"));
	   mem.setGender(Integer.parseInt(request.getParameter("gender")));
	   mem.setTel(request.getParameter("tel"));
	   mem.setEmail(request.getParameter("email"));
	   mem.setPicture(request.getParameter("picture"));
	   Member mem2 = dao.selectOne(login);
	   if(!mem.getPass().equals(mem2.getPass())) {
		   request.setAttribute("msg", "비밀번호가 틀립니다.");
		   request.setAttribute("url", "updateForm?id="+mem.getId());
		   return "alert";
	   } else {
		   if(dao.update(mem)) {
			   request.setAttribute("msg", "회원정보 수정 완료");
			   request.setAttribute("url", "info?id="+mem.getId());
			   return "alert"; 	
		   } else {
			   request.setAttribute("msg", "회원정보 수정 실패");
			   request.setAttribute("url", "updateForm?id="+mem.getId());
			   return "alert";
		   }
	   }
   }
   
   @RequestMapping("deleteForm")
   @MSLogin("loginIdCheck")
   public String deleteForm(HttpServletRequest request, HttpServletResponse response) {
   /*
     1.id 파라미터 저장
     2.로그인 정보 검증
        - 로그아웃 상태 : 로그인하세요. 출력 후 loginForm.jsp로 이동
        - 관리자 제외, 다른 사용자 탈퇴 불가. 본인만 탈퇴 가능합니다. 메세지 출력 후 main.jsp 페이지로 이동
     3. 화면호출 deleteForm.jsp페이지.*/	   
	   return "member/deleteForm";
   }
   
   @RequestMapping("delete")
   @MSLogin("loginIdCheck")
   public String delete(HttpServletRequest request, HttpServletResponse response) {
	   /*
	     1. 파라미터 정보 저장
	     2. 로그인정보 검증
	          -로그아웃 상태 : 로그인하세요. 메세지 출력 후 loginForm.jsp로 이동
	          -다른사람 탈퇴 시도, 관리자제외 : 본인만 탈퇴 가능합니다. 메세치 출력 후 main.jsp로 이동
	     3. 관리자 탈퇴는 불가.
	          - 관리자 정보 탈퇴 시 관리자는 탈퇴 불가. 메세지 출력 후 list.jsp로 이동
	     4. 비밀번호 검증
	          - 로그인 정보로 비밀번호 검증.
	               -비밀번호가 틀림 : 비밀번호가 틀렸습니다 출력 후 deleteForm.jsp로 이동
	               -비밀번호 맞음 : db에서 delete 실행. (MemberDao delete(id))
	                        -일반사용자 : 로그아웃.(세션정보 종료) 탈퇴가 완료되었습니다.출력 후 loginForm.jsp로 이동
	                        -관리자 : 탈퇴 완료 메세지 출력 후 list.jsp로 이동
	     5. 탈퇴 실패 - 일반사용자 : 탈퇴 실패 메세지 출력 후 info.jsp로 이동
	                - 관리자 : 탈퇴 실패 메세지 출력 후 list.jsp로 이동*/
	   String id = request.getParameter("id");
	   String pass = request.getParameter("pass");
	   String login = (String)request.getSession().getAttribute("login");
	   if(id.equals("admin")) {
		   request.setAttribute("msg", "관리자는 탈퇴가 불가합니다.");
		   request.setAttribute("url", "list");
		   return "alert";
	   } 
	   if(!pass.equals(dao.selectOne(login).getPass())) {
		   request.setAttribute("msg", "비밀번호가 틀렸습니다.");
		   request.setAttribute("url", "deleteForm?id="+id);
		   return "alert";
	   } else {
		   if(dao.delete(id)) {
			   if(login.equals("admin")) {
				   request.setAttribute("msg", "강제 탈퇴가 완료되었습니다.");
				   request.setAttribute("url", "list");
				   return "alert";
			   } else {
				   request.getSession().invalidate();
				   request.setAttribute("msg", "탈퇴가 완료되었습니다.");
				   request.setAttribute("url", "loginForm");
				   return "alert";
			   }
			   
		   } else {
			   if(login.equals("admin")) {
				   request.setAttribute("msg", "강제 탈퇴에 실패했습니다.");
				   request.setAttribute("url", "list");
				   return "alert";
			   } else {
				   request.setAttribute("msg", "탈퇴에 실패했습니다.");
				   request.setAttribute("url", "info?id="+id);
				   return "alert";
			   }
		   }
	   }
   }   
   
   @RequestMapping("list")
   @MSLogin("loginAdminCheck") //관리자만 사용가능한 페이지 확인 메서드.
   public String list(HttpServletRequest request, HttpServletResponse response) {
	   /*
	     1. 관리자만 사용가능 페이지 => 검증
	         -로그아웃 상태 : 로그인이 필요합니다. 메세지 출력 후 loginForm.jsp로 이동
	         -로그인 상태,관리자x : 관리자만 이용가능합니다. 메세지 출력 후 main.jsp로 이동
	     2. db에서 모든 회원정보를 조회. List<Member> 객체로 리턴.(MemberDao.list() 함수를 생성해야함)
	     3. List객체를 이용하여 화면에 출력.*/
	   List<Member> list = dao.list();
	   request.setAttribute("list", list);
	   return "member/list";
   }
   
   @RequestMapping("picture")
   public String picture(HttpServletRequest request, HttpServletResponse response) throws IOException {
  /* 1. 이미지 파일 업로드. request 객체 사용 불가
           이미지 파일 업로드 위치 : jspstudy2/picture 폴더로 설정
     2. opener 화면에 결과 전달 => javascript
     3. 현재화면 close() => javascript
   */
	 //서블릿에서 ServletContext가 application 객체임.
	   String path = request.getServletContext().getRealPath("/") + "/picture/"; 
	   String fname = null;
	   File f = new File(path);
	   if(!f.exists()) f.mkdirs(); //업로드 폴더가 없는 경우 폴더 생성.
	   // request : 요청객체. 파라미터, 파일의 내용, 파일 이름
	   // path : 업로드된 파일이 저장 될 폴더
	   MultipartRequest multi = new MultipartRequest(request, path, 10*1024*1024, "UTF-8"); //업로드(최대 10 MB)
	   fname = multi.getFilesystemName("picture"); 
	   request.setAttribute("fname", fname);
	   return "member/picture";
   }
   
   @RequestMapping("idchk")
   public String idchk(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	   request.setCharacterEncoding("UTF-8");
	   String id = request.getParameter("id");
	   Member mem = dao.selectOne(id);
	   request.setAttribute("mem" , mem);
	   return "member/idchk";	   
   }
   
   @RequestMapping("id")
   public String id(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	   /*
	    1. 파라미터 값 저장(email, tel)
        2. db에서 두개의 파라미터를 이용해 id값을 리턴해주는 함수 => MemberDao.idSearch(email, tel) return id
        3. id 검증
          -id 존재 : 1. opener(loginForm) 윈도우에 id값을 전달 -> 현재화면 close
                    2. 화면에 뒤쪽 2자만 ** 표시하여 화면에 출력하기 -> 아이디 전송 버튼을 클릭하면 opener 윈도우에 id값 전달. 현재화면 close
          -id 존재x : 아이디를 찾을 수 없습니다. 현재화면을 idForm.jsp로 이동*/
	   request.setCharacterEncoding("UTF-8");
	   String email = request.getParameter("email");
	   String tel = request.getParameter("tel");
	   String id = dao.idSearch(email, tel);
	   if(id == null) {
		   request.setAttribute("msg", "아이디를 찾을 수 없습니다.");
		   request.setAttribute("url", "idForm");
		   return "alert";
	   } else {
		   request.setAttribute("id", id);
		   return "member/id";
	   }
   }
   
   @RequestMapping("pw")
   public String pw(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	   /*1. 파라미터 저장.
  2. db에서 id,email과 tel 을 이용하여 id값을 리턴
       pass = MemberDao.pwSearch(id,email,tel)
  3. 비밀번호 검증 
     비밀번호 찾은 경우 :화면에 앞 두자리는 **로 표시하여 화면에 출력. 닫기버튼 클릭시 
                     현재 화면 닫기
     비밀번호 못찾은 경우: 정보에 맞는 비밀번호를 찾을 수 없습니다.  메세지 출력후
                     pwForm.jsp로 페이지 이동. */
	   request.setCharacterEncoding("UTF-8");
	   String id = request.getParameter("id");	   
	   String email = request.getParameter("email");
	   String tel = request.getParameter("tel");
	   String pass = dao.pwSearch(id, email, tel);
	   if(pass == null) {
		   request.setAttribute("msg", "비밀번호를 찾을 수 없습니다.");
		   request.setAttribute("url", "pwForm");
		   return "alert";
	   } else {
		   request.setAttribute("pass", pass);
		   return "member/pw";
	   }	   
   }
   
   @RequestMapping("password")
   public String password(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
	   request.setCharacterEncoding("UTF-8");
	   String pass = request.getParameter("pass");
	   String chgpass = request.getParameter("chgpass");
	   String login = (String)request.getSession().getAttribute("login");
	   Member mem = dao.selectOne(login);
	   String dbpass = mem.getPass();
	   System.out.println(dbpass);
	   
	   boolean able= true;
	   if(dao.updatePass(login, chgpass)) able= true;
	   else able=false;
	   
	   request.setAttribute("able", able);
	   request.setAttribute("pass", pass);
	   request.setAttribute("login", login);
	   request.setAttribute("dbpass", dbpass);
	   return "member/password";
   }
   
}
