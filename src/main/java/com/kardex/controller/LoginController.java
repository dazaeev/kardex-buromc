package com.kardex.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kardex.model.User;
import com.kardex.service.UserService;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	/*
	 * Acceso
	 */
	
	@RequestMapping(value = "/forgets", method = RequestMethod.GET)
	public ModelAndView forgets(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forget");
		return modelAndView;
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
		}
		return modelAndView;
	}
	
	/*
	 * 
	 */
	@RequestMapping("/registration-user")
	public ModelAndView registrationUser(Model model) {
		return new ModelAndView("redirect:/adm/registration.html");
	}
	
	/*
	 * Dashboard
	 */
	
	@RequestMapping("/home")
	public ModelAndView home(Model model) {
		String redirect = "";
		// Validar que HOME mostrar
		Map<String, String> mapUserDetails = userService.getUserDetails("LoginController.home");
		if(mapUserDetails.get("ROLE").equals("ADMIN")) {
			redirect = "redirect:/page/home.html";
		}
		if(mapUserDetails.get("ROLE").equals("USERS")) {
			redirect = "redirect:/page/home-local.html";
		}
		return new ModelAndView(redirect);
	}
	
	/*
	 * Plan de carrera
	 */
	
	@RequestMapping("/career/add")
	public ModelAndView careerPlanAddActivity(Model model) {
		return new ModelAndView("redirect:/career/plan-add.html");
	}
	
	@RequestMapping("/career/validate")
	public ModelAndView careerPlanValidateActivity(Model model) {
		return new ModelAndView("redirect:/career/plan-validate.html");
	}
	
	/*
	 * Administracion
	 */
	
	@RequestMapping("/adm/activitie")
	public ModelAndView activities(Model model) {
		return new ModelAndView("redirect:/adm/activities.html");
	}
	
	@RequestMapping("/adm/employee")
	public ModelAndView employees(Model model) {
		return new ModelAndView("redirect:/adm/employees.html");
	}
	
	/*
	 * Solicitar
	 */
	@RequestMapping("/request/generate")
	public ModelAndView generateReq(Model model) {
		return new ModelAndView("redirect:/request/generate-request.html");
	}
	
	@RequestMapping("/request/anwser")
	public ModelAndView answerReq(Model model) {
		return new ModelAndView("redirect:/request/answer-request.html");
	}
	
	/*
	 * Ver
	 */
	
	@RequestMapping("/view/calendar")
	public ModelAndView calendar(Model model) {
		return new ModelAndView("redirect:/view/calendars.html");
	}
	
	@RequestMapping("/view/performance")
	public ModelAndView performance(Model model) {
		return new ModelAndView("redirect:/view/performances.html");
	}
	
	/*
	 * **************************************************************
	 * ************** Administrar Controller 1era fase **************
	 * **************************************************************
	 */
	@RequestMapping("/adm/advanced-searchs")
	public ModelAndView advancedSearchs(Model model) {
		return new ModelAndView("redirect:/adm/advanced-search.html");
	}
	@RequestMapping("/adm/employee-all")
	public ModelAndView employeesAll(Model model) {
		return new ModelAndView("redirect:/adm/employees-all.html");
	}
	@RequestMapping("/adm/employee-gral")
	public ModelAndView employeesGral(Model model) {
		return new ModelAndView("redirect:/adm/employees-gral.html");
	}
	@RequestMapping("/adm/employee-studies")
	public ModelAndView employeesStudies(Model model) {
		return new ModelAndView("redirect:/adm/employees-studies.html");
	}
	@RequestMapping("/adm/employee-demographics")
	public ModelAndView employeesDemographics(Model model) {
		return new ModelAndView("redirect:/adm/employees-demographics.html");
	}
	@RequestMapping("/adm/employee-workexperience")
	public ModelAndView employeesWorkexperience(Model model) {
		return new ModelAndView("redirect:/adm/employees-workexperience.html");
	}
	@RequestMapping("/adm/employee-labor")
	public ModelAndView employeesLabor(Model model) {
		return new ModelAndView("redirect:/adm/employees-labor.html");
	}
	@RequestMapping("/adm/employee-economics")
	public ModelAndView employeesEconomics(Model model) {
		return new ModelAndView("redirect:/adm/employees-economics.html");
	}
	@RequestMapping("/adm/employee-legal")
	public ModelAndView employeesLega(Model model) {
		return new ModelAndView("redirect:/adm/employees-legal.html");
	}
	@RequestMapping("/adm/employee-system-personal")
	public ModelAndView employeesSystemPersonal(Model model) {
		return new ModelAndView("redirect:/adm/employees-system-personal.html");
	}
	/*
	 * **************************************************************
	 * ********************** PLAN DE CARRERA ***********************
	 * **************************************************************
	 */
	@RequestMapping("/adm/certification-track-fases")
	public ModelAndView certificationTrackFases(Model model) {
		return new ModelAndView("redirect:/adm/certification-track-fase.html");
	}
	
	@RequestMapping("/adm/tracing-career-plan")
	public ModelAndView tracingCareerPlan(Model model) {
		return new ModelAndView("redirect:/adm/tracing-career-plans.html");
	}
	
	@RequestMapping("/adm/request-of-course")
	public ModelAndView requestOfCourses(Model model) {
		return new ModelAndView("redirect:/adm/request-of-courses.html");
	}
	/*
	 * **************************************************************
	 * ************************* CATALOGOS **************************
	 * **************************************************************
	 */
	@RequestMapping("/adm/catalogs-area")
	public ModelAndView catalogsArea(Model model) {
		return new ModelAndView("redirect:/adm/catalog-area.html");
	}
	/*
	 * **************************************************************
	 * **************************************************************
	 */
}
