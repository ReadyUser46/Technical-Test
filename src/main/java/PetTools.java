import java.util.*;

public class PetTools {

    private final Map<Long, String> petMap;

    public PetTools(Map<Long, String> petMap) {
        this.petMap = petMap;
    }

    public void samePetNames() {
        System.out.println("\nListing pets with same name...");

        Map<String, Integer> counter = new LinkedHashMap<>();
        for (String keyName : petMap.values()) {
            int value = counter.get(keyName) == null ? 0 : counter.get(keyName);
            counter.put(keyName, value + 1);
        }
        System.out.println(counter);
    }
}
