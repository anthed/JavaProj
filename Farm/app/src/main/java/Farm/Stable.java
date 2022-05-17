package Farm;

public class Stable extends Dwelling {
    public Stable(String name) {
        super(name);
    } 

    @Override
    public boolean add(Animal anyAnimal) {
        if(anyAnimal instanceof Horse) {
            return super.add(anyAnimal);
        }
        System.out.println("Can't add anything other than horses to Stable");
        return false;
    }
}