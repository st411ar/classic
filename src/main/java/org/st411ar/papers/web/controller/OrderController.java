package org.st411ar.papers.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import lombok.Data;

import org.st411ar.papers.dao.DAO;
import org.st411ar.papers.entity.Order;
import org.st411ar.papers.factory.Factory;

@Data
public class OrderController extends AbstractController {
	private Factory daoFactory;

	@Override
	protected ModelAndView handleRequestInternal(
			HttpServletRequest req, HttpServletResponse resp
	) throws Exception {
		DAO dao = daoFactory.getDao();
		List<Order> orders = dao.getOrders();
		Map<String, List<Order>> data = new HashMap<>();
		data.put("orders", orders);
		return new ModelAndView("papers", data);
	}
}