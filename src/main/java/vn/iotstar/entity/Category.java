package vn.iotstar.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String images;

    @ManyToMany
    @JoinTable(
            name = "User_Category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<AppUser> users = new HashSet<>();

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public Set<AppUser> getUsers() { return users; }
    public void setUsers(Set<AppUser> users) { this.users = users; }

    public Set<Product> getProducts() { return products; }
    public void setProducts(Set<Product> products) { this.products = products; }
}
