////////////////////////////////////////////////////////////////////
// Marco Barbaresco 1143032
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import it.unipd.tos.BillCalculator;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.business.exception.TakeAwayBillException;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BillCalculatorTest {
	
	@Test
	public void TestCalcoloTotale() {
		List<MenuItem> item = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            item.add(new MenuItem(MenuItem.itemType.Panini, "Primavera", 5));
        }
        for (int i = 0; i < 3; i++) {
            item.add(new MenuItem(MenuItem.itemType.Bevande, "Sprite", 2.50));
        }
        BillCalculator bill = new BillCalculator();
        double totale = 0;
        try {
            totale = bill.getOrderPrice(item);
            }
        catch (TakeAwayBillException e) {
        	System.out.println("Errore");
        	}
        assertEquals(totale, 22.50, 0.0);
	}
	
    @Test (expected = TakeAwayBillException.class)
    public void NumeroOrdinazioni_Superioria30_TakeAwayBillException() throws TakeAwayBillException {
        List<MenuItem> item = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            item.add(new MenuItem(MenuItem.itemType.Panini, "Vegetariano", 4.50));
        }
        BillCalculator bill = new BillCalculator();
        bill.getOrderPrice(item);
    }

    @Test
    public void NumeroPanini_Superiorea5_ScontoSulMenoCaro() {
        List<MenuItem> item = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            item.add(new MenuItem(MenuItem.itemType.Panini, "Primavera", 5));
        }
        item.add(new MenuItem(MenuItem.itemType.Panini, "Vegetariano", 4.50));
        BillCalculator bill = new BillCalculator();
        double totale = 0;
        try {
            totale = bill.getOrderPrice(item);
        } catch (TakeAwayBillException e) {
            System.out.println("Errore");
        }
        assertEquals(totale, 27.25, 0.0);
    }

    @Test
    public void CalcoloTotale_Superiorea50_ScontoDelDieciPerCento() {
        List<MenuItem> item = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            item.add(new MenuItem(MenuItem.itemType.Fritti, "Olive Ascolane", 6));
        }
        BillCalculator bill = new BillCalculator();
        double totale = 0;
        try {
            totale = bill.getOrderPrice(item);
        } catch (TakeAwayBillException e) {
            System.out.println("Errore");
        }
        assertEquals(totale, 48.60, 0.0);
    }

    @Test
    public void CalcoloTotale_Inferiorea10_AggiuntaCommissione(){
        List<MenuItem> item = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            item.add(new MenuItem(MenuItem.itemType.Bevande, "Fanta", 1.50));
        }
        BillCalculator bill = new BillCalculator();
        double totale = 0;
        try {
            totale = bill.getOrderPrice(item);
        } catch (TakeAwayBillException e) {
            System.out.println("Errore");
        }
        assertEquals(totale, 5.0, 0.0);
    }

}