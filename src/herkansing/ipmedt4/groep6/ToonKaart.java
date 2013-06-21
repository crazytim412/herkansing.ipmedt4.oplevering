package herkansing.ipmedt4.groep6;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * 
 * @author Robin van der Harst
 * @version 1.0
 * 
 * roept de webview aan naar Google Maps
 *
 */

// Robin

public class ToonKaart extends Activity
{

	// Een webview is een ingebouwde methode om een browser te laten zien binnen in de app
	
    WebView webView;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toonkaart);
        
        // Vindt de webview in de XML
        
		webView = (WebView) findViewById(R.id.webView1);
		
		// Google maps is met Javascript gemaakt
		// Voor veiligheid heeft Android dat automatisch uitgeschakeld
		// en geeft het een waarschuwing als je het enabled
		
		webView.getSettings().setJavaScriptEnabled(true);
		
		// Laadt de url in en laat het zien 
		
		webView.loadUrl("http://www.evenementenmail.nl/maps.php");
         }
}