package kz.tenko.machine.coffee.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
