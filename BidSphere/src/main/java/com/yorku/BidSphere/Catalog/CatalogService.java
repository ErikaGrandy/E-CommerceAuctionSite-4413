package com.yorku.BidSphere.Catalog;

import com.yorku.BidSphere.Payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class CatalogService {


    private CatalogItemRepository repo = new CatalogItemRepository() {
        @Override
        public <S extends CatalogItem> S save(S entity) {
            return null;
        }

        @Override
        public <S extends CatalogItem> Iterable<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<CatalogItem> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public Iterable<CatalogItem> findAll() {
            return null;
        }

        @Override
        public Iterable<CatalogItem> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(CatalogItem entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends CatalogItem> entities) {

        }

        @Override
        public void deleteAll() {

        }
    };

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
