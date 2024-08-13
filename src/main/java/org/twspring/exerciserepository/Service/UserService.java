package org.twspring.exerciserepository.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.twspring.exerciserepository.Api.ApiException;
import org.twspring.exerciserepository.Api.ApiResponse;
import org.twspring.exerciserepository.Model.User;
import org.twspring.exerciserepository.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//=======================GET==========================
    //Get all the Users

    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new ApiException("No users found");
        }
        return userRepository.findAll();
    }
    //Get user by email
    public User findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            throw new ApiException("User not found");
        }
        return user;
    }

    //Get All users with specific role
    public List<User> findUsersByRole(String role) {
        List<User> users = userRepository.findUserByRole(role);
        if (users.isEmpty()){
            throw new ApiException("No users found");
        }
        return users;
    }
    //Get All users with specific age or above
    public List<User> findUsersByAgeEqualsOrGreaterThan(int age) {
        List<User> users = userRepository.findUserByAgeEqualsOrGreaterThan(age);
        if (users.isEmpty()){
           throw new ApiException("No users found");
             }
        return users;
    }

    //check if the username and password are correct

    //first method (for more detailed response)
    public String checkUserNameAndPasswordV1(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new ApiException("User not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new ApiException("Wrong password");
        }
        return "User credentials are correct";
    }

    //second method (simple)
    public String checkUserNameAndPasswordV2(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username,password);
        if (user == null) {
            throw new ApiException("User credentials are incorrect");
        }
        return "User credentials are correct";
    }


    //Add new User
    public void addUser(User user) {
        userRepository.save(user);
    }

    //update
    public void updateUser(Integer id, User user) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("User Not Found");
        }
        u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setRole(user.getRole());
        u.setAge(user.getAge());
        userRepository.save(u);
    }

    //delete
    public void deleteUser(Integer id) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("User Not Found");
        }
        userRepository.delete(u);
    }

}
