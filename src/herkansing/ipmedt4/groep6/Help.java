package herkansing.ipmedt4.groep6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * 
 *
 * @author Lisa Uiterwijk
 * @version 1.0
 * 
 * De help, hier worden de verschillende functionaliteiten en weergaves in de applicatie uitgelegd
 
 */

// Lisa
public class Help extends Activity
{
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		
		// als er geklikt wordt gaat gebruiker terug naar de home
		
		ImageView imgview1 = (ImageView)findViewById(R.id.ImageView01);
        imgview1.setOnClickListener(new View.OnClickListener() {
        
        	 @Override
             public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
             	}
         	});
	}
	

	
	
} // Einde Lisa
