
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class DocumentLoader {
    //[2926685]
    private ArrayList<Project> projectList;
    public void setProjectList(){
        this.projectList = ProjectManager.getProjectList();
    }
    /**
     * @param filePath This method's parameter is the filepath to the CSV which needs to be read in.
     * This method reads in a CSV filepath, uses the Scanner object to read in each row of the csv line by line.
     * I then use the split() method to break up the line into an Array of strings to extract info.
     * I create new projects depending on what type of production is provided in each row using the projectCreator class.
     */
    public void loader(String filePath) throws FileNotFoundException {
        setProjectList();
        File inputFile = new File(filePath);
        Scanner in = new Scanner(inputFile);
        in.nextLine(); //Skips the first line which just is the column headings.
        while (in.hasNextLine()){
            String currentLine = in.nextLine();
            String[] bits = currentLine.split(",");
            String type = "";
            if(bits[2].equalsIgnoreCase("Film")){
                type = "Film Project";
            } else if(bits[2].equalsIgnoreCase("Music")){
                type = "Music Project";
            } else if (bits[2].equalsIgnoreCase("Theater")){
                type = "Theater Project";
            } else if(bits[2].equalsIgnoreCase("TV")){
                type = "TV Project";
            }
            Project newProject = ProjectCreator.createProject(type);
            if (newProject instanceof FilmProject) {
                ((FilmProject) newProject).setFormat(bits[bits.length-1]);
            } else if (newProject instanceof MusicProject) {
                ((MusicProject) newProject).setGenre(bits[bits.length-1]);
            } else if (newProject instanceof TheaterProject) {
                ((TheaterProject) newProject).setPlayWright(bits[bits.length-1]);
            } else if (newProject instanceof TVProject){
                ((TVProject) newProject).setNetwork(bits[bits.length-1]);
            }
            newProject.setId(bits[0]);
            newProject.setName(bits[1]);
            newProject.setType(type);
            newProject.setDate(bits[3]);
            newProject.setLocation(bits[4]);
            newProject.setCost(bits[5]);
            newProject.setPriceToConsumer(bits[6]);
            newProject.setVenueSize(bits[7]);
            newProject.setDuration(bits[8]);
            newProject.setDurationType(bits[9]);
            projectList.add(newProject);
        }
        in.close();
    }
}
