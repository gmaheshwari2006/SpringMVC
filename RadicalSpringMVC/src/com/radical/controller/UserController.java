package com.radical.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.radical.entity.Country;
import com.radical.entity.UserEntity;
import com.sun.xml.internal.ws.resources.HttpserverMessages;

@Controller
public class UserController {
	
	@Autowired
    @Qualifier("uservalidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
	
	@ModelAttribute("country")
	public List<Country> getCountry(Model model) {
		List<Country> list = new ArrayList<Country>();
		Country country1 = new Country();
		country1.setCounrtyName("India");
		Country country2 = new Country();
		country2.setCounrtyName("Srilanka");
		list.add(country1);
		list.add(country2);
		return list;
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(
			@Valid @ModelAttribute(value="userModel")  UserEntity userEntity, BindingResult result,
			HttpServletRequest request, Model model) {
		
		if(result.hasErrors()){
			return new ModelAndView("registerUserPage","userModel", userEntity);
		}
		String userName = userEntity.getUserName();
		String fName = userEntity.getfName();
		String lName = userEntity.getlName();

		model.addAttribute("userName", userName);

		model.addAttribute("fName", fName);
		model.addAttribute("lName", lName);
		if(userEntity.getUserId() != null){
			System.out.println("update user");
			
		}else{
			System.out.println("add user");
			userEntity.setUserId(123);
		}
		
		return new ModelAndView("registerUserPage","userModel", userEntity);

	}
	@ResponseBody
	@RequestMapping(value={"/register/save"}, method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public UserEntity testRequestBody(@RequestBody UserEntity user) {
		
		user.setfName("gaurav");
		user.setlName("maheshwari");
		return user;
	}
	@ResponseBody
	@RequestMapping(value={"/register/status"}, method=RequestMethod.POST)
	public String testRequestBodyStatus(@RequestBody UserEntity user) {
		return "success";
	}
	@RequestMapping(value={"/testvoid"}, method=RequestMethod.GET, params="myparam",headers="Host")
	public void testVoidReturn(@CookieValue("JSESSIONID") String cookie, HttpServletRequest req, 
			HttpServletResponse resp, @RequestHeader("Host") String host) throws IOException {
		System.out.println("cookie: " + cookie);
		System.out.println("host: " + host);
		FileInputStream fileInputStream=null;
		 
        File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Hydrangeas.jpg");
 
        byte[] bFile = new byte[(int) file.length()];
 
        try {
            //convert file into array of bytes
	    fileInputStream = new FileInputStream(file);
	    fileInputStream.read(bFile);
	    fileInputStream.close();
 
	   
	    System.out.println("Done");
        }catch(Exception e){
        	e.printStackTrace();
        }
		OutputStream out = resp.getOutputStream();
		out.write(bFile);
	}
	
	@RequestMapping(value="/testRedirect")
	public String testRedirect(@RequestParam(value="name", required=false) String name , Model model){
		if(!name.equals("gaurav")){
			return "redirect:redirectLink";
		}
		return "success";
	}
	
	@RequestMapping(value="/redirectLink")
	public String redirectedPage(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServletException, IOException{
		model.addAttribute("","");
			System.out.println("redirectLink");
			return "redirectedPage";
		/*RequestDispatcher rd = req.getRequestDispatcher("/webpages/redirectedPage.jsp");
		rd.forward(req, resp);*/
		//resp.sendRedirect("webpages/redirectedPage.jsp");
		//resp.sendRedirect("http://www.google.com");
		
		
	}
}
