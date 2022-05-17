package Farm;

public class Animal {
    private String food;
    private String name;

    public Animal(String food, String name) {
        super();
        this.food = food;
        this.name = name;
    }

    // Getter methods
    public String getFood() {
        return this.food;
    }    
    
    public String getName() {
        return this.name;
    }
}
