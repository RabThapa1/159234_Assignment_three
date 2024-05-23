public class Tablet extends Devices{

    private double screenSize;

    public Tablet(String category, String type, String id, String brand, String cpuFamily,double screenSize,double price){
        super(category,type,id,brand,cpuFamily,price);
        setScreenSize(screenSize);
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }
}
