package jp.tuyano;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.jdo.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class CardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("no url...");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean flag = true;
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		if(name.equals(""))flag=false;
		String card = req.getParameter("card");
		if(card.equals(""))flag=false;
		String[] nums = new String[4];
		String number = "";
		nums[0] = req.getParameter("number1");
		nums[1] = req.getParameter("number2");
		nums[2] = req.getParameter("number3");
		nums[3] = req.getParameter("number4");
		number = nums[0] + "-" + nums[1] + "-" + nums[2] + "-" + nums[3];
		String security = req.getParameter("security");
		String period = "";
		String month = req.getParameter("month");
		if(month.equals(""))flag=false;
		String year = req.getParameter("year");
		if(year.equals(""))flag=false;
		period = month + year;
		for (int i = 0; i < nums.length; i++)
			if(nums[i].equals(""))flag=false;
		CardData data = new CardData(name, card, number, security, period);
		if (flag) {
			PersistenceManagerFactory factory = PMF.get();
			PersistenceManager manager = factory.getPersistenceManager();
			try {
				manager.makePersistent(data);
			} finally {
				manager.close();
			}
			resp.sendRedirect("/finish.html");
		} else
			resp.sendRedirect("/carta.html");
	}
}
