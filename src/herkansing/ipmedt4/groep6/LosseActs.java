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
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 *
 * @author Duncan Pronk
 * @author Lisa Uiterwijk
 * @author Deborah Tjin
 * @version 1.0
 * 
 * hierin worden alle acts gemaakt.
 *
 */

// Deborah en Duncan en Lisa

public class LosseActs extends Activity
{
    TextView naam;
    TextView begintijd;
    TextView eindtijd;
    TextView locatie;
    TextView informatie;
    TextView rating;
    TextView genre;
    ArrayList <String> tekst;
    String naamtekst, begintijdtekst, eindtijdtekst;
    String locatietekst, informatietekst, ratingtekst, genretekst;
    
    public JSONArray jArray;
    public String returnString = null;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.losseacts);
        
        // Vind alle goede textviews en geeft hem een variabelen
        
        naam = (TextView)findViewById(R.id.naam);
        begintijd = (TextView)findViewById(R.id.begintijd);
        eindtijd = (TextView)findViewById(R.id.eindtijd);
        locatie = (TextView)findViewById(R.id.locatie);
        informatie = (TextView)findViewById(R.id.info);
        rating = (TextView)findViewById(R.id.rating);
        genre = (TextView)findViewById(R.id.genre);
        
                
        // maak een nieuwe arraylist aan voor de tekst
        
        tekst = new ArrayList<String>();

        // Kijk welke imageview wordt ingedrukt
        
        ImageView imgview1 = (ImageView)findViewById(R.id.ImageView01);
        imgview1.setOnClickListener(new View.OnClickListener() {
        
        	 @Override
             public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
             	}
         	});
       
       Button toonkaart = (Button)findViewById(R.id.button1);
       toonkaart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               Intent intent = new Intent();
             intent.setClass(getApplicationContext(), ToonKaart.class);
              startActivity(intent);
            	}
        	});
       
       new Async().execute();   	
       
    }
    
     /*Lisa; zo'n beetje alles, behalve de hard neergezette waardes, stonden eerst in comment
   	 door middel van toevoegen van <uses-permission android:name="android.permission.INTERNET"></uses-permission>
     en 
    	<activity
    		android:name="herkansing.ipmedt4.groep6.Acts"
    		android:label="@string/app_name" >
    	</activity> 
    	<activity
            android:name="herkansing.ipmedt4.groep6.LosseActs"
            android:label="@string/app_name" >
        </activity>
        <activity
            android:name="herkansing.ipmedt4.groep6.ToonKaart"
            android:label="@string/app_name" >
        </activity>
        <activity
            android:name="herkansing.ipmedt4.groep6.ToonRoute"
            android:label="@string/app_name" >
        </activity>
    	
     in de AndroidManifest.xml deed onderstaande code het zonder foutmeldingen en kon ik van pagina
     naar pagina. Het enige wat er nog niet werkt, is dat er afzonderlijk per act de juiste gegevens
     worden ingevuld	
    	*/
    
    private class Async extends AsyncTask<Void, Void, String>
	{

		@Override
		protected void onPreExecute() {
			
			super.onPreExecute();
			
			// Zet de textviews op de juiste waardes
			// Dit is nu vaste waarden voor debug mode / prototype
			
			// Lisa: even in comment gezet, omdat ik de default wilde zien
			
			/*naam.setText("Just add Water");
	        begintijd.setText("13.20");
	        eindtijd.setText("13.50");
	        locatie.setText("Katoenpark");
	        informatie.setText("Haagse alternatieve rockband");
	        rating.setText("251");
	        genre.setText("Alternative Rock");*/
			
						
		}	
		
		// zie andere klassen wie wat gedaan heeft
		// en wat het doet

		@Override
		
		//Duncan
		protected String doInBackground(Void... params) 
		{

			
			// debug Log.i("test","background");

			InputStream is = null;

			String result = "";
			//data to send
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("test","test"));


			//http post
			try{
				String url = "http://api.evenementenmail.nl/losseact.php";
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(url);
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();


			}catch(Exception e){
				Log.e("log_tag", "Error in http connection "+e.toString());
			}


			//convert response to string
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				result=sb.toString();
			}catch(Exception e){
				Log.e("log_tag", "Error converting result "+e.toString());
			}
			//parse json data
			String returnString = "";
			try{
				jArray = new JSONArray(result);
				for(int i=0;i<jArray.length();i++)
				{
					JSONObject json_data = jArray.getJSONObject(i);
					Log.i("log_tag"," name: "+json_data.getString("naam")  );
					
					//Get an output to the screen					
				}
			}catch(JSONException e){
				Log.e("log_tag", "Error parsing data "+e.toString());
			}
			return returnString;
		}  
	
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			result = result.trim();
			
			if (jArray != null) { 
				   int len = jArray.length();
				   for (int i=0;i<len;i++){ 
				    try {
				    	String ajb = jArray.get(i).toString();
				    	String aub = ajb.replace("{", "");
				    	aub = aub.replace("}", "");
				    	aub = aub.replace("\"", "");
				    	
				    	if( i == 0)
				    	{
				    	aub = aub.replace("naam:", "");
						tekst.add(aub);
				    	}
				    	else if (i == 1)
				    	{
				    	aub = aub.replace("begintijd:", "");
				    	tekst.add(aub);
				    	}
				    	else if (i == 2)
				    	{
				    	aub = aub.replace("eindtijd:", "");
				    	tekst.add(aub);
				    	}
				    	else if ( i == 3 )
				    	{
				    	aub = aub.replace("adres:", "");
				    	tekst.add(aub);
				    	}
				    	else if( i == 4 )
				    	{
				    	aub = aub.replace("informatie:", "");
				    	tekst.add(aub);
				    	}
				    	else if( i == 5 )
				    	{
				    	aub = aub.replace("rating:", "");
				    	tekst.add(aub);
				    	}
				    	else if( i == 6 )
				    	{
				    	aub = aub.replace("genre:", "");
						tekst.add(aub);
				    	}
					}
				    catch (JSONException e)
				    {
				
						e.printStackTrace();
					}
				  } 
				}
			for(int i = 0; i < tekst.size();i++)
			{
			    naamtekst = tekst.get(i);			   
			    begintijdtekst = tekst.get(i);			
			    eindtijdtekst = tekst.get(i);			
			    locatietekst = tekst.get(i);			    
			    informatietekst = tekst.get(i);			    
			    ratingtekst = tekst.get(i);			    
			    genretekst = tekst.get(i);			    
			    
			}
			
			//Lisa: in comment gezet omdat ik de default wilde zien
			// als dit uit de comment wordt gehaald, zie je onderstaande verschijnen op de pagina
			
			/*naam.setText("Just add Water");
	        begintijd.setText("13.20");
	        eindtijd.setText("13.50");
	        locatie.setText("Katoenpark");
	        informatie.setText("Haagse alternatieve rockband");
	        rating.setText("251");
	        genre.setText("Alternative Rock");*/
	        
	        	
		}
	}
    
   //Einde Lisa & Duncan
}   