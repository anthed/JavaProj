package Farm;

import java.util.HashMap;

public class Farm {
    private HashMap<String, Dwelling> dwellings;

    public Farm() {
        this.dwellings = new HashMap<String, Dwelling>();
    }

    public boolean add(Dwelling anyDwelling) {
        if(!this.dwellings.containsKey(anyDwelling.getName())) {
            this.dwellings.put(anyDwelling.getName(), anyDwelling);
            System.out.println(anyDwelling.getName() + " added to Farm");
            return true;
        }
        else {
            System.out.println("Can't add duplicate named buildings to Farm");
            return false; // Already exists
        }
    }

    public HashMap<String, Dwelling> getMap() {
        return this.dwellings;
    }

    public int getSize() {
        return this.dwellings.size();
    }

    public Dwelling get(String key) {
        return this.dwellings.get(key);
    }

    public void listAllDwellings() {
        for(Dwelling i : dwellings.values()) {
            System.out.println(i + " with name: " + i.getName());
        }
    }
}