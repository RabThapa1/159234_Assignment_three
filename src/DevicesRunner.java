import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class DevicesRunner {

    private LinkedList<Devices> devices;


    public DevicesRunner(){

        devices = new LinkedList<>();

        try{

            File file = new File("D:\\Massey\\ObjectOrientedProgramming_159234\\Git_Assignment_three\\src\\computers.txt");
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] words = line.split(",");
                if(words[0].equalsIgnoreCase("Desktop PC")){
                    devices.add(new Desktop(words[0], words[1], words[2],words[3],words[4],Integer.parseInt(words[5]),Integer.parseInt(words[6]),Double.parseDouble(words[7])));

                } else if (words[0].equalsIgnoreCase("Laptop")) {
                    devices.add(new Laptop(words[0], words[1], words[2],words[3],words[4],Integer.parseInt(words[5]),Integer.parseInt(words[6]),Double.parseDouble(words[7]),Double.parseDouble(words[8])));
                }else{
                    devices.add(new Tablet(words[0], words[1], words[2],words[3],words[4],Double.parseDouble(words[5]),Double.parseDouble(words[6])));
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found: " +e.getMessage() );
        }
    }

   //Method to return the devices linked list
    public LinkedList<Devices> getDevices(){
        return devices;
    }

    //Method to add devices
    public void addDevice(String category, String type, String id,String brand, String cpuFamily, int memorySize,int ssdCapacity,double  screenSize,double price){

        boolean isUnique = checkID(id);
        if(isUnique){
            switch (category) {
                case "Laptop" -> {
                    devices.add(new Laptop(category, type, id, brand, cpuFamily, memorySize, ssdCapacity, screenSize, price));

                }
                case "Desktop PC" ->
                        devices.add(new Desktop(category, type, id, brand, cpuFamily, memorySize, ssdCapacity, price));
                case "Tablet" -> devices.add(new Tablet(category, type, id, brand, cpuFamily, screenSize, price));
            }
            JOptionPane.showMessageDialog(null, "The record for the computer is added successfully");

        }else{
            JOptionPane.showMessageDialog(null,"Model ID conflict, Device can't be added.");
        }
    }

    //Method to check if Model ID is unique
    private boolean checkID(String id){
        boolean isUnique = true;
        for(Devices aDevice : devices){
            if(aDevice.getId().equals(id)){
                isUnique = false;
                break;
            }
            }
        return isUnique;
        }

        //Method to delete the entry
        public void deleteDevice(String id){

        Iterator<Devices> iterator = devices.iterator();
        while (iterator.hasNext()){
            Devices aDevice = iterator.next();
            if(aDevice.getId().equals(id)){
                iterator.remove();
                JOptionPane.showMessageDialog(null, "Device with id "+ id + " deleted successfully.");
                break;
            }
        }
        }

    }





