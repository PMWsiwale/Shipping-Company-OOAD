package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import accountpayableSys.ServiceAPS;

@Controller
@RequestMapping("/fragip")
public class ManagerController {
	
	@Autowired
	private ServiceAPS invoiceservice;
	@GetMapping("/admin/manager")
	public String getManagerPage(Model model)
	{
		List invoiceIds =invoiceservice.RetrieveInvoice();
		 model.addAttribute("invoiceIds", invoiceIds);
		
		List list= invoiceservice.extractALLInvoice();
	     model.addAttribute("invoicelist", list);
		return "admin/manager";
	}

}
