package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import accountpayableSys.ServiceAPS;
import jakarta.servlet.http.HttpServletRequest;
import orderProcessingSys.MakeOrder;
import shippingSys.ServiceShippingSys;
import users.Employee;
import users.Login;
import users.SupplerReg;
import users.UserService;

@Controller
@RequestMapping("/fragip")
public class SupplierController {
	
	@Autowired
	private ServiceAPS apsService;
	private String company;
	@Autowired
    private ServiceShippingSys serviceSH;
	
	private  Login data;
	
	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	@GetMapping("/register/supplier")
	public String getSupplierRegister(Model model)
	{
		model.addAttribute("register",new SupplerReg());
		return "user/SupplierRegistration";
	}
     @Autowired
     private UserService service;
	@PostMapping("/register/supplier")
	public String processRegister(SupplerReg register,RedirectAttributes redirectAttrs)
	{
		try
		{
			service.supplierRegister(register);
		}catch(Exception e)
		{
			System.out.printf("ERROR-->>>>%s",e);
		}
		redirectAttrs.addFlashAttribute("message", "Registration Succesful, as:"+register.getSupplierName()+ " Password:"+
				register.getPassword());
		return "redirect:/fragip";
	}
	
	
	@GetMapping("/login/supplier")
	public String getViewSupplier( HttpServletRequest req, Model model,Login supplier,RedirectAttributes redirectAttrs)
	{
		String companyName=req.getParameter("supplier");
		String password=req.getParameter("password");
		
		setCompany(companyName);
		
		data=new Login();
		
		data.setUsername(companyName);
		
		try {
		
		serviceSH.supplierVerify(companyName, supplier);
		
		 if (password.equalsIgnoreCase(supplier.getPassword()))
		 {
			
			 
			 model.addAttribute("employee",supplier);
			
			 model.addAttribute("order",new MakeOrder());
				
				return "user/Supplierview";
				
		 }

		}catch(Exception e)
		{
			System.out.print("Supplier logig failed>>>"+e);
		}
		
		
		
		 redirectAttrs.addFlashAttribute("message", "InCorrect Username or Password");
			
			return "redirect:/fragip";
	}
	
	@PostMapping("/login/supplier")
	public String processdata(MakeOrder order,Model model)
	{
		try {
			order.setSupplierName(getCompany());
			apsService.inserOrder(order);
			System.out.printf("Data from the page:%s", order.toString());
			
		}catch(Exception e)
		{
			System.out.printf("ERROR..%s",e);
		}
		model.addAttribute("employee",data);
		if(order.getEmail().isBlank() || order.getEmail().isEmpty())
		{
			model.addAttribute("message", "Failed to make an Order:No registered customer by this email:"+order.getEmail());
		}
		return "user/Supplierview";
	}
}
