package herkansing.ipmedt4.groep6;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author ipmedt4 groep 6
 * @version 2.0
 * 
 * Wordt niet meer gebruikt
 * Lisa: wel de mogelijkheid tot naar de home pagina en help pagina te gaan ingevoegd incluis de 
 * bijbehorende geluiden, ondanks dat de ToonRoute pagina is weggevallen, voor de zekerheid mocht de 
 * ToonRoute ooit wel gebruikt gaan worden
 */

// Robin

// Hierin zou de route van Google komen te staan
// Maar door tijd gebrek Helaas komen te vervallen

public class ToonRoute extends Activity
{
	// Lisa
	private MediaPlayer mp;	
	// Einde Lisa
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toonroute);
        
        //Lisa
        ImageView imgview1 = (ImageView)findViewById(R.id.ImageView01);
        imgview1.setOnClickListener(new View.OnClickListener() 
        {
        
        	 @Override
             public void onClick(View v) 
        	 {
        		//Lisa: geluid dat je hoort als er op de home button word gedrukt
             	mp = MediaPlayer.create(ToonRoute.this, R.raw.terug);
                mp.setOnCompletionListener(new OnCompletionListener() 
                {
                     public void onCompletion(MediaPlayer mp) 
                     {
                         // TODO Auto-generated method stub
                         mp.release();
                     }

                 });   
                 mp.start();
                 Intent intent = new Intent();
                 intent.setClass(getApplicationContext(), MainActivity.class);
                 startActivity(intent);
             	}
         });
        
        ImageView imgview2 = (ImageView)findViewById(R.id.ImageView02);
        imgview2.setOnClickListener(new View.OnClickListener() 
        {
        
        	 @Override
             public void onClick(View v) 
        	 {
        		//Lisa: geluid voor de button als er geklikt word
            	 mp = MediaPlayer.create(ToonRoute.this, R.raw.klik);
                 mp.setOnCompletionListener(new OnCompletionListener() 
                 {
                     public void onCompletion(MediaPlayer mp) 
                     {
                         // TODO Auto-generated method stub
                         mp.release();
                     }

                 });   
                 mp.start();   
                 Intent intent = new Intent();
                 intent.setClass(getApplicationContext(), Help.class);
                 startActivity(intent);
             }
         });
        
        // Einde Lisa
        // Robin
        
        TextView kaart = (TextView)findViewById(R.id.textView7);
        kaart.setOnClickListener(new View.OnClickListener() 
        {        
        	 @Override
             public void onClick(View v) 
        	 {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ToonKaart.class);
                startActivity(intent);
             }
        });
    }
    	// Einde Robin
}