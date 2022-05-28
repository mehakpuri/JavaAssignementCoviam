import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCollection{
    private static AtomicInteger writeCounter = new AtomicInteger(0);
    private static AtomicInteger readCounter = new AtomicInteger(0);
    private static AtomicInteger counter = new AtomicInteger(0);
    private static List<Employee> employeeList = new Vector<>();

    public static synchronized void add(Employee employee){
        //System.out.println("Add employee "+employee);
        employeeList.add(employee);
        //System.out.println("    " +employeeList.get(0));
        //System.out.println(employeeList.size());
        //System.out.println(employee + " " + writeCounter.get() + " inside add method " + java.lang.Thread.currentThread());
        writeCounter.getAndIncrement();

    }

    public static Employee get(){
       // System.out.println(employeeList.size());
        readCounter.getAndIncrement();
        Employee employee = employeeList.get(counter.get());
        counter.getAndIncrement();
        return employee;
    }

    public int getWriteCounter(){
        return writeCounter.get();
    }

    public int getReadCounter(){
        return readCounter.get();
    }

}
