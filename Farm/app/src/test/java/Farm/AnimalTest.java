package Farm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AnimalTest {
    final String expectedFood = "TestFood";
    final String expectedName = "TestName";

    Animal myAnimal = new Animal(expectedFood, expectedName);
    Animal myDog = new Dog(expectedFood, expectedName);
    Animal myCat = new Cat(expectedFood, expectedName);
    Animal myHorse = new Horse(expectedFood, expectedName);
    
    @Test
    public void animalsShouldHaveValidConstructor() throws Exception {
        
        //Generic Animal
        assertNotNull("Animals name must not be null!", myAnimal.getName());
        assertNotNull("Animals food must not be null!", myAnimal.getFood());
        assertEquals("Animals name must be as expected", myAnimal.getName(), expectedName);
        assertEquals("Animals food must be as expected", myAnimal.getFood(), expectedFood);

        //Dog
        assertNotNull("Animals name must not be null!", myDog.getName());
        assertNotNull("Animals food must not be null!", myDog.getFood());
        assertEquals("Animals name must be as expected", myDog.getName(), expectedName);
        assertEquals("Animals food must be as expected", myDog.getFood(), expectedFood);

        //Cat
        assertNotNull("Animals name must not be null!", myCat.getName());
        assertNotNull("Animals food must not be null!", myCat.getFood());
        assertEquals("Animals name must be as expected", myCat.getName(), expectedName);
        assertEquals("Animals food must be as expected", myCat.getFood(), expectedFood);

        //Horse
        assertNotNull("Animals name must not be null!", myHorse.getName());
        assertNotNull("Animals food must not be null!", myHorse.getFood());
        assertEquals("Animals name must be as expected", myHorse.getName(), expectedName);
        assertEquals("Animals food must be as expected", myHorse.getFood(), expectedFood);
    }   
}
