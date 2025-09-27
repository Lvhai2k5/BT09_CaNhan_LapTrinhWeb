package vn.iotstar.service;

import org.springframework.stereotype.Service;
import vn.iotstar.entity.AppUser;
import vn.iotstar.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<AppUser> findAll() {
        return userRepo.findAll();
    }

    public Optional<AppUser> findById(Long id) {
        return userRepo.findById(id);
    }

    public AppUser save(AppUser user) {
        return userRepo.save(user);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    public AppUser findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
