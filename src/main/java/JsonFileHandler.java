import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JsonFileHandler implements MyFileHandler {
    @SuppressWarnings("unchecked")
    public static ArrayList<Employee> employeeList = new ArrayList<>();
    String path;
    FileReader reader;
    JSONParser jsonParser;
    FileWriter file;
    public static int counter=0;
    public static int writeCounter=0;
    public JsonFileHandler(){
        path = new File("employee.json").getAbsolutePath();
        try {
            reader = new FileReader(path);
            jsonParser = new JSONParser();
            file = new FileWriter("writtenEmployees.json",true);
        }catch(Exception e){
            e.printStackTrace();
        }
        addCharacter("[",false);
    }


    @Override
    public Employee read() {
        if(counter == 0)
        {
            readJSON();
        }
        counter=counter+1;
        return  employeeList.get(counter-1);
    }

    @Override
    public void write(Employee employee) {

        System.out.println(employee);
        writeFile(employee,writeCounter);

    }

    public static Date stringToDateFunction(String date) throws Exception
    {
        String sDate1=date;
        Date date1=new SimpleDateFormat("MM/dd/yy").parse(date);
        return date1;
    }

    public void readJSON() {

        try ( FileReader reader = new FileReader(path) )
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over employee array
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseEmployeeObject(JSONObject employee)
    {
        //Get employee object within list
        JSONObject employeeObject = employee;

        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");
        System.out.println(firstName);

        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);

        String date = ((String) employeeObject.get("dateOfBirth"));
        Date date1= null;
        try {
            date1 = stringToDateFunction(date);
            System.out.println(date1);
        }
        catch(Exception exp){
            exp.printStackTrace();
        }


        //Get employee last name
        double experience = new Double((long)employeeObject.get("experience"));
        System.out.println(experience);

        Employee newemployee = new Employee();
        newemployee.setFirstName(newemployee.getFirstName());
        newemployee.setExperience(newemployee.getExperience());
        newemployee.setLastName(newemployee.getLastName());
        newemployee.setDateOfBirth(newemployee.getDateOfBirth());
        employeeList.add(newemployee);
        System.out.println("\n");
    }


    public void addCharacter(String string,boolean append)
    {
        try (FileWriter file = new FileWriter("writtenEmployees.json",append)) {

            file.write(string);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeFile(Employee employee,int counter)
    {
        JSONObject employeeDetails = new JSONObject();

        employeeDetails.put("firstName", employee.getFirstName());
        employeeDetails.put("lastName", employee.getLastName());
        employeeDetails.put("experience",employee.getExperience() );

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        String strDate = dateFormat.format(employee.getDateOfBirth());
        employeeDetails.put("date",strDate);
        System.out.println(strDate);

        //Write JSON file
        try (FileWriter file = new FileWriter("writtenEmployees.json",true)) {

            file.write(employeeDetails.toJSONString());
            file.flush();
            System.out.println(counter);

            if(counter!=100) {
                file.write(",");
                file.flush();
            }
            else {
                file.write(",");
                file.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
