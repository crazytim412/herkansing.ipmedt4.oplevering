package hsleiden.groep6.festival;

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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
//import android.view.Menu;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
//import android.widget.LinearLayout.LayoutParams;

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

public class Acts extends Activity
{
	TextView txt;
	Button button;
	JSONArray jArray;
	ArrayList<Button> knoppen;
	ArrayList <String> evenementen;
	int iz = 1000;
	int please = 1000;
	int buttonid = 1000;
	int graag = 0;
	int stuurid;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		knoppen = new ArrayList<Button>();
		evenementen = new ArrayList<String>();
		
		for (int i = 0; i < 5; i++) 
	    {
	    	RelativeLayout rl = (RelativeLayout) findViewById(R.id.RL1);
	    	button = new Button(this);
	        //button.setText("Evenement" + i);
	        button.setId(buttonid);
	        buttonid++;
	        int width = 1000;
	        int height = 100;
	        
//	        button.setText(result);
	        
	        knoppen.add(button);
	        
	        button.setBackgroundColor(Color.TRANSPARENT);
	        button.setBackgroundResource(R.drawable.rectangle);
	       
	        button.setOnClickListener(new Button.OnClickListener() {
			@Override
				public void onClick(View arg0) {
				// TODO Auto-generated method stub		
		    		Intent intent = new Intent();
		    		intent.setClass(getApplicationContext(), LosseActs.class);
		    		startActivity(intent); 
					}
		    	});
	        
	    	
	        	if (i == 0) 
	        	{
	            
	            RelativeLayout.LayoutParams rlp1 = new RelativeLayout.LayoutParams(
	                    width, height);
	            //rlp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	            rlp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	            rlp1.topMargin = 100;
	            rlp1.bottomMargin = 10;
	            button.setLayoutParams(rlp1);
	            rl.addView(button, rlp1); 
	        	} else {
	            
	            RelativeLayout.LayoutParams rlp1 = new RelativeLayout.LayoutParams(
	                    width, height);
	            rlp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	            rlp1.addRule(RelativeLayout.BELOW, button.getId() - 1);
	            rlp1.bottomMargin = 10;
	            button.setLayoutParams(rlp1);
	            rl.addView(button, rlp1);
	        	}
	    	}
		

		new ShowDialogAsyncTask().execute();   	
	}


	public int setButtonId(int id) {
	
		stuurid = id;
		
		return stuurid;
		
	}


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) 
//    {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    getMenuInflater().inflate(R.menu.main, menu);
//    return true;
//    }

	private class ShowDialogAsyncTask extends AsyncTask<Void, Void, String>
	{

		@Override
		protected void onPreExecute() {
			// update the UI immediately after the task is executed
			super.onPreExecute();
			
			// debug Log.i("test","pre-execute");
			
			for(int pos = 0; pos < knoppen.size(); pos++)
		   	 {
			 Button b = (Button)findViewById(iz);
		   	 b.setText("Even geduld AUB." );
		   	 iz++;
		     }
		}

		@Override
		protected String doInBackground(Void... params) {

			// debug Log.i("test","background");

			InputStream is = null;

			String result = "";
			//data to send
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("test","test"));


			//http post
			try{
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://api.evenementenmail.nl/act.php");
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
					returnString += jArray.getJSONObject(i).getString("naam");
					returnString = returnString.trim();
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
			
			// debug Log.i("test","POST");
			
			//JSONArray jsonArray = (JSONArray)jArray; 
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  } 
			}
			
			
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
