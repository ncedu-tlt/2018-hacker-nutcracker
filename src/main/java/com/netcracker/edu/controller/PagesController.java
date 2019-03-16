package com.netcracker.edu.controller;

import com.netcracker.edu.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PagesController {

	private static List<Person> persons = new ArrayList<>();
	private static List<String> ways = new ArrayList<>();
	private com.netcracker.edu.controller.Controller controller = new com.netcracker.edu.controller.Controller();


	@GetMapping({"", "/welcome"})
	public String welcomePage() {
		return "WelcomePage";
	}

	@GetMapping ("/main")
	public String mainPage (Model model) {
		persons = controller.getAllPerson();
		model.addAttribute("listOfPersons", persons);
		return "Main";
	}

	@GetMapping ("/personPage")
	public String personPage (@ModelAttribute Person person, Model model) {
		ways = controller.getAllWays();
		model.addAttribute("person", person);
		model.addAttribute("ways", ways);
		return "PersonPage";
	}

	@GetMapping ("/savePerson")
	public String savePerson (@ModelAttribute Person person, Model model, String format) {
		controller.savePersonInFile(person, format);
		//перенаправлять?!!!!!!!!!!!
		persons = controller.getAllPerson();
		model.addAttribute("listOfPersons", persons);
		return "Main";
	}

	@GetMapping ("/updatePerson")
	public String updatePerson (@ModelAttribute Person person, Model model, String radio, String selectWay) {
		if (radio.equals("option1")){
			if((person.getWay().trim()).equals("")){
				person.setWay(selectWay.trim());
			} else {
				person.setWay(person.getWay().trim());
			}
		} else {
			person.setWay(selectWay.trim());
		}
		controller.changePerson(person);
		//перенаправлять?!!!!!!!!!!!

		persons = controller.getAllPerson();
		model.addAttribute("listOfPersons", persons);
		return "Main";
	}

	@GetMapping ("/deletePerson")
	public String deletePerson (@ModelAttribute Person person, Model model) {
		controller.deletePerson(person.getId());
		//перенаправлять?!!!!!!!!!!!
		persons = controller.getAllPerson();
		model.addAttribute("listOfPersons", persons);
		return "Main";
	}

	@GetMapping ("/pageToAddPerson")
	public String pageToAddPerson (Model model) {
		ways = controller.getAllWays();
		model.addAttribute("ways", ways);
		return "AddPerson";

	}

	@GetMapping ("/addPerson")
	public String addPerson (@ModelAttribute Person person, Model model, String radio, String selectWay) {
		String exception;

		if (controller.getPerson(person.getId()).getId()!=0){
			exception="id";
			model.addAttribute("exception", exception);
			return "Exception";//id
		} else {
			if (radio.equals("option1")) {
				if ((person.getWay().trim()).equals("")) {
					if (selectWay.equals("NoWay")) {
						exception="way";
						model.addAttribute("exception", exception);
						return "Exception";//way
					} else {
						person.setWay(selectWay.trim());
					}
				} else {
					person.setWay(person.getWay().trim());
				}
			} else if (selectWay.equals("NoWay")) {
				if ((person.getWay().trim()).equals("")) {
					exception="way";
					model.addAttribute("exception", exception);
					return "Exception";//way
				} else {
					person.setWay(person.getWay().trim());
				}
			} else {
				person.setWay(selectWay.trim());
			}
		}
		controller.createPerson(person);
		//перенаправлять?!!!!!!!!!!!
		persons = controller.getAllPerson();
		model.addAttribute("listOfPersons", persons);
		return "Main";
	}
}
