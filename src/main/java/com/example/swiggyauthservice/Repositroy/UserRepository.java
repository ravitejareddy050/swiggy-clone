package com.example.swiggyauthservice.Repositroy;

import com.example.swiggyauthservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhonenumber(String phonenumber);
}
