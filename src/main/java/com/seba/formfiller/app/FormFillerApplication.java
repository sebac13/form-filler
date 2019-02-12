package com.seba.formfiller.app;

import com.seba.formfiller.GUIUtilis.AlertBox;
import com.seba.formfiller.services.HTMLSourceCodeReader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

@SpringBootApplication
public class FormFillerApplication extends Application {


  public static void main(String[] args) {
    new SpringApplicationBuilder(FormFillerApplication.class).headless(false).run(args);
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    HTMLSourceCodeReader sourceCodeReader = new HTMLSourceCodeReader();
    AlertBox alert = new AlertBox();

    primaryStage.setTitle("Form-filler-app");
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setVgap(8);
    grid.setHgap(10);

    Label urlLabel = new Label("Provide url");
    GridPane.setConstraints(urlLabel, 5, 0);

    TextField urlTextField = new TextField();
    urlTextField.setPromptText("url of page with forms to fill");
    GridPane.setConstraints(urlTextField, 5, 1);

    Button generateButton = new Button("Generate");

    generateButton.setOnAction(e -> {
      try {
        Map mapOfInputFields = sourceCodeReader.getMapOfInputFields(urlTextField.getText());
        if (mapOfInputFields.isEmpty())
          alert.display("No forms to fill", "This page has no forms, why you even bother to launch me?");
        System.out.println(mapOfInputFields);
      } catch (MalformedURLException exception) {
        alert.display("Wrong URL!", "Provide existing url man...");
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    });
    GridPane.setConstraints(generateButton, 5, 2);
    grid.getChildren().addAll(urlLabel, urlTextField, generateButton);

    primaryStage.setScene(new Scene(grid, 350, 200));
    primaryStage.show();
  }
}
