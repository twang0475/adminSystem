package com.landis.throughout.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.landis.throughout.commons.Constants;

@Controller
@RequestMapping(Constants.ADMIN_URL_ROOT + "/machine/*")
public class MachineController {

	@RequestMapping("/view")
	public String view(Model model) {
		return "/admin/machine/view";
	}
}
