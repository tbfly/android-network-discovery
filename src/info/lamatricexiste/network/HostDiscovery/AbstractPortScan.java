package info.lamatricexiste.network.HostDiscovery;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.util.Log;

public abstract class AbstractPortScan extends AsyncTask<Void, Integer, Void> {

    private final String TAG = "AbstractPortScan";
    private int step;
    private long time;

    protected int port_start;
    protected int port_end;
    protected int nb_port;
    protected String host;
    protected int TIMEOUT_SOCKET = 1000;

    protected AbstractPortScan(String host) {
        this.host = host;
    }
    
    protected AbstractPortScan(String host, int timeout) {
        this.host = host;
        this.TIMEOUT_SOCKET = timeout;
    }
    
    abstract protected void stop();
    abstract protected void start(InetAddress ina, final int PORT_START, final int PORT_END) throws InterruptedException, IOException;

    protected Void doInBackground(Void... params) {
        try {
            step = 127;
            InetAddress ina = InetAddress.getByName(host);
            if (nb_port > step) {
                for (int i = port_start; i <= port_end - step; i += step + 1) {
                    time = System.currentTimeMillis();
                    start(ina, i, i + ((i + step <= port_end - step) ? step : port_end - i));
                }
            } else {
                time = System.currentTimeMillis();
                start(ina, port_start, port_end);
            }
        } catch (UnknownHostException e) {
            publishProgress((int) -1, (int) -1);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            stop();
        }
        return null;
    }

    protected void cancelTimeouts() throws IOException {
        if ((System.currentTimeMillis() - time) > TIMEOUT_SOCKET) {
            stop();
        }
    }

    protected void onCancelled() {
        stop();
    }
}

