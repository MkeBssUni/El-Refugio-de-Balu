package com.balu.backend.modules.pets.model.dto;

import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.pets.model.enums.AgeUnits;
import com.balu.backend.modules.pets.model.enums.Genders;
import com.balu.backend.modules.pets.model.enums.LifeStages;
import com.balu.backend.modules.pets.model.enums.WeightUnits;
import com.balu.backend.modules.users.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PetDto {
    @NotNull(groups = Save.class)
    @NotBlank(groups = Save.class)
    private String name;

    @NotNull(groups = Save.class)
    private Genders gender;

    @NotNull(groups = Save.class)
    @NotBlank(groups = Save.class)
    private String breed;

    @NotNull(groups = Save.class)
    @Positive(groups = Save.class)
    private int age;

    @NotNull(groups = Save.class)
    private AgeUnits ageUnit;

    @NotNull(groups = Save.class)
    private LifeStages lifeStage;

    @NotNull(groups = Save.class)
    @Positive(groups = Save.class)
    private Double weight;

    @NotNull(groups = Save.class)
    private WeightUnits weightUnit;

    @NotNull(groups = Save.class)
    @NotBlank(groups = Save.class)
    private String description;

    private String characteristics;

    @NotNull(groups = Save.class)
    @NotBlank(groups = Save.class)
    private String mainImage;

    private List<String> images;

    private boolean vaccinated;

    private boolean sterilized;

    private boolean dewormed;

    private boolean microchip;

    private String observations;

    private String diseases;

    private String allergies;

    @NotNull(groups = Save.class)
    private Category category;

    @NotNull(groups = Save.class)
    private User owner;


    public interface Save {

    }
}
