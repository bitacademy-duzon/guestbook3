package com.douzone.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.guestbook.dao.GuestbookDao;
import com.douzone.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	@RequestMapping( "" )
	public String index( Model model ){
		model.addAttribute( "list", guestbookDao.getList() );
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping( value="/delete/{no}", method=RequestMethod.GET )
	public String delete( @PathVariable( "no" ) Long no, Model model ){
		model.addAttribute( "no", no );
		return "/WEB-INF/views/delete.jsp";
	}
	
	@RequestMapping( value="/delete", method=RequestMethod.POST )
	public String delete( @ModelAttribute GuestbookVo vo ){
		guestbookDao.delete( vo );
		return "redirect:/";
	}
	
	@RequestMapping( value="/add", method=RequestMethod.POST )
	public String add( @ModelAttribute GuestbookVo vo ) {
		guestbookDao.insert(vo);
		return "redirect:/";
	}
}
