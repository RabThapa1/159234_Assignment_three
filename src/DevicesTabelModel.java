import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DevicesTabelModel extends AbstractTableModel {

    private String[] columnNames = {"Category", "Type", "ID", "Brand","CPU Family", "Price($)"};
    private List<Devices> devices;

    public DevicesTabelModel(List<Devices> devices){
        setDevices(devices);
    }

    public void setDevices(List<Devices> devices) {
        this.devices = devices;
    }


    @Override
    public int getRowCount(){
        return devices != null ? devices.size() : 0;
    }

    @Override
    public int getColumnCount(){
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Devices device = devices.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> device.getCategory();
            case 1 -> device.getType();
            case 2 -> device.getId();
            case 3 -> device.getBrand();
            case 4 -> device.getCpuFamily();
            case 5 -> device.getPrice();
            default -> null;
        };
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }


}
