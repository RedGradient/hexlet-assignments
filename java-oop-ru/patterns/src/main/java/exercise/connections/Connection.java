package exercise.connections;

public interface Connection {
    // BEGIN
    public String getCurrentState();
    public void connect();
    public void disconnect();
    public void write(String text);
    // END
}
