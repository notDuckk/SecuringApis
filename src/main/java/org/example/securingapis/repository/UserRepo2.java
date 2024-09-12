package org.example.securingapis.repository;

import org.example.securingapis.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo2 extends CrudRepository<User, Integer> {

}
