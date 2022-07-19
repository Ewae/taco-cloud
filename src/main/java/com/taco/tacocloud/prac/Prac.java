package com.taco.tacocloud.prac;

import java.util.Arrays;
import java.util.List;
// import java.util.stream.Collector;
import java.util.stream.Collectors;

// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taco.tacocloud.prac.Person.Gender;

import lombok.Data;
import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
@Controller
@RequestMapping("/prac")
public class Prac {


    @GetMapping
    public static String main(String[] args, Model model) {
        
        List<Person> persons = Arrays.asList(
            new Person("Julius", 20, Gender.MALE),
            new Person("John", 19, Gender.MALE),
            new Person("Mia", 24, Gender.FEMALE),
            new Person("Therese", 29, Gender.FEMALE)

        );

        Gender[] genders = Person.Gender.values();

        for(Gender gender: genders){
            model.addAttribute(gender.toString().toLowerCase(), filterByGender(persons, gender));
            System.out.println(gender.toString().toLowerCase() + " " + filterByGender(persons, gender));
        }
        model.addAttribute("practice", new Object());
        model.addAttribute("skrt", new Person("Duff", 13, Gender.MALE));
        System.out.println("skrt"+" "+ new Person("Duff", 13, Gender.MALE));
        return "prac";
    }

    private static List<Person> filterByGender(List<Person> persons, Gender gender){
        return persons.stream()
                .filter(x -> x.getGender().equals(gender))
                .collect(Collectors.toList());
    }
 



    
}

@Data
@RequiredArgsConstructor
class Person {

    private final String name;
    private final int age;
    private final Gender gender;

    public enum Gender {
        MALE, FEMALE
    }

}
