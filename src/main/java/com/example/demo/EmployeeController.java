package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import users.Employee;
import users.UserService;

@Controller("fragip")
@RequestMapping("/fragip")
public class EmployeeController {
	
	@GetMapping("/admin/register")
	public String getEmployeeReg(Model model)
	{
		model.addAttribute("register", new Employee());
		
		return "admin/employeeReg";
	}
	@Autowired
	private UserService service;
	
	@PostMapping("/admin/register")
	public String processRegistreation(@ModelAttribute Employee register)
	{
		try {
			service.employeeRegistration(register);
			System.out.printf("The followin data is added:-->%S",register.toString());
			
		}catch(Exception e)
		{
			System.out.println("ERROR----->>>>"+ e);
		}
		
		
		return "redirect:/fragip";
	}

}
