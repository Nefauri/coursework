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

import javafx.util.*;
import javafx.animation.*;

import java.text.DecimalFormat;

// JavaFX documentation: http://docs.oracle.com/javafx/2/api/


public class StopwatchGUI extends Application 
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
        theStage.setTitle("Countdown Timer");
        theStage.getIcons().add(new Image("icon.png"));

        Group root = new Group();
        Scene theScene = new Scene(root);
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
        // code -------------------------------------------------
        final Stopwatch watch = new Stopwatch();
        watch.reset();

        final Text timeDisplay = TextBuilder.create()
            .text("Time")
            .build();

        final Button buttons = ButtonBuilder.create()
            .text("Text")
            .onAction(
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        double time = Globals.startTime*1000 - watch.getRunningTime();
                        if ( (watch.getState() == Stopwatch.DEFAULT || watch.getState() == Stopwatch.PAUSED) )
                        {
                            watch.resume();
                        }
                        else if ( watch.getState() == Stopwatch.RUNNING )
                        {
                            watch.pause();
                        }
                        else
                        {
                            System.err.println(" Whoops! ");
                        }

                    }
                }
            )
            .build();

        Button resetButton = ButtonBuilder.create()
            .text("Reset")
            .onAction(
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        watch.reset();
                    }
                })
            .build();

        final DecimalFormat formatter = new DecimalFormat("#0.00");     
        Timeline times = new Timeline();
        times.setCycleCount( Timeline.INDEFINITE );
        KeyFrame keyF = new KeyFrame(
                Duration.seconds(0.01),
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        watch.update( System.currentTimeMillis() );
                        double time = (Globals.startTime*1000 - watch.getRunningTime())/1000;
                        String displayText = "Time: ";
                        if (time > 0)
                        {
                            displayText = "Time: " + formatter.format(time);
                        }
                        else
                        {
                            displayText = "Time: " + 0;
                            time = 0;
                        }

                        timeDisplay.setText( displayText );

                        if (time <= 0 && watch.getState() == Stopwatch.RUNNING)
                        {
                            watch.pause();
                        }

                        if ( watch.getState() == Stopwatch.DEFAULT )
                        {
                            buttons.setText("Start");
                        }
                        else if ( watch.getState() == Stopwatch.RUNNING )
                        {
                            buttons.setText("Pause");
                        }
                        else if ( watch.getState() == Stopwatch.PAUSED )
                        {
                            buttons.setText("Resume");
                        }
                        else
                        {
                            System.err.println("Whoops!");
                        }
                    }
                });

        final TextField startText = new TextField();
        startText.setPrefWidth(80);
        Button setButton = ButtonBuilder.create()
            .text("Set")
            .onAction(
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        try
                        {
                            double start = Double.parseDouble( startText.getText() );
                            Globals.startTime = start;
                        }
                        catch (Exception e)
                        {
                            String displayText = "Please enter a number.";
                            startText.setText( displayText );
                        }

                    }
                })
            .build();

        final VBox mainBox = VBoxBuilder.create()
            .padding(new Insets(20))     // space outside of this box
            .spacing(10)                 // space between box elements
            .alignment(Pos.CENTER)       // left-aligned by default
            .build();

        final HBox hBox = HBoxBuilder.create()
            .padding(new Insets(20))     // space outside of this box
            .spacing(10)                 // space between box elements
            .alignment(Pos.CENTER)       // left-aligned by default
            .build();

        final HBox hBox2 = HBoxBuilder.create()
            .padding(new Insets(20))     // space outside of this box
            .spacing(10)                 // space between box elements
            .alignment(Pos.CENTER)       // left-aligned by default
            .build();
            
            
        final HBox hBoxTime = HBoxBuilder.create()
            .padding(new Insets(10))
            .spacing(20)
            .alignment(Pos.CENTER)
            .build();

        root.getChildren().add(mainBox);

        hBox.getChildren().addAll( startText);
        hBox2.getChildren().addAll( setButton, buttons, resetButton );
        hBoxTime.getChildren().addAll( timeDisplay );

        mainBox.getChildren().addAll(hBox, hBoxTime, hBox2);
        times.getKeyFrames().add( keyF );
        times.play();

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
        // code --------------------------------------------------------

        theStage.setScene(theScene);
        theStage.show();

    }
}
