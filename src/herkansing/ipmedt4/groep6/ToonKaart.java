package herkansing.ipmedt4.groep6;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

/**
 * 
 * @author Robin van der Harst
 * @author Lisa Uiterwijk
 * @version 2.0
 * 
 * roept de webview aan naar Google Maps
 *
 */

// Robin

public class ToonKaart extends Activity
{
	//Lisa
	private MediaPlayer mp;	
	// Einde Lisa
	
	// Een webview is een ingebouwde methode om een browser te laten zien binnen in de app
	
    WebView webView;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toonkaart);
// einde robin
        
        // Lisa: Ik heb onderstaande code toegevoegd aan deze klasse, zodat er vanaf de kaart ook terug
        // gegaan kan naar de home pagina. Dit was eerst niet mogelijk, nu wel.
        
        ImageView imgview1 = (ImageView)findViewById(R.id.ImageView01);
        imgview1.setOnClickListener(new View.OnClickListener() 
        {        
        	 @Override
             public void onClick(View v) 
        	 {
        		//Lisa: geluid dat je hoort als er op de home button word gedrukt
             	mp = MediaPlayer.create(ToonKaart.this, R.raw.terug);
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
        
               
         // Vindt de webview in de XML
        
         // Lisa: hij laat ook de sateliet weergave van Google Maps zien
         // Einde Lisa
        
        //Robin
        
		webView = (WebView) findViewById(R.id.webView1);
		
		// Google maps is met Javascript gemaakt
		// Voor veiligheid heeft Android dat automatisch uitgeschakeld
		// en geeft het een waarschuwing als je het enabled
		
		webView.getSettings().setJavaScriptEnabled(true);
		
		// Laadt de url in en laat het zien 
		
		webView.loadUrl("http://www.evenementenmail.nl/maps.php");
         }
    
    //Einde robin
}