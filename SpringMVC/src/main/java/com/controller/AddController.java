package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.service.AddService;

@Controller
public class AddController {
	
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam("t1") int i, @RequestParam("t2") int j){
		AddService service = new AddService();
		int k = service.add(i, j);
		
		//this is the way that we are passing the data from controller to jsp
		ModelAndView mv = new ModelAndView();
		mv.setViewName("display");//since we mentioned the suffix in MyConfiguration file, we don't need to put "display.jsp"
		mv.addObject("result", k);
		return mv;
	}
}
