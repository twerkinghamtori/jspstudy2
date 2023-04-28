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
		BufferedReader br = null;
		String path = request.getServletContext().getRealPath("/") + "file/sido.txt";
		try {
			br = new BufferedReader(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		Set<String> set = new LinkedHashSet<>();
		String data = null;
		String si = request.getParameter("si");
		String gu = request.getParameter("gu");
		if(si==null && gu==null) {
			try {
				while((data=br.readLine())!= null) {
					String[] arr = data.split("\\s+");
					if(arr.length >= 3) set.add(arr[0].trim());
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
