package com.landis.throughout.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.landis.throughout.commons.Constants;
import com.landis.throughout.commons.Pager;

@Controller
@RequestMapping(Constants.ADMIN_URL_ROOT + "/quotation/*")
public class QuotationController {

	@RequestMapping("/manufacturers/list/{page}")
	public String list(Model model, @RequestParam(required = false) Integer orderby,
			@PathVariable(value = "page") Integer page) {
		Map<String, Object> filter = new HashMap<String, Object>();
		int count = 0;
		Pager pager = new Pager(count, page);
		model.addAttribute("count", count);
		model.addAttribute("pager", pager);
		return "/admin/quotation/manufacturers/list";
	}

	@RequestMapping("/manufacturers/add")
	public String add(Model model) {

		return "/admin/quotation/manufacturers/add";
	}

	@RequestMapping("/manufacturers/create")
	public String create(Model model) {

		return "/admin/quotation/manufacturers/adddetail";
	}

	@RequestMapping("/manufacturers/view")
	public String view(Model model) {

		return "/admin/quotation/manufacturers/view";
	}

	@RequestMapping("/manufacturers/createdetail")
	public String createdetail(Model model) {

		return "/admin/quotation/manufacturers/view";
	}

	@RequestMapping("/inquiries/list/{page}")
	public String inquiriesList(Model model, @RequestParam(required = false) String machineNo,
			@RequestParam(required = false) Integer orderby, @PathVariable(value = "page") Integer page) {
		Map<String, Object> filter = new HashMap<String, Object>();
		int count = 0;
		Pager pager = new Pager(count, page);
		model.addAttribute("count", count);
		model.addAttribute("pager", pager);
		return "/admin/quotation/inquiries/list";
	}

	@RequestMapping("/inquiries/add")
	public String addInquiry(Model model) {

		return "/admin/quotation/inquiries/add";
	}
}
