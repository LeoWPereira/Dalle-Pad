package dallepad.dallepad;

/**
 * Created by luis on 10/04/2016.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.app.AlertDialog;

public class MidiPlayer{

    private Context context;

    public MidiPlayer(Context current){
        this.context = current;
    }

    private static final String TAG = "soundPool";
    private static final int MAX_STREAMS = 32;
    private static final int SOURCE_QUALITY = 0;
    float leftVolume = 1;
    float rightVolume = 1;

    private int[] sounds = new int[32];
    private int[] streamID = new int[32];
    private int[] loop = new int[32];
    private CharSequence[] effects = {"vocals", "intro", "hihat", "drop_hat", "hadouken", "shoryuken"};
    private CharSequence[] effects2 = {"a1",  "b1", "c1",  "c2", "d1",  "e1", "f1",  "g1", "a1s", "c1s","d1s", "f1s", "g1s"};
    private CharSequence[] effects3 = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11",
            "s12", "s13", "s14", "s15", "s16", "s17", "s18", "s19", "s20", "s21", "s22", "s23", "s24", "s25", "s26","s27", "s28"};
    private CharSequence[] decision = {"Sim", "Não"};
    private int idEffect;

    private SoundPool mySoundPool = new SoundPool(
        MAX_STREAMS,
        AudioManager.STREAM_MUSIC,
        SOURCE_QUALITY);

    public void loadSounds() {
        //        load all the sound clips
        for (int i = 0; i < 13; i++) {
            idEffect = context.getResources().getIdentifier(effects2[i].toString(), "raw", context.getPackageName());
            sounds[i] = mySoundPool.load(context, idEffect, 1);
            sounds[i+13] = mySoundPool.load(context, idEffect, 1);
        }
        for (int i = 0; i < 6; i++) {
            idEffect = context.getResources().getIdentifier(effects2[i].toString(), "raw", context.getPackageName());
            sounds[i+26] = mySoundPool.load(context, idEffect, 1);
        }


        mySoundPool.unload(sounds[24]);
        idEffect = context.getResources().getIdentifier(effects2[2].toString(), "raw", context.getPackageName());
        sounds[24] = mySoundPool.load(context, idEffect, 1);
        mySoundPool.unload(sounds[25]);
        idEffect = context.getResources().getIdentifier(effects2[4].toString(), "raw", context.getPackageName());
        sounds[25] = mySoundPool.load(context, idEffect, 1);
        mySoundPool.unload(sounds[26]);
        idEffect = context.getResources().getIdentifier(effects2[5].toString(), "raw", context.getPackageName());
        sounds[26] = mySoundPool.load(context, idEffect, 1);
        mySoundPool.unload(sounds[27]);
        idEffect = context.getResources().getIdentifier(effects2[6].toString(), "raw", context.getPackageName());
        sounds[27] = mySoundPool.load(context, idEffect, 1);
        mySoundPool.unload(sounds[4]);
        idEffect = context.getResources().getIdentifier(effects2[7].toString(), "raw", context.getPackageName());
        sounds[4] = mySoundPool.load(context, idEffect, 1);
        mySoundPool.unload(sounds[5]);
        idEffect = context.getResources().getIdentifier(effects2[0].toString(), "raw", context.getPackageName());
        sounds[5] = mySoundPool.load(context, idEffect, 1);
        mySoundPool.unload(sounds[6]);
        idEffect = context.getResources().getIdentifier(effects2[1].toString(), "raw", context.getPackageName());
        sounds[6] = mySoundPool.load(context, idEffect, 1);
        mySoundPool.unload(sounds[7]);
        idEffect = context.getResources().getIdentifier(effects2[2].toString(), "raw", context.getPackageName());
        sounds[7] = mySoundPool.load(context, idEffect, 1);
        mySoundPool.unload(sounds[2]);
        idEffect = context.getResources().getIdentifier(effects2[4].toString(), "raw", context.getPackageName());
        sounds[2] = mySoundPool.load(context, idEffect, 1);
        mySoundPool.unload(sounds[10]);
        idEffect = context.getResources().getIdentifier(effects2[4].toString(), "raw", context.getPackageName());
        sounds[10] = mySoundPool.load(context, idEffect, 1);


        for(int i = 0; i<32; i++){
            loop[i] = 0;
        }

    }
    public void playSound(int i, String command) {
        if(loop[i] == -1) {
            if (command.equals("begin")) {
                int priority = 1;
                float frequency = 1;

                mySoundPool.stop(streamID[i]);
                streamID[i] = mySoundPool.play(
                        sounds[i],
                        leftVolume,
                        rightVolume,
                        priority,
                        -1,
                        frequency);
            } else {
                mySoundPool.stop(streamID[i]);

            }
        }
        else{
            if (command.equals("begin")) {
                int priority = 1;
                float frequency = 1;
                mySoundPool.stop(streamID[i]);
                streamID[i] = mySoundPool.play(
                        sounds[i],
                        leftVolume,
                        rightVolume,
                        priority,
                        0,
                        frequency);
            }
        }
    }

    public void changeEffect(final int i){
        AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
        builder2.setTitle("Vai ter loop?");
        builder2.setItems(decision, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if(item==0){
                    loop[i] = -1;
                }
                else{
                    loop[i] = 0;
                }
                dialog.dismiss();
            }
        });
        final AlertDialog alert2 = builder2.create();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Selecione o efeito:");
        builder.setItems(effects, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int item) {
        idEffect = context.getResources().getIdentifier(effects[item].toString(), "raw", context.getPackageName() );
        mySoundPool.unload(sounds[i]);
        sounds[i] = mySoundPool.load(context,idEffect,1);
        alert2.show();
        dialog.dismiss();
        }
        });
        AlertDialog alert = builder.create();
        alert.show();
        };

    public void release(){
        mySoundPool.release();
    }

}
