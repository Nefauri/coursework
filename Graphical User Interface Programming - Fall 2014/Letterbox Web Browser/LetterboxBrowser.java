import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;

import javafx.event.*;

import javafx.geometry.*;

import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.scene.web.*;

import javafx.geometry.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.scene.image.*;
// JavaFX documentation: http://docs.oracle.com/javafx/2/api/


public class LetterboxBrowser extends Application 
{
    /**
     *   Run the application.
     *   Note: Application is a Process, only one allowed per VM.
     */
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(final Stage theStage) 
    {
        theStage.setTitle("Letterbox Web Browser");
        theStage.getIcons().add(new Image("icon.png")); 

        Group root = new Group();
        Scene theScene = new Scene(root);
        theScene.setFill( Color.color(0.1, 0.3, 0.4));

        
        final VBox mainBox = VBoxBuilder.create()
            .padding(new Insets(25,1,1,1))     // space outside of this box
            .spacing(10)                 // space between box elements
            .alignment(Pos.CENTER)       // left-aligned by default
            .build();

        root.getChildren().add(mainBox);
        
          // code -------------------------------------------------
        
        final TabPane tabPane = new TabPane();
        tabPane.setPrefWidth(800);
        tabPane.setPrefHeight(75);
        
        
        
        Tab aTab = new Tab();
        aTab.setText("");
        
        // set up the contents of a Tab similar to a Stage
        final VBox vb = VBoxBuilder.create()
            .padding(new Insets(10))
            .spacing(10)
            .alignment(Pos.CENTER)
            .build();
        aTab.setContent(vb);
        
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        
        
        
        mainBox.prefWidthProperty().bind( theStage.widthProperty().subtract(20) );
        
        webEngine.load("http://www.google.com");

        // don't forget to add the Tab to the TabPane when you're done!
        tabPane.getTabs().add( aTab );



        

         // MENU -------------------------------
        
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind( theStage.widthProperty() );
        root.getChildren().add(menuBar);
        
        // FILE MENU ------------------------------------------------

        Menu menuFile = new Menu("File");

        MenuItem tabMaker = MenuItemBuilder.create()
            .text("New Tab")
            .onAction(
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        Tab newTab = new Tab();
                        newTab.setText("New Tab");
                        tabPane.getTabs().add( newTab );
                        final TabPane tabPaneN = new TabPane();
                        tabPaneN.setPrefWidth(800);
                        tabPaneN.setPrefHeight(75);

                        // set up the contents of a Tab similar to a Stage
                        VBox vbNT = VBoxBuilder.create()
                            .padding(new Insets(10))
                            .spacing(10)
                            .alignment(Pos.CENTER)
                            .build();
                        newTab.setContent(vb);
                        
                        final WebView browserNT = new WebView();
                        final WebEngine webEngineNT = browser.getEngine();
                        
                        
                        
                        mainBox.prefWidthProperty().bind( theStage.widthProperty().subtract(20) );
                        
                        webEngineNT.load("http://www.google.com");

                        // don't forget to add the Tab to the TabPane when you're done!
                        tabPaneN.getTabs().add( newTab );
                        
                                 //url box
                         Text tNew = TextBuilder.create()
                            .text("Enter URL:")
                            .build();

                        final TextField addressBar = TextFieldBuilder.create()
                            .build();

                        Button searchButtonN = ButtonBuilder.create()
                            .text("Go to URL!")
                            .onAction(
                                new EventHandler() 
                                {
                                    public void handle(Event t) 
                                    {
                                        webEngineNT.load("http://"+addressBar.getText());
                                    }           
                                })
                            .build();   
                                
                         
                         
                        Button backButtonN = ButtonBuilder.create()
                            .text("")
                            .graphic( new ImageView(new Image("rsz_back.png")))
                            .onAction(
                                new EventHandler() 
                                {
                                    public void handle(Event t) 
                                    {
                                         webEngineNT.executeScript( "history.back()" );
                                    }           
                                })
                            .build();
                        
                        Button forwardButtonN = ButtonBuilder.create()
                            .text("")
                            .graphic( new ImageView(new Image("rsz_forward.png")))
                            .onAction(
                                new EventHandler() 
                                {
                                    public void handle(Event t) 
                                    {
                                         webEngineNT.executeScript( "history.forward()" );
                                    }           
                                })
                            .build();
                        
                        Button homeButtonN = ButtonBuilder.create()
                            .text("")
                            .graphic( new ImageView(new Image("rsz_home.png")) )
                            .onAction(
                                new EventHandler() 
                                {
                                    public void handle(Event t) 
                                    {
                                         webEngineNT.load("http://www.google.com");
                                    }           
                                })
                            .build();    
                        
                        homeButtonN.setMaxHeight(20);
                        homeButtonN.setMaxWidth(20);
                        
                        backButtonN.setMaxHeight(20);
                        backButtonN.setMaxWidth(20);
                        
                        forwardButtonN.setMaxHeight(20);
                        forwardButtonN.setMaxWidth(20);
                        final HBox navBarN = HBoxBuilder.create()
                        .padding(new Insets(10))
                        .spacing(10)
                        .build();
                        
                    
                        final HBox addrBarN = HBoxBuilder.create()
                        .padding(new Insets (10))
                        .spacing(10)
                        .build();    
                        
                        navBarN.getChildren().addAll(homeButtonN, backButtonN, forwardButtonN, addrBarN, searchButtonN );
                    
                        //mainBox.getChildren().addAll(navBar);
                        //mainBox.getChildren().add( tabPane );
                        
                    }
                })
             .build();
          
            
                MenuItem aboutOpt = MenuItemBuilder.create()
            .text("About")
            .onAction( new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent event)
                    {
                        System.out.println("Letterbox Web Browser brought to you by Victoria Iacono- Henderson!");
                    }
                })
            .build();
             MenuItem exitBrowser = MenuItemBuilder.create()
            .text("Exit")
            .onAction( new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent event)
                    {
                        System.exit(0);
                    }
                })
            .build();
            
        

       
        menuFile.getItems().addAll(tabMaker, aboutOpt, exitBrowser);

        menuBar.getMenus().add( menuFile );
        
         //url box
         Text t2 = TextBuilder.create()
            .text("Enter URL:")
            .build();

        final TextField addressBar = TextFieldBuilder.create()
            .build();

        Button searchButton = ButtonBuilder.create()
            .text("Go to URL!")
            .onAction(
                new EventHandler() 
                {
                    public void handle(Event t) 
                    {
                        webEngine.load("http://"+addressBar.getText());
                    }           
                })
            .build();   
                
         
         
        Button backButton = ButtonBuilder.create()
            .text("")
            .graphic( new ImageView(new Image("rsz_back.png")))
            .onAction(
                new EventHandler() 
                {
                    public void handle(Event t) 
                    {
                         webEngine.executeScript( "history.back()" );
                    }           
                })
            .build();
        
        Button forwardButton = ButtonBuilder.create()
            .text("")
            .graphic( new ImageView(new Image("rsz_forward.png")))
            .onAction(
                new EventHandler() 
                {
                    public void handle(Event t) 
                    {
                         webEngine.executeScript( "history.forward()" );
                    }           
                })
            .build();
        
        Button homeButton = ButtonBuilder.create()
            .text("")
            .graphic( new ImageView(new Image("rsz_home.png")) )
            .onAction(
                new EventHandler() 
                {
                    public void handle(Event t) 
                    {
                         webEngine.load("http://www.google.com");
                    }           
                })
            .build();    
        
        homeButton.setMaxHeight(20);
        homeButton.setMaxWidth(20);
        
        backButton.setMaxHeight(20);
        backButton.setMaxWidth(20);
        
        forwardButton.setMaxHeight(20);
        forwardButton.setMaxWidth(20);
        final HBox navBar = HBoxBuilder.create()
        .padding(new Insets(10))
        .spacing(10)
        .build();
        
    
        final HBox addrBar = HBoxBuilder.create()
        .padding(new Insets (10))
        .spacing(10)
        .build();    
        
        navBar.getChildren().addAll(homeButton, backButton, forwardButton, addressBar, searchButton );
    
        mainBox.getChildren().addAll(navBar);
        mainBox.getChildren().add( tabPane );
        mainBox.getChildren().add( browser );

            
        // code -------------------------------------------------
        
        theStage.setScene(theScene);
        theStage.show();
    }
}
