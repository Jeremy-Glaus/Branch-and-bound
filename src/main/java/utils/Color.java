package utils;

public enum Color {
	ORANGE("orange"),
	RED("red"),
	WHITE("white"),
	GREEN("green");

	private final String color;

	Color(String color) {
		this.color = color;
	}

	public String color(){
		return color;
	}
}
