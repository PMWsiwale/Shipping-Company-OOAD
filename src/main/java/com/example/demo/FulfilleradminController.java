package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import shippingSys.ServiceShippingSys;
import users.Login;

@Controller
@RequestMapping("/fragip")
public class FulfilleradminController {
	@Autowired
	private ServiceShippingSys service;
	
	private  Login data;
	@GetMapping("/admin/fulfil")
	public ModelAndView  getFulfillerpage(@ModelAttribute("employee") Login employee)
	{
		data=new Login();
		data.setFirstName(employee.getFirstName());
		data.setLastName(employee.getLastName());
		data.setPosition(employee.getPosition());
		data.setSsn(employee.getSsn());
		
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("admin/fulfillmentclerk");
		
		Map<String,Object> data= new HashMap<>();	
		try {
		List order=service.getFullfilOder();
		
		data.put("order", order);
		mv.addAllObjects(data);
		}catch(Exception e)
		{
			System.out.print("Failed to show orders>>>"+ e);
		}
		return mv;
	}
  
	@PostMapping("/admin/fulfil/{orderId}")
	public String viewFilledOrder(@PathVariable String orderId, Model model ,RedirectAttributes rA)
	{
		model.addAttribute("employee",data);
		int id= Integer.parseInt(orderId);
		try {
			List viewFilled=service.ViewFilledOrder(id);
			List summary=service.SummaryViewFilledOrder(id);
			
			
			model.addAttribute("viewItem",viewFilled);
			model.addAttribute("summary",summary);
			
		}catch(Exception e)
		{
			System.out.print("Failed to show filled order>>>"+ e);
		}
		return "admin/orderFulfilView";
	}
	@PostMapping("/admin/fulfil/mark/{orderId}")
	public String MarkOrderRead(@PathVariable String orderId,RedirectAttributes rA,Model model)
	{
		int id= Integer.parseInt(orderId);
		try {
//			List viewFilled=service.ViewFilledOrder(id);
//			List summary=service.SummaryViewFilledOrder(id);
			 String statusType="fulfillmentStatus";
		    	
		    	 service.UpdateStatus(id, statusType);
			
//			model.addAttribute("viewItem",viewFilled);
//			model.addAttribute("summary",summary);
			
		}catch(Exception e)
		{
			System.out.print("Failed to show filled order>>>"+ e);
		}
		model.addAttribute("employee",data);
		return "redirect:/fragip/admin/fulfil";
	}
}
