package impl.provider;

import impl.model.Menu;

import java.util.List;

public class MenuProvider {

    public List getMenu(List<Menu> menu) {
        menu.add(new Menu("Burger", 30));
        menu.add(new Menu("Cheese Burger", 35));
        menu.add(new Menu("Bacon Burger", 35));
        menu.add(new Menu("Bacon-Cheese Burger", 37));
        menu.add(new Menu("Vegan Burger", 32));
        menu.add(new Menu("Chicken Burger", 30));
        menu.add(new Menu("Double Cheese Burger", 41));
        menu.add(new Menu("Double Bacon-Cheese Burger", 45));
        menu.add(new Menu("Kids Burger", 25));

        return menu;
    }
}
