package com.example.demo;

import java.util.List;

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

import jakarta.servlet.http.HttpServletRequest;
import shippingSys.ServiceShippingSys;
import shippingSys.ShippeProduct;
import users.CustomerReg;
import users.Login;

@Controller
@RequestMapping("/fragip")
public class ShipperController {
	@Autowired
	 private ServiceShippingSys serviceSH;
	
	private  Login data;
	@GetMapping("/admin/shipper")
	public ModelAndView getShipperPage(@ModelAttribute("employee") Login employee)
	{
		data=new Login();
		data.setFirstName(employee.getFirstName());
		data.setLastName(employee.getLastName());
		data.setPosition(employee.getPosition());
		data.setSsn(employee.getSsn());
		ModelAndView mv=new ModelAndView();
		mv.setViewName("admin/ship");
		List readyOrders=serviceSH.ReadOrders();
		
		mv.addObject("readyOrders",readyOrders);
		
				
		return mv;
	}
  
	 @PostMapping("/admin/shipper")
	    public String searchOrder(
	            @RequestParam(name = "id") String idParam,
	            Model model) {
		 
		 model.addAttribute("employee",data);
		 List readyOrders=serviceSH.ReadOrders();
		 
	        try {
	            int id = Integer.parseInt(idParam);

	            List address=serviceSH.addressOrderInfo(id);
	            
	           
	            if (address == null || address.isEmpty()) {
	            	String error= "No order found with ID: " + id;
	                model.addAttribute("error",error);
	            } else {

//	            	 address=serviceSH.addressOrderInfo(id);
	                model.addAttribute("address", address);
	               
	                
	               
	            }
	            
	        } catch (NumberFormatException e) {
	        	
	            model.addAttribute("error", "Invalid Order ID format");
	        } catch (Exception e) {
	            model.addAttribute("error", "Error retrieving order: " + e.getMessage());
	        }
	        model.addAttribute("readyOrders",readyOrders);
	    	
	        return "admin/ship";
	    }
	 @PostMapping("/admin/shipper/process/{orderId}")
		public String processForm(@PathVariable String orderId, Model model,ShippeProduct ship ,RedirectAttributes redirectAttrs
				,RedirectAttributes rA)
		{
		 model.addAttribute("employee",data);
		 int id = Integer.parseInt(orderId);
		 ship.setOrderId(id);
		 List readyOrders=serviceSH.ReadOrders();
//		 System.out.printf("data processed finished %s***** ",ship.toString()  );
//			
			try {
			String typeStatus="shipmentStatus";
				
				ship.setOrderId(id);					
			model.addAttribute("ship",ship);
			
			serviceSH.shippeOrderInfo(ship);
			serviceSH.UpdateStatusWithNoDate(id,typeStatus);
			
			
			
//			System.out.printf("data processed finished %s***** ",ship.toString()  );
			}catch(Exception e)
			{
				System.out.print("Errror..>>>" + e);
			}
			redirectAttrs.addFlashAttribute("message", "Shipping  successful!");
			
			model.addAttribute("readyOrders",readyOrders);
		
			return "admin/ship";
		}
	 @PostMapping("/admin/shipper/update")
		public String updateLocation(HttpServletRequest par,Model model,ShippeProduct location ,RedirectAttributes redirectAttrs,
				RedirectAttributes rA)
		{
		 model.addAttribute("employee",data);
		 String id=par.getParameter("orderID");
//		 int id = Integer.parseInt(orderId);
//		 ship.setOrderId(id);
		 model.addAttribute("locationUpdate",location);
		 System.out.print("information from locaton:"+location.toString());
		
	        
//		 System.out.printf("data processed finished %s***** ",ship.toString()  );
//			
			try {
				 serviceSH.UpdateStatusLocation(location);
			
			
		
			
			
			
//			System.out.printf("data processed finished %s***** ",ship.toString()  );
			}catch(Exception e)
			{
				System.out.print("Errror..>>>" + e);
			}
			redirectAttrs.addFlashAttribute("message", "Shipping  successful!");
			
			
		
			return "admin/ship";
		}
	    
}
