import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import java.util.Random;
import java.util.ArrayList;
/**
 * This is the class for the Color Game object.
 * @author Soughtout Olasupo-Ojo
 * @version 1.0
 */
public class ColorGame extends Application {
    private String name;
    private int score;
    private ArrayList<Color> colors;
    private ArrayList<String> colorNames;
    private ArrayList<Color> textColors;
    private Random rand;
    private String message;
    private Label nameLabel;
    private Label scoreLabel;
    private Label statusLabel;
    private Rectangle questRect;
    private Rectangle oneRect;
    private Rectangle twoRect;
    private Rectangle teeRect;
    private Text questText;
    private Text oneText;
    private Text twoText;
    private Text teeText;
    private int questI;

    /**
     * This is the program that sets the initial window for the game.
     *
     * @param primaryStage is the Stage everything is set on
     */
    public void start(Stage primaryStage) {
        BorderPane bPane = new BorderPane();
        primaryStage.setMaxWidth(700);
        primaryStage.setMaxHeight(400);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(400);
        name = "None";
        score = 0;
        colors = new ArrayList<Color>();
        colorNames = new ArrayList<String>();
        textColors = new ArrayList<Color>();
        message = "Choose an answer to begin!";
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.PURPLE);
        colorNames.add("Red");
        colorNames.add("Orange");
        colorNames.add("Yellow");
        colorNames.add("Green");
        colorNames.add("Blue");
        colorNames.add("Purple");
        textColors.add(Color.HOTPINK);
        textColors.add(Color.ORANGERED);
        textColors.add(Color.GOLD);
        textColors.add(Color.DARKSEAGREEN);
        textColors.add(Color.LIGHTBLUE);
        textColors.add(Color.SLATEGRAY);
        nameLabel = new Label("Name: " + name);
        statusLabel = new Label(message);
        bPane.setTop(nameLabel);
        bPane.setBottom(statusLabel);
        bPane.setRight(reNone(primaryStage));
        bPane.setCenter(mainSect());
        Scene actScene = new Scene(bPane);
        primaryStage.setScene(actScene);
        primaryStage.setTitle("Color Game");
        primaryStage.show();
    }

    /**
     * This sets up the side for the Reset and None buttons.
     *
     * @return a pane with the reset and none buttons.
     */
    private VBox reNone(Stage primaryStage) {
        VBox vbox = new VBox(20);
        Button resetBut = new Button("Reset");
        resetBut.setOnAction((ActionEvent e) -> {
            resetAllBoxes();
            score = 0;
            name = "None";
            message = "Choose an answer to begin!";
            scoreLabel.setText("Score: " + score);
            nameLabel.setText("Name: " + name);
            statusLabel.setText(message);
        });
        Button noneBut = new Button("None");
        noneBut.setOnMouseClicked(e -> {
            if (!check(oneRect) && !check(twoRect) && !check(teeRect)) {
                score += 1;
                message = "Correct :)";
            } else {
                score = 0;
                message = "Incorrect :(";
            }
            scoreLabel.setText("Score: " + score);
            statusLabel.setText(message);
            resetAllBoxes();
        });
        vbox.getChildren().addAll(resetBut, noneBut);
        return vbox;
    }

    /**
     * This sets up the center of the game(Boxes, Text field and answer Button).
     *
     * @return the center for the game
     */
    private VBox mainSect() {
        VBox vbox = new VBox(20);
        scoreLabel = new Label("Score: " + score);
        vbox.getChildren().addAll(scoreLabel, getHboxOne(), getHboxTwo(), getHboxTee());
        return vbox;
    }

    /**
     * This returns a box with the text inside.
     *
     * @param x is the x-coordinate.
     * @param y is the y-coordinate.
     * @param w is the width of the box.
     * @param h is the height of the box.
     * @return the box, color and text combined.
     */
    private Rectangle makeBox(double x, double y, double w, double h) {
        rand = new Random();
        int sizeC = colors.size();
        Color randColor = colors.get(rand.nextInt(sizeC));
        Rectangle ansBox = new Rectangle(x, y, w, h);
        ansBox.setFill(randColor);
        return ansBox;
    }

    /**
     * This sets up the text for each box.
     *
     * @param x is the x-position
     * @param y is the y-position
     * @param w is the width
     * @param h is the height
     * @return
     */
    private Text makeText(int x, int y, int w, int h) {
        rand = new Random();
        int sizeN = colorNames.size();
        int sizeC = textColors.size();
        String randText = colorNames.get(rand.nextInt(sizeN));
        Color randColor = textColors.get(rand.nextInt(sizeC));
        double textX = (x + w) / 2;
        double textY = (y + h) / 2;
        Text ansTex = new Text();
        ansTex.setX(textX);
        ansTex.setY(textY);
        ansTex.setText(randText);
        Font siz = new Font(50);
        ansTex.setFont(siz);
        ansTex.setFill(randColor);
        return ansTex;
    }

    /**
     * This creates a box with the background and text.
     *
     * @param box  is the rectangle(Background)
     * @param text is the name of the color
     * @return a StackPane with the background and text
     */
    private StackPane makeLabelBox(Rectangle box, Text text) {
        StackPane labelBox = new StackPane();
        labelBox.getChildren().addAll(box, text);
        return labelBox;
    }

    /**
     * This resets the color and name of the color.
     *
     * @param box  is the rectangle(Background)
     * @param text is the name of the color
     */
    private void resetLabelBox(Rectangle box, Text text) {
        rand = new Random();
        int sizeC = colors.size();
        Color randColor = colors.get(rand.nextInt(sizeC));
        int sizeN = colorNames.size();
        String randText = colorNames.get(rand.nextInt(sizeN));
        int sizeCo = textColors.size();
        Color randTColor = textColors.get(rand.nextInt(sizeCo));
        box.setFill(randColor);
        text.setText(randText);
        text.setFill(randTColor);
    }

    /**
     * This resets all the boxes on the screen.
     */
    private void resetAllBoxes() {
        resetLabelBox(questRect, questText);
        resetLabelBox(oneRect, oneText);
        resetLabelBox(twoRect, twoText);
        resetLabelBox(teeRect, teeText);
    }

    /**
     * This checks whether the box background mateches the Question Box name.
     *
     * @param box is the background being checked
     * @return whether they are the same or not
     */
    private boolean check(Rectangle box) {
        Boolean bool = false;
        int check = colorNames.indexOf(questText.getText());
        int on = colors.indexOf(box.getFill());
        if (check == on) {
            bool = true;
        }
        return bool;
    }

    /**
     * This contains the textfield and the enter button.
     *
     * @return an Hbox with a textfield and enter button
     */
    private HBox getHboxOne() {
        HBox box = new HBox();
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name here");
        Button enterButton = new Button("Enter");
        enterButton.setOnAction((ActionEvent e) -> {
            name = nameField.getText();
            nameField.setText("");
            nameLabel.setText("Name: " + name);
        });
        box.getChildren().addAll(nameField, enterButton);
        return box;

    }

    /**
     * This contains the Question box.
     *
     * @return an Hbox with the question box
     */
    private HBox getHboxTwo() {
        HBox box = new HBox(40);
        questRect = makeBox(20, 40, 200, 100);
        questText = makeText((int) questRect.getX(), (int) questRect.getY(), (int) questRect.getWidth(),
                (int) questRect.getHeight());
        StackPane questBox = makeLabelBox(questRect, questText);
        box.getChildren().add(questBox);
        return box;

    }

    /**
     * This contains the three answer boxes.
     *
     * @return an Hbox containing the three answer boxes.
     */
    private HBox getHboxTee() {
        HBox box = new HBox(20);
        oneRect = makeBox(20, 40, 200, 100);
        twoRect = makeBox(240, 40, 200, 100);
        teeRect = makeBox(460, 40, 200, 100);
        oneText = makeText((int) oneRect.getX(), (int) oneRect.getY(), (int) oneRect.getWidth(),
                (int) oneRect.getHeight());
        twoText = makeText((int) twoRect.getX(), (int) twoRect.getY(), (int) twoRect.getWidth(),
                (int) twoRect.getHeight());
        teeText = makeText((int) teeRect.getX(), (int) teeRect.getY(), (int) twoRect.getWidth(),
                (int) teeRect.getHeight());
        StackPane boxOne = makeLabelBox(oneRect, oneText);
        StackPane boxTwo = makeLabelBox(twoRect, twoText);
        StackPane boxTee = makeLabelBox(teeRect, teeText);
        oneRect.setOnMouseClicked((EventHandler<MouseEvent>) e -> {
            boolean see = check(oneRect);
            if (see) {
                score += 1;
                message = "Correct :)";
            } else {
                score = 0;
                message = "Incorrect :(";
            }
            scoreLabel.setText("Score: " + score);
            statusLabel.setText(message);
            resetAllBoxes();
        });
        twoRect.setOnMouseClicked((EventHandler<MouseEvent>) e -> {
            boolean see = check(twoRect);
            if (see) {
                score += 1;
                message = "Correct :)";
            } else {
                score = 0;
                message = "Incorrect :(";
            }
            scoreLabel.setText("Score: " + score);
            statusLabel.setText(message);
            resetAllBoxes();
        });
        teeRect.setOnMouseClicked((EventHandler<MouseEvent>) e -> {
            boolean see = check(teeRect);
            if (see) {
                score += 1;
                message = "Correct :)";
            } else {
                score = 0;
                message = "Incorrect :(";
            }
            scoreLabel.setText("Score: " + score);
            statusLabel.setText(message);
            resetAllBoxes();
        });
        box.getChildren().addAll(boxOne, boxTwo, boxTee);
        return box;

    }

    /**
     * This is the function that loads the main function.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}