package in.bloodsync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import in.bloodsync.dao.BloodRequestDao;
import in.bloodsync.pojo.BloodRequestPojo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HospitalRequestServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		
		String hospitalName=req.getParameter("hospital_name");
		String bloodType=req.getParameter("blood_type");
		String strUnits=req.getParameter("blood_units");
		int bloodUnits=Integer.parseInt(strUnits);
		String urgency=req.getParameter("urgency");
		
		BloodRequestPojo request=new BloodRequestPojo();
		request.setHospitalName(hospitalName);
		request.setBloodType(bloodType);
		request.setRequestedUnits(bloodUnits);
		request.setUrgency(urgency);
		
		
		RequestDispatcher rd=req.getRequestDispatcher("html/response_handling.html");
		try {
			boolean ans=BloodRequestDao.addBloodRequest(request);
			if(ans) {
				pw.println("<title>Registration Successful</title>");
				pw.println("<div class='wrapper'>");
				pw.println("<div class='container'>");
				pw.println("<h1 class='green'>Registration Successful<h1>");
				pw.println("<p>Your blood request has been successfully registered</p>");
				pw.println("<p>We appreciate your support in saving lives</p>");
				pw.println("<a href='html/index.html' class='btn'>Go to Home</a>");
				pw.println("</div></div>");
				
			}else {
				pw.println("<title>Registration Failed</title>");
				pw.println("<div class='wrapper'>");
				pw.println("<div class='container'>");
				pw.println("<h1 class='red'>Registration Failed<h1>");
				pw.println("<p>Something went wrong. Please try later.</p>");
				pw.println("<p>If the problem persists, contact support.</p>");
				pw.println("<a href='html/index.html' class='btn'>Go to Home</a>");
				pw.println("</div></div>");
			}
		}catch(SQLException ex) {
			pw.println("<title>Server Error</title>");
			pw.println("<div class='wrapper'>");
			pw.println("<div class='container'>");
			pw.println("<h1 class='red'>Server Error<h1>");
			pw.println("<p>"+ex.getMessage()+"</p>");
			pw.println("<a href='html/hospital_request.html' class='btn'>Please try again</a>");
			pw.println("</div></div>");
		}finally {
			pw.flush();
			rd.include(req, resp);
			
			}
		

		
		
		
	}
	
	

}





















































































