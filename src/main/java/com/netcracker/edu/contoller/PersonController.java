package com.netcracker.edu.contoller;

import com.netcracker.edu.entity.dao.CpeRepository;
import com.netcracker.edu.entity.dao.PeDao;
import com.netcracker.edu.entity.dao.PeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    @Autowired
    private PeRepository peRepository;

    @Autowired
    private CpeRepository CpeRepository;

    @GetMapping("/all")
    public @ResponseBody
    Iterable<PeDao> getAllUsers() {
        return peRepository.findAll();
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<PeDao> peDao = peRepository.findAll();

        model.put("peDao", peDao);

        return "nemain";
    }

//    @PostMapping
//    public String add(@RequestParam String id, @RequestParam String name, @RequestParam String personWayName,
//                      @RequestParam String usd, Map<String, Object> model) {
//        Iterable<Way> wayList;
//        wayList = wayRepository.findBySadwayName(personWayName);
//
//        if (((List<Way>) wayList).isEmpty()) {
//            wayRepository.save(new Way(personWayName));
//
//            wayList = wayRepository.findBySadwayName(personWayName);
//        }
//
//
//        Person person = new Person(Integer.parseInt(id), name, ((List<Way>) wayList).get(0), Integer.parseInt(usd));
//
//        personRepository.save(person);
//
//        Iterable<Person> persons = personRepository.findAll();
//
//        model.put("persons", persons);
//        return "main";
//    }
//
//    @PostMapping("update")
//    public String update(@RequestParam String id, @RequestParam String name, @RequestParam String personWayName,
//                         @RequestParam String usd, Map<String, Object> model) {
//        Iterable<Person> persons;
//
//        Iterable<Way> wayList;
//        wayList = wayRepository.findBySadwayName(personWayName);
//
//        if (((List<Way>) wayList).isEmpty()) {
//            wayRepository.save(new Way(personWayName));
//
//            wayList = wayRepository.findBySadwayName(personWayName);
//        }
//
//        Person person = personRepository.findById(Integer.parseInt(id)).get(0);
//
//         person.setName(name);
//         person.setPersonWayName(((List<Way>) wayList).get(0));
//         person.setUsd(Integer.parseInt(usd));
//
//         personRepository.save(person);
//
//         persons = personRepository.findAll();
//
//        model.put("persons", persons);
//        return "main";
//    }
//
//    @PostMapping("delete")
//    @Transactional
//    public String filter(@RequestParam String id, Map<String, Object> model ){
//        Iterable<Person> personList;
//
//        if (id !=null && !id.isEmpty()){
//            Integer sid = Integer.valueOf(id);
//
//            personRepository.deleteById(sid);
//            personList = personRepository.findAll();
//
//        }else {
//            personList = personRepository.findAll();
//        }
//
//        model.put("persons",personList);
//        return "main";
//    }
//
//
//
//    @PostMapping("filter")
//    public String filter(@RequestParam String id, @RequestParam String name, @RequestParam String personWayName,
//                         @RequestParam String usd, Map<String, Object> model){
//        Iterable<Person> personList;
//        Iterable<Way> wayList;
//
//        int idInt;
//        int usdInt;
//
//        if ((id !=null && !id.isEmpty())){
//             idInt = Integer.valueOf(id);
//        }else {
//            idInt = 0;
//        }
//        if ((usd !=null && !usd.isEmpty())){
//            usdInt = Integer.parseInt(usd);
//        }else {
//            usdInt =0;
//        }
//
//        wayList = wayRepository.findBySadwayName(personWayName);
//
//        if (((List<Way>) wayList).isEmpty()) {
//            ((List<Way>) wayList).add(new Way(""));
//        }
//
//        if (!id.isEmpty()|| !name.isEmpty()|| !personWayName.isEmpty() || !usd.isEmpty())
//            personList = personRepository.findByIdOrNameOrPersonWayNameOrUsd(idInt, name, ((List<Way>) wayList).get(0), usdInt);
//        else{
//            personList = personRepository.findAll();
//        }
//
//        model.put("persons",personList);
//        return "main";
//    }


}
