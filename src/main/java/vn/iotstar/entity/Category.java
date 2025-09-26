package vn.iotstar.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity @Table(name = "Categories")
public class Category {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String images;

  // Many-to-Many mapped by users
  @ManyToMany(mappedBy = "categories")
  private Set<User> users = new HashSet<>();

  // One-to-Many Category -> Product
  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Product> products = new ArrayList<>();

  // getters/setters
  public Long getId() { return id; } public void setId(Long id) { this.id = id; }
  public String getName() { return name; } public void setName(String name) { this.name = name; }
  public String getImages() { return images; } public void setImages(String images) { this.images = images; }
  public Set<User> getUsers() { return users; } public void setUsers(Set<User> users) { this.users = users; }
  public List<Product> getProducts() { return products; } public void setProducts(List<Product> products) { this.products = products; }
}
