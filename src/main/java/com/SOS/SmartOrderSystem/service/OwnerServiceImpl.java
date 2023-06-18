package com.SOS.SmartOrderSystem.service;

import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.repository.MenuRepository;
import com.SOS.SmartOrderSystem.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService{

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public boolean join(Owner owner) {
        //validateDuplicateOwner(owner);
        //repository가 비었는지 여부는 체크하지 않음 나중에 해야됨

        System.out.println("test code");

        String id = owner.getId();

/*        System.out.println("id = " + id);

        System.out.println("ownerRepository = " + ownerRepository);

        Optional<Owner> foundOwner = ownerRepository.findById(id);
        System.out.println("1111");
        String foundId = foundOwner.get().getId();
        System.out.println("2222");

        //System.out.println("ownerRepository.findById(owner.getId()) = " + ownerRepository.findById(owner.getId()));
        //System.out.println("ownerRepository = " + ownerRepository.findById(owner.getId()).get().getId());
       if(foundId == null)
        {
            return false;
        }
       else{
           ownerRepository.save(owner);
           return true;
       }*/

        ownerRepository.save(owner);


        return true;

    }

    private void validateDuplicateOwner(Owner owner) {
        ownerRepository.findById(owner.getId()).ifPresent((m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }));
    }
    @Override
    public Owner findOwnerID(String id) {
        Optional<Owner> byId = ownerRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        else{
            return null;
        }
    }

    @Override
    public Owner findOwnerPW(String pw) {
        return null;
    }
}
