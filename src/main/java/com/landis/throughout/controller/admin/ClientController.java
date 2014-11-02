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
@RequestMapping(Constants.ADMIN_URL_ROOT + "/client/*")
public class ClientController {

	@RequestMapping("/list/{page}")
	public String list(Model model, @RequestParam(required = false) Integer orderby,
			@PathVariable(value = "page") Integer page) {
		Map<String, Object> filter = new HashMap<String, Object>();
		int count = 0;
		Pager pager = new Pager(count, page);
		model.addAttribute("count", count);
		model.addAttribute("pager", pager);
		return "/admin/client/list";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		return "/admin/client/add";
	}

	@RequestMapping("/create")
	public String create(Model model) {
		return "/admin/client/view";
	}

	@RequestMapping("/view")
	public String view(Model model) {
		return "/admin/client/view";
	}

	@RequestMapping("/addMachine")
	public String addMachine(Model model) {
		return "/admin/client/addMachine";
	}

	@RequestMapping("/createMachine")
	public String createdetail(Model model) {

		return "/admin/client/list";
	}
}
