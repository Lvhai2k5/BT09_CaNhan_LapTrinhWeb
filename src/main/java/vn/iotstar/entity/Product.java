package vn.iotstar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer quantity;

    @Column(name = "description") // tránh trùng keyword
    private String description;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id")  // FK sang User
    private User owner;

    @ManyToOne
    @JoinColumn(name = "category_id") // FK sang Category
    private Category category;

    // GETTER & SETTER
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
