package com.taco.tacocloud;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {
    
    @NotNull
    @Size(min=5, message = "Name must be atleast five characters long.")
    private String name;

    @Size(min=1, message = "You must put atleast one ingredients")
    private List<String> ingredients;
    
}
