package com.bidSphere.BidSphereUser.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    public User getUserByUserNameAndPassword(String userName, String password);

    public int countUserByUserName(String username);

    public User getUserByUserName(String userName);
}
