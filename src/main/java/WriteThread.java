import java.util.Date;

public class WriteThread extends Thread{
    private MyFileHandler typeOfFile;
    private int counter;

    public WriteThread(MyFileHandler typeOfFile, int counter){
        this.typeOfFile = typeOfFile;
        this.counter = counter;
//        System.out.println("Costructor" + counter);
    }
    public void run(){

//            while (MyController.index.get() < 300) {
//                Employee employee = new Employee("ahay","gupta", new Date(1999, 8,5), 2);
//                MyController.myCollection.add(employee);
//
//                //Employee employee = MyController.myCollection.get(index.get());
//                //typeOfFile.write(employee);
//                MyController.index.getAndIncrement();
//
//            }
        if(counter == 0) {
            for (int index = 0; index < 100; index++) {
                Employee employee = MyController.myCollection.get();
                //System.out.println(employee);
                //MyController.myCollection.add(employee);
                typeOfFile.write(employee);
            }
        }
        else if(counter ==1) {
            for (int index = 100; index < 200; index++) {
                Employee employee = MyController.myCollection.get();
                //System.out.println(employee);
                //MyController.myCollection.add(employee);
                typeOfFile.write(employee);
            }
        }
        else {
            for (int index = 200; index < 300; index++) {
                Employee employee = MyController.myCollection.get();
                //System.out.println(employee);
                //MyController.myCollection.add(employee);
                typeOfFile.write(employee);
            }
        }

//        while (MyController.counter < 300) {
//            //Employee employee = new Employee("abhay", "gupta", new Date(1999, 8, 5), 2);
//            //System.out.println("Counter "+MyController.counter);
//            Employee employee = MyController.myCollection.get();
//            //System.out.println(employee);
//            //MyController.myCollection.add(employee);
//            typeOfFile.write(employee);
//            MyController.counter++;
//            //System.out.println(MyController.counter + " " + currentThread());
//            //System.out.println(MyController.myCollection.getWriteCounter());
//        }
        //System.out.println("Size of the list "+MyController.myCollection.employeeList.size());
    }

}