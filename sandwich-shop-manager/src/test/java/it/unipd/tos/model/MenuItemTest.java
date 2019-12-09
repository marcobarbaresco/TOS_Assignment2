////////////////////////////////////////////////////////////////////
// Marco Barbaresco 1143032
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import it.unipd.tos.model.MenuItem;

import static org.junit.Assert.assertEquals;


public class MenuItemTest{

    @org.junit.Test
    public void testGetType(){
        MenuItem item = new MenuItem(MenuItem.itemType.Fritti, "Arancini", 4.50);
        assertEquals(item.getType(), MenuItem.itemType.Fritti);
    }

    @org.junit.Test
    public void testGetName(){
        MenuItem item = new MenuItem(MenuItem.itemType.Panini, "Vegetariano", 6.00);
        assertEquals(item.getName(),  "Vegetariano");
    }

    @org.junit.Test
    public void testGetPrice(){
        MenuItem item = new MenuItem(MenuItem.itemType.Bevande, "Sprite", 2.50);
        assertEquals(item.getPrice(), 2.50,0.0);
    }
}