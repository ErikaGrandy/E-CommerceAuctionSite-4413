package com.yorku.BidSphere.DutchCatalog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import com.yorku.BidSphere.Payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DutchCatalogService {
	
    @Autowired private DutchCatalogItemRepository repo;

    public ArrayList<DutchCatalogItem> getAllItems()
    {
        ArrayList<DutchCatalogItem> list = new ArrayList<>();
        Iterable<DutchCatalogItem> itList = repo.findAll();
        if (itList != null)
        {
            Iterator<DutchCatalogItem> iterator = itList.iterator();
            while(iterator.hasNext()){
                list.add(iterator.next());
            }

            return list;
        }
        else
        {
            return null;
        }

    }

    public DutchCatalogItem addItem(DutchCatalogItem item)
    {
    	DutchCatalogItem addedItem = repo.save(item);
        return addedItem;
    }

    public DutchCatalogItem getItem(int itemID)
    {
        Optional<DutchCatalogItem> item = repo.findById(itemID);
        return (item.isPresent() ? item.get() : null);
    }

    public DutchCatalogItem decrementPrice(int itemID)
    {
        Optional<DutchCatalogItem> item = repo.findById(itemID);
        DutchCatalogItem dutchItem = item.get();
        dutchItem.lowerPrice();
        repo.save(dutchItem);
        return dutchItem;
        
    }
    public DutchCatalogItem itemSold(int itemID) {
    	
        Optional<DutchCatalogItem> item = repo.findById(itemID);
        DutchCatalogItem dutchItem = item.get();
        dutchItem.setAvailible(false);
        repo.save(dutchItem);
    	return dutchItem;
    }

	public boolean verifySeller(int itemID, int userID) {
		  Optional<DutchCatalogItem> item = repo.findById(itemID);
	      DutchCatalogItem dutchItem = item.get();
	      
		return (dutchItem.getSellerID() == userID);
	}

}
