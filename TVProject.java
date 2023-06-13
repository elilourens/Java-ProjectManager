public class TVProject extends Project{ //2926685
    public TVProject(){};

    //Project Specific getters and setters.
    public String getNetwork() {
        return network;
    }
    public void setNetwork(String network) {
        this.network = network;
    }
    private String network;

    /**
     * @return returns the project in String form but with the project specific variable aswell.
     */
    @Override
    public String toString(){
        return  "ID: " + getId()  + " Name: " + getName() + " Production Type: " + getType() + " DateCreated: " + getDate() + " Cost: " +
                getCost() + " Price to Consumer: " + getPriceToConsumer() + " Duration: " + getDuration() + getDurationType() + " Location: "+
                getLocation() + " Venue Size: " + getVenueSize() + "Network: " + getNetwork();
    }
}
