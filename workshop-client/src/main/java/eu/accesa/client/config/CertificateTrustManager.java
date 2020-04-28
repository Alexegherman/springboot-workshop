package eu.accesa.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class CertificateTrustManager {

    static final Logger log = LoggerFactory.getLogger(CertificateTrustManager.class);

    private CertificateTrustManager() {
    }

    public static void installTrustAll() {

        // Do not verify host names
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
                // Trust everything

            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
                // Trust everything
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(new KeyManager[0], trustAllCerts, new SecureRandom());
            SSLContext.setDefault(sc);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            log.warn("Trust all SSL cerificates has been installed");
        } catch (Exception e) {
            log.error("Trust installation failed", e);
        }
    }
}
