package com.ts.tutorials.spring.demoserver.jpa;

import com.ts.tutorials.spring.demoserver.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
