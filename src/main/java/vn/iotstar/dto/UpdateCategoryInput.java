package vn.iotstar.dto;

public class UpdateCategoryInput {
    private Long id;
    private String name;
    private String images;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
}
