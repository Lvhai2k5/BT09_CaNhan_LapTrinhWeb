package vn.iotstar.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity @Table(name = "Users")
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fullname;

  @Column(unique = true, nullable = false)
  private String email;

  private String password;
  private String phone;

  // Many-to-Many User <-> Category
  @ManyToMany
  @JoinTable(name = "user_categories",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<Category> categories = new HashSet<>();

  // One-to-Many User (owner) -> Product
  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Product> products = new ArrayList<>();

  // getters/setters
  public Long getId() { return id; } public void setId(Long id) { this.id = id; }
  public String getFullname() { return fullname; } public void setFullname(String fullname) { this.fullname = fullname; }
  public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
  public String getPassword() { return password; } public void setPassword(String password) { this.password = password; }
  public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
  public Set<Category> getCategories() { return categories; } public void setCategories(Set<Category> categories) { this.categories = categories; }
  public List<Product> getProducts() { return products; } public void setProducts(List<Product> products) { this.products = products; }
}
