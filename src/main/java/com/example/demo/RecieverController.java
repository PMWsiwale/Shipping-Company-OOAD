package com.example.demo;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import jakarta.servlet.http.HttpSession;
import shippingSys.NewProduct;
import shippingSys.ServiceShippingSys;
import shippingSys.UpdateInventory;
import users.Login;

@Controller
@RequestMapping("/fragip")
public class RecieverController{
	
//	@Autowired
//	private UpdateInventory serviceupdate;
	
	@Autowired
    private ServiceShippingSys serviceSH;
	
	
	private  Login data;
	
	
	@GetMapping("/admin")
	public String getReciever(@ModelAttribute("employee") Login employee)
	{
		data=new Login();
		data.setFirstName(employee.getFirstName());
		data.setLastName(employee.getLastName());
		data.setPosition(employee.getPosition());
		data.setSsn(employee.getSsn());
//		System.out.printf(data.toString());
		
		
		
		return "admin/ReceiveClerk.html";
	}
	
	@GetMapping("/admin/verifiedorders")
	public ModelAndView veiwVerifiedOrders(Model model)
	{
		
		
	  
	   ModelAndView mv=new ModelAndView();
	   
		
		mv.setViewName("admin/verifiedorders.html");
		
//		 =serviceupdate.ViewVeriFiedOrder();
		 List View=serviceSH.ViewVeriFiedOrder();
		mv.addObject("ViewOrder",View);
		
		return mv;
	   
		
	}
	@GetMapping("/admin/menu")
	public ModelAndView BackMenu(RedirectAttributes rA,Model model)
	{
		
		
		 rA.addFlashAttribute("employee",data);

	   ModelAndView mv=new ModelAndView();
//	   model.addAttribute("employee", data);
	   
		
		mv.setViewName("redirect:/fragip/admin");
		

		
		return mv;
	   
		
	}
	@GetMapping("/admin/stock")
	public ModelAndView manageStock(Model model)
	{
		
		
	  
	   ModelAndView mv=new ModelAndView();
	   
		
		mv.setViewName("redirect:../admin/clerk");
		
//		List View =serviceupdate.ViewVeriFiedOrder();	
//		mv.addObject("ViewOrder",View);
		
		return mv;
	   
		
	}
//	post methods
	
	 @PostMapping("/admin")
	    public String searchOrder(
	            @RequestParam(name = "id") String idParam,
	            Model model) {
	    	
	    
	    
		 model.addAttribute("employee",data);
	    	
	    	
	        try {
	            int id = Integer.parseInt(idParam);
//	            MakeOrder order= new MakeOrder();
	            List order=serviceSH.GetInvoiceToReceiverList(id);
	            if (order == null) {
	                model.addAttribute("error", "No order found with ID: " + id);
	            } else {
//	            	 serviceSH.giveReseciever(id, order);
	            	 order=serviceSH.GetInvoiceToReceiverList(id);
	            	 
	            	
	                model.addAttribute("order", order);
	                
	                
	               
	            }
	            
	        } catch (NumberFormatException e) {
	            model.addAttribute("error", "Invalid Order ID format");
	        } catch (Exception e) {
	            model.addAttribute("error", "Error retrieving order: " + e.getMessage());
	        }
	    	
	       
	        System.out.printf(data.toString());
	        return "admin/ReceiveClerk";
	    }
	    
	    @PostMapping("/admin/verify/{orderId}")
	    public String verifyOrder(@PathVariable String orderId,Model model,NewProduct stolk) {
	    	 int id= Integer.parseInt(orderId);
	    	 
	    	 model.addAttribute("employee",data);
	    	 String statusType="approvalStatus";
	    	 model.addAttribute("stolk",stolk);
	    	try { 
	    	 serviceSH.UpdateStatus(id, statusType);
	    	

	     	serviceSH.StockNewProduct(stolk);
	    	} 
	    	catch(Exception e)
	    	{
	    		System.out.print("Failed to Upadate>>"+e);
	    	}
	    	 System.out.print("stolk information>>>"+ stolk.toString());
	        return "admin/ReceiveClerk";
	    }
	    
	
	

}
