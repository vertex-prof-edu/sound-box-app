package vertex.pro.edu.soung_box_app.common.utils;

import com.google.common.collect.Sets;
import lombok.SneakyThrows;

import java.net.ServerSocket;
import java.util.Set;

public class PortProvider {

    private static final Set<Integer> ALLOCATED_PORT = Sets.newConcurrentHashSet();

    public int getAvailablePort() {
        synchronized (PortProvider.class) {
            int port = findPort();
            if (ALLOCATED_PORT.contains(port)) {
                return getAvailablePort();
            }
            ALLOCATED_PORT.add(port);
            return port;
        }
    }

    @SneakyThrows
    private int findPort() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(0);
            return serverSocket.getLocalPort();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

    public void releasePort(Integer port) {
        synchronized (PortProvider.class) {
            ALLOCATED_PORT.remove(port);
        }
    }
}
