package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

//Manages Data of the Application
public class DataContainer {

	public static ArrayList<Element> data;

	public static void clearData(){
		data = new ArrayList<>();
	}

	//Function to populate list of available items
	public static ArrayList<Element> generateRandomData(int amount){
		Random random = new Random();
		ArrayList<Element> elements = new ArrayList<>();

		double min = 1;
		double max = 20;

		for(int i = 0; i < amount ; i++){
			double volume = min + (max - min) * random.nextDouble();
			double value = min + (max - min) * random.nextDouble();

			volume = Math.round(volume * 100.0) / 100.0;
			value = Math.round(value * 100.0) / 100.0;

			Element element = new Element(volume, value);
			element.setId(i);
			elements.add(element);
		}
		return elements;
	}

	//sort the data for experimental purposes. related to the checkbox in the application
	public static void sortData(){
		data = new ArrayList<>(data.stream().sorted(Comparator.comparingDouble(Element::getValue)
				.reversed()).collect(Collectors.toList()));
	}

	//overrides current data in application
	public static void setData(ArrayList<Element> newData){
		data = newData;
	}
}
