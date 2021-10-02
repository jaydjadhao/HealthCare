package in.nareshit.raghu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.raghu.entity.Specilization;
import in.nareshit.raghu.exception.SpecilizationNotFoundException;
import in.nareshit.raghu.service.ISpecilizationService;
import in.nareshit.raghu.view.SpecializationExcelView;

@Controller
@RequestMapping("/spec")
public class SpecilizationController {

	@Autowired
	private ISpecilizationService service;
	
	/***
	 * 1. Show Register Page
	 * @return
	 */
	
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecilizationRegister";
	}
	
	/***
	 * 2. on Submit Form Save Data
	 */
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specilization specilization, Model model) {
		Long id = service.saveSpecilization(specilization);
		String message = "Record ("+ id +") is created !!!";
		model.addAttribute("message", message);
		return "SpecilizationRegister";
	}
	
	/***
	 * 3. Display all Specialization
	 */
	@GetMapping("/all")
	public String viewAll(Model model,@RequestParam(value = "message", required = false) String message ) {
		List<Specilization> specilizations = service.getAllSpecilizations();
		model.addAttribute("specilizations", specilizations);
		model.addAttribute("message", message);
		return "SpecilizationData";
	}
	
	
	/***
	 * 4. Delete by id
	 */
	@GetMapping("/delete")
	public String deleteData(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.removeSpecilization(id);
			attributes.addAttribute("message", "Record ("+ id +") is removed!!!");
		} catch (SpecilizationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}
	
	/***
	 * 5. Fetch data into Edit Page
	 * 	End User clicks on link, may enter id manually.
	 * 	if entered Id is present in DB
	 * 		> Load Row as Object
	 * 		> Send to Edit Page
	 * 		> Fill in Form
	 * 	Else
	 * 		> Redirect to all (Data Page)
	 * 		> Show Error message (Not Found)	  
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {
			Specilization specilization = service.getOneSpecilization(id);
			model.addAttribute("specilization", specilization);
			page = "SpecilizationEdit";
			
		} catch (SpecilizationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}
	
	/***
	 * 6. Update Form data and redirect to all
	 */
	@PostMapping("/update")
	public String updatedata(@ModelAttribute Specilization specilization, RedirectAttributes attributes) {
		service.updateSpecilization(specilization);
		attributes.addAttribute("message", "Record ("+specilization.getId() +") is updated!!!");
		return "redirect:all";
	}
	
	/***
	 * 7. Read code and check with Service 
	 * 	  Return message back to UI
	 */
	@GetMapping("/checkcode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code) {
		return service.isSpecCodeExists(code)? code + " already exists" : "";
	}
	
	/***
	 * 8. Export Data to excel file
	 */
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView view = new ModelAndView();
		view.setView(new SpecializationExcelView());
		
		// read data from DB
		List<Specilization> specilizations = service.getAllSpecilizations();
		//send to excel Impl class
		view.addObject("specilizations", specilizations);
		return view;
	}
	

}
