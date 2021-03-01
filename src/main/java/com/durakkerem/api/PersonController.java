package com.durakkerem.api;


import com.durakkerem.Repository.PersonRepository;
import com.durakkerem.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;


    @PostConstruct
    public void init() {
        Person person = new Person();

        person.setId(1L);
        person.setName("Kerem");
        person.setSurname("Durak");
        person.setAddress("Sakarya");
        person.setBirthday(Calendar.getInstance().getTime());

        personRepository.save(person);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<Person>> getPerson(@PathVariable String search) {
        List<Person> personList = personRepository.findByNameLikeOrSurnameLike(search,search);
        return ResponseEntity.ok(personList);
    }
}
