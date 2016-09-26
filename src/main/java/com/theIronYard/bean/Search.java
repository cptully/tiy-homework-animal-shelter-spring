package com.theIronYard.bean;

/**
 * Created by davehochstrasser on 9/26/16.
 */

public class Search {
    private String name = null;
    private Integer typeId = null;
    private Integer breedId = null;
    private Integer animalId = null;

    public Search() {
    }

    public Search(String name, Integer typeId, Integer breedId, Integer animalId) {
        setName(name);
        this.typeId = typeId;
        this.breedId = breedId;
        this.animalId = animalId;
    }

    public String getNameForSearch() {
        return name == null || name.equals("") ? "" : "%" + name + "%";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name == null || name.equals("") ? null : name);
    }



    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getBreedId() {
        return breedId;
    }

    public void setBreedId(Integer breedId) {
        this.breedId = breedId;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }
}
