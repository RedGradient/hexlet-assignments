package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection implements Connection {

    private Connection connection;

    TcpConnection(String ip, int port) {
        connection = new Disconnected(this);
    }

    public void changeState(Connection conState) {
        this.connection = conState;
    }

    @Override
    public String getCurrentState() {
        return connection.getCurrentState();
    }

    @Override
    public void connect() {
        connection.connect();
    }

    @Override
    public void disconnect() {
        connection.disconnect();
    }

    @Override
    public void write(String text) {
        connection.write(text);
    }
}
// END
