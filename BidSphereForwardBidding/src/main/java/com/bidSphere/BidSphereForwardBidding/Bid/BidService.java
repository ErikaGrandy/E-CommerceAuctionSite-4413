package com.bidSphere.BidSphereForwardBidding.Bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class BidService {

    @Autowired private BidRepository repo;

    public ArrayList<Bid> getAllItems()
    {
        ArrayList<Bid> list = new ArrayList<>();
        Iterable<Bid> itList = repo.findAll();
        if (itList != null)
        {
            Iterator<Bid> iterator = itList.iterator();
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

    public Bid addItem(Bid item)
    {
        Bid addedItem = repo.save(item);
        return addedItem;
    }

    public Bid getItem(int itemID)
    {
        Optional<Bid> item = repo.findById(itemID);
        return (item.isPresent() ? item.get() : null);
    }

    public ArrayList<Bid> getBidsbyCatID(int id)
    {
        ArrayList<Bid> list = repo.findBidByCatalogItemID(id);
        System.out.println("Bids by catalog ID size: " + list.size());
        return list;
    }

}
