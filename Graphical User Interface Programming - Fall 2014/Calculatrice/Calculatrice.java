import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;

import javafx.event.*;

import javafx.geometry.*;

import java.net.*;
import java.io.*;
import java.util.*;

import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.collections.*;

import javafx.scene.control.ScrollPane.*;

import javafx.util.*;
import javafx.animation.*;

import net.objecthunter.exp4j.*;

// JavaFX documentation: http://docs.oracle.com/javafx/2/api/


public class Calculatrice extends Application 
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
        theStage.setTitle("Calculatrice");
        theStage.getIcons().add(new Image("icon.png"));

        Group root = new Group();
        Scene theScene = new Scene(root);
        // theScene.setFill(Color.color(0.0, 0.0, 0.0));

        final HBox supermainBox = HBoxBuilder.create()
            .padding(new Insets(10))     // space outside of this box
            .spacing(10)                 // space between box elements
            .alignment(Pos.CENTER)       // left-aligned by default
            .build();

        final VBox mainBox = VBoxBuilder.create()
            .padding(new Insets(10))     // space outside of this box
            .spacing(10)                 // space between box elements
            .alignment(Pos.CENTER)       // left-aligned by default
            .build();

        final VBox verticalBox = VBoxBuilder.create()
            .padding(new Insets(10))     // space outside of this box
            .spacing(10)                 // space between box elements
            .alignment(Pos.TOP_RIGHT)       // left-aligned by default
            .build();

        // code -------------------------------------------------

        try
        {
            File cssFile = new File("displayStyle.css");
            URL  cssURL  = cssFile.toURI().toURL();   
            theScene.getStylesheets().clear();
            theScene.getStylesheets().add( cssURL.toString() );
        }
        catch (Exception ex)
        {
            System.out.println("Couldn't find/parse stylesheet file.");
            ex.printStackTrace();
        }

        // model
        final Display myDisplay = new Display();

        // view 

        final Label displayLeft = LabelBuilder.create().build();
        displayLeft.getStyleClass().add("displayText");

        final Label displayRight = LabelBuilder.create().build();
        displayRight.getStyleClass().add("displayText");

        final Label displayCursor = LabelBuilder.create().text("|").build();
        displayCursor.getStyleClass().add("displayCursor");

        HBox displayRow = HBoxBuilder.create()
            .padding(new Insets(6))
            .alignment(Pos.CENTER_RIGHT)
            .build();

        final ScrollPane sp = ScrollPaneBuilder.create()
            .prefWidth(300)
            .prefHeight(60)
            .vbarPolicy(ScrollBarPolicy.NEVER)
            .hbarPolicy(ScrollBarPolicy.ALWAYS)
            .content( displayRow )
            .build();

        // controller
        Timeline timey = new Timeline();
        timey.setCycleCount(Timeline.INDEFINITE);

        KeyFrame displayViewUpdater = new KeyFrame(
                Duration.seconds(0.1), 
                new EventHandler<ActionEvent>() 
                {
                    public void handle(ActionEvent event) 
                    {
                        displayLeft.setText( myDisplay.getSubstringLeft() );
                        displayRight.setText( myDisplay.getSubstringRight() );

                        int time = (int)System.currentTimeMillis(); 

                        // oscillate value between 0 and 1
                        double opacity = Math.abs( Math.sin( time / 1000.0 * 3.14 ) );

                        displayCursor.setOpacity( opacity );
                    }
                });

        timey.getKeyFrames().add(displayViewUpdater);
        timey.play();

        // navigation buttons 
        Button buttonLeft = ButtonBuilder.create()
            .text("\u2190")
            .onAction( new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        myDisplay.moveLeft();
                    }                    
                })
            .build();

        Button buttonRight = ButtonBuilder.create()
            .text("\u2192")
            .onAction( new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        myDisplay.moveRight();
                    }                    
                })
            .build();

        Button buttonBacksp = ButtonBuilder.create()
            .text("\u232B")
            .onAction( new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        myDisplay.delete();
                    }                    
                })
            .build();

        Button buttonAC = ButtonBuilder.create()
            .text("AC")
            .onAction( new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        myDisplay.clear();
                    }                    
                })
            .build();

        Button buttonEval = ButtonBuilder.create()
            .text("=")
            .onAction( new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        String mathString = myDisplay.getContents();

                        Expression e = new ExpressionBuilder(mathString)
                            .build();

                        String result = "";

                        try
                        {
                            double y = e.evaluate();
                            result = Double.toString(y);
                        }
                        catch (Exception ex)
                        {
                            result = "Error";   
                        }

                        myDisplay.clear();
                        myDisplay.insert(result);
                    }                    
                })
            .build();

        // custom button
        //Button b337 = buttonMaker("337", "337", myDisplay);

        GridPane grid = GridPaneBuilder.create()
            .padding(new Insets(10)) 
            .gridLinesVisible(true)
            .hgap(1)
            .vgap(1)
            .build();

        String[] buttonNames = {
                "(", ")", "x^2", "x^3",
                "%", "\u221A", "cbrt", "\u03C0", 
                "sin", "cos", "tan", "exp",
                "asin","acos", "atan", "ln",
                "1", "2", "3", "\u00F7", 
                "4", "5", "6", "*", 
                "7", "8", "9", "-", 
                "^", ".", "0", "+"}; 

        String[] buttonInserts = {
                "(", ")", "^2", "x^3",
                "%", "sqrt(", "cbrt(", "3.14", 
                "sin(", "cos(", "tan(", "exp(",
                "asin(","acos(", "atan(", "ln(",
                "1", "2", "3", "/", 
                "4", "5", "6", "*", 
                "7", "8", "9", "-", 
                "^", ".", "0", "+"}; 

        // "^2", 1-9, 
        //                 "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".",
        //                 "+", "-", "*", "/", "^", "(", ")", "%", 
        //                 "^2", "^3", "sqrt(", "cbrt(",
        //                 "3.14", "sin(", "cos(", "tan(", "asin(", "acos(", "atan(",
        //                 "exp(", "ln("

        for (int i = 0; i < buttonNames.length; i++)
        {
            Button b = buttonMaker( buttonNames[i], buttonInserts[i], myDisplay );
            grid.add( b, i%4, i/4 );
        }

        //-------------------------------------------
        HBox hbox = new HBox(2); // spacing = 8
        hbox.getChildren().addAll(grid, verticalBox);

        //mainBox.getChildren().add( sp ); //scrollpane is the first part of the mainBox.
        displayRow.getChildren().addAll(displayLeft, displayCursor, displayRight);
        verticalBox.getChildren().addAll(buttonAC, buttonLeft, buttonRight, buttonBacksp, buttonEval ); 
        mainBox.getChildren().addAll(sp, hbox); //holds the scrollpane and the box of buttons
        root.getChildren().add(mainBox); //so this is the group that holds everything

        LinearGradient background = LinearGradientBuilder.create()
            .stops( 
                new Stop(0, Color.color(1.0, 0.40, 0.60)),  
                new Stop(1, Color.color(1.0, 1.0, 1.00))
            )
            .startX(0).startY(0) 
            .endX(0).endY(1)     
            .proportional(true)  
            .build();

        theScene.setFill( background );
        // code -------------------------------------------------

        theStage.setScene(theScene);
        theStage.show();
    }

    public Button buttonMaker(String labelText, final String insertText, final Display disp)
    {
        Button b = ButtonBuilder.create()
            .text( labelText )
            .prefWidth(64)
            .prefHeight(48)
            .onAction( new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        disp.insert( insertText );
                    }                    
                })
            .build();

        return b;
    }

}
