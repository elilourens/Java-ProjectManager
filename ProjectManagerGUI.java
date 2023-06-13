import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Objects;
//[2926685]
public class ProjectManagerGUI extends JFrame implements ActionListener{
    public ProjectManager myProjectManager = new ProjectManager();
    private final String[] typeChoices = {"Other", "Theater Project", "Music Project", "Film Project", "TV Project"}; //dropdown choices
    private final JTextField name = new JTextField(30);
    private final JComboBox<String> typeBox = new JComboBox<>(typeChoices); //Dropdown
    private final JTextField day = new JTextField(2);
    private final JTextField month = new JTextField(2);
    private final JTextField year = new JTextField(4);
    private final JTextField location = new JTextField(8);
    private final JTextField cost = new JTextField(5);
    private final JTextField priceToConsumer = new JTextField(5);
    private final JTextField venueSize = new JTextField(8);
    private final JTextField duration = new JTextField(5);
    private final JTextField durationType = new JTextField(8);
    private final JTextArea outputBox = new JTextArea(30, 50);
    private final JButton addButtonProject = new JButton("Add Project");
    private final JButton nightMode = new JButton("\uD83C\uDF11");
    private final JButton dayMode = new JButton("\uD83C\uDF1E");
    private final JButton summary = new JButton("Summary");
    private final JButton fileSelectorButton = new JButton("...");
    private final JButton searchByType = new JButton("Search by Type");
    private final JButton searchByLocation = new JButton("Search by Location");
    private final JButton searchByDate = new JButton("Search By Date");
    private final JButton searchbyName = new JButton("Search By Name");



    public static void main(String[] args) throws FileNotFoundException { //main
        ProjectManagerGUI temp = new ProjectManagerGUI();
        DocumentLoader loaderClass = new DocumentLoader(); //Loader class instance
        loaderClass.loader(args[0]); //Load in csv as a argument.
    }


    public ProjectManagerGUI(){ //GUI
        super("ProjectManager");
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.gray);

        JLabel nameLabel = new JLabel("Name:");
        add(nameLabel);
        add(name);
        name.setEditable(true);

        JLabel typeLabel = new JLabel("Production type:");
        add(typeLabel);
        add(typeBox);
        typeBox.addActionListener(this);

        JLabel dayLabel = new JLabel("day:");
        add(dayLabel);
        add(day);
        day.setEditable(true);

        JLabel monthLabel = new JLabel("month:");
        add(monthLabel);
        add(month);
        month.setEditable(true);

        JLabel yearLabel = new JLabel("year:");
        add(yearLabel);
        add(year);
        year.setEditable(true);

        JLabel locationLabel = new JLabel("Location:");
        add(locationLabel);
        add(location);
        location.setEditable(true);

        JLabel costLabel = new JLabel("Cost:");
        add(costLabel);
        add(cost);
        cost.setEditable(true);

        JLabel priceToConsumerLabel = new JLabel("Price To Consumer");
        add(priceToConsumerLabel);
        add(priceToConsumer);
        priceToConsumer.setEditable(true);

        JLabel venueSizeLabel = new JLabel("Venue Size:");
        add(venueSizeLabel);
        add(venueSize);
        venueSize.setEditable(true);

        JLabel durationLabel = new JLabel("Duration:");
        add(durationLabel);
        add(duration);
        duration.setEditable(true);

        JLabel durationTypeLabel = new JLabel("Duration Units:");
        add(durationTypeLabel);
        add(durationType);
        durationType.setEditable(true);

        add(otherLabel);
        add(other);
        other.setEditable(true);

        add(addButtonProject);
        addButtonProject.addActionListener(this);

        JLabel fileLabel = new JLabel("Choose CSV:");
        add(fileLabel);
        add(fileSelectorButton);
        fileSelectorButton.addActionListener(this);
        JLabel typeSearch = new JLabel("Use Drop down menu to select Type:");
        add(typeSearch);
        add(searchByType);
        searchByType.addActionListener(this);
        add(searchByDate);
        searchByDate.addActionListener(this);
        add(searchbyName);
        searchbyName.addActionListener(this);
        add(searchByLocation);
        searchByLocation.addActionListener(this);
        add(summary);
        summary.addActionListener(this);
        add(outputBox);
        outputBox.setEditable(false);
        add(nightMode);
        nightMode.addActionListener(this);
        add(dayMode);
        dayMode.addActionListener(this);
        setSize(1420, 650);
        setVisible(true);
        blankDisplay();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    JLabel otherLabel = new JLabel("Project type dependant:"); //This changes depending on what is selected in the dropdown.
    JTextField other = new JTextField(12);

    /**
     * Handles actions of the GUI
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        String selectedType = (String) typeBox.getSelectedItem(); //gets selected project from dropdown.
        assert selectedType != null;
        //Sets the label for the project dependent variable to different things dependent on dropdown menu.
        if(selectedType.equals("TV Project")){
            otherLabel.setText("Network:");
        }
        if(selectedType.equals("Theater Project")){
            otherLabel.setText("PlayWright:");
        }
        if(selectedType.equals("Film Project")){
            otherLabel.setText("Format:");
        }
        if(selectedType.equals("Music Project")){
            otherLabel.setText("Genre:");
        }
        if(selectedType.equals("Other")){
            otherLabel.setText("Project type dependant:");
        }
        if(e.getSource() == addButtonProject){ //Adds inputted text to make new project.
            addProject();
        }
        if(e.getSource() == summary){
            blankDisplay();
            outputBox.setText(myProjectManager.getSummary()); //prints a summary to the output box.
        }
        if(e.getSource() == nightMode){ //Darkmode
            getContentPane().setBackground(Color.gray);
        }
        if(e.getSource() == dayMode){ //Lightmode
            getContentPane().setBackground(Color.PINK);
        }
        if(e.getSource() == fileSelectorButton){ //Optional function to add CSV file through a file selector.
            outputBox.setText(fileSearch());
        }
        if(e.getSource() == searchByDate){ //Search for a project by inputted date.
            int d = Integer.parseInt(day.getText());
            int m = Integer.parseInt(month.getText());
            int y = Integer.parseInt(year.getText());
            String output = myProjectManager.SearchByDate(d,m,y);
            outputBox.setText(output);
        }
        if(e.getSource() == searchByLocation){ //Search for a project by inputted location.
            String output = myProjectManager.searchByLocation(location.getText());
            outputBox.setText(output);
        }
        if(e.getSource() == searchByType){ //Uses the dropdown to choose what type of project to search for.
            String s = (String) typeBox.getSelectedItem();
            String typeList = "";
            if(s.equals("TV Project")){
                typeList = myProjectManager.searchByType("TV");
            }
            if(s.equals("Theater Project")){
                typeList = myProjectManager.searchByType("Theater");
            }
            if(s.equals("Film Project")){
                typeList = myProjectManager.searchByType("Film");
            }
            if(s.equals("Music Project")){
                typeList = myProjectManager.searchByType("Music");
            }
            if(s.equals("Other")){
                typeList = "Enter Project Type Using DropDown";
            }
            outputBox.setText(typeList); //Outputs all found projects to the output box.
        }
        if(e.getSource() == searchbyName){
            outputBox.setText(myProjectManager.searchByName(name.getText()));
        }
    }
    /**
     * Creates a new project based off user inputted text input once the add button is clicked.
     */
    public void addProject(){
        Project newProject = ProjectCreator.createProject((String) Objects.requireNonNull(typeBox.getSelectedItem()));
        //^^ Creates a new project based off which project type is selected in the dropdown box.
        //These lines assign project specific variables.
        if (newProject instanceof FilmProject) {
            ((FilmProject) newProject).setFormat(other.getText());
        } else if (newProject instanceof MusicProject) {
            ((MusicProject) newProject).setGenre(other.getText());
        } else if (newProject instanceof TheaterProject) {
            ((TheaterProject) newProject).setPlayWright(other.getText());
        } else if (newProject instanceof TVProject){
            ((TVProject) newProject).setNetwork(other.getText());
        }
        //These lines assign default project variables from what is inputted by the user.
        newProject.setId(Integer.toString(myProjectManager.projectListSize()+1)); //Dynamically increments ID number.
        newProject.setName(name.getText());
        newProject.setType((String)typeBox.getSelectedItem());
        String inputDate = day.getText() + "/" + month.getText() + "/" + year.getText();
        newProject.setDate(inputDate);
        newProject.setLocation(location.getText());
        newProject.setCost(cost.getText());
        newProject.setPriceToConsumer(priceToConsumer.getText());
        newProject.setVenueSize(venueSize.getText());
        newProject.setDuration(duration.getText());
        newProject.setDurationType(durationType.getText());
        ProjectManager.addNewProject(newProject); //Add new Project to project list.
        blankDisplay(); //Wipe text boxes.
    }
    /**
     * Loads in the CSV file by user search.
     */
    public String fileSearch(){
        JFileChooser fileSelector = new JFileChooser();
        String output = "";
        int result = fileSelector.showOpenDialog(ProjectManagerGUI.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileSelector.getSelectedFile().getAbsolutePath();
            DocumentLoader loaderClass = new DocumentLoader();
            try {
                loaderClass.loader(selectedFile); //Uses inputted CSV filepath to create java objects and save them.
                output += "Success";
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            loaderClass.setProjectList(); //Updates the project list with added csv data objects.
        }
        return output;
    }
    public void blankDisplay() { //Makes all the text boxes blank to wipe the screen.
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        location.setText("");
        cost.setText("");
        priceToConsumer.setText("");
        venueSize.setText("");
        duration.setText("");
        durationType.setText("");
        other.setText("");
        outputBox.setText("");
    }
}
/**
 * This method creates new/different project objects depending on what is inputted.
 */
class ProjectCreator{
    public static Project createProject(String inputString){ //Creates a new child class of Project, or a new normal project.
        return switch (inputString) {
            case "TV Project" -> new TVProject();
            case "Film Project" -> new FilmProject();
            case "Theater Project" -> new TheaterProject();
            case "Music Project" -> new MusicProject();
            case "Other", "" -> new Project();
            default -> throw new IllegalArgumentException();
        };
    }
}