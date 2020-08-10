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

    // Getters
    public long getPetId() {
        return petId;
    }

    public String getPetName() {
        return petName;
    }
}


