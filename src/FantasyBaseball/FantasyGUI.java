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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class FantasyGUI{
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
    
    private HBox teamhbox;
    private VBox teamvbox;
    private Label draftNameLabel;
    private Button teamAddButton;
    private Button teamRemoveButton;
    private Button pencilButton;
    private TextField nameTeamField;
    private Label teamSelectLabel;
    private ComboBox teamSelectComboBox;
    
    
    private HBox startinghbox;
    //starting lineup table
    TableView<Superplayer> startingTable;
    TableColumn sPosition;
    TableColumn sFirst;
    TableColumn sLast;
    TableColumn sProTeam;
    TableColumn sPositions;
    TableColumn sRW;
    TableColumn sHRSV;
    TableColumn sRBIK;
    TableColumn sSBERA;
    TableColumn sBAWHIP;
    TableColumn sValue;
    TableColumn sContract;
    TableColumn sSalary;
    
    FantasyTeam currentTeam;
    
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
    private HBox teamhbox2;
    
    
    private String sname; //what is currently in nameTextField
    boolean thisisanewfile = true; // true is it has not been saved or was loaded
    private String realname; // name the save file is saved as
    
    
    private VBox vbox5;
    private Label selectproteam;
    private ComboBox selectproteamComboBox;
    TableView<Superplayer> mlbteamsTable;
    TableColumn mFirst;
    TableColumn mLast;
    TableColumn mPositions;
    ObservableList<Superplayer> mlbList = null;
    
    private VBox vbox3;
    TableView<SuperFantasyTeam> fantasyStandingsTable;
    TableColumn fTeamName;
    TableColumn fPlayersNeeded;
    TableColumn fLeft;
    TableColumn fPP;
    TableColumn fR;
    TableColumn fHR;
    TableColumn fRBI;
    TableColumn fSB;
    TableColumn fBA;
    TableColumn fW;
    TableColumn fSV;
    TableColumn fK;
    TableColumn fERA;
    TableColumn fWHIP;
    TableColumn fTOTALPOINTS;
    
    private VBox vbox4;
    private HBox hbox4;
    Button starButton;
    Button playButton;
    Button pauseButton;
    TableView<Superplayer> draftSummaryTable;
    TableColumn dPick;
    TableColumn dFirst;
    TableColumn dLast;
    TableColumn dTeam;
    TableColumn dContract;
    TableColumn dSalary;
    
    private HBox teamhbox3;
    TableView<Superplayer> taxiTable;
    TableColumn tPosition;
    TableColumn tFirst;
    TableColumn tLast;
    TableColumn tProTeam;
    TableColumn tPositions;
    TableColumn tRW;
    TableColumn tHRSV;
    TableColumn tRBIK;
    TableColumn tSBERA;
    TableColumn tBAWHIP;
    TableColumn tValue;
    TableColumn tContract;
    TableColumn tSalary;
    
    
    
    
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
        
        myPane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        ////######################################################################
        teamvbox = new VBox();
        teamhbox = new HBox();
        teamhbox2 = new HBox();
        nameTeamField = new TextField();
        teamAddButton = initChildButton(teamhbox2, CSB_PropertyType.ADD_ICON, CSB_PropertyType.ADD_LECTURE_TOOLTIP, false);
        teamRemoveButton = initChildButton(teamhbox2, CSB_PropertyType.MINUS_ICON, CSB_PropertyType.REMOVE_LECTURE_TOOLTIP,false);
        pencilButton = initChildButton(teamhbox2, CSB_PropertyType.PENCIL_ICON, CSB_PropertyType.PENCIL_TOOLTIP,true);
        draftNameLabel = initLabel(CSB_PropertyType.LECTURES_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        teamSelectLabel = initLabel(CSB_PropertyType.SELECT_TEAM_LABEL, CLASS_SUBHEADING_LABEL);
        teamSelectComboBox = new ComboBox();
        teamhbox.getChildren().add(draftNameLabel);
        teamhbox.getChildren().add(nameTeamField);
        teamhbox2.getChildren().add(teamSelectLabel);
        teamhbox2.getChildren().add(teamSelectComboBox);
        
        teamvbox.getChildren().add(teamhbox);
        teamvbox.getChildren().add(teamhbox2);
        
        topWorkspacePane.getChildren().add(teamvbox);
       //######################################################################## 
        teamAddButton.setOnAction(e -> {
            FantasyFileController.handleNewTeamRequest(this);
            //System.out.println("sup");
            
        
        });
        
        //*******************************************************************8
       
        
        //topWorkspacePane.getChildren().add(myPane);
        
        topWorkspacePane.getChildren().add(botToolbarPane);
        
        
        // AND NOW PUT IT IN THE WORKSPACE
        workspaceScrollPane = new ScrollPane();
        //workspaceScrollPane.getStyleClass().add(CLASS_BORDERED_PANE);
        workspaceScrollPane.setContent(workspacePane);
        workspaceScrollPane.setFitToWidth(true);
        workspaceScrollPane.setFitToHeight(true);
        
        
        
        
        workspaceActivated = false;
        
        initNameTeamField();
        
        
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
        
        saveCourseButton.setOnAction(e -> {
            FantasyFileController.handleSaveDraftRequest(this);
        });
        
        loadCourseButton.setOnAction(e -> {
            FantasyFileController.handleLoadDraftRequest(this);
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
    
    private void initNameTeamField(){
        nameTeamField.textProperty().addListener(new ChangeListener<String>() {
        @Override
             public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                 //System.out.println(newValue);
                 sname = newValue;
             }
             
        
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
        
        teamvbox = new VBox();
        teamhbox = new HBox();
        teamhbox2 = new HBox();
        teamhbox3 = new HBox();
        startinghbox = new HBox();
        nameTeamField = new TextField();
        teamAddButton = initChildButton(teamhbox2, CSB_PropertyType.ADD_ICON, CSB_PropertyType.ADD_LECTURE_TOOLTIP, false);
        teamRemoveButton = initChildButton(teamhbox2, CSB_PropertyType.MINUS_ICON, CSB_PropertyType.REMOVE_LECTURE_TOOLTIP,false);
        pencilButton = initChildButton(teamhbox2, CSB_PropertyType.PENCIL_ICON, CSB_PropertyType.PENCIL_TOOLTIP,false);
        draftNameLabel = initLabel(CSB_PropertyType.LECTURES_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        teamSelectLabel = initLabel(CSB_PropertyType.SELECT_TEAM_LABEL, CLASS_SUBHEADING_LABEL);
        teamSelectComboBox = new ComboBox();
        
        ///////////////////////////////////////
        
        sPosition = new TableColumn("Position");
        sFirst = new TableColumn("First");
        sLast = new TableColumn("Last");
        sProTeam = new TableColumn("Pro Team");
        sPositions = new TableColumn("Positions");
        sRW = new TableColumn("R/W");
        sHRSV = new TableColumn("HR/SV");
        sRBIK = new TableColumn("RBI/K");
        sSBERA = new TableColumn("SB/ERA");
        sBAWHIP = new TableColumn("BA/WHIP");
        sValue = new TableColumn("Estimated Value");
        sContract = new TableColumn("Contract");
        sSalary = new TableColumn("Salary");
        
        sPosition.setCellValueFactory(new PropertyValueFactory<String, String>("truePosition"));
        sFirst.setCellValueFactory(new PropertyValueFactory<String, String>("FIRST"));
        sLast.setCellValueFactory(new PropertyValueFactory<String, String>("LAST"));
        sProTeam.setCellValueFactory(new PropertyValueFactory<String, String>("proteam"));
        sPositions.setCellValueFactory(new PropertyValueFactory<String, String>("position"));
        sRW.setCellValueFactory(new PropertyValueFactory<Integer, String>("rw"));
        sHRSV.setCellValueFactory(new PropertyValueFactory<Integer, String>("hrsv"));
        sRBIK.setCellValueFactory(new PropertyValueFactory<Integer, String>("rbik"));
        sSBERA.setCellValueFactory(new PropertyValueFactory<Double, String>("sbera"));
        sBAWHIP.setCellValueFactory(new PropertyValueFactory<Double, String>("bawhip"));
        sValue.setCellValueFactory(new PropertyValueFactory<Double, String>("estimatedValue"));
        sContract.setCellValueFactory(new PropertyValueFactory<String, String>("Contract"));
        sSalary.setCellValueFactory(new PropertyValueFactory<String, String>("Salary"));
        
        startingTable = new TableView();
        startingTable.getColumns().add(sPosition);
        startingTable.getColumns().add(sFirst);
        startingTable.getColumns().add(sLast);
        startingTable.getColumns().add(sProTeam);
        startingTable.getColumns().add(sPositions);
        startingTable.getColumns().add(sRW);
        startingTable.getColumns().add(sHRSV);
        startingTable.getColumns().add(sRBIK);
        startingTable.getColumns().add(sSBERA);
        startingTable.getColumns().add(sBAWHIP);
        startingTable.getColumns().add(sValue);
        startingTable.getColumns().add(sContract);
        startingTable.getColumns().add(sSalary);
        
        if(currentTeam != null){
        currentTeam.sortTeam();
        }
        try{
            startingTable.setItems(currentTeam.getTeam());
        }
        catch(NullPointerException e){
            
        }
        
        tPosition = new TableColumn("Position");
        tFirst = new TableColumn("First");
        tLast = new TableColumn("Last");
        tProTeam = new TableColumn("Pro Team");
        tPositions = new TableColumn("Positions");
        tRW = new TableColumn("R/W");
        tHRSV = new TableColumn("HR/SV");
        tRBIK = new TableColumn("RBI/K");
        tSBERA = new TableColumn("SB/ERA");
        tBAWHIP = new TableColumn("BA/WHIP");
        tValue = new TableColumn("Estimated Value");
        tContract = new TableColumn("Contract");
        tSalary = new TableColumn("Salary");
        
        tPosition.setCellValueFactory(new PropertyValueFactory<String, String>("truePosition"));
        tFirst.setCellValueFactory(new PropertyValueFactory<String, String>("FIRST"));
        tLast.setCellValueFactory(new PropertyValueFactory<String, String>("LAST"));
        tProTeam.setCellValueFactory(new PropertyValueFactory<String, String>("proteam"));
        tPositions.setCellValueFactory(new PropertyValueFactory<String, String>("position"));
        tRW.setCellValueFactory(new PropertyValueFactory<Integer, String>("rw"));
        tHRSV.setCellValueFactory(new PropertyValueFactory<Integer, String>("hrsv"));
        tRBIK.setCellValueFactory(new PropertyValueFactory<Integer, String>("rbik"));
        tSBERA.setCellValueFactory(new PropertyValueFactory<Double, String>("sbera"));
        tBAWHIP.setCellValueFactory(new PropertyValueFactory<Double, String>("bawhip"));
        tValue.setCellValueFactory(new PropertyValueFactory<Double, String>("estimatedValue"));
        tContract.setCellValueFactory(new PropertyValueFactory<String, String>("Contract"));
        tSalary.setCellValueFactory(new PropertyValueFactory<String, String>("Salary"));
       
        taxiTable = new TableView();
        taxiTable.getColumns().add(tPosition);
        taxiTable.getColumns().add(tFirst);
        taxiTable.getColumns().add(tLast);
        taxiTable.getColumns().add(tProTeam);
        taxiTable.getColumns().add(tPositions);
        taxiTable.getColumns().add(tRW);
        taxiTable.getColumns().add(tHRSV);
        taxiTable.getColumns().add(tRBIK);
        taxiTable.getColumns().add(tSBERA);
        taxiTable.getColumns().add(tBAWHIP);
        taxiTable.getColumns().add(tValue);
        taxiTable.getColumns().add(tContract);
        taxiTable.getColumns().add(tSalary);
        
        
        try{
            taxiTable.setItems(currentTeam.getTaxiTeam());
        }
        catch(NullPointerException e){
            
        }
        
        
        
        //////////////////////////////////////////
        
        
        /////////////////////////////////////////
           
        nameTeamField.setText(sname);
        
        teamhbox.getChildren().add(draftNameLabel);
        teamhbox.getChildren().add(nameTeamField);
        teamhbox2.getChildren().add(teamSelectLabel);
        teamhbox2.getChildren().add(teamSelectComboBox);
        startinghbox.getChildren().add(startingTable);
        teamhbox3.getChildren().add(taxiTable);
        
        
        teamvbox.getChildren().add(teamhbox);
        teamvbox.getChildren().add(teamhbox2);
        teamvbox.getChildren().add(startinghbox);
        teamvbox.getChildren().add(teamhbox3);
        
       
        topWorkspacePane.getChildren().add(teamvbox);
        topWorkspacePane.getChildren().add(botToolbarPane);
        
        
        // AND NOW PUT IT IN THE WORKSPACE
        Pane1 = new ScrollPane();
        Pane1.setContent(workspacePane);
        Pane1.setFitToWidth(true);
        Pane1.setFitToHeight(true);

        workspaceActivated = false;
        
        csbPane.setCenter(Pane1); 
        initNameTeamField();
        
        teamAddButton.setOnAction(e -> {
            FantasyFileController.handleNewTeamRequest(this);
            //System.out.println("sup");
            
        });
        
        teamRemoveButton.setOnAction(e -> {
           FantasyFileController.handleRemoveTeamRequest(this);
        });
        
        pencilButton.setOnAction(e -> {
            FantasyFileController.handleEditTeamRequest(this);
        });
        
        ArrayList<FantasyTeam> qwerty = jcfm.getFantasyTeamList();
        teamSelectComboBox.getItems().clear();
        for(FantasyTeam f : qwerty){
            teamSelectComboBox.getItems().add(f.getName());
        }
        
        teamSelectComboBox.setOnAction(e -> {
            FantasyFileController.handleTeamSelectRequest(this);
        });
       
        /**
        try{
            if(currentTeam!=null){
            teamSelectComboBox.setValue(currentTeam.getName());
            }
        }
        catch(NullPointerException e){
            
        }
        **/
        
        startingTable.setOnMouseClicked(e -> {
           if(e.getClickCount() == 2){
               Superplayer sp = startingTable.getSelectionModel().getSelectedItem();
               //System.out.println(sp.getFIRST());
               FantasyFileController.handleResetPlayer(this, sp);
           } 
        });
        
    }
    
    public void setCurrentTeam(FantasyTeam team){
        currentTeam = team;
    }
    
    public FantasyTeam getCurrentTeam(){
        return currentTeam;
    }
    
    public String getTeamSelectComboBox(){
        
        String a = null;
        try{
        a = teamSelectComboBox.getSelectionModel().getSelectedItem().toString();
        
        }
        catch(NullPointerException e){
            
        }
        return a;
    }
    
    
    
    public void AvailablePlayers(){
       // System.out.println("Hi");
        
        topWorkspacePane = new VBox();
        topWorkspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        

        // HERE'S THE LABEL
        courseHeadingLabel = initChildLabel(topWorkspacePane, CSB_PropertyType.COURSE_INFO_LABEL, CLASS_HEADING_LABEL);

        workspacePane = new BorderPane();
        workspacePane.setCenter(topWorkspacePane);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        
        
        myPane = new ScrollPane();
        myPane.setFitToHeight(true);
        myPane.setFitToWidth(true);
        
       // letsgo = new GridPane();
       ///////////////////////////////////////////////////<- 
       
        vbox = new VBox();
        hbox = new HBox();
        //hbox.getStyleClass().add(CLASS_SUBJECT_PANE);
        hbox2 = new HBox();
        searchField = new TextField();
        
        addButton = initChildButton(hbox, CSB_PropertyType.ADD_ICON, CSB_PropertyType.ADD_ITEM_TOOLTIP, false);
        removeButton = initChildButton(hbox, CSB_PropertyType.MINUS_ICON, CSB_PropertyType.REMOVE_ITEM_TOOLTIP, false);
        
        removeButton.setOnAction(e -> {
            FantasyFileController.handleRemovePlayerRequest(this , playerTable.getSelectionModel().getSelectedItem());
        });
        
        
        
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
        lastColumn.setCellValueFactory(new PropertyValueFactory<String, String>("LAST"));
        proteamColumn.setCellValueFactory(new PropertyValueFactory<String, String>("proteam"));
        positionsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("position"));
        yearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("year"));
        rwColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("rw"));
        hrsvColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("hrsv"));
        rbikColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("rbik"));
        sberaColumn.setCellValueFactory(new PropertyValueFactory<Double, String>("sbera"));
        bawhipColumn.setCellValueFactory(new PropertyValueFactory<Double, String>("bawhip"));
        estimatedValueColumn.setCellValueFactory(new PropertyValueFactory<Double, String>("estimatedValue"));
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
            a.setEstimatedValue(estimateValue(p));
            
            
            a.setPlayer(p); // link the SuperPlayer to the player it is using.
            
            jcfm.addobp(a);
        
        }
        
        
       
        playerTable.setItems(jcfm.getobp());
        playerTable.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                Superplayer s =  playerTable.getSelectionModel().getSelectedItem();
                //System.out.println(s.getFIRST());
                FantasyFileController.handleAddPlayerToTeam(this, s);
            }
        });
        
        
       
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        hbox.getChildren().add(searchLabel);
        hbox.getChildren().add(searchField);
        
        
       
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(hbox2);
        vbox.getChildren().add(playerTable);
        //hbox.getStyleClass().add(CLASS_BORDERED_PANE);
        //vbox.getStyleClass().add(CLASS_BORDERED_PANE);
        
        
        
        
        
        //////////////////////////////////////////////////////////<-
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
        
        ///////////////////
        vbox3 = new VBox();
        fTeamName = new TableColumn("Team Name");
        fPlayersNeeded = new TableColumn("Players Needed");
        fLeft = new TableColumn("$Left");
        fPP = new TableColumn("$PP");
        fR = new TableColumn("R");
        fHR = new TableColumn("HR");
        fRBI = new TableColumn("RBI");
        fSB = new TableColumn("SB");
        fBA = new TableColumn("BA");
        fW = new TableColumn("W");
        fSV = new TableColumn("SV");
        fK = new TableColumn("K");
        fERA = new TableColumn("ERA");
        fWHIP = new TableColumn("WHIP");
        fTOTALPOINTS = new TableColumn("Total Points");
        
        fTeamName.setCellValueFactory(new PropertyValueFactory<String, String>("TEAMNAME"));
        fPlayersNeeded.setCellValueFactory(new PropertyValueFactory<Integer, String>("PLAYERSNEEDED"));
        fLeft.setCellValueFactory(new PropertyValueFactory<Integer, String>("LEFT"));
        fPP.setCellValueFactory(new PropertyValueFactory<Integer, String>("PP"));
        fR.setCellValueFactory(new PropertyValueFactory<Integer, String>("R"));
        fHR.setCellValueFactory(new PropertyValueFactory<Integer, String>("HR"));
        fRBI.setCellValueFactory(new PropertyValueFactory<Integer, String>("RBI"));
        fSB.setCellValueFactory(new PropertyValueFactory<Integer, String>("SB"));
        fBA.setCellValueFactory(new PropertyValueFactory<Double, String>("BA"));
        fW.setCellValueFactory(new PropertyValueFactory<Double, String>("W"));
        fSV.setCellValueFactory(new PropertyValueFactory<Integer, String>("SV"));
        fK.setCellValueFactory(new PropertyValueFactory<Integer, String>("K"));
        fERA.setCellValueFactory(new PropertyValueFactory<Double, String>("ERA"));
        fWHIP.setCellValueFactory(new PropertyValueFactory<Double, String>("WHIP"));
        fTOTALPOINTS.setCellValueFactory(new PropertyValueFactory<Double, String>("TOTALPOINTS"));
        
        fantasyStandingsTable = new TableView();
        fantasyStandingsTable.getColumns().add(fTeamName);
        fantasyStandingsTable.getColumns().add(fPlayersNeeded);
        fantasyStandingsTable.getColumns().add(fLeft);
        fantasyStandingsTable.getColumns().add(fPP);
        fantasyStandingsTable.getColumns().add(fR);
        fantasyStandingsTable.getColumns().add(fHR);
        fantasyStandingsTable.getColumns().add(fRBI);
        fantasyStandingsTable.getColumns().add(fSB);
        fantasyStandingsTable.getColumns().add(fBA);
        fantasyStandingsTable.getColumns().add(fW);
        fantasyStandingsTable.getColumns().add(fSV);
        fantasyStandingsTable.getColumns().add(fK);
        fantasyStandingsTable.getColumns().add(fERA);
        fantasyStandingsTable.getColumns().add(fWHIP);
        fantasyStandingsTable.getColumns().add(fTOTALPOINTS);
        
        
        ArrayList<FantasyTeam> aft = jcfm.getFantasyTeamList();
        jcfm.clearsft();
        jcfm.calculatePoints();
        for(FantasyTeam ft : aft){
            SuperFantasyTeam sft = new SuperFantasyTeam();
            sft.setTEAMNAME(ft.getName());
            ft.updatePlayersNeeded();
            sft.setPLAYERSNEEDED(ft.getPlayersNeeded());
            ft.updateMoney();
            sft.setLEFT(ft.getMoney());
            try{
            sft.setPP(Math.round(ft.getMoney()/ft.getPlayersNeeded()));
            }
            catch(ArithmeticException e){
                sft.setPP(-1);
            }
            sft.setR(ft.getR());
            sft.setHR(ft.getHR());
            sft.setRBI(ft.getRBI());
            sft.setSB(ft.getSB());
            sft.setBA(ft.getBA());
            sft.setW(ft.getW());
            sft.setSV(ft.getSV());
            sft.setK(ft.getK());
            sft.setERA(ft.getERA());
            sft.setWHIP(ft.getWHIP());
            sft.setTOTALPOINTS(ft.getPoints());
            jcfm.addsft(sft);
        }
        fantasyStandingsTable.setItems(jcfm.getsft());
        
        
        vbox3.getChildren().add(fantasyStandingsTable);
        
        //////////////////////
        topWorkspacePane.getChildren().add(vbox3);
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
        
        
        /////
        vbox4 = new VBox();
        hbox4 = new HBox();
        
        starButton = initChildButton(hbox4, CSB_PropertyType.STAR_ICON, CSB_PropertyType.STAR_TOOLTIP, false);
        playButton = initChildButton(hbox4, CSB_PropertyType.PLAY_ICON, CSB_PropertyType.PLAY_TOOLTIP, false);
        pauseButton = initChildButton(hbox4, CSB_PropertyType.PAUSE_ICON, CSB_PropertyType.PAUSE_TOOLTIP, false);
        
        starButton.setPrefSize(40, 40);
        playButton.setPrefSize(40,40);
        pauseButton.setPrefSize(40, 40);
        
        starButton.setOnAction(e ->{
            FantasyFileController.handleSTAR(this);
        });
        
        playButton.setOnAction(e ->{
            FantasyFileController.handlePlay(this, starButton);
           
            
        });
        pauseButton.setOnAction(e ->{
            FantasyFileController.handlePause();
        });
       
        dPick = new TableColumn("Pick#");
        dFirst = new TableColumn("First");
        dLast = new TableColumn("Last");
        dTeam = new TableColumn("Team");
        dContract = new TableColumn("Contract");
        dSalary = new TableColumn("Salary($)");
        
        dPick.setCellValueFactory(new PropertyValueFactory<Integer, String>("pick"));
        dFirst.setCellValueFactory(new PropertyValueFactory<String,String>("FIRST"));
        dLast.setCellValueFactory(new PropertyValueFactory<String, String>("LAST"));
        dTeam.setCellValueFactory(new PropertyValueFactory<String, String>("trueTeam"));
        dContract.setCellValueFactory(new PropertyValueFactory<String, String>("contract"));
        dSalary.setCellValueFactory(new PropertyValueFactory<String, String>("salary"));
        
        draftSummaryTable = new TableView();
        draftSummaryTable.getColumns().add(dPick);
        draftSummaryTable.getColumns().add(dFirst);
        draftSummaryTable.getColumns().add(dLast);
        draftSummaryTable.getColumns().add(dTeam);
        draftSummaryTable.getColumns().add(dContract);
        draftSummaryTable.getColumns().add(dSalary);
        
        jcfm.resortDraftOrder();
        draftSummaryTable.setItems(jcfm.getDraftOrder());
        
        vbox4.getChildren().add(hbox4);
        
        vbox4.getChildren().add(draftSummaryTable);



        //////
       
        
        topWorkspacePane.getChildren().add(vbox4);
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
       ////////////////
        vbox5 = new VBox();
        selectproteam = initLabel(CSB_PropertyType.SELECT_PRO_TEAM_LABEL, CLASS_SUBHEADING_LABEL);
        selectproteamComboBox = new ComboBox();
        
        mFirst = new TableColumn("First");
        mLast = new TableColumn("Last");
        mPositions = new TableColumn("Positions");
        
        mFirst.setCellValueFactory(new PropertyValueFactory<String, String>("FIRST"));
        mLast.setCellValueFactory(new PropertyValueFactory<String, String>("LAST"));
        mPositions.setCellValueFactory(new PropertyValueFactory<String, String>("position"));

        mlbteamsTable = new TableView();
        mlbteamsTable.getColumns().add(mFirst);
        mlbteamsTable.getColumns().add(mLast);
        mlbteamsTable.getColumns().add(mPositions);
        
        jcfm.emptyAll();
        jcfm.emptyArr();
        jcfm.loadA();
        
        
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
            
            
            a.setPlayer(p); // link the SuperPlayer to the player it is using.
            
            jcfm.addobp(a);
        }
        
        
        selectproteamComboBox.getItems().add("ATL");
        selectproteamComboBox.getItems().add("AZ");
        selectproteamComboBox.getItems().add("CHC");
        selectproteamComboBox.getItems().add("CIN");
        selectproteamComboBox.getItems().add("COL");
        selectproteamComboBox.getItems().add("LAD");
        selectproteamComboBox.getItems().add("MIA");
        selectproteamComboBox.getItems().add("MIL");
        selectproteamComboBox.getItems().add("NYM");
        selectproteamComboBox.getItems().add("PHI");
        selectproteamComboBox.getItems().add("PIT");
        selectproteamComboBox.getItems().add("SD");
        selectproteamComboBox.getItems().add("SF");
        selectproteamComboBox.getItems().add("STL");
        selectproteamComboBox.getItems().add("WAS");
        
        selectproteamComboBox.setOnAction(e -> {
            String a = selectproteamComboBox.getSelectionModel().getSelectedItem().toString();
            FantasyFileController.handleMlbSelectRequest(this, a);
        });
        
        mlbteamsTable.setItems(mlbList);
        
        vbox5.getChildren().add(selectproteam);
        vbox5.getChildren().add(selectproteamComboBox);
        vbox5.getChildren().add(mlbteamsTable);
        
        ///////////////
        topWorkspacePane.getChildren().add(vbox5);
        topWorkspacePane.getChildren().add(botToolbarPane);
        
        
        // AND NOW PUT IT IN THE WORKSPACE
        Pane5 = new ScrollPane();
        Pane5.setContent(workspacePane);
        Pane5.setFitToWidth(true);
        Pane5.setFitToHeight(true);

        workspaceActivated = false;
        
        csbPane.setCenter(Pane5); 
    }
    
    public ObservableList<Superplayer> getMlbList(){
        return mlbList;
    }
    
    public void setMlbList(ObservableList<Superplayer> mlbList){
        this.mlbList = mlbList;
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
            a.setPlayer(p);
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
            a.setPlayer(p);
            jcfm.addobp(a);
            
            playerTable.setItems(jcfm.getobp());
        
        }
    }
    
    public JsonDraftFileManager getjcfm(){
        return jcfm;
    }
    
    public String getSNAME(){
        return sname;
    }
    
    public boolean getthisisanewfile(){
        return thisisanewfile;
    }
    
    public void setthisisanewfile(Boolean a){
        thisisanewfile = a;
    }
    
    public String getRealName(){
        return realname;
    }
    
    public void setRealName(String a){
        realname = a;
    }
    
    public void setNameTeamField(String name){
        nameTeamField.setText(name);
    }
    
    public ComboBox getTSCB(){
        return teamSelectComboBox;
    }
    
    public void clearStartingTable(){
        startingTable.setItems(null);
    }

    private double estimateValue(Player p) {
       double b = 0.0;
       double money = 0.0;
       int rank = 1;
       ArrayList<FantasyTeam> teamlist = jcfm.getFantasyTeamList();
       for(FantasyTeam ft : teamlist){
           ft.updateMoney();
           money = money + ft.getMoney();
       }
       
       if(p.getPosition().equals("P")){
           rank = getRANKP((Pitcher) p);
       }
       else{
           
       }
               
       return b/rank;
    }
    
    
    private int getRANKP(Pitcher p){
        int a = 1;
        ArrayList<Pitcher> plist = jcfm.arrP;
        for(Pitcher pz : plist){
            
        }
        
        return a;
    }
}
