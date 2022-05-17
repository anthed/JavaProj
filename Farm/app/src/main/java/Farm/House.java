package Farm;

public class House extends Dwelling {
    public House(String name) {
        super(name);
    } 

    @Override
    public boolean add(Animal anyAnimal) {
        if(anyAnimal instanceof Cat || anyAnimal instanceof Dog) {
            return super.add(anyAnimal);
        }
        System.out.println("Can't add anything other than Cat or Dog to House");
        return false;
    }
}
