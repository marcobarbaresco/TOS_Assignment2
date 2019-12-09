////////////////////////////////////////////////////////////////////
// Marco Barbaresco 1143032
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

import java.util.List;

public interface TakeAwayBill {
    double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException;
}