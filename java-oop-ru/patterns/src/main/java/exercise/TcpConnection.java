package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {

    private String ip;
    private int port;
    private Connection state;

    TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        state = new Disconnected(this);
    }

    public void setState(Connection conState) {
        state = conState;
    }

    public String getCurrentState() {
        return state.getName();
    }

    public void connect() {
        state.connect();
    }

    public void disconnect() {
        state.disconnect();
    }

    public void write(String text) {
        state.write(text);
    }
}
// END
