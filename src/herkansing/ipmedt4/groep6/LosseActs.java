package herkansing.ipmedt4.groep6;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

// Deborah en Duncan

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
//               Intent intent = new Intent();
//               intent.setClass(getApplicationContext(), ToonKaart.class);
//               startActivity(intent);
            	}
        	});
       
       new Async().execute();   	
    }

    private class Async extends AsyncTask<Void, Void, String>
	{

		@Override
		protected void onPreExecute() {
			
			super.onPreExecute();
			
			// Zet de textviews op de juiste waardes
			// Dit is nu vaste waarden voor debug mode / prototype
			
			naam.setText("Just add Water");
	        begintijd.setText("13.20");
	        eindtijd.setText("13.50");
	        locatie.setText("Katoenpark");
	        informatie.setText("Haagse alternatieve rockband");
	        rating.setText("251");
	        genre.setText("Alternative Rock");
			
		}
		
		// zie andere klassen wie wat gedaan heeft
		// en wat het doet

		@Override
		protected String doInBackground(Void... params) 
		{

			String returnString = null;
			// debug Log.i("test","background");
/*
			InputStream is = null;

			String result = "";
			//data to send
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("test","test"));


			//http post
			try{
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://api.evenementenmail.nl/losseact.php");
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
				for(int i=0;i<jArray.length();i++){
					//JSONObject json_data = jArray.getJSONObject(i);
					//Log.i("log_tag"," name: "+json_data.getString("naam")  );
					//Get an output to the screen					
				}
			}catch(JSONException e){
				Log.e("log_tag", "Error parsing data "+e.toString());
			}*/
			return returnString;
		}  
	
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
/*			if (jArray != null) { 
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
			naam.setText("Just add Water");
	        begintijd.setText("13.20");
	        eindtijd.setText("13.50");
	        locatie.setText("Katoenpark");
	        informatie.setText("Haagse alternatieve rockband");
	        rating.setText("251");
	        genre.setText("Alternative Rock");*/
		}
	}
}   