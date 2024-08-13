package org.twspring.exerciserepository.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.twspring.exerciserepository.Model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    User findUserByUsernameAndPassword(String username, String password);
    List<User> findUserByRole(String role);
    @Query("select u from User u where u.age>=?1")
    List<User> findUserByAgeEqualsOrGreaterThan(Integer age);
}
