package com.tutorialjava.springbootcrudrestfulservices.repository;

import com.tutorialjava.springbootcrudrestfulservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{

}
