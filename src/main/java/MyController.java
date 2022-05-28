import java.util.concurrent.atomic.AtomicInteger;

public class MyController {
    public static MyCollection myCollection = new MyCollection();
    public static int counter = 0;
    public static void main(String[] args) {
        MyFileHandler csvFileHandler= new CSVFileHandler();
        MyFileHandler xmlFileHandler = new XMLFileHandler("employee.xml");
        MyFileHandler jsonFileHandler = new JsonFileHandler();

        Thread csv = new Thread(new ReadThread(csvFileHandler));
        Thread xml = new Thread(new ReadThread(xmlFileHandler));
        Thread json = new Thread(new ReadThread(jsonFileHandler));

        csv.start();
        xml.start();
        json.start();


        try {
            csv.join();
            xml.join();
            json.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        csv = new Thread(new WriteThread(csvFileHandler, counter));
        xml = new Thread(new WriteThread(xmlFileHandler, counter));
        json = new Thread(new WriteThread(jsonFileHandler, counter));

        csv.start();
        xml.start();
        json.start();


    }
}
