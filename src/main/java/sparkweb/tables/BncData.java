package sparkweb.tables;

public class BncData {
    private Double beds;
    private String neighbourhood;
    private Double count;

    public Double getBeds() {
        return beds;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public void setBeds(Double beds) {
        this.beds = beds;
    }
}
