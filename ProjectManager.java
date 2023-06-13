import java.util.ArrayList;
public class ProjectManager {
    //[2926685]
    private static ArrayList<Project> projectList; // the overall Project List.
    public ProjectManager()  {projectList = new ArrayList<Project>();}
    /**
     * @param temp Adds this new project object to the project list.
     */
    public static void addNewProject(Project temp){ //Adds project to project list.
        projectList.add(temp);
    }
    /**
     * @return returns the stored ProjectList
     */
    public static ArrayList<Project> getProjectList(){
        return projectList;
    }
    public String printAllProjects(){
        String output = "";
        for (Project current : projectList) {
            output += "\n" + current.toString();
        }
        return output;
    }
    /**
     * @return Simply returns the current projectList size.
     */
    public int projectListSize(){
        return projectList.size();
    }
    /**
     * @param d Day to look for.
     * @param m Month to look for.
     * @param y Year to look for.
     * @return Returns the project in String form.
     */
    public String SearchByDate(int d, int m, int y){
        String output = "";
        for(int j = 0; j < projectListSize();j++){
            String temp = projectList.get(j).getDate();
            String[] pDate = temp.split("/");
            if (Integer.parseInt(pDate[0]) == d && Integer.parseInt(pDate[1]) == m && Integer.parseInt(pDate[2]) == y){
                 output += projectList.get(j).toString() + "\n";
            }
        }
        return output;
    }
    /**
     *
     * @param location user inputted location to search for.
     * @return Returns all the projects in String form which took place at the location.
     */
    public String searchByLocation(String location){
        String output = "";
        for(int k = 0; k < projectListSize();k++){
            if(projectList.get(k).getLocation().equalsIgnoreCase(location)){
                output += projectList.get(k).toString() + "\n";
            }
        }
        return output;
    }
    /**
     *
     * @param name name to search for.
     * @return Returns all projects with that name. Projects plural in case there is ever more than one.
     */
    public String searchByName(String name){
        String output = "";
        for(int j = 0; j < projectListSize(); j++){
            if(projectList.get(j).getName().equalsIgnoreCase(name)){
                output += projectList.get(j).toString() + "\n";
            }
        }
        return output;
    }
    /**
     * @param type The type of Project to search for.
     * @return Returns a formatted String of all tje projects of that type and data about them.
     */
    public String searchByType(String type) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        //String builder used here as it is much faster than using "+=".
        Class<?> temp = null;
        //String property = null;

        switch (type.toUpperCase()) {
            case "TV" -> {
                temp = TVProject.class;

            }
            case "FILM" -> {
                temp = FilmProject.class;

            }
            case "THEATER" -> {
                temp = TheaterProject.class;

            }
            case "MUSIC" -> {
                temp = MusicProject.class;

            }
            default -> {
                return "Invalid project type use dropdown: " + type;
            }
        }

        for (Project p : projectList) {
            if (temp.isInstance(p)) {
                sb.append(p.getName()).append(", ").append("\n ");

                counter++;
            }
        }

        return "Number of " + type + " Projects: " + counter + " Their names are: \n" + sb;
    }
    /**
     * @return Returns an Array of Strings that contain information about all the projects to be printed out in the summary.
     */
    public String[] getProfitability(){
        String[] output = new String[15]; /*
        Index 0 = Total Profitability. 1 = Film profitability, 2 = Music profitability, 3 = Theatre profitability,
        4 = TV profitability, 5 = most profitable project, 6 = How profitable it was, 7 = Least Profitable project, 8 = How unprofitable,
        9 = How many Profitable Projects there were, 10 = no. of film Projects, 11 = no. of music projects,
        12 = number of theatre projects, 13 = number of TV projects, 14 = most profitable location from one project.
        */
        String mostProfitable = projectList.get(0).getName();
        Double mostProfitableAmount = projectList.get(0).getProfit();
        String leastProfitable = projectList.get(0).getName();
        Double leastProfitableAmount = projectList.get(0).getProfit();
        int totalProfit = 0;
        int tvProjects = 0;
        int filmProjects = 0;
        int musicProjects = 0;
        int theatreProjects = 0;
        int tvProjectsProfit = 0;
        int filmProjectsProfit = 0;
        int musicProjectsProfit = 0;
        int theatreProjectsProfit = 0;
        int profitableProjects = 0;
        String mostProfitableLocation = projectList.get(0).getLocation();
        for (int i = 0; i < projectListSize(); i++){
            Project current = projectList.get(i);
            double profit = current.getProfit();
            totalProfit += profit;
            if(profit > 0){
                profitableProjects++;
            }
            if(profit > mostProfitableAmount){
                mostProfitableAmount = profit;
                mostProfitable = current.getName();
                mostProfitableLocation = current.getLocation();
            }
            if(profit < leastProfitableAmount){
                leastProfitableAmount = profit;
                leastProfitable = current.getName();
            }
            if(current instanceof FilmProject){
                filmProjects++;
                filmProjectsProfit += profit;
            } else if (current instanceof MusicProject) {
                musicProjects++;
                musicProjectsProfit += profit;
            } else if (current instanceof TheaterProject) {
                theatreProjects++;
                theatreProjectsProfit += profit;
            } else if (current instanceof TVProject) {
                tvProjects++;
                tvProjectsProfit += profit;
            }
        }
        output[0] = Integer.toString(totalProfit);
        output[1] = Integer.toString(filmProjectsProfit);
        output[2] = Integer.toString(musicProjectsProfit);
        output[3] = Integer.toString(theatreProjectsProfit);
        output[4] = Integer.toString(tvProjectsProfit);
        output[5] = mostProfitable;
        output[6] = Double.toString(mostProfitableAmount);
        output[7] = leastProfitable;
        output[8] = Double.toString(leastProfitableAmount);
        output[9] = Integer.toString(profitableProjects);
        output[10] = Integer.toString(filmProjects);
        output[11] = Integer.toString(musicProjects);
        output[12] = Integer.toString(theatreProjects);
        output[13] = Integer.toString(tvProjects);
        output[14] = mostProfitableLocation;
        return output;
    }

    /**
     * This method uses the getProfitability() method to create stats about the profit of the company.
     * @return Returns the summary String to be printed out in the GUI.
     */
    public String getSummary(){
        String[] stats = getProfitability();
        return "Summary of Kilted Haggis Projects:  \n"
        + "A Total of " + projectListSize() + " Projects were made. \n"
        + stats[9] + " Projects were profitable. \n"
        + stats[5] + " Was the most profitable making " + stats[6] + ". " + stats[7] + " Was the least, making " + stats[8] + "\n"
        + "Total profit made overall = " + stats[0] + "\n"
        + stats[10] + " Film Projects were made, their overall profit was: " + stats[1] + "\n"
        + stats[11] + " Music Projects were made, their overall profit was: " + stats[2] + "\n"
        + stats[12] + " Theatre Projects were made, their overall profit was: " + stats[3] + "\n"
        + stats[13] + " TV Projects were made, their overall profit was: " + stats[4] + "\n"
        + stats[14] + " Was the location that generated the most profit from one project.";
    }
}
