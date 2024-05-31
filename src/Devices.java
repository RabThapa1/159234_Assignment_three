public abstract class Devices {

    private String category;
    private String type;
    private String id;
    private String brand;
    private String cpuFamily;
    private double price;

    public  Devices(String category, String type, String id, String brand, String cpuFamily, double price){
        setCategory(category);
        setType(type);
        setId(id);
        setBrand(brand);
        setCpuFamily(cpuFamily);
        setPrice(price);
      }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCpuFamily() {
        return cpuFamily;
    }

    public void setCpuFamily(String cpuFamily) {
        this.cpuFamily = cpuFamily;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

 }

