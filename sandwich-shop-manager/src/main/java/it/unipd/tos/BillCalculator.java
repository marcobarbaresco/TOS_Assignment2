////////////////////////////////////////////////////////////////////
// Marco Barbaresco 1143032
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.exception.TakeAwayBillException;
import java.util.List;

public class BillCalculator implements TakeAwayBill {


    public double getOrderPrice(List<MenuItem> itemsOrdered)
            throws TakeAwayBillException {//Dato un elenco di ordinazioni (Panini e Fritti e Bevande) calcolare il totale
        double totale = 0;
        totale = itemsOrdered.stream().mapToDouble(x -> x.getPrice()).sum();
        
        //Se vengono ordinati più di 5 Panini viene fatto uno sconto del 50% sul prezzo del panino meno caro
        if(itemsOrdered
                .stream()
                .filter(x -> x.getType() == MenuItem.itemType.Panini)
                .count() > 5 ) {
            totale -= (itemsOrdered
                    .stream()
                    .filter(x -> x.getType() == MenuItem.itemType.Panini)
                    .mapToDouble(x -> x.getPrice()).min().getAsDouble())*0.5;
        }
        
        //Se l’importo totale delle ordinazioni (Panini e Fritti) supera i 50 euro viene fatto il 10% di sconto
        if(totale > 50) {
            totale -= totale*0.1;
        }
        
        //Non è possibile avere un’ordinazione con più di 30 elementi (se accade prevedere un messaggio d’errore
        if(itemsOrdered.size() > 30) { 
            throw new TakeAwayBillException("Errore: hai superato il numero massimo di ordinazioni");
        }
        
        //Se l’importo totale è inferiore a 10 € viene aggiunta una commissione di 0,50 €
        if(totale < 10){
            totale =totale + 0.5;
        }
        return totale;
    }
}