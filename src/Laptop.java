public class Laptop extends Devices implements HasScreenSize, HasMemorySize, HasSSD{

    private int memorySize;
    private int ssdCapacity;
    private double screenSize;

    public Laptop(String category, String type, String id, String brand, String cpuFamily, int memorySize,int ssdCapacity,double screenSize,double price ){

        super(category,type,id,brand,cpuFamily,price);
        setMemorySize(memorySize);
        setSsdCapacity(ssdCapacity);
        setScreenSize(screenSize);
    }

    @Override
    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }


    @Override
    public int getSsdCapacity() {
        return ssdCapacity ;
    }

    public void setSsdCapacity(int ssdCapacity) {
        this.ssdCapacity = ssdCapacity;
    }

    @Override
    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }
}
