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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    static final String Line = "################################################################################################################################################################";
    private String b;
    
    JsonDraftFileManager jcfm = new JsonDraftFileManager();
    
    
    private Stage primaryStage;
    private Scene primaryScene;
    
    private FlowPane fileToolbarPane;
    private FlowPane botToolbarPane;
    private Button newCourseButton;
    private Button loadCourseButton;
    private Button saveCourseButton;
    private Button exportSiteButton;
    private Button exitButton;
    
    
    private HBox hbox;
    private VBox vbox;
    private Label searchLabel;
    private Button addButton;
    private Button removeButton;
    private TextField searchField;
    
    private ToggleGroup group = new ToggleGroup();
    private RadioButton rb1 = new RadioButton("ALL");
    private RadioButton rb2 = new RadioButton("C");
    private RadioButton rb3 = new RadioButton("1B");
    private RadioButton rb4 = new RadioButton("CI");
    private RadioButton rb5 = new RadioButton("3B");
    private RadioButton rb6 = new RadioButton("2B");
    private RadioButton rb7 = new RadioButton("MI");
    private RadioButton rb8 = new RadioButton("SS");
    private RadioButton rb9 = new RadioButton("OF");
    private RadioButton rb10 = new RadioButton("U");
    private RadioButton rb11 = new RadioButton("P");
    
    
    
    
    
    
    
    
    TableView<Superplayer> playerTable;
    TableColumn firstColumn;
    TableColumn lastColumn;
    TableColumn proteamColumn;
    TableColumn positionsColumn;
    TableColumn yearOfBirthColumn;
    TableColumn rwColumn;
    TableColumn hrsvColumn;
    TableColumn rbikColumn;
    TableColumn sberaColumn;
    TableColumn bawhipColumn;
    TableColumn estimatedValueColumn;
    TableColumn notesColumn;
    
    BorderPane workspacePane;
    boolean workspaceActivated;
    
    // HERE ARE OUR DIALOGS
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    ProgressDialog progressDialog;
    
    private BorderPane csbPane;
    private SplitPane topWorkspaceSplitPane;
    private ScrollPane myPane;
    private Pane topWorkspacePane;
    private Pane botWorkSpacePane;
    private Label courseHeadingLabel;
    private ScrollPane workspaceScrollPane;
    private ScrollPane Pane1;
    private ScrollPane Pane2;
    private ScrollPane Pane3;
    private ScrollPane Pane4;
    private ScrollPane Pane5;
    private FantasyFileController FantasyFileController;
    private Button FantasyTeamButton;
    private Button AvailablePlayersButton;
    private Button FantasyStandingsButton;
    private Button DraftSummaryButton;
    private Button MLBTeamsButton;
    private Label courseInfoLabel;
    
    private GridPane letsgo;
    private HBox hbox2;
    
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

        ///////////////////////////////////////////////////////////////////////////////
        //topWorkspacePane.getChildren().add(topWorkspaceSplitPane);
///////////////////////////////////////////////////////////////////////////////////////////////////
        // THIS IS FOR MANAGING SCHEDULE EDITING
        //initScheduleItemsControls();

        // THIS HOLDS ALL OUR WORKSPACE COMPONENTS, SO NOW WE MUST
        // ADD THE COMPONENTS WE'VE JUST INITIALIZED
        workspacePane = new BorderPane();
        //workspacePane.setTop(topWorkspacePane); //cant break it
        workspacePane.setCenter(topWorkspacePane);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        
        
        botToolbarPane = new FlowPane();
        
        FantasyTeamButton = initChildButton(botToolbarPane, CSB_PropertyType.FANTASY_TEAM_ICON, CSB_PropertyType.FANTASY_TEAM_TOOLTIP, false);
        AvailablePlayersButton = initChildButton(botToolbarPane, CSB_PropertyType.AVAILABLE_PLAYERS_ICON, CSB_PropertyType.AVAILABLE_PLAYERS_TOOLTIP, false);
        FantasyStandingsButton = initChildButton(botToolbarPane, CSB_PropertyType.FANTASY_STANDINGS_ICON, CSB_PropertyType.FANTASY_STANDINGS_TOOLTIP, false);
        DraftSummaryButton = initChildButton(botToolbarPane, CSB_PropertyType.DRAFT_SUMMARY_ICON, CSB_PropertyType.DRAFT_SUMMARY_TOOLTIP, false);
        MLBTeamsButton = initChildButton(botToolbarPane, CSB_PropertyType.MLB_TEAMS_ICON, CSB_PropertyType.MLB_TEAMS_TOOLTIP, false);
        
        myPane = new ScrollPane();
        myPane.setFitToHeight(true);
        myPane.setFitToWidth(true);
       
        
        topWorkspacePane.getChildren().add(myPane);
        
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
    private void initWindow(String windowTitle) throws IOException {
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
        jcfm.loadALL();
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
        FantasyFileController = new FantasyFileController(messageDialog, yesNoCancelDialog, progressDialog);
        newCourseButton.setOnAction(e -> {
            FantasyFileController.handleNewCourseRequest(this);
        });
        
        exitButton.setOnAction(e -> {
            //FantasyFileController.handleExitRequest(this);
            System.exit(0);
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        FantasyTeamButton.setOnAction(e -> {
            FantasyFileController.handleFantasyTeamRequest(this);
        });
        
        AvailablePlayersButton.setOnAction(e -> {
            FantasyFileController.handleAvailablePlayersRequest(this);
        
        });
        
        FantasyStandingsButton.setOnAction(e -> {
           FantasyFileController.handleFantasyStandingsRequest(this);
        });
        
        DraftSummaryButton.setOnAction(e -> {
           FantasyFileController.handleDraftSummaryRequest(this);
        });
        
        MLBTeamsButton.setOnAction(e -> {
           FantasyFileController.handleMLBTeamsRequest(this);
        });
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle){
                if (group.getSelectedToggle() != null){
                    String z = "1";
                    try{
                        z = group.getSelectedToggle().getUserData().toString();
                                }catch (NullPointerException e){
                        
                    }
                        
                    
                   
                   //System.out.println(z);
                    
                    int a = Integer.parseInt(z);
                    
                    
                    if(b == null || b == ""){
                    //    System.out.println(b);
                    if(a == 1){
                        
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        
                        jcfm.loadA();
                        display();
                        
                    }
                    if(a == 2){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadC();
                        display();
                        
                    }
                    if(a == 3){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.load1B();
                        display();
                        
                    }
                    if(a == 4){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadCI();
                        display();
                        
                    }
                    if(a == 5){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.load3B();
                        display();
                        
                    }
                    if(a == 6){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.load2B();
                       display();
                        
                    }
                    if(a == 7){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadMI();
                        display();
                        
                    }
                    if(a == 8){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadSS();
                        display();
                        
                    }
                    if(a == 9){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadOF();
                       display();
                        
                    }
                    if(a == 10){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadU();
                        display();
                        
                    }
                    if(a == 11){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadP();
                        display();
                        
                    }
                    }else{
                     //   System.out.println(b);
                        if(a == 1){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadA();
                        jcfm.startWith(b);
                        displayz();
                        
                    }
                    if(a == 2){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadC();
                        jcfm.startWith(b);
                        displayz();
                        
                    }
                    if(a == 3){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.load1B();
                        jcfm.startWith(b);
                        displayz();
                        
                    }
                    if(a == 4){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadCI();
                        jcfm.startWith(b);
                        displayz();
                        
                    }
                    if(a == 5){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.load3B();
                        jcfm.startWith(b);
                        displayz();
                        
                    }
                    if(a == 6){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.load2B();
                        jcfm.startWith(b);
                        displayz();
                        
                    }
                    if(a == 7){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadMI();
                        jcfm.startWith(b);
                        displayz();
                        
                    }
                    if(a == 8){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadSS();
                        jcfm.startWith(b);
                        displayz();
                        
                    }
                    if(a == 9){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadOF();
                        jcfm.startWith(b);
                        displayz();
                        
                    }
                    if(a == 10){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadU();
                        jcfm.startWith(b);
                        displayz();
                        
                    }
                    if(a == 11){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadP();
                        jcfm.startWith(b);
                        displayz();
                        
                    }
                    }
                    
                    
                    //System.out.print(b);
                }
            }
        });
        
       // registerTextFieldController(searchField);
        
        
    }
    
    private void initsearchfield(){
        searchField.textProperty().addListener(new ChangeListener<String>() {
        @Override
             public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
             //System.out.println(newValue);
             
             String z = "1";
             try{
                        z = group.getSelectedToggle().getUserData().toString();
                                }catch (NullPointerException e){
                        
                    }
             b = newValue;
             int a = Integer.parseInt(z);
             //System.out.println(newValue);
                    if(a == 1){
                        
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadA();
                        jcfm.startWith(newValue);
                        displayz();
                        
                    }
                    if(a == 2){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadC();
                        jcfm.startWith(newValue);
                        displayz();
                        
                    }
                    if(a == 3){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.load1B();
                        jcfm.startWith(newValue);
                        displayz();
                        
                    }
                    if(a == 4){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadCI();
                        jcfm.startWith(newValue);
                        displayz();
                        
                    }
                    if(a == 5){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.load3B();
                        jcfm.startWith(newValue);
                        displayz();
                        
                    }
                    if(a == 6){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.load2B();
                        jcfm.startWith(newValue);
                        displayz();
                        
                    }
                    if(a == 7){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadMI();
                        jcfm.startWith(newValue);
                        displayz();
                    }
                    if(a == 8){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadSS();
                        jcfm.startWith(newValue);
                        displayz();
                        
                    }
                    if(a == 9){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadOF();
                        jcfm.startWith(newValue);
                        displayz();
                        
                    }
                    if(a == 10){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadU();
                        jcfm.startWith(newValue);
                        displayz();
                        
                    }
                    if(a == 11){
                        jcfm.emptyAll();
                        jcfm.emptyArr();
                        jcfm.loadP();
                        jcfm.startWith(newValue);
                        displayz();
                    }
    }
});
    }
    
    // REGISTER THE EVENT LISTENER FOR A TEXT FIELD
    private void registerTextFieldController(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue != newValue){
                //System.out.println("SUP");
            }
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
    
    public void FantasyTeam(){
        topWorkspacePane = new VBox();
        topWorkspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        

        // HERE'S THE LABEL
        courseHeadingLabel = initChildLabel(topWorkspacePane, CSB_PropertyType.COURSE_HEADING_LABEL, CLASS_HEADING_LABEL);

        workspacePane = new BorderPane();
        workspacePane.setCenter(topWorkspacePane);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        myPane = new ScrollPane();
        myPane.setFitToHeight(true);
        myPane.setFitToWidth(true);
       
        
        topWorkspacePane.getChildren().add(myPane);
        topWorkspacePane.getChildren().add(botToolbarPane);
        
        
        // AND NOW PUT IT IN THE WORKSPACE
        Pane1 = new ScrollPane();
        Pane1.setContent(workspacePane);
        Pane1.setFitToWidth(true);
        Pane1.setFitToHeight(true);

        workspaceActivated = false;
        
        csbPane.setCenter(Pane1); 
    }
    
    public void AvailablePlayers(){
       // System.out.println("Hi");
        
        topWorkspacePane = new VBox();
        topWorkspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        

        // HERE'S THE LABEL
        courseHeadingLabel = initChildLabel(topWorkspacePane, CSB_PropertyType.COURSE_INFO_LABEL, CLASS_HEADING_LABEL);

        ///////////////////////////////////////////////////////////////////////////////
        //topWorkspacePane.getChildren().add(topWorkspaceSplitPane);
///////////////////////////////////////////////////////////////////////////////////////////////////
        // THIS IS FOR MANAGING SCHEDULE EDITING
        //initScheduleItemsControls();

        // THIS HOLDS ALL OUR WORKSPACE COMPONENTS, SO NOW WE MUST
        // ADD THE COMPONENTS WE'VE JUST INITIALIZED
        workspacePane = new BorderPane();
        //workspacePane.setTop(topWorkspacePane); //cant break it
        workspacePane.setCenter(topWorkspacePane);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        
        
        myPane = new ScrollPane();
        myPane.setFitToHeight(true);
        myPane.setFitToWidth(true);
        
        letsgo = new GridPane();
        
        /*
        addButton = initChildButton(letsgo, CSB_PropertyType.ADD_ICON, CSB_PropertyType.ADD_ITEM_TOOLTIP, true);
        letsgo.add(addButton, 0, 0, 1, 1);
        removeButton = initChildButton(letsgo, CSB_PropertyType.MINUS_ICON, CSB_PropertyType.REMOVE_ITEM_TOOLTIP, true);
        letsgo.add(removeButton, 1, 0, 1, 1);
        searchField = initGridTextField(letsgo, SMALL_TEXT_FIELD_LENGTH, EMPTY_TEXT, true, 2, 0, 1, 1);
        
        myPane.setContent(letsgo);
        
        */
        vbox = new VBox();
        hbox = new HBox();
        //hbox.getStyleClass().add(CLASS_SUBJECT_PANE);
        hbox2 = new HBox();
        searchField = new TextField();
        
        addButton = initChildButton(hbox, CSB_PropertyType.ADD_ICON, CSB_PropertyType.ADD_ITEM_TOOLTIP, true);
        removeButton = initChildButton(hbox, CSB_PropertyType.MINUS_ICON, CSB_PropertyType.REMOVE_ITEM_TOOLTIP, true);
        searchLabel = initLabel(CSB_PropertyType.HWS_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        
        
        rb1.setToggleGroup(group);
        //rb1.setSelected(true);
        rb1.setUserData(1);
        rb2.setToggleGroup(group);
        rb2.setUserData(2);
        rb3.setToggleGroup(group);
        rb3.setUserData(3);
        rb4.setToggleGroup(group);
        rb4.setUserData(4);
        rb5.setToggleGroup(group);
        rb5.setUserData(5);
        rb6.setToggleGroup(group);
        rb6.setUserData(6);
        rb7.setToggleGroup(group);
        rb7.setUserData(7);
        rb8.setToggleGroup(group);
        rb8.setUserData(8);
        rb9.setToggleGroup(group);
        rb9.setUserData(9);
        rb10.setToggleGroup(group);
        rb10.setUserData(10);
        rb11.setToggleGroup(group);
        rb11.setUserData(11);
        
        hbox2.getChildren().add(rb1);
        hbox2.getChildren().add(rb2);
        hbox2.getChildren().add(rb3);
        hbox2.getChildren().add(rb4);
        hbox2.getChildren().add(rb5);
        hbox2.getChildren().add(rb6);
        hbox2.getChildren().add(rb7);
        hbox2.getChildren().add(rb8);
        hbox2.getChildren().add(rb9);
        hbox2.getChildren().add(rb10);
        hbox2.getChildren().add(rb11);
        hbox2.setSpacing(10);
        
        initsearchfield();
       /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        firstColumn = new TableColumn("First");
        lastColumn = new TableColumn("Last");
        proteamColumn = new TableColumn("Pro Team");
        positionsColumn = new TableColumn("Positions");
        yearOfBirthColumn = new TableColumn("Year of Birth");
        rwColumn = new TableColumn("R/W");
        hrsvColumn = new TableColumn("HR/SV");
        rbikColumn = new TableColumn("RBI/K");
        sberaColumn = new TableColumn("SB/ERA");
        bawhipColumn = new TableColumn("BA/WHIP");
        estimatedValueColumn = new TableColumn("Estimated Value");
        notesColumn = new TableColumn("Notes");
        
        
        
        firstColumn.setCellValueFactory(new PropertyValueFactory<String, String>("FIRST"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<String, String>("lAST"));
        proteamColumn.setCellValueFactory(new PropertyValueFactory<String, String>("proteam"));
        positionsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("position"));
        yearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("year"));
        rwColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("rw"));
        hrsvColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("hrsv"));
        rbikColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("rbik"));
        sberaColumn.setCellValueFactory(new PropertyValueFactory<Double, String>("sbera"));
        bawhipColumn.setCellValueFactory(new PropertyValueFactory<Double, String>("bawhip"));
        estimatedValueColumn.setCellValueFactory(new PropertyValueFactory<Double, String>("estimatedvalue"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<String, String>("note"));
        
        
        playerTable = new TableView();
        playerTable.getColumns().add(firstColumn);
        playerTable.getColumns().add(lastColumn);
        playerTable.getColumns().add(proteamColumn);
        playerTable.getColumns().add(positionsColumn);
        playerTable.getColumns().add(yearOfBirthColumn);
        playerTable.getColumns().add(rwColumn);
        playerTable.getColumns().add(hrsvColumn);
        playerTable.getColumns().add(rbikColumn);
        playerTable.getColumns().add(sberaColumn);
        playerTable.getColumns().add(bawhipColumn);
        playerTable.getColumns().add(estimatedValueColumn);
        playerTable.getColumns().add(notesColumn);
        
        //Player pla = new Pitcher("last", "first");
        //jcfm.addobp(pla);
        
        jcfm.emptyAll();
        jcfm.emptyArr();
        jcfm.loadA();
        
        for(Player p : jcfm.arr){
            Superplayer a = new Superplayer();
            a.setFIRST(p.getFirst());
            a.setLAST(p.getLast());
            a.setProteam(p.getProteam());
            a.setPosition(p.getPosition());
            a.setYear(p.getYear());
            a.setRw(p.getRW());
            a.setHrsv(p.getHRSV());
            a.setRbik(p.getRBIK());
            a.setSbera(p.getSBERA());
            a.setBawhip(p.getBAWHIP());
            a.setNote(p.getNote());
            jcfm.addobp(a);
        
        }
        
        
       
        playerTable.setItems(jcfm.getobp());
        //playerTable.
        
        
       
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        hbox.getChildren().add(searchLabel);
        hbox.getChildren().add(searchField);
        
        
       
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(hbox2);
        vbox.getChildren().add(playerTable);
        //hbox.getStyleClass().add(CLASS_BORDERED_PANE);
        //vbox.getStyleClass().add(CLASS_BORDERED_PANE);
        
        
        
        
        topWorkspacePane.getChildren().add(vbox);
        //topWorkspacePane.getChildren().add(letsgo);
        
        topWorkspacePane.getChildren().add(botToolbarPane);
        
        //FantasyTeamButton = initChildButton(botToolbarPane, CSB_PropertyType.FANTASY_TEAM_ICON, CSB_PropertyType.FANTASY_TEAM_TOOLTIP, false);
        
        
        // AND NOW PUT IT IN THE WORKSPACE
        Pane2 = new ScrollPane();
        //workspaceScrollPane.getStyleClass().add(CLASS_BORDERED_PANE);
        Pane2.setContent(workspacePane);
        Pane2.setFitToWidth(true);
        Pane2.setFitToHeight(true);
        
        
        csbPane.setCenter(Pane2);
        
        
    }
    
    public void FantasyStandings(){
        topWorkspacePane = new VBox();
        topWorkspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        

        // HERE'S THE LABEL
        courseHeadingLabel = initChildLabel(topWorkspacePane, CSB_PropertyType.COURSE_SUBJECT_LABEL, CLASS_HEADING_LABEL);

        workspacePane = new BorderPane();
        workspacePane.setCenter(topWorkspacePane);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        myPane = new ScrollPane();
        myPane.setFitToHeight(true);
        myPane.setFitToWidth(true);
       
        
        topWorkspacePane.getChildren().add(myPane);
        topWorkspacePane.getChildren().add(botToolbarPane);
        
        
        // AND NOW PUT IT IN THE WORKSPACE
        Pane3 = new ScrollPane();
        Pane3.setContent(workspacePane);
        Pane3.setFitToWidth(true);
        Pane3.setFitToHeight(true);

        workspaceActivated = false;
        
        csbPane.setCenter(Pane3); 
    }
    
    public void DraftSummary(){
        topWorkspacePane = new VBox();
        topWorkspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        

        // HERE'S THE LABEL
        courseHeadingLabel = initChildLabel(topWorkspacePane, CSB_PropertyType.COURSE_NUMBER_LABEL, CLASS_HEADING_LABEL);

        workspacePane = new BorderPane();
        workspacePane.setCenter(topWorkspacePane);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        myPane = new ScrollPane();
        myPane.setFitToHeight(true);
        myPane.setFitToWidth(true);
       
        
        topWorkspacePane.getChildren().add(myPane);
        topWorkspacePane.getChildren().add(botToolbarPane);
        
        
        // AND NOW PUT IT IN THE WORKSPACE
        Pane4 = new ScrollPane();
        Pane4.setContent(workspacePane);
        Pane4.setFitToWidth(true);
        Pane4.setFitToHeight(true);

        workspaceActivated = false;
        
        csbPane.setCenter(Pane4); 
    }
    
    public void MLBTeams(){
        topWorkspacePane = new VBox();
        topWorkspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        

        // HERE'S THE LABEL
        courseHeadingLabel = initChildLabel(topWorkspacePane, CSB_PropertyType.COURSE_SEMESTER_LABEL, CLASS_HEADING_LABEL);

        workspacePane = new BorderPane();
        workspacePane.setCenter(topWorkspacePane);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        myPane = new ScrollPane();
        myPane.setFitToHeight(true);
        myPane.setFitToWidth(true);
       
        
        topWorkspacePane.getChildren().add(myPane);
        topWorkspacePane.getChildren().add(botToolbarPane);
        
        
        // AND NOW PUT IT IN THE WORKSPACE
        Pane5 = new ScrollPane();
        Pane5.setContent(workspacePane);
        Pane5.setFitToWidth(true);
        Pane5.setFitToHeight(true);

        workspaceActivated = false;
        
        csbPane.setCenter(Pane5); 
    }
    
    
    public void display(){
        String z = "1";
             try{
                        z = group.getSelectedToggle().getUserData().toString();
                                }catch (NullPointerException e){
                        
                    }
        if(z.equals("1")){
        rwColumn.setText("R/W");
        hrsvColumn.setText("HR/SV");
        rbikColumn.setText("RBI/K");
        sberaColumn.setText("SB/ERA");
        bawhipColumn.setText("BA/WHIP");
        }
        
        if(z.equals("11")){
        rwColumn.setText("W");
        hrsvColumn.setText("SV");
        rbikColumn.setText("K");
        sberaColumn.setText("ERA");
        bawhipColumn.setText("WHIP");
        }
        
        if(!z.equals("1") && !z.equals("11")){
        rwColumn.setText("R");
        hrsvColumn.setText("HR");
        rbikColumn.setText("RBI");
        sberaColumn.setText("SB");
        bawhipColumn.setText("BA");
        }
        
        jcfm.clearobp();
        for(Player p : jcfm.arr){
            Superplayer a = new Superplayer();
            a.setFIRST(p.getFirst());
            a.setLAST(p.getLast());
            a.setProteam(p.getProteam());
            a.setPosition(p.getPosition());
            a.setYear(p.getYear());
            a.setRw(p.getRW());
            a.setHrsv(p.getHRSV());
            a.setRbik(p.getRBIK());
            a.setSbera(p.getSBERA());
            a.setBawhip(p.getBAWHIP());
            a.setNote(p.getNote());
            jcfm.addobp(a);
            
            playerTable.setItems(jcfm.getobp());
        
        }
    }
    
    public void displayz(){
        String z = "1";
             try{
                        z = group.getSelectedToggle().getUserData().toString();
                                }catch (NullPointerException e){
                        
                    }
        
        if(z.equals("1")){
        rwColumn.setText("R/W");
        hrsvColumn.setText("HR/SV");
        rbikColumn.setText("RBI/K");
        sberaColumn.setText("SB/ERA");
        bawhipColumn.setText("BA/WHIP");
        }
        
        if(z.equals("11")){
        rwColumn.setText("W");
        hrsvColumn.setText("SV");
        rbikColumn.setText("K");
        sberaColumn.setText("ERA");
        bawhipColumn.setText("WHIP");
        }
        
        if(!z.equals("1") && !z.equals("11")){
        rwColumn.setText("R");
        hrsvColumn.setText("HR");
        rbikColumn.setText("RBI");
        sberaColumn.setText("SB");
        bawhipColumn.setText("BA");
        }     
             
        jcfm.clearobp();
        for(Player p : jcfm.all){
            Superplayer a = new Superplayer();
            a.setFIRST(p.getFirst());
            a.setLAST(p.getLast());
            a.setProteam(p.getProteam());
            a.setPosition(p.getPosition());
            a.setYear(p.getYear());
            a.setRw(p.getRW());
            a.setHrsv(p.getHRSV());
            a.setRbik(p.getRBIK());
            a.setSbera(p.getSBERA());
            a.setBawhip(p.getBAWHIP());
            a.setNote(p.getNote());
            jcfm.addobp(a);
            
            playerTable.setItems(jcfm.getobp());
        
        }
    }
    
}
