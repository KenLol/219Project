/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FantasyBaseball;

import csb.CSB_PropertyType;
import static csb.CSB_StartupConstants.CLOSE_BUTTON_LABEL;
import static csb.CSB_StartupConstants.PATH_CSS;
import static csb.CSB_StartupConstants.PATH_IMAGES;
import csb.gui.MessageDialog;
import csb.gui.ProgressDialog;
import csb.gui.YesNoCancelDialog;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Kenneth
 */
public class FantasyGUI {
    static final String PRIMARY_STYLE_SHEET = PATH_CSS + "csb_style.css";
    static final String CLASS_BORDERED_PANE = "bordered_pane";
    static final String CLASS_SUBJECT_PANE = "subject_pane";
    static final String CLASS_HEADING_LABEL = "heading_label";
    static final String CLASS_SUBHEADING_LABEL = "subheading_label";
    static final String CLASS_PROMPT_LABEL = "prompt_label";
    static final String EMPTY_TEXT = "";
    static final int LARGE_TEXT_FIELD_LENGTH = 20;
    static final int SMALL_TEXT_FIELD_LENGTH = 5;
    
    JsonDraftFileManager jcfm = new JsonDraftFileManager();
    
    Stage primaryStage;
    Scene primaryScene;
    
    FlowPane fileToolbarPane;
    FlowPane botToolbarPane;
    Button newCourseButton;
    Button loadCourseButton;
    Button saveCourseButton;
    Button exportSiteButton;
    Button exitButton;
    
    BorderPane workspacePane;
    boolean workspaceActivated;
    
    // HERE ARE OUR DIALOGS
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    ProgressDialog progressDialog;
    
    private BorderPane csbPane;
    SplitPane topWorkspaceSplitPane;
    private Pane topWorkspacePane;
    private Pane botWorkSpacePane;
    private Label courseHeadingLabel;
    private ScrollPane workspaceScrollPane;
    private FantasyFileController FantasyFileController;
    
    /**
     * Constructor for making this GUI, note that it does not initialize the UI
     * controls. To do that, call initGUI.
     *
     * @param initPrimaryStage Window inside which the GUI will be displayed.
     */
    public FantasyGUI(Stage initPrimaryStage) {
        primaryStage = initPrimaryStage;
    }
    
    
     /**
     * Accessor method for the window (i.e. stage).
     *
     * @return The window (i.e. Stage) used by this UI.
     */
    public Stage getWindow() {
        return primaryStage;
    }
    
    public MessageDialog getMessageDialog() {
        return messageDialog;
    }
    
    public YesNoCancelDialog getYesNoCancelDialog() {
        return yesNoCancelDialog;
    }
    
    /**
     * This method fully initializes the user interface for use.
     *
     * @param windowTitle The text to appear in the UI window's title bar.
     * @param subjects The list of subjects to choose from.
     * @throws IOException Thrown if any initialization files fail to load.
     */
    public void initGUI(String windowTitle) throws IOException {
        // INIT THE DIALOGS
        initDialogs();
        
        // INIT THE TOOLBAR
        initFileToolbar();


        initWorkspace();

        // NOW SETUP THE EVENT HANDLERS
        initEventHandlers();

        // AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
        initWindow(windowTitle);
    }
    
    private void initDialogs() {
        messageDialog = new MessageDialog(primaryStage, CLOSE_BUTTON_LABEL);
        yesNoCancelDialog = new YesNoCancelDialog(primaryStage);
        progressDialog = new ProgressDialog(primaryStage, "Preparing");
    }
    
    // CREATES AND SETS UP ALL THE CONTROLS TO GO IN THE APP WORKSPACE
    private void initWorkspace() throws IOException {
        
        //topWorkspaceSplitPane = new SplitPane();
     

        topWorkspacePane = new VBox();
        topWorkspacePane.getStyleClass().add(CLASS_BORDERED_PANE);

        // HERE'S THE LABEL
        courseHeadingLabel = initChildLabel(topWorkspacePane, CSB_PropertyType.COURSE_HEADING_LABEL, CLASS_HEADING_LABEL);

        // AND NOW ADD THE SPLIT PANE
        //topWorkspacePane.getChildren().add(topWorkspaceSplitPane);

        // THIS IS FOR MANAGING SCHEDULE EDITING
        //initScheduleItemsControls();

        // THIS HOLDS ALL OUR WORKSPACE COMPONENTS, SO NOW WE MUST
        // ADD THE COMPONENTS WE'VE JUST INITIALIZED
        workspacePane = new BorderPane();
        workspacePane.setTop(topWorkspacePane);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        
        botToolbarPane = new FlowPane();
        
        //newCourseButton = initChildButton(botToolbarPane, CSB_PropertyType.NEW_COURSE_ICON, CSB_PropertyType.NEW_COURSE_TOOLTIP, false);
        loadCourseButton = initChildButton(botToolbarPane, CSB_PropertyType.LOAD_COURSE_ICON, CSB_PropertyType.LOAD_COURSE_TOOLTIP, false);
        //saveCourseButton = initChildButton(botToolbarPane, CSB_PropertyType.SAVE_COURSE_ICON, CSB_PropertyType.SAVE_COURSE_TOOLTIP, false);
        //exportSiteButton = initChildButton(botToolbarPane, CSB_PropertyType.EXPORT_PAGE_ICON, CSB_PropertyType.EXPORT_PAGE_TOOLTIP, false);
        //exitButton = initChildButton(botToolbarPane, CSB_PropertyType.EXIT_ICON, CSB_PropertyType.EXIT_TOOLTIP, false);
        
        
        topWorkspacePane.getChildren().add(botToolbarPane);
        
        
        // AND NOW PUT IT IN THE WORKSPACE
        workspaceScrollPane = new ScrollPane();
        //workspaceScrollPane.getStyleClass().add(CLASS_BORDERED_PANE);
        workspaceScrollPane.setContent(workspacePane);
        workspaceScrollPane.setFitToWidth(true);
        workspaceScrollPane.setFitToHeight(true);

        
        workspaceActivated = false;
    }
    
    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    private void initFileToolbar() {
        fileToolbarPane = new FlowPane();

        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        newCourseButton = initChildButton(fileToolbarPane, CSB_PropertyType.NEW_COURSE_ICON, CSB_PropertyType.NEW_COURSE_TOOLTIP, false);
        loadCourseButton = initChildButton(fileToolbarPane, CSB_PropertyType.LOAD_COURSE_ICON, CSB_PropertyType.LOAD_COURSE_TOOLTIP, false);
        saveCourseButton = initChildButton(fileToolbarPane, CSB_PropertyType.SAVE_COURSE_ICON, CSB_PropertyType.SAVE_COURSE_TOOLTIP, true);
        exportSiteButton = initChildButton(fileToolbarPane, CSB_PropertyType.EXPORT_PAGE_ICON, CSB_PropertyType.EXPORT_PAGE_TOOLTIP, true);
        exitButton = initChildButton(fileToolbarPane, CSB_PropertyType.EXIT_ICON, CSB_PropertyType.EXIT_TOOLTIP, false);
    }
    
    // INITIALIZE THE WINDOW (i.e. STAGE) PUTTING ALL THE CONTROLS
    // THERE EXCEPT THE WORKSPACE, WHICH WILL BE ADDED THE FIRST
    // TIME A NEW Course IS CREATED OR LOADED
    private void initWindow(String windowTitle) {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // ADD THE TOOLBAR ONLY, NOTE THAT THE WORKSPACE
        // HAS BEEN CONSTRUCTED, BUT WON'T BE ADDED UNTIL
        // THE USER STARTS EDITING A COURSE
        csbPane = new BorderPane();
        csbPane.setTop(fileToolbarPane);
        primaryScene = new Scene(csbPane);

        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        primaryScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
    
     // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
    private Button initChildButton(Pane toolbar, CSB_PropertyType icon, CSB_PropertyType tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }
    
    // INIT A LABEL AND SET IT'S STYLESHEET CLASS
    private Label initLabel(CSB_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }
    
     // INIT A LABEL AND PLACE IT IN A GridPane INIT ITS PROPER PLACE
    private Label initGridLabel(GridPane container, CSB_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }

    // INIT A LABEL AND PUT IT IN A TOOLBAR
    private Label initChildLabel(Pane container, CSB_PropertyType labelProperty, String styleClass) {
        Label label = initLabel(labelProperty, styleClass);
        container.getChildren().add(label);
        return label;
    }

     // INIT A TEXT FIELD AND PUT IT IN A GridPane
    private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }
    
    
    private void initEventHandlers() throws IOException {
        newCourseButton.setOnAction(e -> {
            FantasyFileController = new FantasyFileController(messageDialog, yesNoCancelDialog, progressDialog);
            FantasyFileController.handleNewCourseRequest(this);
        });
        
        exitButton.setOnAction(e -> {
            //FantasyFileController.handleExitRequest(this);
            System.exit(0);
        });
        
    }
    
    // REGISTER THE EVENT LISTENER FOR A TEXT FIELD
    private void registerTextFieldController(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
           // courseController.handleCourseChangeRequest(this);
        });
    }
    
     public void updateToolbarControls(boolean saved) {
        // THIS TOGGLES WITH WHETHER THE CURRENT COURSE
        // HAS BEEN SAVED OR NOT
        saveCourseButton.setDisable(saved);

        // ALL THE OTHER BUTTONS ARE ALWAYS ENABLED
        // ONCE EDITING THAT FIRST COURSE BEGINS
        loadCourseButton.setDisable(false);
        exportSiteButton.setDisable(false);

        // NOTE THAT THE NEW, LOAD, AND EXIT BUTTONS
        // ARE NEVER DISABLED SO WE NEVER HAVE TO TOUCH THEM
    }
    
    

    
     public void activateWorkspace() {
        if (!workspaceActivated) {
            // PUT THE WORKSPACE IN THE GUI
            csbPane.setCenter(workspaceScrollPane);
            workspaceActivated = true;
        }
    }
     
    public void reload(FantasyDraft draftToReload){
        if (!workspaceActivated) {
            activateWorkspace();
        }
    }
}