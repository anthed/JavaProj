package Farm;

import java.util.HashMap;
import java.util.Scanner;

public class UserInteraction {
    private Scanner myScanner;
    private FarmRepository myRepository;
    public UserInteraction() {
        myScanner = new Scanner(System.in);
        myRepository = new FarmRepository();
    }

    public void menu(Farm farm) {
        boolean status = false;
        String input = "";
        while(status == false) {
            System.out.println("\n----------------");
            System.out.println("1. Add a new dwelling to my farm");
            System.out.println("2. List all dwellings in my farm");
            System.out.println("3. Create animals and add to dwellings");
            System.out.println("4. List all animals in a specific dwelling");
            System.out.println("5. List all animals in my farm");
            System.out.println("6. Load dwellings from database");
            System.out.println("7. Save dwellings to database");
            System.out.println("8. Exit");
            System.out.print("Select 1 to 8: ");
            input = myScanner.nextLine(); // get user input string

            switch(input) {
                case "1":
                addDwelling(farm);
                break;

                case "2":
                System.out.println("\n----------------");
                System.out.println("Listing all dwellings in farm:");
                farm.listAllDwellings();
                break;

                case "3":
                createAnimal(farm);
                break;

                case "4":
                listAnimalsInDwelling(farm);
                break;

                case "5":
                listCompletFarm(farm);
                break;

                case "6":
                myRepository.loadRepository(farm);
                break;

                case "7":
                myRepository.saveRepository(farm);
                break;

                case "8":
                status = true;
                break;

                default:
                System.out.println("Invalid input [" + input + "], try again.\n");
            }
        }
    }

    private void addDwelling(Farm farm) {
        boolean status = false;
        String input = "";
        String name = "";
        while(status == false) {
            System.out.println("\n----------------");
            System.out.println("Which type of dwelling do you want?");
            System.out.println("1. Stable");
            System.out.println("2. House");
            System.out.print("Select 1 to 2: ");
            input = myScanner.nextLine();

            switch(input) {
                case "1":
                System.out.print("Enter the name of the stable: ");
                name = myScanner.nextLine();
                Stable aStable = new Stable(name);
                if(farm.add(aStable)) {
                    System.out.println("A new stable with the name " + name
                        + " added to your farm");
                    status = true;
                }
                else {
                    System.out.println("A stable with that name already exists, try again");
                }
                break;

                case "2":
                System.out.print("Enter the name of the house: ");
                name = myScanner.nextLine();
                House aHouse = new House(name);
                if(farm.add(aHouse)) {
                    System.out.println("A new house with the name " + name
                        + " added to your farm");
                    status = true;
                }
                else {
                    System.out.println("A house with that name already exists, try again");
                }
                break;

                default:
                System.out.println("Invalid input [" + input + "], try again.\n");
            }
        }
    }

    private void createAnimal(Farm farm) {
        boolean status = false;
        String input = "";
        String name = "";

        while(status == false) {
            System.out.println("\n----------------");
            System.out.println("Which type of animal do you want to create?");
            System.out.println("1. Dog");
            System.out.println("2. Cat");
            System.out.println("3. Horse");
            System.out.print("Select 1 to 3: ");
            input = myScanner.nextLine();

            switch(input) {
                case "1":
                System.out.print("Enter the name of the dog: ");
                name = myScanner.nextLine();
                Dog aDog = new Dog("Dog food", name);
                if(addAnimalToDwelling(farm, aDog)) {
                    status = true;
                }
                break;

                case "2":
                System.out.print("Enter the name of the cat: ");
                name = myScanner.nextLine();
                Cat aCat = new Cat("Cat food", name);
                if(addAnimalToDwelling(farm, aCat)) {
                    status = true;
                }
                break;

                case "3":
                System.out.print("Enter the name of the horse: ");
                name = myScanner.nextLine();
                Horse aHorse = new Horse("Dog food", name);
                if(addAnimalToDwelling(farm, aHorse)) {
                    status = true;
                }
                break;

                default:
                System.out.println("Invalid input [" + input + "], try again.\n");
            }
        }

    }

    private boolean addAnimalToDwelling(Farm farm, Animal animal) {
        boolean status = false;
        String name = "";
        Dwelling tmpDwelling = null;

        System.out.println("To which dwelling do you want to add " + animal.getName() + "?");
        System.out.print("Enter the name of the dwelling: ");
        name = myScanner.nextLine();
        tmpDwelling = farm.get(name);
        if(tmpDwelling != null) {
            if(tmpDwelling.add(animal)) {
                System.out.println(animal.getName() + " added to " + name);
                status = true;
            }
            else {
                System.out.println("You can't add this animal type to this dwelling type, try again.");
            }
        }
        else {
            System.out.println("Can't find a dwelling with this name, try again.");
        }

    return status;
    }

    private void listAnimalsInDwelling(Farm farm) {
        String name = "";
        Dwelling tmpDwelling = null;

        System.out.print("Enter the name of the dwelling: ");
        name = myScanner.nextLine();        
        tmpDwelling = farm.get(name);
        if(tmpDwelling != null) {
            System.out.println("\n----------------");
            System.out.println("Listing all animals in " + name + ":");
            tmpDwelling.listAllAnimals();
        } 
        else {
            System.out.println("Can't find a dwelling with that name.");
        }
    }

    private void listCompletFarm(Farm farm) {
        HashMap<String, Dwelling> map = farm.getMap();

        System.out.println("\n----------------");
        for(HashMap.Entry<String, Dwelling> entry : map.entrySet()) {
            String key = entry.getKey();
            Dwelling value = entry.getValue();
            System.out.println("Dwelling " + value + " with name " + key + " contains:");
            value.listAllAnimals();
        }
    }

}