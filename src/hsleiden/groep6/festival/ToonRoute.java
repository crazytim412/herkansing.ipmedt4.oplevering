package hsleiden.groep6.festival;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 *
 * @author ipmedt4 groep 6
 * @version 0.1
 * 
 * Wordt niet meer gebruikt
 *
 */
public class ToonRoute extends Activity
{
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toonroute);
        
        ImageView imgview1 = (ImageView)findViewById(R.id.ImageView01);
        imgview1.setOnClickListener(new View.OnClickListener() {
        
        	 @Override
             public void onClick(View v) {
                 // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
             	}
         	});
        
        TextView kaart = (TextView)findViewById(R.id.textView7);
        kaart.setOnClickListener(new View.OnClickListener() {
        
        	 @Override
             public void onClick(View v) {
                 // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ToonKaart.class);
                startActivity(intent);
             	}
         	});
    }

}