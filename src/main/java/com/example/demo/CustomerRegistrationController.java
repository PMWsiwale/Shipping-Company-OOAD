package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import shippingSys.ServiceShippingSys;

import org.springframework.ui.Model;

import users.Address;
import users.CustomerReg;
import users.Login;
import users.UserService;

@Controller
@RequestMapping("/fragip")
public class CustomerRegistrationController {
 
//	@GetMapping("/customer")
//	public String getCustomerPage()
//	{
//		return "user/userview.html";
//	}
	@Autowired
    private ServiceShippingSys serviceSH;
	
	private  Login data;
	
	@GetMapping("/register")
	public String getCustomerRegister()
	{
		return "user/customer_register";
	}
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register/login")
	public String processForm(Model model,CustomerReg register ,RedirectAttributes redirectAttrs)
	{
//		CustomerReg register =new CustomerReg();
		try {
		
		
		register.setUsername(register.getEmail());
		model.addAttribute("register",register);
		
		service.customerRegister(register);
		
//		System.out.printf("data processed finished %s***** ",register.toString()  );
		}catch(Exception e)
		{
			System.out.print("Errror..>>>" + e);
//			System.out.printf("data processed finished %s***** ",register.toString()  );
		}
		redirectAttrs.addFlashAttribute("message", "Registration successful!");
		
	
		return "redirect:/fragip";
	}
	@PostMapping("/login/user")
	public String login(@RequestParam(name = "username") String username,@RequestParam(name = "password") String password,
			RedirectAttributes rA,@ModelAttribute Login customer,RedirectAttributes redirectAttrs,Model model)
	{
		
		
		
		
		try {
		serviceSH.customerVerify(username, customer);
		List order=serviceSH.updateCustomerOrder(username);
		
		System.out.print("inside"+customer.toString());
		data=new Login();
		data.setFirstName(customer.getFirstName());
		data.setLastName(customer.getLastName());
		data.setUsername(username);
		 if (password.equalsIgnoreCase(customer.getPassword()))
		 {
			
			 
			 model.addAttribute("employee",customer);
			 model.addAttribute("order",order);
			 
			 return "/user/userview";
		 }
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		
		System.out.printf("password and username "+username+password);
		redirectAttrs.addFlashAttribute("message", "InCorrect Username or Password");
		
		return "redirect:/fragip";
		
		
	}

 @PostMapping("/login/user/confirm")
 public String confirmOrder(@RequestParam(name = "orderId") String idParam,Model model)
 {
	 
	 int orderid=Integer.parseInt(idParam);
	 try {
	 serviceSH.confirmOrderReceived(orderid);
	 List order=serviceSH.updateCustomerOrder(data.getUsername());
	 model.addAttribute("order",order);
	 model.addAttribute("employee",data);
	 }catch(Exception e)
	 {
		 System.out.print("error from conrfimin order>>>"+e);
	 }
	 
//	 model.addAttribute("employee",data);
	 return "/user/userview";
 }
 
 @PostMapping("/login/user/payment")
 public String processPayment(@RequestParam("paymentMethod") String paymentMethod,
                              @RequestParam("orderId") int orderId,
                              Model model) {
     // Process payment here (simulation)
	 serviceSH.PaymentUpdateStatus(orderId);
     model.addAttribute("message", "Payment via " + paymentMethod + " was successful for Order ID: " + orderId);
     return "/user/paymentConfirmation"; // Create paymentConfirmation.html to display this
 }

}
