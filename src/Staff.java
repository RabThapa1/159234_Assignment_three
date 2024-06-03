import java.util.HashSet;

public class Staff {
    private String staffDetails;
    private String userName;
    private String password;
    private HashSet<Staff> staffCredentials;

    public Staff(String staffDetails, String userName, String password ){
        setStaffDetails(staffDetails);
        setUserName(userName);
        setPassword(password);
    }

    public Staff(){

    }

    public String getStaffDetails() {
        return staffDetails;
    }

    public void setStaffDetails(String staffDetails) {
        this.staffDetails = staffDetails;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Method to load the staff credentials
    public void loadStaffCredentials(){
        staffCredentials = new HashSet<>();

        staffCredentials.add(new Staff("staff 1 - Salesperson", "p1", "p1"));
        staffCredentials.add(new Staff("staff 2 - Salesperson", "p2", "p2"));
        staffCredentials.add(new Staff("staff 3 - Salesperson", "p3", "p3"));
        staffCredentials.add(new Staff("staff 4 - Manager", "m1", "m1"));
        staffCredentials.add(new Staff("staff 5 - Manager", "m2", "m2"));

    }

    //Method to get the staff credentials
    public HashSet<Staff> getStaffCredentials(){
        return staffCredentials;
    }
}
