
import java.time.LocalDate;
import java.time.ZoneId;

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

	ZoneId zonedId = ZoneId.of( "America/Montreal" );
	LocalDate today = LocalDate.now( zonedId );

	GuardianTextBuilder gBuild = new GuardianTextBuilder(today.toString());
	GuardianTextBuilder gBuild2 = new GuardianTextBuilder("2016-12-16");

//	TweetParser tp = new TweetParser();

	Corpus guardian = new Corpus(gBuild.callArticleTexts());
//	Corpus twitter = new Corpus(tp.getWordList());
	Corpus twitter = new Corpus(gBuild2.callArticleTexts());

	FrequencyProfiler freq = new FrequencyProfiler(guardian.getTotalWordCount(), twitter.getTotalWordCount());

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
		    def.setPadding(new Insets(25, 22, 25, 22));
		    def.setSpacing(10);
		    def.setStyle("-fx-background-color: #ffffff; -fx-font: 18 arial;");
		    border.setCenter(def);

		    TableView<Word> notFoundTable = new TableView<Word>();
		    notFoundTable.setEditable(true);

		    ObservableList<Word> notFound = FXCollections.observableArrayList();
		    notFoundTable.setItems(notFound);

		    TableColumn<Word,String> notFoundList = new TableColumn<Word,String>("Not in either corpus:");
		    notFoundList.setCellValueFactory(new PropertyValueFactory("word"));

		    notFoundTable.getColumns().setAll(notFoundList);
		    notFoundTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		    StackPane notList = new StackPane();
		    notList.getChildren().add(notFoundTable);
		    border.setRight(notList);

		    table.setOnMouseClicked(new EventHandler<MouseEvent>() {

		        @Override
		        public void handle(MouseEvent event) {
		        	Text definition = new Text(table.getSelectionModel().getSelectedItem().getDefinition());
		        	definition.setWrappingWidth(200);
		        	def.getChildren().clear();
		        	def.getChildren().add(definition);

		        }
		    });

		    notFoundTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

		        @Override
		        public void handle(MouseEvent event) {
		        	Text definition = new Text(notFoundTable.getSelectionModel().getSelectedItem().getDefinition());
		        	definition.setWrappingWidth(200);
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
				    theWord.setValue(searchWord.toLowerCase());

					double tweet;
					if (twitter.getWords().get(searchWord) == null) {
						tweet = 0;
					} else {
						tweet = twitter.getWords().get(searchWord);
					}

					double article;
					if (guardian.getWords().get(searchWord) == null) {
						article = 0;
					} else {
						article = guardian.getWords().get(searchWord);
					}

					if (tweet == 0 && article == 0) {
						notFound.add(new Word(theWord, searchWord, 0));
					} else {

						double ll = freq.getFrequencyProfile(article,tweet);

				    	searchWords.add(new Word(theWord, searchWord, ll));
					}
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

		System.out.println("Welcome to Media-Ling.\n");
		System.out.println("Aquiring resources, please wait...\n");

		launch(args);


	}
}