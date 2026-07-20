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

import orderProcessingSys.ServiceOPS;
import users.Login;

@Controller
@RequestMapping("/fragip")
public class OrderPSysController {
	
	@Autowired
	private ServiceOPS service;
	
	private  Login data;
	
	@GetMapping("/admin/ops")
	public ModelAndView shopOPSpage(@ModelAttribute("employee") Login employee)
	{
		
		data=new Login();
		data.setFirstName(employee.getFirstName());
		data.setLastName(employee.getLastName());
		data.setPosition(employee.getPosition());
		data.setSsn(employee.getSsn());
		 ModelAndView mv =new ModelAndView();
		 
		 		 
		 mv.setViewName("admin/orderprocessing");
		 
		 Map<String, Object> data=new HashMap<>();
		 try {
		 List status=service.getMoreOrderStatus();
		  
		 List requestOrder=service.RetrieveOrder();
		 
		 
		 
		 
		 data.put("statusOrder", status);
		 data.put("order",requestOrder);
		 } catch(Exception e) { System.out.print("Failed to retrieve>>"+e); }
		 
		 mv.addAllObjects(data);
		return mv;
	}
	
	
	 @PostMapping("/admin/ops/{orderId}")
	    public String verifyOrder(@PathVariable String orderId, Model model ,RedirectAttributes rA) {
	    	 int id= Integer.parseInt(orderId);
	    	
	    	 String statusType="approvalStatus";
	    	try { 
                   
	    		service.sendRequest(id);
	    		
	    	} 
	    	catch(Exception e)
	    	{
	    		System.out.print("Failed to Upadate>>"+e);
	    	}
	        
	    	
	    	 rA.addFlashAttribute("employee",data);
        return "redirect:/fragip/admin/ops";

	    }

}
