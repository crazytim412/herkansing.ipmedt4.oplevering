package herkansing.ipmedt4.groep6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.app.Activity;
import android.content.Intent;

/**
 * 
 *
 * @author Robin van der Harst
 * @version 1.0
 * 
 * roept de webview aan naar Google Maps
 *
 */

public class ToonKaart extends Activity
{
	
    WebView browser;
    WebView webView;
	
    /**
     * @param onCreate
     * 
     * Hier wordt alles aangeroepen
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toonkaart);
        
//        ImageView imgview1 = (ImageView)findViewById(R.id.ImageView01);
//        imgview1.setOnClickListener(new View.OnClickListener() {
//        
//        	 @Override
//             public void onClick(View v) {
//                 // TODO Auto-generated method stub
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//             	}
//         	});
        
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://www.evenementenmail.nl/maps.php");
		
/*        Uri uri = Uri.parse("http://www.evenementenmail.nl/maps.php");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);*/
		

         }
         
        
    

}
