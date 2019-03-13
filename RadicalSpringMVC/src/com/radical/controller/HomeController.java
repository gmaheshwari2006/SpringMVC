package com.radical.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.radical.entity.Country;
import com.radical.entity.UserEntity;

@Controller
@SessionAttributes("sessionuser")
public class HomeController {

	
    
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

	@RequestMapping(value = "/")
	public ModelAndView welcomePage(Model model, HttpServletResponse res) {
		String instName = "Radical Technologies";
//		model.addAttribute("name", instName);
		ModelAndView modelview = new ModelAndView();
//		modelview.addObject("sessionuser", "user has come to welcome page");
		modelview.addObject("name", instName);
		modelview.setViewName("loginPage"); 
		model.addAttribute("sessionuser", "user has come to welcome page");
		return modelview;
	}
	 
	/*@RequestMapping(value = "/")
	public String welcomePage(Model model) {
		String instName = "Radical Technologies";
		model.addAttribute("name", instName);
		return "loginPage";
		
	}*/

	@RequestMapping(value = "/login")
	public String login(Model model, HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		model.addAttribute("userName", username);
		model.addAttribute("fName", "Gaurav");
		model.addAttribute("lName", "Maheshwari");
		
		return "showUser";
	}

//	@RequestMapping(value = "/registerPage/{city}", method = RequestMethod.GET)
	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public ModelAndView registerUserPage(@ModelAttribute("country") List<Country> country, 
			HttpServletRequest request, HttpSession session, Model model
			) {
		String welcomeMsg = (String)session.getAttribute("sessionuser");
		System.out.println("session value: " + welcomeMsg);
//		System.out.println("city Name Is: " + cityName);
		System.out.println("registerPage");
		
		ModelAndView modelview = new ModelAndView("registerUserPage", "userModel",
				new UserEntity());
		return modelview;
	}
	
	

	
	@RequestMapping(value="/pamartest")
	public void pamartest(@RequestParam(value="city") String cityName){
		System.out.println("requested city name: " + cityName);
		
	}
	@RequestMapping(value="/pathvar/{mycity}", params="param=15")
	public void testPathVariable(@PathVariable(value="mycity")  String mycity, @RequestParam String param ){
		System.out.println("name of city is: " + mycity);
		
	}
}
