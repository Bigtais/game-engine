package sample;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class MyApplication extends javafx.application.Application {

  @Override
  public void start(Stage stage) throws MalformedURLException {


    Scene scene = createScene();

    stage.setTitle("Loading an image");

    stage.setScene(scene);

    stage.show();
  }

  private static Scene createScene() throws MalformedURLException {
    Image image = new Image("sample/image.png");
    ImageIcon image1 = new ImageIcon(MyApplication.class.getResource("/sample/image.png"));

    Text text = new Text("Width : " + image1.getIconWidth() + "// Height : " + image1.getIconHeight());

    text.setX(50);
    text.setY(50);


    //Setting the image view
    ImageView imageView = new ImageView(image);

    //Setting the position of the image
    imageView.setX(100);
    imageView.setY(100);

    //setting the fit height and width of the image view
    imageView.setFitHeight(216);
    imageView.setFitWidth(256);

    //Setting the preserve ratio of the image view
    imageView.setPreserveRatio(true);

    //Creating a Group object
    Group root = new Group(imageView, text);

    //Creating and returning  a scene object
    return new Scene(root, 600, 600);
  }

  public static void main(String[] args) {
    launch();
  }
}
