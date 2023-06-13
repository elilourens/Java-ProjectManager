public class TheaterProject extends Project{
    //[2926685]
    public TheaterProject(){};

    //Project Specific getters and setters.
    public String getPlayWright() {
        return playWright;
    }
    public void setPlayWright(String playWright) {
        this.playWright = playWright;
    }
    private String playWright;

    /**
     * @return returns the project in String form but with the project specific variable aswell.
     */
    @Override
    public String toString(){
        return  "ID: " + getId()  + " Name: " + getName() + " Production Type: " + getType() + " DateCreated: " + getDate() + " Cost: " +
                getCost() + " Price to Consumer: " + getPriceToConsumer() + " Duration: " + getDuration() + getDurationType() + " Location: "+
                getLocation() + " Venue Size: " + getVenueSize() + " PlayWright: " + getPlayWright();
    }
}
