package com.company.inventary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity
@Table(name="category")
public class Category  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @NotBlank(message = "Description is mandatory")
//    @Pattern(regexp = "^[A-Za-z ]+$")
    private String name;

//    @NotBlank(message = "Description is mandatory")
//    @Pattern(regexp = "^[A-Za-z ]+$", message = "Description must contain only letters and spaces")
    private String descripcion;
}
