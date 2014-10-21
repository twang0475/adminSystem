package com.landis.throughout.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.landis.throughout.commons.Constants;

@Controller
@RequestMapping(Constants.ADMIN_URL_ROOT + "/*")
public class LoginController {

	private final Log log = LogFactory.getLog(LoginController.class);

	@RequestMapping("index")
	public void index() {
	}

}
