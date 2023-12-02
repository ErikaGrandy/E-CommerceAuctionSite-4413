package com.bidSphere.BidSphereForwardBidding.Catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class CatalogService {


    @Autowired private CatalogItemRepository repo;

    public ArrayList<CatalogItem> getAllItems()
    {
        ArrayList<CatalogItem> list = new ArrayList<>();
        Iterable<CatalogItem> itList = repo.findAll();
        if (itList != null)
        {
            Iterator<CatalogItem> iterator = itList.iterator();
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

    public CatalogItem addItem(CatalogItem item)
    {
        CatalogItem addedItem = repo.save(item);
        return addedItem;
    }

    public CatalogItem getItem(int itemID)
    {
        Optional<CatalogItem> item = repo.findById(itemID);
        return (item.isPresent() ? item.get() : null);
    }


}
