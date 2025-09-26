package vn.iotstar.graphql;

import vn.iotstar.entity.User;
import vn.iotstar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.*;

import java.util.List;

@Controller
public class UserGraphQL {
  private final UserService service;
  public UserGraphQL(UserService service){ this.service = service; }

  @QueryMapping public List<User> users(){ return service.findAll(); }
  @QueryMapping public User userById(@Argument Long id){ return service.findById(id).orElse(null); }

  @MutationMapping public User createUser(@Argument String fullname, @Argument String email,
                                          @Argument String password, @Argument String phone){
    User u = new User();
    u.setFullname(fullname); u.setEmail(email); u.setPassword(password); u.setPhone(phone);
    return service.save(u);
  }
  @MutationMapping public User updateUser(@Argument Long id, @Argument String fullname,
                                          @Argument String phone){
    User u = service.findById(id).orElse(null);
    if(u==null) return null;
    u.setFullname(fullname); u.setPhone(phone);
    return service.save(u);
  }
  @MutationMapping(name="deleteUserGraphql")
  public Boolean deleteUserGraphql(@Argument Long id){ service.delete(id); return true; }
}
