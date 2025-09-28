package nazeem.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nazeem.web.model.ProductType;
import nazeem.web.model.User;
import nazeem.web.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService iuserService;
	
	private String list_template = "/admin/user/list-user";

	@GetMapping("/list")
    public String listProductType(Model model) {
        List<User> listUsers = iuserService.findAll();
        model.addAttribute("listUsers", listUsers);

        return list_template;
    }
}
