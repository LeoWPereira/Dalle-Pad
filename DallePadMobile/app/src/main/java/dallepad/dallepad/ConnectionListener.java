package dallepad.dallepad;

import android.util.Log;

/**
 * Created by luis on 10/04/2016.
 */
public class ConnectionListener {

    MidiPlayer midiPlayer;
    String[] separate = new String[2];
    String convertInt;

    private class CheckMessage implements Runnable {
        private String message;

        public CheckMessage(String message) {
            this.message = message;
        }

        public void run() {

            try {
                separate = message.split(":");
                for(int i=0; i<32;i++) {
                    convertInt = Integer.toString(i);
                    if (separate[1].equals(convertInt)) {
                        midiPlayer.playSound(i, separate[0]);
                    }
                }
            } catch(Exception e) { e.printStackTrace();}
        }
    }
}
