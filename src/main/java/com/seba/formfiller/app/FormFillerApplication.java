package com.seba.formfiller.app;

import com.seba.formfiller.GUIUtilis.MainFrame;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FormFillerApplication extends Application {


  public static void main(String[] args) {
    new SpringApplicationBuilder(FormFillerApplication.class).headless(false).run(args);
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    MainFrame mainFrame = new MainFrame();
    mainFrame.launchWindow(primaryStage);
  }
}
