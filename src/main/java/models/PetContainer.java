package models;

import java.util.ArrayList;
import java.util.List;

public class PetContainer {

    private List<Pet> pets = new ArrayList<>();

    public PetContainer(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
