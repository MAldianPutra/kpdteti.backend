package edu.kpdteti.backend.repository;

import edu.kpdteti.backend.entity.User;
import edu.kpdteti.backend.enums.UserRoleEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUserId(String userId);

    User findByUserEmail(String userEmail);

    List<User> findAllByUserRoleEnum(UserRoleEnum userRoleEnum);

}
