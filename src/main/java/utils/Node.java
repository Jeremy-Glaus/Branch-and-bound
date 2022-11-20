package utils;

//Represents the nodes in the call-tree
public class Node {
	//node properties
	private double currentValue;
	private double currentVolume;
	private Color color;

	//contains data of current node
	private Element element;

	//children
	private Node left;
	private Node right;

	//constructor with default color
	public Node(double currentValue, double currentVolume, Element newElement){
		this.currentValue = currentValue;
		this.currentVolume = currentVolume;
		this.element = newElement;
		this.color = Color.WHITE;
	}

	//constructor with specific color
	public Node(double currentValue, double currentVolume, Element newElement, Color c){
		this.currentValue = currentValue;
		this.currentVolume = currentVolume;
		this.element = newElement;
		this.color = c;
	}

	//getters and setters
	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Element getElement() {
		return element;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public String getColor() {
		return color.color();
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public double getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public void setCurrentVolume(double currentVolume) {
		this.currentVolume = currentVolume;
	}

	@Override
	public String toString() {
		return "{" +
				"\"currentValue\":" + currentValue + "," +
				"\"currentVolume\":" + currentVolume + "," +
				"\"color\":" + color + "," +
				"\"element\":" + element + "," +
				"\"left\":" + left + "," +
				"\"right\":" + right +
				'}';
	}
}
