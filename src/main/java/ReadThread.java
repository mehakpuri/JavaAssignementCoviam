public class ReadThread extends Thread{
    private MyFileHandler typeOfFile;
    public ReadThread(MyFileHandler typeOfFile){
        this.typeOfFile = typeOfFile;
    }
    public void run(){

        for(int index = 0;index<100;index++) {
            //System.out.println(typeOfFile);
            Employee employee = typeOfFile.read();
            MyController.myCollection.add(employee);
            // System.out.println(index + "from " + currentThread());
        }

    }
}
