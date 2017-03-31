import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import java.text.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.event.*;
import javafx.beans.value.*;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;


/** 
 * Victoria Iacono Henderson
 */
public class TemperatureConverter extends Application 
{
    /**
     *   Runs the application.
     *   Note: Application is a Process, only one allowed per VM.
     */
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle("Temperature Converter - Fahrenheit to Celsius");
        
        StackPane root = new StackPane();
        root.setId("pane");
        Scene scene = new Scene(root, 600, 700);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        theStage.setScene(scene);
        theStage.show();
        theStage.getIcons().add(new Image("meter.png"));

        VBox mainBox = VBoxBuilder.create()
            .padding(new Insets(10))     
            .spacing(10)                 
            .alignment(Pos.CENTER)       
            .build();

        VBox celBox = VBoxBuilder.create()
            .padding(new Insets(5))
            .spacing(5)
            .alignment(Pos.CENTER)
            .build();
            
        root.getChildren().add(mainBox);
        root.getChildren().add(celBox);
        
        final Text fah = TextBuilder.create()
            .text("Fahrenheit")
            .stroke( Color.PURPLE)
            .build();
        final Text cel = TextBuilder.create()
            .text("Celsius")
            .stroke( Color.INDIGO)
            .build();
            

        Slider mySlider = SliderBuilder.create()
            .prefHeight(700)
            .prefWidth(100)          // how wide should it be?
            .min(-50)                  // smallest value on slider
            .max(250)                 // largest value on slider
            .value(0)                // initial value set on slider
            .majorTickUnit(10)        // every X units, draw tickmark/label
            .minorTickCount(5)       // draw this many in between major units
            .showTickLabels(true)    // draw labels?
            .showTickMarks(true)     // draw small marks between larger ones?
            .snapToTicks(true)       // when released, round to nearest tick mark
            .orientation(Orientation.VERTICAL)
            .build();
            
            

        mySlider.valueProperty().addListener( 
        new ChangeListener()
            {
                public void changed(ObservableValue o, Object oldValue, Object newValue)
                {
                    final DoubleProperty fahr = new SimpleDoubleProperty((Double)newValue);    
                    DoubleBinding cels = new DoubleBinding()
                    {
                        // initializer for anonymous inner class, works as a constructor
                        {
                            super.bind(fahr);
                        }
                        
                        @Override
                        protected double computeValue()
                        {
                            Double temp = -1.0;
                            temp = ((fahr.getValue() - 32)*5)/9;
                            return temp;
                        }
                    }
                    ;
                    DecimalFormat df = new DecimalFormat("#.#");
                    fah.setText(df.format(fahr.getValue()) + "° Fahrenheit");
                    cel.setText(df.format(cels.getValue()) + "° Celsius");

                
                }            
            }
            );
            

        mainBox.getChildren().addAll( fah, mySlider, celBox, cel );

        theStage.setScene(scene);
        theStage.show();
    }
}
