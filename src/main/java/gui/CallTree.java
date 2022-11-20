package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import main.Main;
import utils.BnB;
import utils.DataContainer;
import utils.Element;
import utils.Node;

import java.util.ArrayList;
import java.util.List;

public class CallTree {
	public TextField customVolume;
	public TextField customValue;
	public TextField customIterations;
	public TableView dataTable;
	public Label resultVolume;
	public Label resultValue;
	public TextField backpackSize;
	public CheckBox enablePresort;
	public CheckBox enableCopy;
	public Label iterationSaved;
	public Label iterationAmount;
	public Pane canvas;

	@FXML
	public void initialize(){
		TableColumn<Integer, Element> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Double, Element> valueColumn = new TableColumn<>("Value");
		valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

		TableColumn<Double, Element> volumeColumn = new TableColumn<>("Volume");
		volumeColumn.setCellValueFactory(new PropertyValueFactory<>("volume"));

		dataTable.getColumns().add(idColumn);
		dataTable.getColumns().add(valueColumn);
		dataTable.getColumns().add(volumeColumn);
	}

	//handels button click
	public void generateRandomValues(ActionEvent actionEvent) {
		int amount = Integer.parseInt(customIterations.getText());
		DataContainer.setData(DataContainer.generateRandomData(amount));
		displayData();
	}

	//handels event if user adds element manually
	public void addValue(ActionEvent actionEvent) {
		double volume = Double.parseDouble(this.customVolume.getText());
		double value = Double.parseDouble(this.customValue.getText());

		Element element = new Element(volume, value);
		element.setId(DataContainer.data.size());
		DataContainer.data.add(element);

		displayData();
	}

	//renders the data in the list
	public void displayData(){
		dataTable.getItems().clear();
		for(Element e : DataContainer.data){
			dataTable.getItems().add(e);
		}
	}

	//
	public void calculate(ActionEvent actionEvent) {
		double size = Double.parseDouble(backpackSize.getText());
		BnB bnB;

		int bruteForceIterations = (int) Math.pow(2, DataContainer.data.size());

		if(enableCopy.isSelected()){
			List<Element> copyList = new ArrayList<>(DataContainer.data);
			bnB = new BnB(size, copyList);
		} else {
			bnB = new BnB(size, DataContainer.data);
		}

		if(enablePresort.isSelected()){
			DataContainer.sortData();
			displayData();
		}

		Node test = bnB.solveKnapsack();


		resultValue.setText(bnB.getBestSolution()+"");
		resultVolume.setText(bnB.getBestSolutionVolume()+"");
		iterationAmount.setText(bnB.getIterations()+"");
		iterationSaved.setText((bruteForceIterations - bnB.getIterations()) + "");


		this.iterations = bnB.getIterations();

		canvas.getChildren().clear();

		int startX = (int) Main.getClientScreen().getWidth() / 2;
		int startY = 100;
		drawTree(startX, startY, test);
	}

	private final int radius = 30; //radius of node
	private final int distanceY = radius * 2; //distance between left and right node
	private int iterations = 0; //counts iterations on the call tree

	public void drawTree(int x, int y, Node node){
		Circle circle = new Circle(x, y, radius, Paint.valueOf(node.getColor()));
		canvas.getChildren().add(circle);

		int distanceX =(int) ((1000.0 * this.iterations) / (y/ 1.2));
		System.out.println(distanceX);

		if(node.getLeft() != null){
			Line line = new Line(x, y, x - distanceX ,y + distanceY);
			canvas.getChildren().add(line);
			Text text = new Text(x - radius / 2,y - radius / 2, createNodeText(node));
			canvas.getChildren().add(text);
			drawTree(x - distanceX ,y + distanceY, node.getLeft());
		}

		if(node.getRight() != null){
			Line line = new Line(x, y, x + distanceX, y + distanceY);
			canvas.getChildren().add(line);
			Text text = new Text(x - radius / 2,y - radius / 2, createNodeText(node));
			canvas.getChildren().add(text);
			drawTree(x + distanceX, y + distanceY, node.getRight());
		}

		//leaf
		if(node.getRight() == null && node.getLeft() == null){
			Text text = new Text(x - radius / 2,y - radius / 2, createNodeText(node));
			canvas.getChildren().add(text);
		}
	}


	private String createNodeText(Node node){
		double volumeAvailable = Math.round(node.getCurrentVolume() * 100.0) / 100.0;
		double valueCurrent = Math.round(node.getCurrentValue() * 100.0) / 100.0;

		String text;
		if(node.getElement() != null){
			double volumeElement = node.getElement().getVolume();
			double valueElement = node.getElement().getValue();
			text =
					"volume left: " + volumeAvailable + "\n" +
							"value: " + valueCurrent + "\n" +
							"vol: " + volumeElement + ", val: " + valueElement;
		} else {
			text =
					"volume left: " + volumeAvailable + "\n" +
							"value: " + valueCurrent + "\n";
		}
		return text;
	}

	public void clearData(ActionEvent actionEvent) {
		DataContainer.clearData();
		displayData();
	}
}
