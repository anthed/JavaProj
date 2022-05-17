package Farm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

public class FarmRepositoryTest {
    FarmRepository myFarmRepository = new FarmRepository();

    @BeforeClass
    public static void setUpClass() {
        FarmRepository.createDwellingTable();
    }
    
    @Test
    public void shouldCreateAndReadDwellingRecord() {
        Dwelling myWriteHouse = new House("House");
        Dwelling myWriteStable = new Stable("Stable");

        myFarmRepository.createDwellingRecord(myWriteHouse);
        myFarmRepository.createDwellingRecord(myWriteStable);

        Dwelling myReadHouse = myFarmRepository.findDwellingById(1);
        Dwelling myReadStable = myFarmRepository.findDwellingById(2);

        assertEquals("Name should be equal on read and write object", myWriteHouse.getName(), myReadHouse.getName());
        assertTrue("Read object should be of same type as write object", myReadHouse instanceof House);

        assertEquals("Name should be equal on read and write object", myWriteStable.getName(), myReadStable.getName());
        assertTrue("Read object should be of same type as write object", myReadStable instanceof Stable);
    }
    
    @Test
    public void shouldUpdateDwellingRecord() {
        //fail("Not implemented yet");
    }
    
    @Test
    public void shouldDeleteDwellingRecord() {
        //fail("Not implemented yet");
    }
}
