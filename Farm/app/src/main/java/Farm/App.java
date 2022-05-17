package Farm;

public class App {
    public static void main(String[] args) {
        Farm myFarm = new Farm();
        UserInteraction myMenu = new UserInteraction();
        myMenu.menu(myFarm);
    }
}
