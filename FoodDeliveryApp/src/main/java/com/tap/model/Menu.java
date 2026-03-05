package com.tap.model;

public class Menu {

	private int menuId;
    private int restaurantId;
    private String name;
    private String description;
    private double price;
    private double rating;
    private String imageUrl;
    private String type;          // VEG / NON_VEG / VEGAN
    private boolean isAvailable;
    
    public Menu() {
    	
    }

	public Menu(int menuId, int restaurantId, String name, String description, double price, double rating,
			String imageUrl, String type, boolean isAvailable) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.imageUrl = imageUrl;
		this.type = type;
		this.isAvailable = isAvailable;
	}
	public Menu(int restaurantId, String name, String description, double price, double rating,
			String imageUrl, String type, boolean isAvailable) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.imageUrl = imageUrl;
		this.type = type;
		this.isAvailable = isAvailable;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", restaurantId=" + restaurantId + ", name=" + name + ", description="
				+ description + ", price=" + price + ", rating=" + rating + ", imageUrl=" + imageUrl + ", type=" + type
				+ ", isAvailable=" + isAvailable + "]";
	}
	
}
