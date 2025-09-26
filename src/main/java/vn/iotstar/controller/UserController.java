package vn.iotstar.controller;

import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Query: Lấy tất cả user
    @QueryMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Query: Lấy user theo id
    @QueryMapping
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Mutation: Thêm user mới
    @MutationMapping
    public User addUser(String fullname, String email, String password, String phone) {
        User u = new User();
        u.setFullname(fullname);
        u.setEmail(email);
        u.setPassword(password);
        u.setPhone(phone);
        return userRepository.save(u);
    }

    // Mutation: Xóa user theo id
    @MutationMapping
    public Boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
