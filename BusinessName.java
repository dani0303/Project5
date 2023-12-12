package utils;

public class BusinessName implements Comparable<BusinessName> {
    private String name;
    private String municipality;

    public BusinessName(String name, String municipality) {
        this.name = name;
        this.municipality = municipality;
    }

    public BusinessName(String name) {
        this.name = name;
        this.municipality = null;
    }

    @Override
    public int compareTo(BusinessName o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    public String toString() {
        return "name of the resturant is " + name + " and the city is " + municipality;
    }
}
