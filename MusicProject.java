public class MusicProject extends Project{
    //[2926685]
    public MusicProject(){};

    //Project Specific getters and setters.
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    private String genre;
    /**
     * @return returns the project in String form but with the project specific variable aswell.
     */
    @Override
    public String toString(){
        return  "ID: " + getId()  + " Name: " + getName() + " Production Type: " + getType() + " DateCreated: " + getDate() + " Cost: " +
                getCost() + " Price to Consumer: " + getPriceToConsumer() + " Duration: " + getDuration() + getDurationType() + " Location: "+
                getLocation() + " Venue Size: " + getVenueSize() + " Music Genre: " + getGenre();
    }
}
