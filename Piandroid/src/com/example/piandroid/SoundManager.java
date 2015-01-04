package com.example.piandroid;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
//import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SoundManager extends Activity{

	public static SoundPool mSoundPool;
	@SuppressWarnings("rawtypes")
	public static HashMap mSoundPoolMap;
	public static AudioManager mAudioManager;
	public Context mContext;
	public SoundManager mSoundManager;
		
	//Handler handler;
	
	boolean isRecording = false,isPlaying = false;
	public File recordingFile;
	int frequency = 8000,channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
	int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
	public TextView statusText;
	public Button startRecordingButton, stopRecordingButton, startPlaybackButton,stopPlaybackButton;
	public PlayAudio playTask;
	public RecordAudio recordTask;
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        //handler = new Handler();
        //refreshFromFeed();
        
        mSoundManager = new SoundManager();
        mSoundManager.initSounds(getBaseContext());
        mSoundManager.addSound(1, R.raw.c);
        mSoundManager.addSound(2, R.raw.cs);
        mSoundManager.addSound(3, R.raw.d);
        mSoundManager.addSound(4, R.raw.ds);
        mSoundManager.addSound(5, R.raw.e);
        mSoundManager.addSound(6, R.raw.f);
        mSoundManager.addSound(7, R.raw.fs);
        mSoundManager.addSound(8, R.raw.g);
        mSoundManager.addSound(9, R.raw.gs);
        mSoundManager.addSound(10, R.raw.a);
        mSoundManager.addSound(11, R.raw.as);
        mSoundManager.addSound(12, R.raw.b);
        mSoundManager.addSound(13, R.raw.c_2);
        mSoundManager.addSound(14, R.raw.cs_2);
        mSoundManager.addSound(15, R.raw.d_2);
        mSoundManager.addSound(16, R.raw.ds_2);
        mSoundManager.addSound(17, R.raw.e_2);
        mSoundManager.addSound(18, R.raw.f_2);
        mSoundManager.addSound(19, R.raw.fs_2);
        mSoundManager.addSound(20, R.raw.g_2);
        mSoundManager.addSound(21, R.raw.gs_2);
        mSoundManager.addSound(22, R.raw.a_2);
        mSoundManager.addSound(23, R.raw.as_2);
        mSoundManager.addSound(24, R.raw.b_2);
        
        
        statusText = (TextView) this.findViewById(R.id.StatusTextView);
        
        startRecordingButton = (Button) this.findViewById(R.id.StartRecordingButton);
	    stopRecordingButton = (Button) this.findViewById(R.id.StopRecordingButton);
	    startPlaybackButton = (Button) this.findViewById(R.id.StartPlaybackButton);
	    stopPlaybackButton = (Button) this.findViewById(R.id.StopPlaybackButton);
	    
	    stopRecordingButton.setEnabled(false);
	    startPlaybackButton.setEnabled(false);
	    stopPlaybackButton.setEnabled(false);
	    
	    File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/com.apress.proandroidmedia.ch07.altaudiorecorder/files/");
	    path.mkdirs();
	    try {
	    	recordingFile = File.createTempFile("recording", ".mp3", path);
	    } catch (IOException e) {
	    	throw new RuntimeException("Couldn't create file on SD card", e);
	    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
/*
 * initSounds inicializa los sonidos, 
 */
    
	@SuppressWarnings("rawtypes")
	public void initSounds(Context theContext) {
	    mContext = theContext;
	    mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
	    mSoundPoolMap = new HashMap();
	    mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
	}

	/*
	 * Agrega un sonido para que pueda ser utilizado
	 */
	@SuppressWarnings("unchecked")
	public void addSound(int index, int SoundID){
	    mSoundPoolMap.put(index, mSoundPool.load(mContext, SoundID, 1));
	}
	
	/*
	 * le da play al sonido
	 */
	public void onCClick(View view){
		int index = 1;
		switch(view.getId()){
		case 0x7f080005:
			index = 1;
			break;
		case 0x7f080006:
			index = 2;
			break;
		case 0x7f080007:
			index = 3;
			break;
		case 0x7f080008:
			index = 4;
			break;
		case 0x7f080009:
			index = 5;
			break;
		case 0x7f08000a:
			index = 6;
			break;
		case 0x7f08000b:
			index = 7;
			break;
		case 0x7f08000c:
			index = 8;
			break;
		case 0x7f08000d:
			index = 9;
			break;
		case 0x7f08000e:
			index = 10;
			break;
		case 0x7f08000f:
			index = 11;
			break;
		case 0x7f080010:
			index = 12;
			break;
		case 0x7f080011:
			index = 13;
			break;
		case 0x7f080012:
			index = 14;
			break;
		case 0x7f080013:
			index = 15;
			break;
		case 0x7f080014:
			index = 16;
			break;
		case 0x7f080015:
			index = 17;
			break;
		case 0x7f080016:
			index = 18;
			break;
		case 0x7f080017:
			index = 19;
			break;
		case 0x7f080018:
			index = 20;
			break;
		case 0x7f080019:
			index = 21;
			break;
		case 0x7f08001a:
			index = 22;
			break;
		case 0x7f08001b:
			index = 23;
			break;
		case 0x7f08001c:
			index = 24;
			break;
		default:
			break;
		}
		float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		streamVolume = streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		mSoundPool.play((Integer) mSoundPoolMap.get(index), streamVolume, streamVolume, 1, 0, 1f);
	}
	
/*	public void playLoopedSound(int index){
	    float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	    streamVolume = streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	    mSoundPool.play((Integer) mSoundPoolMap.get(index), streamVolume, streamVolume, 1, -1, 1f);
	}
*/	
/*
 *  RECORD
 */
	
	public void record(View view) {
		startRecordingButton.setEnabled(false);
		stopRecordingButton.setEnabled(true);
	    startPlaybackButton.setEnabled(false);
	    recordTask = new RecordAudio();
	    recordTask.execute();
	}	
/*
 * STOP RECORDING
 */
	public void stopRecording(View view) {
		isRecording = false;
	}
/*
 * PLAY
 */
	public void play(View view) {
	    startPlaybackButton.setEnabled(false);
	    stopPlaybackButton.setEnabled(true);
	    playTask = new PlayAudio();
	    playTask.execute();
	}
/*
 * STOP PLAYBACK
 */
	public void stopPlaying(View view) {
		isPlaying = false;
		stopPlaybackButton.setEnabled(false);
		startPlaybackButton.setEnabled(true);
	}
/*
 * CLASES
 * 	RecordAudio
 * 	PlayAudio
 */
	public class RecordAudio extends AsyncTask<Void, Integer, Void> {
		@SuppressLint("UseValueOf")
		@Override
		protected Void doInBackground(Void... params) {
			isRecording = true;
			try {
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(recordingFile)));
				int bufferSize = AudioRecord.getMinBufferSize(frequency,channelConfiguration, audioEncoding);
				AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, frequency,channelConfiguration, audioEncoding, bufferSize);

				short[] buffer = new short[bufferSize];
				audioRecord.startRecording();
				int r = 0;
				while (isRecording) {
					int bufferReadResult = audioRecord.read(buffer, 0,bufferSize);
					for (int i = 0; i < bufferReadResult; i++) {
						dos.writeShort(buffer[i]);
					}
					publishProgress(new Integer(r));
					r++;
				}
				audioRecord.stop();
				dos.close();
			} catch (Throwable t) {
				Log.e("AudioRecord", "Recording Failed");
			}
			return null;
		}
		protected void onProgressUpdate(Integer... progress) {
			statusText.setText(progress[0].toString());
		}
		protected void onPostExecute(Void result) {
			startRecordingButton.setEnabled(true);
			stopRecordingButton.setEnabled(false);
			startPlaybackButton.setEnabled(true);
		}
	}

/*
 *===================================================================================
 */
	
	private class PlayAudio extends AsyncTask<Void, Integer, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			isPlaying = true;
	
			int bufferSize = AudioTrack.getMinBufferSize(frequency,channelConfiguration, audioEncoding);
			short[] audiodata = new short[bufferSize / 4];

			try {
				DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(recordingFile)));
				AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, frequency,channelConfiguration, audioEncoding, bufferSize,AudioTrack.MODE_STREAM);
				audioTrack.play();
				while (isPlaying && dis.available() > 0) {
					int i = 0;
					while (dis.available() > 0 && i < audiodata.length) {
						audiodata[i] = dis.readShort();
						i++;
					}
					audioTrack.write(audiodata, 0, audiodata.length);
					//stopPlaybackButton.setEnabled(false);
				}
				isPlaying = false;
				
				dis.close();
				startPlaybackButton.setEnabled(false);
				stopPlaybackButton.setEnabled(true);
			} catch (Throwable t) {
				//stopPlaybackButton.setEnabled(true);
				Log.e("AudioTrack", "Error");
			}
			return null;
		}
	}
}


