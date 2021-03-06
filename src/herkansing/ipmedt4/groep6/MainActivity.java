package herkansing.ipmedt4.groep6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 *
 * @author Duncan Pronk
 * @author Lisa Uiterwijk
 * @version 2.0
 * 
 * De main activity, hier worden de events ingeladen
 * en in knoppen laten zien
 *
 */

public class MainActivity extends Activity 
{
	
	//maak 2 arraylisten aan voor de knoppen en evenementen
	//De rest zijn speciale ints en hij roept de nieuwe klasse aan
	
	TextView txt;
	Button button;
	JSONArray jArray;
	ArrayList<Button> knoppen;
	ArrayList <String> evenementen;
	int iz = 1000;
	int please = 1000;
	int buttonid = 1000;
	int buttonidtwee = 1000;
	int graag = 0;
	Acts acts; 
	int id;
	
	// Lisa
	private Dialog myDialog;
	private MediaPlayer mp;	
	// Einde Lisa
	
	// Duncan
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// declareer de arraylists en de klasse
		
		knoppen = new ArrayList<Button>();
		evenementen = new ArrayList<String>();
		acts = new Acts();
		
		// maak zolang het resultaat op het scherm past een listview aan
		// en vul deze met de resultaten uit de arraylist
		// dit staat nog in debug mode, met vastte waardes
		
		//Lisa: om naar de Help pagina te gaan
		ImageView imgview2 = (ImageView)findViewById(R.id.ImageView02);
        imgview2.setOnClickListener(new View.OnClickListener() 
        {
           	 @Override
             public void onClick(View v) 
           	 {
        		// Lisa: geluid voor de button als er geklikt word
				 mp = MediaPlayer.create(MainActivity.this, R.raw.klik);
	             mp.setOnCompletionListener(new OnCompletionListener() 
	             {

	                 public void onCompletion(MediaPlayer mp) 
	                 {
	                     // TODO Auto-generated method stub
	                     mp.release();
	                 }

	             });   
	             mp.start();
	             // Einde Lisa 
                 Intent intent = new Intent();
                 intent.setClass(getApplicationContext(), Help.class);
                 startActivity(intent);
             }
         });
		
		// Lisa: Dialog toegevoegd zodat de zoekknop die het niet doet, netjes afgehandeld word d.m.v.
				// een Dialog. Gekozen voor een Dialog, zodat ik zelf de layout van de dialog kan bepalen.
			
        ImageButton myButton = (ImageButton)findViewById(R.id.imageButton6);        
        myButton.setOnClickListener(new OnClickListener() 
        {        	 
        @Override
            public void onClick(View v) 
        	{
	         
        	//Lisa: geluid voor de button als er geklikt word
        	 mp = MediaPlayer.create(MainActivity.this, R.raw.klik);
             mp.setOnCompletionListener(new OnCompletionListener() 
             {
                 public void onCompletion(MediaPlayer mp) 
                 {
                     // TODO Auto-generated method stub
                     mp.release();
                 }

             });   
             mp.start();      
             
        	//Lisa: de dialog aanmaak
        	myDialog = new Dialog(MainActivity.this, R.style.FullHeightDialog); // maak nieuw myDialog aan
	        																		// en haal de default titel weg
	        	myDialog.setContentView(R.layout.dialogzoek);
	        	myDialog.setCancelable(true);
                ImageView imageView = (ImageView)myDialog.findViewById(R.id.imageView1);
                imageView.setOnClickListener(new OnClickListener() 
                {
                @Override
                    public void onClick(View v) 
                	{
                	
                	//Lisa: geluid dat je hoort als er op "Ok" word gedrukt
                	mp = MediaPlayer.create(MainActivity.this, R.raw.terug);
                    mp.setOnCompletionListener(new OnCompletionListener() 
                    {

                        public void onCompletion(MediaPlayer mp) 
                        {
                            // TODO Auto-generated method stub
                            mp.release();
                        }

                    });   
                    mp.start();
                	myDialog.dismiss();
                    }
                }); 
                myDialog.show();
            }
        });
        // Einde Lisa
        
        
		for (int i = 0; i < 7; i++) 
	    {
	    	RelativeLayout rl = (RelativeLayout) findViewById(R.id.RL1); // vindt de layout
	    	
	    	//maak de knop aan
	    	
	    	button = new Button(this);
//	        button.setText("Evenement" + i);	Debug
	        button.setId(buttonid);
	        buttonid++;
	        int width = 1000;
	        int height = 100;
	        
//	        button.setText(result); 	Debug
	        
	        // voeg de knop toe aan de layout
	        
	        knoppen.add(button);
	        
	        // geeft de button een mooie style
	        
	        button.setBackgroundColor(Color.TRANSPARENT);
	        button.setBackgroundResource(R.drawable.rectangle);
	       
	        // handel de button pressed af
	        
	        
	        
	        button.setOnClickListener(new Button.OnClickListener() 
	        {
			@Override
				public void onClick(View arg0) 
				{
				
				// Lisa: geluid voor de button als er geklikt word
				 mp = MediaPlayer.create(MainActivity.this, R.raw.klik);
	             mp.setOnCompletionListener(new OnCompletionListener() 
	             {

	                 public void onCompletion(MediaPlayer mp) 
	                 {
	                     // TODO Auto-generated method stub
	                     mp.release();
	                 }

	             });   
	             mp.start();
	             // Einde Lisa
				
				// kijk welke button ingedrukt is
				// Dit roept de goede query aan in de acts klasse
				
				id = button.getId();
				acts.setButtonId(id);
					
		    		Intent intent = new Intent();
		    		intent.setClass(getApplicationContext(), Acts.class);
		    		startActivity(intent); 
					}
		    	});
	        
	        	// Einde Duncan
	    		// Deborah
	        
	        	// Geef de relative layout de waardes, zodat alles goed staat
	        	// Daarna zet ik de button goed erin met de Rules en Margins
	        	// Als I groter of kleiner is dan 0, dan wordt het scherm aangepast
	        
	        	if (i == 0) 
	        	{
	            
	            RelativeLayout.LayoutParams rlp1 = new RelativeLayout.LayoutParams(
	                    width, height);
	            //rlp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	            rlp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	            rlp1.topMargin = 140; // Lisa: op 140 gezet ipv 100 zodat er genoeg speling zit voor de 
	            					  // knoppen en het zoekveld en de zoekknop
	            				      // Einde Lisa
	            rlp1.bottomMargin = 10;
	            button.setLayoutParams(rlp1);
	            rl.addView(button, rlp1); 
	        	}
	        	
	        	else 
	        	{
	            
	            RelativeLayout.LayoutParams rlp1 = new RelativeLayout.LayoutParams(
	                    width, height);
	            rlp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	            rlp1.addRule(RelativeLayout.BELOW, button.getId() - 1);
	            rlp1.bottomMargin = 10;
	            button.setLayoutParams(rlp1);
	            rl.addView(button, rlp1);
	        	}
	    	}
		
		// Roep de methode aan die de webserver aanroept

		new ShowDialogAsyncTask().execute();   	
	}

	// Einde Deborah
	// Duncan
	
	private class ShowDialogAsyncTask extends AsyncTask<Void, Void, String>
	{
		
		// Een asynctask is een methode om een network thread te draaien op
		// hoofdklasse. Het heeft 3 methodes, maar kan er ook 4 hebben.

		@Override
		protected void onPreExecute() {
			
			// Update de tekst op het scherm, zodra de methode wordt aangeroepen
			// zolang de positie binnen de arraylist valt, wordt de tekst aangehouden
			// Zodra de lijst volledig is, wordt de postExecute aangeroepen
			
			super.onPreExecute();
			
			// debug Log.i("test","pre-execute");	Voor LogCat
			
			for(int pos = 0; pos < knoppen.size(); pos++)
		   	 {
			 Button b = (Button)findViewById(iz);
		   	 b.setText("Even geduld AUB." );
		   	 iz++;
		     }
		}

		// Hierin wordt de webserver aangeroepen, HTTP Post en Get sturen een
		// verzoek naar een bestand en halen het resultaat terug
		// De query zit voor de evenementen in de eve.php op de webserver
		
		@Override
		protected String doInBackground(Void... params) {

			// debug Log.i("test","background");	Voor LogCat

			InputStream is = null;

			String result = "";
			
			// Dit is nodig om aan te sturen, maar in deze klasse 
			// heeft het geen nut, in de andere klassen stuur ik de button id mee, om
			// het goeie evenementen terug te halen
			
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("test","test"));


			// http post, hierin wordt de bestand aangeroepen, zoals eerder gezegd
			// De try is altijd nodig om fouten af te handelen, anders kan de app en de
			// server crashen
			
			try{
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://api.evenementenmail.nl/eve.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
			}
			
			catch(Exception e)
			{
				Log.e("log_tag", "Error in http connection "+e.toString()); // Voor LogCat
			}


			// Gelijk met de get krijgen we een resultaat. hierin wordt het resultaat omgezet
			// naar een string, via een Stringbuilder
			
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				result=sb.toString();
			}
			catch(Exception e)
			{
				Log.e("log_tag", "Error converting result "+e.toString());
			}
			
			// Hier parsen ik de json data, Vanuit de jArray wordt het omgezet in een arraylist
			// Ook trimmen we het resultaat, zodat de Json tekens wegvallen
			
			String returnString = "";
			try
			{
				jArray = new JSONArray(result);
				for(int i=0;i<jArray.length();i++){
					returnString += jArray.getJSONObject(i).getString("naam");
					returnString = returnString.trim();
				}
			}catch(JSONException e){
				Log.e("log_tag", "Error parsing data "+e.toString());
			}
			return returnString; 
		}  
	
		// Zodra de arraylist gevuld is, wordt de arraylist uitgelezen en omgezet
		// naar de textviews. Voor de zekerheid trim ik hier nog een keer en replace
		// ik de rest van de tekens
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			result = result.trim();
			
			// debug Log.i("test","POST");	Voor LogCat

			if (jArray != null) { 
			   int len = jArray.length();
			   for (int i=0;i<len;i++){ 
			    try {
			    	String ajb = jArray.get(i).toString();
			    	String aub = ajb.replace("{", "");
			    	aub = aub.replace("}", "");
			    	aub = aub.replace("\"", "");
			    	aub = aub.replace("naam:", "");
					evenementen.add(aub);
				}
			    catch (JSONException e)
			    {
					e.printStackTrace();
				}
			  } 
			}
			
			// geef elke button een eigen Unieke ID voor de hierboven aangegeven
			// button pressed.
			
		for(int pos = 0; pos < knoppen.size(); pos++)
	   	 {
			Button bt = (Button)findViewById(please);
			bt.setText(evenementen.get(graag) );
			please++;
			graag++;
		}				  
	  }
   }
}