package Task3.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pet {

    @SerializedName("id")
    @Expose
    private long petId;
    @SerializedName("category")
    @Expose
    private Category petCategory;
    @SerializedName("name")
    @Expose
    private String petName;
    @SerializedName("photoUrls")
    @Expose
    private List<String> petPhotoUrls;
    @SerializedName("tags")
    @Expose
    private List<Tag> petTags;
    @SerializedName("status")
    @Expose
    private String petStatus;

    public Pet(int petId, Category petCategory, String petName, List<String> petPhotoUrls, List<Tag> petTags, String petStatus) {
        this.petId = petId;
        this.petCategory = petCategory;
        this.petName = petName;
        this.petPhotoUrls = petPhotoUrls;
        this.petTags = petTags;
        this.petStatus = petStatus;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public Category getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(Category petCategory) {
        this.petCategory = petCategory;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public List<String> getPetPhotoUrls() {
        return petPhotoUrls;
    }

    public void setPetPhotoUrls(List<String> petPhotoUrls) {
        this.petPhotoUrls = petPhotoUrls;
    }

    public List<Tag> getPetTags() {
        return petTags;
    }

    public void setPetTags(List<Tag> petTags) {
        this.petTags = petTags;
    }

    public String getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(String petStatus) {
        this.petStatus = petStatus;
    }
}


