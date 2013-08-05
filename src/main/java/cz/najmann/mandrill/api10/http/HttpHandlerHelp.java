package cz.najmann.mandrill.api10.http;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

class HttpHandlerHelp {

    static byte[] readResponse(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        for (int count; (count = is.read(buffer)) != -1; ) {
            baos.write(buffer, 0, count);
        }
        return baos.toByteArray();
    }

    static void close(Closeable... cs) {
        for (Closeable c : cs) {
            try { if (c != null) c.close(); } catch (IOException ignored) {}
        }
    }
}
