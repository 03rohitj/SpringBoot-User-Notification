package com.decipherzone.usernotification.repository;

import com.decipherzone.usernotification.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
