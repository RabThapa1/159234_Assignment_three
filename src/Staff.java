public class Staff {
    private String staffDetails;
    private String userName;
    private String password;

    public Staff(String staffDetails, String userName, String password ){
        setStaffDetails(staffDetails);
        setUserName(userName);
        setPassword(password);
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
}
