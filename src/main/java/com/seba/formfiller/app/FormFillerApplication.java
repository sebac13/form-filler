package com.seba.formfiller.app;

import com.seba.formfiller.services.HTMLSourceCodeReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@SpringBootApplication
public class FormFillerApplication implements CommandLineRunner {

  public static void main(String[] args) {
    HTMLSourceCodeReader sourceCodeReader = new HTMLSourceCodeReader();
    try{
      sourceCodeReader.getMapOfInputFields("http://nasze.miasto.gdynia.pl/ed_miej/login.pl");
    }catch(IOException e){
      e.printStackTrace();
    }
    
    new SpringApplicationBuilder(FormFillerApplication.class).headless(false).run(args);
  }
  @Override
  public void run(String... args) {
    JFrame frame = new JFrame("Spring Boot Swing App");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 300);
    JPanel panel = new JPanel(new BorderLayout());
    JTextField text = new JTextField("Spring Boot can be used with Swing apps");
    panel.add(text, BorderLayout.CENTER);
    frame.setContentPane(panel);
    frame.setVisible(true);
  }
  
}