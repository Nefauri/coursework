import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.*;
import org.jsoup.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

// JavaFX documentation:
//     http://docs.oracle.com/javafx/2/api/

/**
 *
 *Victoria Iacono Henderson
 *
**/
public class Feed extends Application 
{
    /**
     *   runs the application.
     *   Note: Application is a Process, only one allowed per VM.
     */
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setTitle("Social Media Summarizer");
        primaryStage.getIcons().add(new Image("blog.png"));

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill( Color.color(0.02,0.6,0.7) );


        VBox vb1 = VBoxBuilder.create()
            .padding(new Insets(10))     // space outside of this box
            .spacing(10)                 // space between box elements
            .alignment(Pos.CENTER)       // left-aligned by default
            .build();

        root.getChildren().add(vb1);

        // first row
        
        javafx.scene.text.Text t1 = TextBuilder.create()
            .text("Enter blog name: ")
            .build();
        
        
        final javafx.scene.text.Text btitle = TextBuilder.create()
            .text("")
            .build();

        final javafx.scene.text.Text desc = TextBuilder.create()
            .text("")
            .build();
            
        final javafx.scene.text.Text date = TextBuilder.create()
            .text("")
            .build(); 
        
            
        final javafx.scene.text.Text madStuff = TextBuilder.create()
            .text("")
            .wrappingWidth(800.0)
            .build();
            
        javafx.scene.text.Text madStuff2 = TextBuilder.create()
            .text("")
            .wrappingWidth(800.0)
            .build();
            
        javafx.scene.text.Text madStuff3 = TextBuilder.create()
            .text("")
            .wrappingWidth(800.0)
            .build();
            
        final TextField tf1 = TextFieldBuilder.create()
            .build();

        final TextArea all = new TextArea("");

            
        HBox hb1 = HBoxBuilder.create()
            .spacing(10)
            .build();
        
        hb1.getChildren().addAll(t1, tf1);
       
        // second row
        
        Button b1 = ButtonBuilder.create()
            .text("Summarize!")
            .build();
            
        // third row
        
        final javafx.scene.text.Text t2 = TextBuilder.create()
            .text("Displays 3 most recent items.")
            .build();

        // add rows to column
        
        vb1.getChildren().addAll( hb1, b1, t2, madStuff, madStuff2, madStuff3, all );
        
        
        // set up the interactive part
        // event handler - interface
        EventHandler<ActionEvent> eh1 = new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event) 
                {

                    try
                    {
                       URL url = new URL("http://"+tf1.getText() + ".blogspot.com/feeds/posts/default?alt=rss");
                        


            
                       Document doc = DocumentBuilderFactory.newInstance()
                            .newDocumentBuilder()
                            .parse( url.openStream() );
            
           
                       doc.getDocumentElement().normalize();
                       
                       Element root = doc.getDocumentElement();
            
                       String title = DomUtils.getChildText(root, "title");
                       btitle.setText("Title: " + title);
                        
            
                       String description = DomUtils.getChildText(root, "description");
                       desc.setText("Description: " + description);
                        
                       int itemsToDisplay = 3;
                       
            
                       List<Element> recentItems = DomUtils.getElementsByTagName(root, "item");
                       String hella1, hella2, hella3 = new String();

                       Element item0 = recentItems.get(0);   
                            hella1 = ("----------------------------" + 
                                "\n" + "Item " + 1 + "\n" + "----------------------------" + "\n" + "Title: " + DomUtils.getChildText(item0, 
                                "title")  + "\n" + "\n" + "Description: " + Jsoup.parse(DomUtils.getChildText(item0, "description")).text() 
                                 + "\n" + "\n" + "Publication Date: " + DomUtils.getChildText(item0, "pubDate") + "\n" + "\n");
                        
                           
                       Element item1 = recentItems.get(1);
                            hella2 = ("----------------------------" + 
                                "\n" + "Item " + 2 + "\n" + "----------------------------" + "\n" + "Title: " + DomUtils.getChildText(item1, 
                                "title")  + "\n" +  "\n" +"Description: " + Jsoup.parse(DomUtils.getChildText(item1, "description")).text() 
                                 + "\n" + "\n" + "Publication Date: " + DomUtils.getChildText(item1, "pubDate") + "\n" + "\n"  );
                  


                       Element item2 = recentItems.get(2);
                            hella3 = ("----------------------------" + 
                                "\n" + "Item " + 3 + "\n" + "----------------------------" + "\n" + "Title: " + DomUtils.getChildText(item2, 
                                "title") + "\n" + "\n" + "Description: " + Jsoup.parse(DomUtils.getChildText(item2, "description")).text() 
                                 + "\n"+ "\n" + "Publication Date: " + DomUtils.getChildText(item2, "pubDate") + "\n" + "\n"  );
   
                             
                       String everything = new String( hella1 + hella2 + hella3);

                       all.setWrapText(true);
                       all.setText(everything);
                             
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                }

            }
            ;
            
        // event listeners
        b1.setOnAction( eh1 );
        tf1.setOnAction( eh1 );
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}