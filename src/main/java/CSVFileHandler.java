
import com.opencsv.CSVWriter;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CSVFileHandler implements MyFileHandler{

    String outputFileName = "employee_output.csv";
    String inputFileName =  new File("employee.csv").getAbsolutePath();
    BufferedReader reader;
    FileReader filereader;
    String line;
    FileWriter outputfile;
//    CSVWriter writer;

    public CSVFileHandler(){

        try {
            filereader = new FileReader(inputFileName);
            outputfile = new FileWriter(outputFileName,true);
//            writer =  new CSVWriter(outputfile);
            reader = new BufferedReader(filereader);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Employee read() {


        try {

            line = reader.readLine();
            if (line!=null) {
                System.out.println(line);
                String row[] = line.split(",");
                String firstName = row[0];
                String lastName = row[1];
                String[] dateArray = row[2].split("/");
                Date date = new Date(Integer.parseInt(dateArray[2]),Integer.parseInt(dateArray[1]),Integer.parseInt(dateArray[0]));
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                String d = dateFormat.format(date);
                double experience = Double.parseDouble(row[3]);

                Employee employee = new Employee();
                employee.setDateOfBirth(date);
                employee.setExperience(experience);
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
//                System.out.println(firstName);
//                System.out.println(lastName);
//                System.out.println(d);
//                System.out.println(experience);
//                System.out.println();

                return employee;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(Employee employee) {

        try {

            // add data to csv
            //System.out.println(employee + "in write csv");



            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String date = dateFormat.format(employee.getDateOfBirth());
            String experience = String.valueOf(employee.getExperience());
            String[] data1 = { employee.getFirstName(),employee.getLastName(),date,experience };
            String d = String.join(",",data1);
           // String data = String.join(employee.getFirstName()+","+employee.getLastName()+","+date+","+experience);
            System.out.println(d + " data set");
            outputfile.write(d);
            outputfile.write("\n");
            outputfile.flush();
            //outputfile.writeNext(d);


            // closing writer connection
            //outputfile.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
