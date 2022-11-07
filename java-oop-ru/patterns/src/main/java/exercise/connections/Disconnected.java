package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {

    private TcpConnection connection;

    public Disconnected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        connection.setState(new Connected(connection));
        System.out.println("Connected");
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void write(String text) {
        System.out.println("Error! Can't write when connection is disconnected");
    }

    @Override
    public String getName() {
        return "disconnected";
    }

}
// END
