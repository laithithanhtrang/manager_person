package manager_person.managerperson.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import manager_person.managerperson.model.Person;
import manager_person.managerperson.repository.PersonRepository;
import manager_person.managerperson.service.StorageService;

@Controller
public class PersonController {
    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private StorageService storageService;

    @GetMapping("/")
    public String home(Model model) {
        List<Person> people = personRepo.getAll();
        model.addAttribute("people", people);
        return "listAll";
    }

    
    @PostMapping(value = "/post", consumes = {"multipart/form-data"})
    public String postInfo(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model) throws Exception {
        if (person.getPhoto().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("person", "photo", "Photo is required"));
        }
        if (result.hasErrors()) {
            return "home";
        }

        if (person.getId() > 0) {
            personRepo.edit(person);
        } else {
            personRepo.create(person);
        }
        storageService.uploadFile(person.getPhoto(), person.getId());
        model.addAttribute("people", personRepo.getAll());
        return "listAll";
    }

    @GetMapping("/listAll")
    public String listAll(Model model) {
        List<Person> people = personRepo.getAll();
        model.addAttribute("people", people);
        return "listAll";
    }
    @GetMapping("/person/{id}")
    public String personInfo(@PathVariable("id")int id,Model model){
        Optional<Person> person = personRepo.get(id);
        if(person.isPresent()){
            model.addAttribute("person",person.get());
            return "personInfo";
        }
        return "404";
    }



    @GetMapping("/person/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        Optional<Person> person = personRepo.get(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        }
        return "personForm";
    }
}
