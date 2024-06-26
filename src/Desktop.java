public class Desktop extends Devices implements HasMemorySize, HasSSD{

    private int memorySize;
    private int ssdCapacity;

    public Desktop(String category, String type, String id, String brand, String cpuFamily, int memorySize,int ssdCapacity,double price ){

        super(category,type,id,brand,cpuFamily,price);
        setMemorySize(memorySize);
        setSsdCapacity(ssdCapacity);

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
        return ssdCapacity;
    }

    public void setSsdCapacity(int ssdCapacity) {
        this.ssdCapacity = ssdCapacity;
    }

}
