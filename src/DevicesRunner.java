import java.io.File;
import java.io.FileNotFoundException;
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

    public void displayAllDevices(){
        for( Devices device : devices ){
            System.out.println(device);
        }
    }

    public static void main(String[] args){
        DevicesRunner devicesRunner = new DevicesRunner();
        devicesRunner.displayAllDevices();
    }
}