package utils;

public class Element implements Comparable{
	private double volume;
	private double value;
	private int id;

	public Element(double weight, double value){
		this.volume = weight;
		this.value = value;
	}

	@Override
	public String toString() {
		return "\"volume " + this.volume + ", value = " + this.value + "\"" ;
	}

	//compares value from two different elements
	@Override
	public int compareTo(Object o) {
		Element compare = (Element) o;
		if(compare.getValue() > this.value){
			return 1;
		} else {
			return 0;
		}
	}

	//Getters
	public double getVolume() {
		return this.volume;
	}

	public double getValue() {
		return this.value;
	}

	public int getId() {
		return id;
	}

	//Setters
	public void setValue(double value) {
		this.value = value;
	}

	public void setVolume(double weight) {
		this.volume = weight;
	}

	public void setId(int id){
		this.id = id;
	}
}
