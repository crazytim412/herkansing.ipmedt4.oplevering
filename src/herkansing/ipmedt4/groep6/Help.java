package herkansing.ipmedt4.groep6;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * 
 *
 * @author Lisa Uiterwijk
 * @version 2.0
 * 
 * De help pagina, waarbinnen terug gegaan kan naar de evenementen pagina. In de help.xml is de tekst
 * d.m.v. strings.xml hardcoded weergegeven, omdat ik geen tijd meer had dit te doen via de database
 
 */


// Lisa
public class Help extends Activity
{
	private MediaPlayer mp;	
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		
		// als er geklikt wordt gaat gebruiker terug naar de home/evenementen pagina
		
		ImageView imgview1 = (ImageView)findViewById(R.id.ImageView01);
        imgview1.setOnClickListener(new View.OnClickListener() 
        {
           	 @Override
             public void onClick(View v) 
           	 {
        		//Lisa: geluid dat je hoort als er op de home button word gedrukt
             	mp = MediaPlayer.create(Help.this, R.raw.terug);
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
	}	
	
} // Einde Lisa
