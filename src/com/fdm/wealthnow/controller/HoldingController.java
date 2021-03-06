package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.Holding;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.dao.HoldingDAO;

/**
 * Servlet implementation class HoldingController
 */
@WebServlet("/HoldingController")
public class HoldingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoldingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			List<Holding> holdingList = new ArrayList<>();
			long userId = 0;
			holdingList = HoldingDAO.retrieveHolding(userId); 
			
			HttpSession session = request.getSession(true);

			request.getRequestDispatcher("PortfolioView.jsp").forward(request, response); 
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new ServletException("Unsupported operation");
		
		
		
	}

}
