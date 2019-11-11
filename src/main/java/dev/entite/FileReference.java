package dev.entite;

public final class FileReference {
	
	private int numReference;
	private String color;
	private double price;
	private int size;
	
	public FileReference(int numReference, String color, double price, int size) {
		this.numReference = numReference;
		this.color = color;
		this.price = price;
		this.size = size;
	}
	public int getNumReference() {
		return numReference;
	}
	public void setNumReference(int numReference) {
		this.numReference = numReference;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reference [numReference=");
		builder.append(numReference);
		builder.append(", color=");
		builder.append(color);
		builder.append(", price=");
		builder.append(price);
		builder.append(", size=");
		builder.append(size);
		builder.append("]");
		return builder.toString();
	}

}
