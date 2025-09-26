package vn.iotstar.service;

import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
  private final UserRepository repo;
  public UserService(UserRepository repo){ this.repo = repo; }

  public List<User> findAll(){ return repo.findAll(); }
  public Optional<User> findById(Long id){ return repo.findById(id); }
  public Optional<User> findByEmail(String email){ return repo.findByEmail(email); }
  public User save(User u){ return repo.save(u); }
  public void delete(Long id){ repo.deleteById(id); }
}
