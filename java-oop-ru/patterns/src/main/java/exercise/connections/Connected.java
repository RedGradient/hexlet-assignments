package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {

    private TcpConnection connection;

    Connected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already connected");
    }

    @Override
    public void disconnect() {
        connection.setState(new Disconnected(connection));
        System.out.println("Disconnected");
    }

    @Override
    public void write(String text) {

    }

    @Override
    public String getName() {
        return "connected";
    }
}
// END
