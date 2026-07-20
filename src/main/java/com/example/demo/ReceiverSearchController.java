//package com.example.demo;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import jakarta.servlet.http.HttpSession;
//import orderProcessingSys.MakeOrder;
//import shippingSys.NewProduct;
//import shippingSys.ServiceShippingSys;
//import shippingSys.UpdateInventory;
//import users.Login;
//@Controller
//@RequestMapping("/fragip")
//public class ReceiverSearchController {
//    
//    @Autowired
//    private ServiceShippingSys serviceSH;
//   
//    
//
//    @PostMapping("/admin")
//    public String searchOrder(
//            @RequestParam(name = "id") String idParam,
//            Model model,@ModelAttribute("employee") Login data) {
//    	
//    
//    
//    	
//    	
//    	
//        try {
//            int id = Integer.parseInt(idParam);
////            MakeOrder order= new MakeOrder();
//            List order=serviceSH.GetInvoiceToReceiverList(id);
//            if (order == null) {
//                model.addAttribute("error", "No order found with ID: " + id);
//            } else {
////            	 serviceSH.giveReseciever(id, order);
//            	 order=serviceSH.GetInvoiceToReceiverList(id);
//            	 
//            	
//                model.addAttribute("order", order);
//                
//                
//               
//            }
//            
//        } catch (NumberFormatException e) {
//            model.addAttribute("error", "Invalid Order ID format");
//        } catch (Exception e) {
//            model.addAttribute("error", "Error retrieving order: " + e.getMessage());
//        }
//    	
//       
//    	
//        return "admin/ReceiveClerk";
//    }
//    
//    @PostMapping("/admin/verify/{orderId}")
//    public String verifyOrder(@PathVariable String orderId,Model model,NewProduct stolk) {
//    	 int id= Integer.parseInt(orderId);
//    	 
//    	 String statusType="approvalStatus";
//    	 model.addAttribute("stolk",stolk);
//    	try { 
//    	 serviceSH.UpdateStatus(id, statusType);
//    	
//
//     	serviceSH.StockNewProduct(stolk);
//    	} 
//    	catch(Exception e)
//    	{
//    		System.out.print("Failed to Upadate>>"+e);
//    	}
//    	 System.out.print("stolk information>>>"+ stolk.toString());
//        return "admin/ReceiveClerk";
//    }
//    
//    
//
//}
