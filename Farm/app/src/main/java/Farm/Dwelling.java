package Farm;

import java.util.ArrayList;

public class Dwelling {
  private ArrayList<Animal> animals; 
  private String dwellingName; 

  public Dwelling(String name) {
    this.animals = new ArrayList<Animal>();
    this.dwellingName = name;    
  }

  public boolean add(Animal anyAnimal) {
    for(Animal i: animals) {
      if(i.getName() == anyAnimal.getName()) {
        if(i.getClass().equals(anyAnimal.getClass())) {
          System.out.println("duplicate name for same animal type");
          return false;
        }
      }
    }
    this.animals.add(anyAnimal);
    return true;
  }

  public int getSize() {
    return this.animals.size();
  }

  public String getName() {
    return this.dwellingName;
  }

  public Animal getAnimal(int index) {
    return this.animals.get(index);
  }

  public void listAllAnimals() {
    System.out.println("This dwelling contains:");
    for (Animal i : animals) {
      System.out.println(i.getName() + " " + i.getClass());
    }
    System.out.println();
  }
}
