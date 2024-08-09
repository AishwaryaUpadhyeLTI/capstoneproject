package com.wecp.educationalresourcedistributionsystem.repository;


import com.wecp.educationalresourcedistributionsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>{
    // extend jpa repostiory and add custom method if needed
}
