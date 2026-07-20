package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import shippingSys.ServiceShippingSys;

@Controller()
@RequestMapping("/fragip")
public class StolkClerkController {
	
	@Autowired
	private ServiceShippingSys serviceSH;
	
	@GetMapping("/admin/clerk")
	public ModelAndView getClerkpage()
	{
		
		
		ModelAndView  mv=new ModelAndView ();
		mv.setViewName("admin/stolkclerk");
		List records=serviceSH.getInventoryRecord();
		mv.addObject("records",records);
		
		return mv;
	}
	@PostMapping("/admin/clerk")
	public String getback()
	{
		return "admin/stolkclerk";
	}

}
