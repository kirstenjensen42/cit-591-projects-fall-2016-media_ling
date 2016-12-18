
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			BorderPane border = new BorderPane();


			// SEARCH BOX
			HBox hbox = new HBox();
		    hbox.setPadding(new Insets(15, 12, 15, 12));
		    hbox.setSpacing(10);
		    hbox.setStyle("-fx-background-color: #337691;");

		    GridPane inputBox = new GridPane();

		    Text enter = new Text("Enter a word: ");
		    enter.setFont(Font.font("Arial", FontWeight.NORMAL,18));
		    final TextField wordField = new TextField();
		    inputBox.add(enter, 1,1);
		    inputBox.add(wordField,1,2);

		    Button randomButton = new Button("Get random\nword!");
		    randomButton.setStyle("-fx-font: 12 arial; -fx-text-alignment: center;");
		    randomButton.setPrefSize(150, 50);

		    randomButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					String randomWord = RandomWord.get();
					wordField.clear();
					wordField.setPromptText(randomWord);
				}
			});


		    Button searchButton = new Button("Search for\nthis word");
		    searchButton.setStyle("-fx-font: 12 arial; -fx-text-alignment: center;");
		    searchButton.setPrefSize(150, 50);



		    hbox.getChildren().addAll(inputBox, searchButton, randomButton);
		    hbox.setAlignment(Pos.CENTER);
		    border.setBottom(hbox);


		    TableView<Word> table = new TableView<Word>();
		    table.setEditable(true);

		    ObservableList<Word> searchWords = FXCollections.observableArrayList();
		    table.setItems(searchWords);

		    TableColumn<Word,String> twitterCol = new TableColumn<Word,String>("Twitter");
		    twitterCol.setCellValueFactory(new PropertyValueFactory("twitterLL"));
		    TableColumn<Word,String> wordCol = new TableColumn<Word,String>("");
		    wordCol.setCellValueFactory(new PropertyValueFactory("word"));
		    TableColumn<Word,String> guardianCol = new TableColumn<Word,String>("Guardian");
		    guardianCol.setCellValueFactory(new PropertyValueFactory("guardianLL"));

		    table.getColumns().setAll(twitterCol, wordCol, guardianCol);
		    twitterCol.setPrefWidth(70);
		    twitterCol.setStyle("-fx-alignment: CENTER-RIGHT;") ;
		    wordCol.setPrefWidth(150);
		    wordCol.setStyle( "-fx-alignment: CENTER;");
		    guardianCol.setPrefWidth(80);

		    HBox def = new HBox();
		    border.setCenter(def);
		    def.setPadding(new Insets(25, 22, 25, 22));
		    def.setSpacing(10);
		    def.setStyle("-fx-background-color: #ffffff; -fx-font: 24 arial;");


		    table.setOnMouseClicked(new EventHandler<MouseEvent>() {

		        @Override
		        public void handle(MouseEvent event) {
		        	Text definition = new Text(table.getSelectionModel().getSelectedItem().getDefinition());
		        	def.getChildren().clear();
		        	def.getChildren().add(definition);

		        }
		    });

		    searchButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					String searchWord = wordField.getText() ;
					if (searchWord.equals("")) searchWord = wordField.getPromptText();
					if (searchWord.equals("")) return;
				    StringProperty theWord = new SimpleStringProperty();
				    theWord.setValue(searchWord);
				    searchWords.add(new Word(theWord));

				}
			});

		    StackPane list = new StackPane();
		    list.getChildren().add(table);
		    border.setLeft(list);


	        Scene scene = new Scene(border, 800, 600);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Media Linguist");
	        primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}




//ListView<String> list1 = new ListView<String>();
//list1.setEditable(true);
//ObservableList<String> items1 =FXCollections.observableArrayList (
//    "**", "****");
//list1.setItems(items1);
//
//ListView<String> list2 = new ListView<String>();
//list2.setEditable(true);
//ObservableList<String> items2 =FXCollections.observableArrayList (
//    "Word1", "Word2");
//list2.setItems(items2);
//
//ListView<S	atring> list1 = new ListView<String>();
//list1.setEditable(true);
//ObservableList<String> items1 =FXCollections.observableArrayList (
//    "**", "****");
//list1.setItems(items1);
//
//ListView<String> list2 = new ListView<String>();
//list2.setEditable(true);
//ObservableList<String> items2 =FXCollections.observableArrayList (
//    "Word1", "Word2");
//list2.setItems(items2);



//Text sceneTitle = new Text("Add a Word");
//sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,12));
//pane.add(sceneTitle, 2, 1, 2, 1);
//Label enter = new Label("Enter a word:");
//final TextField wordField = new TextField();
//HBox field = new HBox(20);
//field.getChildren().add(enter);
//field.getChildren().add(wordField);
//searchBar.set(field);

//Button randomWord = new Button("Get random word");
//Button search = new Button("Search");
//HBox buttons = new HBox(10);
//hbox.setAlignment(Pos.BOTTOM_CENTER);
//buttons.getChildren().add(randomWord);
//buttons.getChildren().add(search);
//pane.add(hbox, 2, 1);

//HBox input = new HBox(10);
//input.getChildren().add(field);
//input.getChildren().add(buttons);

//borderPane.setBottom(input);

//final Text showWord = new Text();
//pane.add(showWord, 1, 6);

//randomWord.setOnAction(new EventHandler<ActionEvent>() {
//
//	@Override
//	public void handle(ActionEvent t) {
//		String randomWord = RandomWord.get();
//		wordField.clear();
//		wordField.setPromptText(randomWord);
//	}
//});


//primaryStage.setScene(scene);










//public HBox addHBox() {
//    HBox hbox = new HBox();
//    hbox.setPadding(new Insets(15, 12, 15, 12));
//    hbox.setSpacing(10);
//    hbox.setStyle("-fx-background-color: #336699;");
//
//    Button buttonCurrent = new Button("Current");
//    buttonCurrent.setPrefSize(100, 20);
//
//    Button buttonProjected = new Button("Projected");
//    buttonProjected.setPrefSize(100, 20);
//    hbox.getChildren().addAll(buttonCurrent, buttonProjected);
//
//    return hbox;
//}




//public VBox addVBox() {
//VBox vbox = new VBox();
//vbox.setPadding(new Insets(10));
//vbox.setSpacing(8);
//
//Text title = new Text("Data");
//title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//vbox.getChildren().add(title);
//
//Hyperlink options[] = new Hyperlink[] {
//    new Hyperlink("Sales"),
//    new Hyperlink("Marketing"),
//    new Hyperlink("Distribution"),
//    new Hyperlink("Costs")};
//
//for (int i=0; i<4; i++) {
//    VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
//    vbox.getChildren().add(options[i]);
//}
//
//return vbox;
//}


//public void addStackPane(HBox hb) {
//StackPane stack = new StackPane();
//Rectangle helpIcon = new Rectangle(30.0, 25.0);
//helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
//    new Stop[]{
//    new Stop(0,Color.web("#4977A3")),
//    new Stop(0.5, Color.web("#B0C6DA")),
//    new Stop(1,Color.web("#9CB6CF")),}));
//helpIcon.setStroke(Color.web("#D0E6FA"));
//helpIcon.setArcHeight(3.5);
//helpIcon.setArcWidth(3.5);

//Text helpText = new Text("?");
//helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
//helpText.setFill(Color.WHITE);
//helpText.setStroke(Color.web("#7080A0"));

//stack.getChildren().addAll(helpIcon, helpText);
//stack.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in stack
//StackPane.setMargin(helpText, new Insets(0, 10, 0, 0)); // Center "?"
//
//hb.getChildren().add(stack);            // Add to HBox from Example 1-2
//HBox.setHgrow(stack, Priority.ALWAYS);    // Give stack any extra space
//}

//public GridPane addGridPane() {
//GridPane grid = new GridPane();
//grid.setHgap(10);
//grid.setVgap(10);
//grid.setPadding(new Insets(0, 10, 0, 10));

// Category in column 2, row 1
//Text category = new Text("Sales:");
//category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//grid.add(category, 1, 0);

// Title in column 3, row 1
//Text chartTitle = new Text("Current Year");
//chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//grid.add(chartTitle, 2, 0);

// Subtitle in columns 2-3, row 2
//Text chartSubtitle = new Text("Goods and Services");
//grid.add(chartSubtitle, 1, 1, 2, 1);

// House icon in column 1, rows 1-2
//ImageView imageHouse = new ImageView(
//  new Image(LayoutStyle.class.getResourceAsStream("graphics/house.png")));
//grid.add(imageHouse, 0, 0, 1, 2);

// Left label in column 1 (bottom), row 3
//Text goodsPercent = new Text("Goods\n80%");
//GridPane.setValignment(goodsPercent, VPos.BOTTOM);
//grid.add(goodsPercent, 0, 2);

// Chart in columns 2-3, row 3
//ImageView imageChart = new ImageView(
// new Image(LayoutSample.class.getResourceAsStream("graphics/piechart.png")));
//grid.add(imageChart, 1, 2, 2, 1);

// Right label in column 4 (top), row 3
//Text servicesPercent = new Text("Services\n20%");
//GridPane.setValignment(servicesPercent, VPos.TOP);
//grid.add(servicesPercent, 3, 2);
//
//return grid;
//}
//
//
//public FlowPane addFlowPane() {
//FlowPane flow = new FlowPane();
//flow.setPadding(new Insets(5, 0, 5, 0));
//flow.setVgap(4);
//flow.setHgap(4);
//flow.setPrefWrapLength(170); // preferred width allows for two columns
//flow.setStyle("-fx-background-color: DAE6F3;");
//
//ImageView pages[] = new ImageView[8];
//for (int i=0; i<8; i++) {
//    pages[i] = new ImageView(
//        new Image(LayoutPath.class.getResourceAsStream(
//        "graphics/chart_"+(i+1)+".png")));
//    flow.getChildren().add(pages[i]);
//}
//
//return flow;
//}




