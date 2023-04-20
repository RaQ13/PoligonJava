package pl.poligonjava.models;

public class Customer {

    private String firstName = "Ferdek";
    private String lastName = "Kiepski";
    private String comments = "dej mnie to kurde";
    private String companyName = "Bezrobotny";
    private String countrySelect = "Poland";
    private String streetHouseAddress = "Ćwiartki 3";
    private String streetApartmentAddress = "4";
    private String postalCode = "59-003";
    private String city = "Wrocław";
    private String phone = "66666666";
    private String email = "ferderkkiepski@gmail.com";

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountrySelect() {
        return countrySelect;
    }

    public void setCountrySelect(String countrySelect) {
        this.countrySelect = countrySelect;
    }

    public String getStreetHouseAddress() {
        return streetHouseAddress;
    }

    public void setStreetHouseAddress(String streetHouseAddress) {
        this.streetHouseAddress = streetHouseAddress;
    }

    public String getStreetApartmentAddress() {
        return streetApartmentAddress;
    }

    public void setStreetApartmentAddress(String streetApartmentAddress) {
        this.streetApartmentAddress = streetApartmentAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
