// Observer Pattern Implementation
import java.util.*;

// Subject Interface
interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}

// Observer Interface
interface Observer {
    void update();
}

// AdminSystem as Subject
class AdminSystem implements Subject {
    private List<Observer> observers = new ArrayList<>();

    public void updateProductInfo() {
        System.out.println("Product information updated.");
        notifyObservers();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}

// Kiosk as Observer
class Kiosk implements Observer {
    private String name;

    public Kiosk(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(name + " received product update.");
    }
}

// Decorator Pattern Implementation
abstract class MenuItem {
    public abstract String getDescription();
    public abstract double getCost();
}

// Concrete Component
class Drink extends MenuItem {
    private String description = "Basic Drink";

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

// Abstract Decorator
abstract class CustomizationDecorator extends MenuItem {
    protected MenuItem menuItem;

    public CustomizationDecorator(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}

// Concrete Decorators
class ExtraSugar extends CustomizationDecorator {
    public ExtraSugar(MenuItem menuItem) {
        super(menuItem);
    }

    @Override
    public String getDescription() {
        return menuItem.getDescription() + ", Extra Sugar";
    }

    @Override
    public double getCost() {
        return menuItem.getCost() + 0.5;
    }
}

class ExtraTopping extends CustomizationDecorator {
    public ExtraTopping(MenuItem menuItem) {
        super(menuItem);
    }

    @Override
    public String getDescription() {
        return menuItem.getDescription() + ", Extra Topping";
    }

    @Override
    public double getCost() {
        return menuItem.getCost() + 1.0;
    }
}

// Main Class to Demonstrate Patterns
public class KioskSystem {
    public static void main(String[] args) {
        // Observer Pattern Demo
        AdminSystem adminSystem = new AdminSystem();
        Kiosk kiosk1 = new Kiosk("Kiosk 1");
        Kiosk kiosk2 = new Kiosk("Kiosk 2");

        adminSystem.attach(kiosk1);
        adminSystem.attach(kiosk2);

        adminSystem.updateProductInfo();

        // Decorator Pattern Demo
        MenuItem drink = new Drink();
        System.out.println(drink.getDescription() + " - $" + drink.getCost());

        drink = new ExtraSugar(drink);
        System.out.println(drink.getDescription() + " - $" + drink.getCost());

        drink = new ExtraTopping(drink);
        System.out.println(drink.getDescription() + " - $" + drink.getCost());
    }
}
