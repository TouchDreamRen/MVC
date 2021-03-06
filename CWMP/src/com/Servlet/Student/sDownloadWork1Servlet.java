package com.Servlet.Student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BeanDao.StudentDao;
import com.BeanDao.WorkDao;
import com.JavaBean.Student;
import com.JavaBean.Work;

/**
 * Servlet implementation class sDownloadServlet
 */
@WebServlet("/sDownloadWork1Servlet")
public class sDownloadWork1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sDownloadWork1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");
		
		System.out.println("***这是获取作业表的sDownloadWork1Servlet****");
		//获取学号
		HttpSession session = request.getSession(); 
		String account = session.getAttribute("account").toString();
		System.out.println("sDownloadWork1Servlet页面:"+account);
		//获取该学生的班级号
		StudentDao stu = new StudentDao();
		Student s = stu.QueryBySno(account);
		String CLno = s.getCLno().toString();
		System.out.println("sDownloadWork1Servlet页面CLno:"+CLno);
		//按班级号在作业表中查询到所有的作业记录，放到数组wList中
		WorkDao  wDao = new WorkDao();
		List<Work> wList = (List<Work>) wDao.QueryByCLno(CLno);
		
		
		if( wList == null ){
			System.out.println("没有获取到作业表中的数据!***");
			request.getRequestDispatcher("/page/student/sDownloadWork.jsp").forward(request, response);
		}else{
			//将装有作业表的list保存到request中，传到sDownload.jsp页面
			request.setAttribute("list", wList);
			System.out.println("****sDownloadWork.jsp******");
			request.getRequestDispatcher("/page/student/sDownloadWork.jsp").forward(request, response);	
		}	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
