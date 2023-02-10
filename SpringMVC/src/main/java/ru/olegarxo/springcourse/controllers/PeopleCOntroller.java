package ru.olegarxo.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.olegarxo.springcourse.dao.PersonDAO;
import ru.olegarxo.springcourse.models.People;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleCOntroller {
    private final PersonDAO personDAO;
    @Autowired
    public PeopleCOntroller(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    //Получим всех дюдей и отдадим на представление view
    public String index(Model model){
        model.addAttribute("peoples", personDAO.peopleList());
        return "people/peopleList";
    }
    @GetMapping("/{id}")
    //Получим конкретного человека по id и отдадим view на представление
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("people", personDAO.show(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new People());
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") People people){
        personDAO.save(people);
        return "redirect:/people";

    }
}
