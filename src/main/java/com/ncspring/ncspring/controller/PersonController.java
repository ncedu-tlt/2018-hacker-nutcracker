package com.ncspring.ncspring.controller;

import com.ncspring.ncspring.model.Person;
import com.ncspring.ncspring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

//    @GetMapping("/all")
//    public @ResponseBody
//    Iterable<Person> getAllUsers() {
//
//        return personRepository.findAll();
//    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Person> persons = personRepository.findAll();

        model.put("persons", persons);

        return "main";
    }

    @PostMapping
    public String add(@RequestParam String id, @RequestParam String name, @RequestParam String way,
                      @RequestParam String usd, Map<String, Object> model) {

        Person person = new Person(Integer.parseInt(id), name, way, Integer.parseInt(usd));

        personRepository.save(person);

        Iterable<Person> persons = personRepository.findAll();

        model.put("persons", persons);
        return "main";
    }

//    @PostMapping("filter")
//    public String filter(@RequestParam String id, Map<String, Object> model ){
//        Iterable<Person> personList;
//
//        if (id !=null && !id.isEmpty()){
//            Integer sid = Integer.valueOf(id);
//
////            personList = personRepository.findByName(name);
//            personList = personRepository.findById(sid);
//        }else {
//            personList = personRepository.findAll();
//        }
//        model.put("persons",personList);
//        return "main";
//    }

    @PostMapping("filter")
    public String filter(@RequestParam String id, @RequestParam String name, @RequestParam String way,
                         @RequestParam String usd, Map<String, Object> model){
        Iterable<Person> personList;

        int idInt;
        int usdInt;

        if ((id !=null && !id.isEmpty())){
             idInt = Integer.valueOf(id);
        }else {
            idInt = 0;
        }
        if ((usd !=null && !usd.isEmpty())){
            usdInt = Integer.parseInt(usd);
        }else {
            usdInt =0;
        }


        if (!id.isEmpty()|| !name.isEmpty()|| !way.isEmpty() || !usd.isEmpty())
            personList = personRepository.findByIdOrNameOrWayOrUsd(idInt, name, way, usdInt);
        else{
            personList = personRepository.findAll();
        }

        model.put("persons",personList);
        return "main";
    }


}
