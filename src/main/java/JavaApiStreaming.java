import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JavaApiStreaming {
    public static void main (String[]args) throws IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {

            // Set these variables to whatever personal ones are preferred
            String domain = "https://stream-fxpractice.oanda.com";
            String access_token = "ACCESS-TOKEN";
            String account_id = "1234567";
            String instruments = "EUR_USD,USD_JPY,EUR_JPY";


            HttpUriRequest httpGet = new HttpGet(domain + "/v1/prices?accountId=" + account_id + "&instruments=" + instruments);
            httpGet.setHeader(new BasicHeader("Authorization", "Bearer " + access_token));

            System.out.println("Executing request: " + httpGet.getRequestLine());

            HttpResponse resp = httpClient.execute(httpGet);
            HttpEntity entity = resp.getEntity();

            if (resp.getStatusLine().getStatusCode() == 200 && entity != null) {
                InputStream stream = entity.getContent();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));

                while ((line = br.readLine()) != null) {

                    Object obj = JSONValue.parse(line);
                    JSONObject tick = (JSONObject) obj;

                    // unwrap if necessary
                    if (tick.containsKey("tick")) {
                        tick = (JSONObject) tick.get("tick");
                    }

                    // ignore heartbeats
                    if (tick.containsKey("instrument")) {
                        System.out.println("-------");

                        String instrument = tick.get("instrument").toString();
                        String time = tick.get("time").toString();
                        double bid = Double.parseDouble(tick.get("bid").toString());
                        double ask = Double.parseDouble(tick.get("ask").toString());

                        System.out.println(instrument);
                        System.out.println(time);
                        System.out.println(bid);
                        System.out.println(ask);
                    }
                }
            } else {
                // print error message
                String responseString = EntityUtils.toString(entity, "UTF-8");
                System.out.println(responseString);
            }

        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }
}
