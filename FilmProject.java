public class FilmProject extends Project{
    //[2926685]
    private String format; //Project specific variable.
    public FilmProject(){}

    //Project specific getters and setters.
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return returns the project in String form but with the project specific variable aswell.
     */
    @Override
    public String toString(){
        return  "ID: " + getId()  + " Name: " + getName() + " Production Type: " + getType() + " DateCreated: " + getDate() + " Cost: " +
                getCost() + " Price to Consumer: " + getPriceToConsumer() + " Duration: " + getDuration() + getDurationType() + " Location: "+
                getLocation() + " Venue Size: " + getVenueSize() + " Format: " + getFormat();
    }
}
