package com.ts.tsadmin.repository;


import com.ts.tsadmin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailId(String emailId);

    Long countByEmailId(String emailId);

}