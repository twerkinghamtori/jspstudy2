package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.mskim.MskimRequestMapping;
import gdu.mskim.RequestMapping;

@WebServlet(urlPatterns= {"/ajax/*"}, initParams= {@WebInitParam(name="view", value="/view/")})
public class AjaxController extends MskimRequestMapping {
	
	@RequestMapping("select")
	public String select(HttpServletRequest request, HttpServletResponse response) {
		BufferedReader br = null; //한줄씩 읽으려고
		String path = request.getServletContext().getRealPath("/") + "file/sido.txt";
		try {
			br = new BufferedReader(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		Set<String> set = new LinkedHashSet<>(); //LinkedHashSet => 순서유지
		String data = null;
		String si = request.getParameter("si"); 
		String gu = request.getParameter("gu"); 
		if(si==null && gu==null) { //si, gu 둘 다 null 이면 시/도 먼저 선택해야함
			try {
				while((data=br.readLine())!= null) {
					String[] arr = data.split("\\s+"); // \\s+ : 공백(\s) 한 개 이상으로 분리
					if(arr.length >= 3) set.add(arr[0].trim()); //arr[0] : 시도
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		} else if(gu==null) {
			si = si.trim();
			try {
				while((data=br.readLine())!= null) {
					String[] arr = data.split("\\s+");
					if(arr.length >= 3 && arr[0].equals(si) && !arr[1].contains(arr[0])) set.add(arr[1].trim()); 
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		} else if(si != null && gu != null) {
			si = si.trim();
			gu = gu.trim();
			try {
				while((data=br.readLine())!= null) {
					String[] arr = data.split("\\s+");
					if(arr.length >= 3 
							&& arr[0].equals(si) && arr[1].equals(gu) 
							&& !arr[1].contains(arr[0]) && !arr[2].contains(arr[1])) {
						if(arr.length > 3) {
							if(arr[3].contains(arr[1])) continue;
							arr[2] += " " +  arr[3];
						}
							set.add(arr[2].trim()); 
					}						
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("list",new ArrayList<String>(set));
		request.setAttribute("length", set.size());
		return "ajax/select";
	}
}
