package cz.najmann.mandrill.api10.http;

import cz.najmann.mandrill.api10.MandrillError;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import static cz.najmann.mandrill.api10.http.HttpHandlerHelp.close;
import static cz.najmann.mandrill.api10.http.HttpHandlerHelp.readResponse;

public final class URLConnectionHandler implements HttpHandler {

    @Override
    public SimpleResponse doPost(String url, String content) {
        HttpsURLConnection urlConnection = null;
        OutputStream out = null;
        InputStream in = null;
        try {
            URL link = new URL(url);
            urlConnection = (HttpsURLConnection) link.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setFixedLengthStreamingMode(content.length());
            urlConnection.setRequestProperty("Content-Type", "application/json");
            out = urlConnection.getOutputStream();
            out.write(content.getBytes("UTF-8"));
            in = urlConnection.getInputStream();
            return new SimpleResponse(urlConnection.getResponseCode(), new String(readResponse(in)));
        } catch (IOException e) {
            throw new MandrillError(e);
        } finally {
            close(in, out);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
