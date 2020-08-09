import java.util.*;

public class PetTools {

    private final Map<Integer, String> petMap;

    public PetTools(Map<Integer, String> petMap) {
        this.petMap = petMap;
    }

    public void samePetNames() {
        System.out.println("\n\nListing pets with same name...\n");

        Map<String, Integer> counter = new LinkedHashMap<>();
        for (String k : petMap.values()) {
            int value = counter.get(k) == null ? 0 : counter.get(k);
            counter.put(k, value + 1);
        }

        System.out.println(counter);
        System.out.println(petMap);
    }
}
