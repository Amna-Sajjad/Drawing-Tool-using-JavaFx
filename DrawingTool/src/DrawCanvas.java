
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class DrawCanvas extends Application {
	private MenuBar menuBar;
	private Menu file,Shape,Clear,About;
	private MenuItem New,Exit,about,allClear;
	private RadioMenuItem Cir,Rec,RondRec,Line,Text,Eraser;
	private Canvas canva;
	private ToolBar toolBar;
	private BorderPane bp;
	private Label line,fill,stSize,text,textColor,textFont,textSize,fStrokeColor,fStyle,fWeight,sLine,eSize;
	private TextField stSizeField,textField,fontSize;
	private ComboBox<String> fontStyle;
	private ColorPicker fontColor,fontStrokeColor;
	private double xStrt,yStrt;
	private RadioButton italic,regular,bold,normal,dotLine,solidLine;
	private Slider eraserSlider;
	private GraphicsContext gc;
	private Pane pane;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		//create borderpane
		bp=new BorderPane();
		//create scene and group
		
		Scene scene=new Scene(bp,800,650);
		//create menubar
		menuBar=new MenuBar();
		//create tool bar
		toolBar=new ToolBar();
		toolBar.setStyle("-fx-background-color: lightgrey");
		toolBar.setMinSize(75, 625);
		
		//create menu
		file=new Menu("File");
		Shape=new Menu("Shape");
		Clear=new Menu("Clear");
		About=new Menu("About");
		//create menuitems
		ImageView newIcon = new ImageView(new Image("file:///G:/JP/Eclipse/DrawingTool/IconsImage/NewIcon2.png"));
	    newIcon.setFitWidth(20);
	    newIcon.setFitHeight(25);
	    ImageView exitIcon=new ImageView(new Image("file:///G:/JP/Eclipse/DrawingTool/IconsImage/ExitIcon2.png"));
	    exitIcon.setFitWidth(20);
	    exitIcon.setFitHeight(20);
	    ImageView mouseIcon=new ImageView(new Image("file:///G:/JP/Eclipse/DrawingTool/IconsImage/mouseIcon2.png"));
	    mouseIcon.setFitWidth(20);
	    mouseIcon.setFitHeight(20);
	    ImageView eraserIcon=new ImageView(new Image("file:///G:/JP/Eclipse/DrawingTool/IconsImage/EraserIcon.png"));
	    eraserIcon.setFitWidth(20);
	    eraserIcon.setFitHeight(20);
	    ImageView recIcon=new ImageView(new Image("file:///G:/JP/Eclipse/DrawingTool/IconsImage/RecIcon2.png"));
	    recIcon.setFitWidth(25);
	    recIcon.setFitHeight(18);
	    ImageView ecpIcon=new ImageView(new Image("file:///G:/JP/Eclipse/DrawingTool/IconsImage/EclipsIcon2.png"));
	    ecpIcon.setFitWidth(25);
	    ecpIcon.setFitHeight(18);
	    ImageView lineIcon=new ImageView(new Image("file:///G:/JP/Eclipse/DrawingTool/IconsImage/LineIcon.png"));
	    lineIcon.setFitWidth(15);
	    lineIcon.setFitHeight(18);
	    ImageView rondRecIcon=new ImageView(new Image("file:///G:/JP/Eclipse/DrawingTool/IconsImage/RoundRecIcon.png"));
	    rondRecIcon.setFitWidth(25);
	    rondRecIcon.setFitHeight(18);
	    ImageView textIcon=new ImageView(new Image("file:///G:/JP/Eclipse/DrawingTool/IconsImage/TextIcon2.png"));
	    textIcon.setFitWidth(15);
	    textIcon.setFitHeight(18);
	    ImageView clearIcon=new ImageView(new Image("file:///G:/JP/Eclipse/DrawingTool/IconsImage/ClearIcon3.png"));
	    clearIcon.setFitWidth(18);
	    clearIcon.setFitHeight(18);
	    ImageView aboutIcon=new ImageView(new Image("file:///G:/JP/Eclipse/DrawingTool/IconsImage/AboutIcon.png"));
	    aboutIcon.setFitWidth(18);
	    aboutIcon.setFitHeight(18);
	    New=new MenuItem("New",newIcon);
		Exit=new MenuItem("Exit",exitIcon);
		Cir=new RadioMenuItem("Circle",ecpIcon);
		Rec=new RadioMenuItem("Rectangle",recIcon);
		RondRec=new RadioMenuItem("Rounded Rectangle",rondRecIcon);
		Line=new RadioMenuItem("Line",lineIcon);
		about=new MenuItem("About",aboutIcon);
		allClear=new MenuItem("Clear",clearIcon);
		Eraser=new RadioMenuItem("Eraser",eraserIcon);
		Text=new RadioMenuItem("Text",textIcon);
		//add in toggle group
		ToggleGroup ShapeMenu=new ToggleGroup();
		Cir.setToggleGroup(ShapeMenu);
		Rec.setToggleGroup(ShapeMenu);
		RondRec.setToggleGroup(ShapeMenu);
		Line.setToggleGroup(ShapeMenu);
		Text.setToggleGroup(ShapeMenu);
		Eraser.setToggleGroup(ShapeMenu);
		//add menu items to their menu
		file.getItems().addAll(New,Exit);
		Shape.getItems().addAll(Text,Line,Cir,Rec,RondRec);
		About.getItems().addAll(about);
		Clear.getItems().addAll(Eraser,allClear);
		//add menu to menubar
		menuBar.getMenus().addAll(file,Shape,Clear,About);		
		
		//color pallets
		line=new Label("Line Color");
		fill=new Label("Fill Color");
		ColorPicker shapeColor=new ColorPicker(Color.TRANSPARENT);
		shapeColor.setMaxWidth(40);
		ColorPicker strokeColor=new ColorPicker(Color.BLACK);
		strokeColor.setMaxWidth(40);
		HBox shapeFill=new HBox(35);
		shapeFill.getChildren().addAll(fill,shapeColor);
		HBox shapeLine=new HBox(28);
		shapeLine.getChildren().addAll(line,strokeColor);
				
		//Stroke Size
		stSize=new Label("Stroke Size");
		stSizeField=new TextField();
		stSizeField.setText("1");
		stSizeField.setMaxWidth(40);
		HBox strokeSize=new HBox(23);
		strokeSize.getChildren().addAll(stSize,stSizeField);
		
		//vBox
		Separator shapeSep=new Separator(Orientation.HORIZONTAL);
		shapeSep.setStyle("-fx-background-color: darkgrey");
		Separator textSep=new Separator(Orientation.HORIZONTAL);
		textSep.setStyle("-fx-background-color: darkgrey");		
		
		//Text
		text=new Label("Text");
		textColor=new Label("Font Color");
		textFont=new Label("Font Style");
		textSize=new Label("Font Size");
		fStrokeColor=new Label("Stroke Color");
		textField=new TextField();
		textField.setMaxWidth(122);
		fontSize=new TextField();
		fontSize.setText("12");
		fontSize.setMaxWidth(40);
		fontColor=new ColorPicker(Color.BLACK);
		fontColor.setMaxWidth(40);
		fontStrokeColor=new ColorPicker(Color.TRANSPARENT);
		fontStrokeColor.setMaxWidth(40);
		fontStyle=new ComboBox<String>();
		fontStyle.setVisibleRowCount(5);
		fontStyle.setValue("Arial");
		fontStyle.setMaxWidth(122);
		fontStyle.getItems().addAll("Algerian","Arial","Bell MT","Bodoni MT","Calibri","Cambria","Candara","Century","Comic Sans MS",
				"Consolas","Gabriola","Dialog","Elephant","Euclid","Freestyle Script","Georgia","Impact","Lucida Console","Maiandra GD",
				"Malgun Gothic","Microsoft Sans Serif","Onyx","Stencil","Times New Roman","Verdana");
		HBox textColorBox=new HBox(25);
		textColorBox.getChildren().addAll(textColor,fontColor);
		HBox textSizeBox=new HBox(32);
		textSizeBox.getChildren().addAll(textSize,fontSize);
		HBox textStrokeBox=new HBox(15);
		textStrokeBox.getChildren().addAll(fStrokeColor,fontStrokeColor);
		
		//Radio Buttons 
		fStyle=new Label("Font Posture");
		ToggleGroup Style=new ToggleGroup();
		italic=new RadioButton("Italic");
		regular=new RadioButton("Regular");
		italic.setToggleGroup(Style);
		regular.setToggleGroup(Style);
		regular.setSelected(true);
		
		//Radio Button font weight
		fWeight=new Label("Font Weight");
		ToggleGroup Weight=new ToggleGroup();
		bold=new RadioButton("Bold");
		normal=new RadioButton("Normal");
		bold.setToggleGroup(Weight);
		normal.setToggleGroup(Weight);
		normal.setSelected(true);
		
		//Radio Button line setting
		sLine=new Label("Line");
		ToggleGroup shLine=new ToggleGroup();
		solidLine=new RadioButton("Solid");
		dotLine=new RadioButton("Doted");
		solidLine.setToggleGroup(shLine);
		dotLine.setToggleGroup(shLine);
		solidLine.setSelected(true);
		
		//Eraser Size Slider
		eSize=new Label("Eraser Size");
		eraserSlider=new Slider(10,50, 10);
		eraserSlider.setMajorTickUnit(10);
		eraserSlider.setMinorTickCount(1);
		eraserSlider.setShowTickLabels(true);
		eraserSlider.setShowTickMarks(true);
				
		VBox shapeBox=new VBox(shapeFill,shapeLine,strokeSize,sLine,solidLine,dotLine,shapeSep,text,textField,textColorBox,textStrokeBox,textSizeBox,textFont,fontStyle,fStyle,regular,italic,fWeight,normal,bold,textSep,eSize,eraserSlider);
		shapeBox.setSpacing(5);		
		
		//add items to toolbar
		toolBar.getItems().add(shapeBox);
		//create canvas
		canva=new Canvas();
		canva.setDisable(true);
		gc=canva.getGraphicsContext2D();
		pane=new Pane();
		pane.getChildren().add(canva);
		
		New.setOnAction(event-> {
			Stage newWin=new Stage();
			Label cHeight=new Label("Height");
			Label cWidth=new Label("Width");
			TextField Width=new TextField();
			Width.setMaxWidth(100);
			TextField Height=new TextField();
			Height.setMaxWidth(100);
			HBox heightBox=new HBox(cHeight,Height);
			heightBox.setSpacing(20);
			HBox widthBox=new HBox(cWidth,Width);
			widthBox.setSpacing(23);
			Button create=new Button("Create");
			create.setMaxWidth(100);
			Button cancel=new Button("Cancel");
			cancel.setMaxWidth(100);
			
			create.setOnAction(e->{
				if(Integer.parseInt(Width.getText())>625 || Integer.parseInt(Height.getText())>600) {
					Alert error=new Alert(AlertType.ERROR);
					error.setHeaderText("Canav Size Error");
					error.setContentText("Canvas width should be less than or equal to 625 and height should be less than or equal to 600");
					error.showAndWait();
				}
				else {
					canva.setDisable(false);
					pane.setMaxSize(625, 600);
					canva.setWidth(Integer.parseInt(Width.getText()));
					canva.setHeight(Integer.parseInt(Height.getText()));
					gc.setFill(Color.WHITE);
					gc.fillRect(0, 0, Integer.parseInt(Width.getText()), Integer.parseInt(Height.getText()));
					newWin.close();
				}
			});
			cancel.setOnAction(e->{
				stage.close();
			});
			newWin.initModality(Modality.WINDOW_MODAL);
			GridPane gp=new GridPane();
			gp.add(heightBox, 0, 0);
			gp.add(widthBox, 0, 1);
			gp.add(create, 0, 2);
			gp.add(cancel, 0, 3);
			GridPane.setHalignment(create, HPos.CENTER);
			GridPane.setHalignment(cancel, HPos.CENTER);
			gp.setAlignment(Pos.CENTER);
			gp.setVgap(10);
			Scene newScene=new Scene(gp,250,200);
			newWin.initOwner(stage);
			newWin.setTitle("New Canvas");
			newWin.setScene(newScene);
			newWin.show();			
		});
		Exit.setOnAction(event-> {
			stage.close();
		});
		
		Ellipse circle=new Ellipse();
		Rectangle rec=new Rectangle();
		Rectangle rondRec=new Rectangle();
		Line line=new Line();
		Rectangle eraser=new Rectangle();
				
		canva.setOnMousePressed(e->{
			if(Text.isSelected()) {
				if(regular.isSelected()&&normal.isSelected())
					gc.setFont(Font.font(fontStyle.getSelectionModel().getSelectedItem().toString(),FontWeight.NORMAL,FontPosture.REGULAR,Integer.parseInt(fontSize.getText())));
				if(italic.isSelected()&&normal.isSelected())
					gc.setFont(Font.font(fontStyle.getSelectionModel().getSelectedItem().toString(),FontWeight.NORMAL,FontPosture.ITALIC,Integer.parseInt(fontSize.getText())));
				if(regular.isSelected()&&bold.isSelected())
					gc.setFont(Font.font(fontStyle.getSelectionModel().getSelectedItem().toString(),FontWeight.BOLD,FontPosture.REGULAR,Integer.parseInt(fontSize.getText())));
				if(italic.isSelected()&&bold.isSelected())
					gc.setFont(Font.font(fontStyle.getSelectionModel().getSelectedItem().toString(),FontWeight.BOLD,FontPosture.ITALIC,Integer.parseInt(fontSize.getText())));
				gc.setLineDashes(null);
				gc.setFill(fontColor.getValue());
				gc.setStroke(fontStrokeColor.getValue());
				gc.fillText(textField.getText(), e.getX(), e.getY());
				gc.strokeText(textField.getText(), e.getX(), e.getY());
			}
			else if(Cir.isSelected()) {
				if(dotLine.isSelected()) {
					gc.setLineDashes(10,10);
				}
				if(solidLine.isSelected()) {
					gc.setLineDashes(null);
				}
				gc.setLineWidth(Double.parseDouble(stSizeField.getText()));
				gc.setStroke(strokeColor.getValue());
				gc.setFill(shapeColor.getValue());
				circle.setStrokeWidth(Integer.parseInt(stSizeField.getText()));
				circle.setStroke(strokeColor.getValue());
				circle.setFill(shapeColor.getValue());
				xStrt=e.getX();
				yStrt=e.getY();
				circle.setCenterX(xStrt);
				circle.setCenterY(yStrt);
				circle.setRadiusX(0);
				circle.setRadiusY(0);
				pane.getChildren().add(circle);
			}
			else if(Rec.isSelected()) {
				if(dotLine.isSelected())
					gc.setLineDashes(10,10);
				if(solidLine.isSelected())
					gc.setLineDashes(null);
				gc.setLineWidth(Integer.parseInt(stSizeField.getText()));
				gc.setStroke(strokeColor.getValue());
				gc.setFill(shapeColor.getValue());
				rec.setStrokeWidth(Integer.parseInt(stSizeField.getText()));
				rec.setStroke(strokeColor.getValue());
				rec.setFill(shapeColor.getValue());
				xStrt=e.getX();
				yStrt=e.getY();
				rec.setX(xStrt);
				rec.setY(yStrt);
				rec.setWidth(0);
				rec.setHeight(0);
				pane.getChildren().add(rec);
			}
			else if(RondRec.isSelected()) {
				if(dotLine.isSelected())
					gc.setLineDashes(10,10);
				if(solidLine.isSelected())
					gc.setLineDashes(null);
				gc.setLineWidth(Integer.parseInt(stSizeField.getText()));
				rondRec.setStrokeWidth(Integer.parseInt(stSizeField.getText()));
				gc.setStroke(strokeColor.getValue());
				gc.setFill(shapeColor.getValue());
				rondRec.setStroke(strokeColor.getValue());
				rondRec.setFill(shapeColor.getValue());
				rondRec.setArcHeight(30);
				rondRec.setArcWidth(30);
				xStrt=e.getX();
				yStrt=e.getY();
				rondRec.setX(xStrt);
				rondRec.setY(yStrt);
				rondRec.setWidth(0);
				rondRec.setHeight(0);
				pane.getChildren().add(rondRec);
			}
			else if(Line.isSelected()) {
				if(dotLine.isSelected())
					gc.setLineDashes(10,10);
				if(solidLine.isSelected())
					gc.setLineDashes(null);
				gc.setLineWidth(Integer.parseInt(stSizeField.getText()));
				line.setStrokeWidth(Integer.parseInt(stSizeField.getText()));
				gc.setStroke(strokeColor.getValue());
				line.setStroke(strokeColor.getValue());
				xStrt=e.getX();
				yStrt=e.getY();
				line.setStartX(xStrt);
				line.setStartY(yStrt);
				line.setEndX(e.getX());
				line.setEndY(e.getY());
				pane.getChildren().add(line);
			}
			else if(Eraser.isSelected()) {
				
				gc.setStroke(Color.WHITE);
				gc.setFill(Color.WHITE);
				eraser.setStroke(Color.WHITE);
				eraser.setFill(Color.WHITE);
				eraser.setWidth(eraserSlider.getValue());
				eraser.setHeight(eraserSlider.getValue());
				eraser.setX(e.getX()-eraser.getWidth()/2);
				eraser.setY(e.getY()-eraser.getHeight()/2);
				gc.fillRect(eraser.getX(), eraser.getY(), eraser.getWidth(), eraser.getHeight());//min to draw in different quadrant
				gc.strokeRect(eraser.getX(), eraser.getY(), eraser.getWidth(), eraser.getHeight());
				pane.getChildren().add(eraser);
			}
		});
		canva.setOnMouseDragged(e->{
			if(Cir.isSelected()) {
				circle.setCenterX((e.getX()+xStrt)/2);
				circle.setCenterY((e.getY()+yStrt)/2);
				circle.setRadiusX(Math.abs((e.getX()-xStrt)/2));
				circle.setRadiusY(Math.abs((e.getY()-yStrt)/2));
			}
			else if(Rec.isSelected()) {
				rec.setX(Math.min(xStrt, e.getX()));//to display rectangle
				rec.setY(Math.min(yStrt, e.getY()));
				rec.setWidth(Math.abs(e.getX()-xStrt));
				rec.setHeight(Math.abs(e.getY()-yStrt));
			}
			else if(RondRec.isSelected()) {
				rondRec.setX(Math.min(xStrt, e.getX()));//to display rectangle
				rondRec.setY(Math.min(yStrt, e.getY()));
				rondRec.setWidth(Math.abs(e.getX()-xStrt));
				rondRec.setHeight(Math.abs(e.getY()-yStrt));
			}
			else if(Line.isSelected()) {
				line.setStartX(xStrt);
				line.setStartY(yStrt);
				line.setEndX(e.getX());
				line.setEndY(e.getY());
			}
			else if(Eraser.isSelected()) {
				eraser.setWidth(eraserSlider.getValue());
				eraser.setHeight(eraserSlider.getValue());
				eraser.setX(e.getX()-eraser.getWidth()/2);
				eraser.setY(e.getY()-eraser.getHeight()/2);
				gc.fillRect(eraser.getX(), eraser.getY(), eraser.getWidth(), eraser.getHeight());//min to draw in different quadrant
				gc.strokeRect(eraser.getX(), eraser.getY(), eraser.getWidth(), eraser.getHeight());
			}
		});
		canva.setOnMouseReleased(e->{
			if(Cir.isSelected()) {
				pane.getChildren().remove(circle);
				gc.fillOval(Math.min(xStrt, e.getX()), Math.min(yStrt, e.getY()), Math.abs(e.getX() - xStrt), Math.abs(e.getY() - yStrt));//min to draw in different quadrant
				gc.strokeOval(Math.min(xStrt, e.getX()), Math.min(yStrt, e.getY()), Math.abs(e.getX() - xStrt), Math.abs(e.getY() - yStrt));
			}
			else if(Rec.isSelected()) {
				pane.getChildren().remove(rec);
				//to display on graphic context/canvas
				gc.fillRect(Math.min(xStrt, e.getX()), Math.min(yStrt, e.getY()), Math.abs(e.getX()-xStrt), Math.abs(e.getY()-yStrt));//min to draw in different quadrant
				gc.strokeRect(Math.min(xStrt, e.getX()), Math.min(yStrt, e.getY()), Math.abs(e.getX()-xStrt), Math.abs(e.getY()-yStrt));
			}
			else if(RondRec.isSelected()) {
				pane.getChildren().remove(rondRec);
				//to display on graphic context/canvas
				gc.fillRoundRect(Math.min(xStrt, e.getX()), Math.min(yStrt, e.getY()), Math.abs(e.getX()-xStrt), Math.abs(e.getY()-yStrt), 30, 30);
				gc.strokeRoundRect(Math.min(xStrt, e.getX()), Math.min(yStrt, e.getY()), Math.abs(e.getX()-xStrt), Math.abs(e.getY()-yStrt), 30, 30);
			}
			else if(Line.isSelected()) {
				pane.getChildren().remove(line);
				//to display on graphic context/canvas
				gc.strokeLine(xStrt, yStrt, e.getX(), e.getY());
			}
			else if(Eraser.isSelected()) {
				pane.getChildren().remove(eraser);
				eraser.setWidth(eraserSlider.getValue());
				eraser.setHeight(eraserSlider.getValue());
				eraser.setX(e.getX()-eraser.getWidth()/2);
				eraser.setY(e.getY()-eraser.getHeight()/2);
				gc.fillRect(eraser.getX(), eraser.getY(), eraser.getWidth(), eraser.getHeight());//min to draw in different quadrant
				gc.strokeRect(eraser.getX(), eraser.getY(), eraser.getWidth(), eraser.getHeight());
			}
		});
		about.setOnAction(event-> {
			Alert msg=new Alert(AlertType.INFORMATION);
			msg.setTitle("About");
			msg.setHeaderText("About Drawing Tool");
			msg.setContentText("This application have the features to draw basic shapes e.g. Line, Circle, Rectangle and Rounded Rectangle."
					+ "This application have variety of color range and allow user to customize shape color and shape stroke color according to their need."
					+ "This application allow user to display text on canvas and customize its color, font, weight, posture and size."
					+ "This small application have an Eraser tool you can erase your drawing from it.");
			msg.showAndWait();
		});
		allClear.setOnAction(e-> {
			gc.setFill(Color.WHITE);
			gc.fillRect(0, 0, canva.getWidth(), canva.getHeight());
		});
		
		bp.setLeft(toolBar);
		bp.setCenter(pane);
		bp.setTop(menuBar);
		stage.setScene(scene);
		stage.setTitle("Drawing Tool");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
