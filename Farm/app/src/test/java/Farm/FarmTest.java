package Farm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FarmTest {
    Farm myFarm = new Farm();
    final String expectedName = "Hus";
    
    @Test
    public void farmShouldNotBeAbleToAddDuplicateNamedBuildings() {
        assertEquals("Farm should have no dwellings when initialized", 0, myFarm.getSize());

        Dwelling myHouse = new House(expectedName);
        Dwelling myStable = new Stable(expectedName);

        assertTrue("Farm should be able to add a unique named building", myFarm.add(myHouse));
        assertNotNull("Farm should not return null when building has been added", myFarm.get(expectedName));

        assertFalse("Farm should not be able to add a duplicate named building", myFarm.add(myStable));
    }
}
