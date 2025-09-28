package nazeem.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nazeem.web.model.Departs;
import nazeem.web.model.Product;
import nazeem.web.model.ProductType;
import nazeem.web.model.Staffs;
import nazeem.web.repository.DepartRepository;
import nazeem.web.service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	private DepartRepository departRepository;
	@Autowired
	private StaffService service;
	private String list_template = "/admin/staff/view-staffs";
	private String add_edit_template="/admin/staff/addOrEdit";
	private String list_redirect="redirect:/staff/list";
	
	@RequestMapping("/list")
	public String listStaff(ModelMap model) {
		model.addAttribute("list",service.findAll() );
		return list_template;
	}
	@GetMapping("/add")
    public String addProduct(Staffs staffs, Model model){
        model.addAttribute("staffs", staffs);
        List<Departs> departs = (List<Departs>) departRepository.findAll();
        model.addAttribute("departs",departs);

        return add_edit_template;
    }
	
	 @PostMapping("/save")
	    public String saveProduct(@Valid @ModelAttribute("staffs") Staffs staffs, BindingResult result, Model model){
	        model.addAttribute("staffs", staffs);
	        List<Departs> departs =(List<Departs>) departRepository.findAll();
	        model.addAttribute("departs",departs);

	        if(result.hasErrors()){
	            return add_edit_template;
	        }

	        service.save(staffs);
	        return list_redirect+"?success";
	    }

}
