public class Project {
    //[2926685]
    //Project Variables.
    private String name;
    private String id;
    private String type;
    private String date;
    private String location;
    private String cost;
    private String priceToConsumer;
    private String venueSize;
    private String duration;
    private String durationType;

    public Project(){}; //Constructor with no parameters.

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    /**
     * @return returns the profitability of the individual project.
     */
    public Double getProfit(){
        return Double.parseDouble(priceToConsumer) - Double.parseDouble(cost);
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPriceToConsumer() {
        return priceToConsumer;
    }

    public void setPriceToConsumer(String priceToConsumer) {
        this.priceToConsumer = priceToConsumer;
    }

    public String getVenueSize() {
        return venueSize;
    }

    public void setVenueSize(String venueSize) {
        this.venueSize = venueSize;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }
    /**
     * @return returns the project in String form.
     */
    public String toString(){
        return  "ID: " + getId()  + " Name: " + getName() + " Production Type: " + getType() + " DateCreated: " + getDate() + " Cost: " +
                getCost() + " Price to Consumer: " + getPriceToConsumer() + " Duration: " + getDuration() + getDurationType() + " Location: "+
                getLocation() + " Venue Size: " + getVenueSize();
    }

}
