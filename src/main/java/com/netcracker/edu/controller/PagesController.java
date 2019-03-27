package com.netcracker.edu.controller;

import com.netcracker.edu.model.dao.PersonDao;
import com.netcracker.edu.model.dao.WayDao;
import com.netcracker.edu.model.dto.PersonDto;
import com.netcracker.edu.model.repository.PersonRepository;
import com.netcracker.edu.model.repository.WayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PagesController {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private WayRepository wayRepository;
	@Autowired
	private ConversionService conversionService;
	@Autowired
	private FileHelper fileHelper;

	private List<PersonDao> persons = new ArrayList<>();
	private List<WayDao> ways = new ArrayList<>();


	@GetMapping ({"", "/welcome"})
	public ModelAndView welcomePage() {
		return new ModelAndView("WelcomePage");
	}

	@GetMapping ("/main")
	public String mainPage (Model model) {
		redirectOutputMain(model);
		return "Main";
	}

	@GetMapping ("/personPage")
	public String personPage (@ModelAttribute PersonDto personDto, Model model) {
		ways = wayRepository.findAll();
		model.addAttribute("person", personDto);
		model.addAttribute("ways", ways);
		return "PersonPage";
	}

	@GetMapping ("/savePerson")
	public String savePerson (@ModelAttribute PersonDto personDto, Model model, String format) {
		switch (format){
			case "xml": fileHelper.createFileXML(personDto); break;
			case "csv": fileHelper.createFileCSV(personDto); break;
			case "json": fileHelper.createFileJSON(personDto); break;
		}
		redirectOutputMain(model);
		return "Main";
	}

	@GetMapping ("/updatePerson")
	public String updatePerson (@ModelAttribute PersonDto persondto111, Model model, String id, String name,
	                            String way, String USD, String radio, String selectWay) {
		PersonDto personDto = new PersonDto(Integer.parseInt(id), name, way, Integer.parseInt(USD));

		if (radio.equals("option1")){
			if((personDto.getWay().trim()).equals("")){
				personDto.setWay(selectWay.trim());
			} else {
				personDto.setWay(personDto.getWay().trim());
			}
		} else {
			personDto.setWay(selectWay.trim());
		}
		PersonDao personDao = conversionService.convert(personDto, PersonDao.class);
		personRepository.save(personDao);

		redirectOutputMain(model);
		return "Main";
	}

	@GetMapping ("/deletePerson")
	public String deletePerson (@ModelAttribute PersonDto persondto1111, String id, Model model) {
		personRepository.deleteById(Integer.parseInt(id));

		redirectOutputMain(model);
		return "Main";
	}

	@GetMapping ("/pageToAddPerson")
	public String pageToAddPerson (Model model) {
		ways = wayRepository.findAll();
		model.addAttribute("ways", ways);
		return "AddPerson";

	}

	@GetMapping ("/addPerson")
	public String addPerson (@ModelAttribute PersonDto persondto111, Model model, String id, String name,
	                         String way, String USD, String radio, String selectWay) {

		PersonDto personDto = new PersonDto(Integer.parseInt(id), name, way, Integer.parseInt(USD));

		if (personRepository.existsById(personDto.getId())){
			model.addAttribute("exception", "id");
			return "Exception";//id
		} else {
			if (radio.equals("option1")) {
				if ((personDto.getWay().trim()).equals("")) {
					if (selectWay.equals("NoWay")) {
						model.addAttribute("exception", "way");
						return "Exception";//way
					} else {
						personDto.setWay(selectWay.trim());
					}
				} else {
					personDto.setWay(personDto.getWay().trim());
				}
			} else if (selectWay.equals("NoWay")) {
				if ((personDto.getWay().trim()).equals("")) {

					model.addAttribute("exception", "way");
					return "Exception";//way
				} else {
					personDto.setWay(personDto.getWay().trim());
				}
			} else {
				personDto.setWay(selectWay.trim());
			}
		}
		PersonDao personDao = conversionService.convert(personDto, PersonDao.class);
		personRepository.save(personDao);

		redirectOutputMain(model);
		return "Main";
	}

	public void redirectOutputMain (Model model){
		persons = personRepository.findAllByOrderByIdAsc();
		List<PersonDto> result = new ArrayList<>();
		persons.forEach(person -> result.add(conversionService.convert(person, PersonDto.class)));
		model.addAttribute("listOfPersons", result);
	}
}
