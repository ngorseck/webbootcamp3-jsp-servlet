package com.samanecorporation.webbootcamp3.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samanecorporation.webbootcamp3.dto.UserAccountDTO;
import com.samanecorporation.webbootcamp3.service.IUserAccountService;
import com.samanecorporation.webbootcamp3.service.UserAccountService;

@WebServlet(name = "admin", value = "/admin")
public class AdminServlet extends HttpServlet {

	private IUserAccountService userService = new UserAccountService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("usersList", userService.getAll());
		
		req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		boolean state = Boolean.valueOf(req.getParameter("state"));
		
		UserAccountDTO userDto = new UserAccountDTO();
		userDto.setEmail(email);
		userDto.setPassword(password);
		userDto.setState(state);

		Optional<UserAccountDTO> user = userService.save(userDto);
		if (user.isEmpty()) {
			//TODO LOGGER
		} else {
			//TODO LOGGER
		}
		doGet(req, resp);
	}
}
