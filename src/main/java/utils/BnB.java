package utils;

import java.util.ArrayList;
import java.util.List;

//Magic is happening here: Solves the Knapsack-problem
public class BnB {

	private double maxVolume;
	private double bestSolution;
	private double bestSolutionVolume;
	private List<Element> elements;

	public BnB(double maxVolume, List<Element> elements){
		this.maxVolume = maxVolume; //max volume of the knapsack
		this.bestSolution = 0; //keeps track on the current highest value
		this.bestSolutionVolume = 0; //keeps track on the current best volume to solve the problem
		this.elements = elements; //contains the tree
	}

	//Returns the node with the best solution
	public Node solveKnapsack(){
		Node result = solveKnapsack(elements, this.maxVolume, 0);
		System.out.println(result.toString());

		return result;
	}

	public double getBestSolution() {
		return this.bestSolution;
	}

	public double getBestSolutionVolume() {
		return this.bestSolutionVolume;
	}

	public int getIterations() {
		return iterations;
	}

	private int iterations = 0; //counts the amount of recursive iterations
	public Node solveKnapsack(List<Element> elements, double restVolume, double currentValue){
		this.iterations++;

		//After iterating trough elements, the best value is compared with the current best
		if(elements.size() == 0){
			if(currentValue > this.bestSolution){
				this.bestSolution = currentValue;
				this.bestSolutionVolume = restVolume;
			}
			return new Node(currentValue, restVolume, null, Color.GREEN);
		}

		//Calculate sum of all available items not packed yet
		double upperBound = elements.stream().mapToDouble(e -> e.getValue()).sum();

		//In the knapsack is more value than available in the other items, so we stop
		if(this.bestSolution > upperBound + currentValue){
			return new Node(currentValue, restVolume, null, Color.RED);
		}

		//Define current utils.Element
		Element currentElement = elements.get(0);

		Node node = new Node(currentValue, restVolume, currentElement);

		//Removes current utils.Element, it gets discarded soon
		elements.remove(0);

		//Pack element if enough space is available
		if(restVolume >= currentElement.getVolume()){
			//Pack element
			Node resultPack = solveKnapsack(new ArrayList<>(elements), restVolume - currentElement.getVolume(), currentValue + currentElement.getValue());
			node.setLeft(resultPack);
		}

		//Not packing element
		Node resultNotPack = solveKnapsack(new ArrayList<>(elements), restVolume, currentValue);
		resultNotPack.setColor(Color.ORANGE);
		node.setRight(resultNotPack);

		return node;
	}
}
