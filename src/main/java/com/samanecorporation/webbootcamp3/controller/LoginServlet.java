package com.samanecorporation.webbootcamp3.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorporation.webbootcamp3.dto.UserAccountDTO;
import com.samanecorporation.webbootcamp3.exception.EntityNotFoundException;
import com.samanecorporation.webbootcamp3.service.IUserAccountService;
import com.samanecorporation.webbootcamp3.service.UserAccountService;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

	private IUserAccountService userAccountService = new UserAccountService();
	private Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	@Override
	public void init(ServletConfig config) throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		  try { 
			  Optional<UserAccountDTO> user = userAccountService.login (email, password); 
			  if (user.isPresent()) { 
				  logger.info("Infos corrects");
				  resp.sendRedirect("welcome"); 
			  } else { 
				  logger.warn("Infos incorrects");
			  }
		  } catch (Exception e) { 
			  logger.error("Erruer : {}", e.getMessage());
			  resp.sendRedirect("login"); 
		  } 	
	}
}
