package com.bidSphere.BidSphereDutchBidding.DutchBid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
@Service
public class DutchBidService {
	
	@Autowired private DutchBidRepository repo;
	
	   public ArrayList<DutchBid> getAllItems()
	    {
	        ArrayList<DutchBid> list = new ArrayList<>();
	        Iterable<DutchBid> itList = repo.findAll();
	        if (itList != null)
	        {
	            Iterator<DutchBid> iterator = itList.iterator();
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
	   
	   public DutchBid addItem(DutchBid item)
	    {
		   DutchBid addedItem = repo.save(item);
	        return addedItem;
	    }
	   public DutchBid getItem(int itemID)
	    {
	        Optional<DutchBid> item = repo.findById(itemID);
	        return (item.isPresent() ? item.get() : null);
	    }
	   

}
