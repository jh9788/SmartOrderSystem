package com.SOS.SmartOrderSystem.repository;

import com.SOS.SmartOrderSystem.domain.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository
//public class MemoryOwnerRepository implements OwnerRepository{

   /* private static Map<String, Owner> store = new HashMap<>();


    //이거 제대로 작동하는지 의심됨
    //@Autowired
    private ApplicationContext ac;



    @Override
    public Owner save(Owner owner) {

        System.out.println("owner.getId() = " + owner.getId());
        store.put(owner.getId(), owner);
        System.out.println("owner.getId() = " + owner.getId());

        return owner;
    }

    @Override
    public Optional<Owner> findById(String id) {
       String foundId = store.get(id).getId();
        System.out.println("foundId = " + foundId);
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean isAccountValid(String id, String password) {

        OwnerRepository bean = ac.getBean(OwnerRepository.class);



        Optional<Owner> foundOwner = bean.findById(id);
        System.out.println("foundOwner.get().getId() = " + foundOwner.get().getId());

        if(foundOwner.isPresent() || (foundOwner.get().getId() == id) ||
                foundOwner.get().getPassword() == password){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public List<Owner> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }*/
//}
