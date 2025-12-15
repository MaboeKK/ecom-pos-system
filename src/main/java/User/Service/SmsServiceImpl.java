package User.Service;

import java.io.IOException;
import java.util.logging.Logger;
import okhttp3.*;

    public class SmsServiceImpl implements SmsService {
        private static final Logger logger = Logger.getLogger(SmsServiceImpl.class.getName());

        // SMS configuration loaded from environment variables for security
        // Set: INFOBIP_API_KEY, INFOBIP_BASE_URL
        private static final String API_KEY = getEnvOrDefault("INFOBIP_API_KEY", "");
        private static final String BASE_URL = getEnvOrDefault("INFOBIP_BASE_URL", "https://api.infobip.com");

        private static String getEnvOrDefault(String key, String defaultValue) {
            String value = System.getProperty(key);
            if (value != null && !value.isEmpty()) return value;
            value = System.getenv(key);
            if (value != null && !value.isEmpty()) return value;
            return defaultValue;
        }

        public void sendSMS(String phoneNumber, String message) {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            String jsonPayload = "{\"messages\":[{\"destinations\":[{\"to\":\"" + phoneNumber + "\"}],\"from\":\"ServiceSMS\",\"text\":\"" + message + "\"}]}";

            RequestBody body = RequestBody.create(mediaType, jsonPayload);
            Request request = new Request.Builder()
                    .url(BASE_URL + "/sms/2/text/advanced")
                    .method("POST", body)
                    .addHeader("Authorization", "App " + API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    logger.severe("Unexpected code " + response);
                    logger.severe("Response Body: " + response.body().string());
                } else {
                    logger.info("Response: " + response.body().string());
                }
            } catch (IOException e) {
                logger.severe("An error occurred while sending the SMS:");
                logger.severe(e.getMessage());
            }
        }
    }