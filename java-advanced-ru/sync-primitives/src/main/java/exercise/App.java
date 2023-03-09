package exercise;

class App {

    public static void main(String[] args) throws InterruptedException {
        // BEGIN
        var list = new SafetyList();

        var listThread_1 = new ListThread(list);
        var listThread_2 = new ListThread(list);

        listThread_1.start();
        listThread_2.start();

        listThread_1.join();
        listThread_2.join();

        System.out.println("Size: " + list.getSize());
        // END
    }
}

