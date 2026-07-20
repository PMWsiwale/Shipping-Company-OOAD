package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import shippingSys.ServiceShippingSys;
import users.Login;

@Controller
@RequestMapping("/fragip")
public class HomePageController {
	
	@Autowired
	private ServiceShippingSys serviceSH;
	
	@GetMapping("")
	public String homepage()
	{
		return "HomePage.html";
	}

	
	@PostMapping("/login/admin")
	public String getAdmin(HttpServletRequest req,RedirectAttributes rA,@ModelAttribute Login emp,Model model)
	{
		String ssn=req.getParameter("username");
		int SSN=Integer.parseInt(ssn);
		String pass=req.getParameter("password");
		
		ModelAndView mv=new ModelAndView ();
		
		try {
		serviceSH.VerifyEmp(SSN, emp);
		

		
		
		 if (pass.equalsIgnoreCase(emp.getPassword()) && emp.getPosition().equalsIgnoreCase("Receiving clerk"))
		 {
			 emp.setSsn(SSN);
			 rA.addFlashAttribute("employee",emp);
			 return "redirect:/fragip/admin";
		 }
		 else if (pass.equalsIgnoreCase(emp.getPassword()) && emp.getPosition().equalsIgnoreCase("Stock clerk"))
		 {
			emp.setSsn(SSN);
			 rA.addFlashAttribute("employee",emp);
			 return "redirect:/fragip/admin/clerk";
		 }
		 else if (pass.equalsIgnoreCase(emp.getPassword()) && emp.getPosition().equalsIgnoreCase("shipper"))
		 {
//			
			 emp.setSsn(SSN);
			 rA.addFlashAttribute("employee",emp);
			 return "redirect:/fragip/admin/shipper";
		 }
		 else if (pass.equalsIgnoreCase(emp.getPassword()) && emp.getPosition().equalsIgnoreCase("order Processor"))
		 {
			 emp.setSsn(SSN);
			 rA.addFlashAttribute("employee",emp);
			 return "redirect:/fragip/admin/ops";
		 }
		 else if (pass.equalsIgnoreCase(emp.getPassword()) && emp.getPosition().equalsIgnoreCase("Filling clerk")) {
			 emp.setSsn(SSN);
			 rA.addFlashAttribute("employee",emp);
			 return "redirect:/fragip/admin/fulfil";
		 }
		 
		}catch(Exception e)
		{
			System.out.print(e);
		}
		model.addAttribute("message", "Incorrect Credentials"); 
		
		
		return "HomePage";
	}

	@PostMapping("/admin/logout")
	public String logout()
	{
		return "redirect:/fragip";
	}
}
