package Farm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DwellingTest {
    final String expectedName = "TestName";
    
    Dwelling myDwelling = new Dwelling(expectedName);
    Dwelling myHouse = new House(expectedName);
    Dwelling myStable = new Stable(expectedName);

    int numberOfAnimalsHouse = myHouse.getSize();
    int numberOfAnimalsStable = myStable.getSize();

    Animal myDog = new Dog("TestFood", "TestName");
    Animal myCat = new Cat("TestFood", "TestName");
    Animal myHorse = new Horse("TestFood", "TestName");

    @Test
    public void dwellingsShouldHaveValidConstructor() throws Exception {

        //Generic Dwelling
        assertNotNull("Dwellings name must not be null", myDwelling.getName());
        assertEquals("Dwellings name must be as expected", expectedName, myDwelling.getName());

        //House
        assertNotNull("Dwellings name must not be null", myHouse.getName());
        assertEquals("Dwellings name must be as expected", expectedName, myHouse.getName());

        //Stable
        assertNotNull("Dwellings name must not be null", myStable.getName());
        assertEquals("Dwellings name must be as expected", expectedName, myStable.getName());       
    }

    @Test
    public void animalsInDwellingShouldBeOrdered() {
        Dwelling myDwelling1 = new Dwelling("Test");
        Animal testDog1 = new Dog("Food", "Joe");
        Animal testDog2 = new Dog("Food", "Shmoe");
        Animal testDog3 = new Dog("Food", "Dough");

        assertEquals("Dwelling should have no animals when initialized", 0, myDwelling1.getSize());

        myDwelling1.add(testDog1);
        myDwelling1.add(testDog2);
        myDwelling1.add(testDog3);

        assertEquals("Position in list should be as expected", testDog1, myDwelling1.getAnimal(0));
        assertEquals("Position in list should be as expected", testDog2, myDwelling1.getAnimal(1));
        assertEquals("Position in list should be as expected", testDog3, myDwelling1.getAnimal(2));
    }

    @Test
    public void dwellingsShouldBeAbleToAddAnimals() throws Exception {
        
        //House Dog
        assertTrue("House should be able to add Dog", myHouse.add(myDog));
        assertEquals("Number of animals in House must increment with one when adding one dog",
        ++numberOfAnimalsHouse, myHouse.getSize());

        //House Cat
        assertTrue("House should be able to add Cat", myHouse.add(myCat));
        assertEquals("Number of animals in House must increment with one when adding one cat",
        ++numberOfAnimalsHouse, myHouse.getSize());

        //House Horse
        assertFalse("House should not be able to add Horse", myHouse.add(myHorse));
        assertEquals("Number of animals in House must not change when trying to add an animal of wrong type",
        numberOfAnimalsHouse, myHouse.getSize()); //No increment

        //Stable Dog
        assertFalse("Stable should not be able to add Dog", myStable.add(myDog));
        assertEquals("Number of animals in House must not change when trying to add an animal of wrong type",
        numberOfAnimalsStable, myStable.getSize()); //No increment

        //Stable Cat
        assertFalse("Stable should not be able to add Dog", myStable.add(myCat));
        assertEquals("Number of animals in House must not change when trying to add an animal of wrong type",
        numberOfAnimalsStable, myStable.getSize()); //No increment

        //Stable Horse
        assertTrue("Stable should be able to add Dog", myStable.add(myHorse));
        assertEquals("Number of animals in House must increment with one when adding one horse",
        ++numberOfAnimalsStable, myStable.getSize());
    }
    
    @Test
    public void dwellingsShouldNotAllowDuplicateNamedAnimalsOfSameType() {
        Animal testDog1 = new Dog("Food", "Joe");
        Animal testDog2 = new Dog("Food", "Joe");
        Animal testDog3 = new Dog("Food", "Dough");

        Animal testCat1 = new Cat("Food", "Bo");
        Animal testCat2 = new Cat("Food", "Bo");
        Animal testCat3 = new Cat("Food", "Dough");

        Animal testHorse1 = new Horse("Food", "Poe");
        Animal testHorse2 = new Horse("Food", "Poe");

        //House
        myHouse.add(testDog1);
        assertFalse("Duplicate named dog", myHouse.add(testDog2));
        assertTrue("Unique dog named same as cat3", myHouse.add(testDog3)); //Same name as 3rd Cat

        myHouse.add(testCat1);
        assertFalse("Duplicate named cat", myHouse.add(testCat2));
        assertTrue("Unique cat named same as dog3", myHouse.add(testCat3)); //Same name as 3rd dog

        //Stable
        myStable.add(testHorse1);
        assertFalse("Duplicate named horse", myStable.add(testHorse2));
    }
}
