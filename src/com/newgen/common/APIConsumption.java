package com.newgen.common;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Properties;

public class APIConsumption {

    public static final String SPECIAL_STRING = "\",\r\n";

    public String consumeAPI(String request, String process) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmm");
        Properties properties = new Properties();
        StringBuilder response = new StringBuilder();
        String output = "";
        try (
                InputStream input = new FileInputStream(System.getProperty("user.dir") + "/Config/Integration.properties");
        ) {
            properties.load(input);
            String Url = "https://testapi.urlsite.com/api/v1/connect";
            String basicAuthKey = "Basic YnBtOlN5HG4JFDHFG67DBGFG";

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String finalTimestamp = sdf.format(timestamp);

            String data = Base64.getEncoder().encodeToString(request.getBytes());
            String signModel = "{\"Model\":" + request + ",\"TimeStamp\":\"" + finalTimestamp + "\"}";

            String signature = sign(signModel);
            LogMessages.statusLog.info("SIgnature::" + signature);
            String finalPayload = "{\r\n" +
                    " \"FunctionName\": \"" + process + SPECIAL_STRING +
                    " \"Data\": \"" + data + SPECIAL_STRING +
                    " \"Signature\": \"" + signature + SPECIAL_STRING +
                    " \"TimeStamp\": \"" + finalTimestamp + SPECIAL_STRING +
                    "}";


            LogMessages.errorLog.info(Url + "###" + basicAuthKey + "##\n\r" + finalPayload);
            HttpURLConnection conn = getHttpURLConnection(nabilUrl, basicAuthKey);

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(finalPayload);
            out.flush();
            out.close();
            LogMessages.statusLog.info("Inside APIConsumption=" + conn.getResponseCode());

            if (conn.getResponseCode() == 200) {
                LogMessages.statusLog.info("inside 200");
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                while ((output = br.readLine()) != null) {
                    response.append(output + "\n");
                }
                output = "SUCCESS##" + response;
                br.close();
            } else {
                LogMessages.statusLog.info("inside !200");
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
                while ((output = br.readLine()) != null) {
                    response.append(output + "\n");
                }
                output = conn.getResponseCode() + "##" + response;
                br.close();
            }
            LogMessages.errorLog.info("Response=" + response);
        } catch (IOException e) {
            LogMessages.errorLog.info("Exception inside APIConsumption=" + e);
        }
        return output;
    }

    private HttpURLConnection getHttpURLConnection(String requestUrl, String basicAuthKey) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", basicAuthKey);
        conn.setDoOutput(true);
        return conn;
    }

    private RSAPrivateKey readPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String key2 = "-----BEGIN PRIVATE KEY-----\n"
                + "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDHo5iwXwl9l3O+\n"
                + "VqPEKkt0CxYp06KlYQfQBRf6lXpawucsrgEYw8KfgAWqsYvnyMsq97fGeuRQ6mQx\n"
                + "qm2VGc3AVhQv+kqdzXf9ZNrHc5SxKTONjnxUxQV5VCyHD3GbO05YO+a41/9c4zNi\n"
                + "FzbAo/VOoBKtfdXZVEzXOVY2+rMEuBxI2/G7x+xJ/Hk7Uu1qZ4h+TxXCRpJUGgCQ\n"
                + "9OwDRWdsarIh5Ib82gDiWfcTjzCTre9uTQelyOs/tmjAOElUneaChW96ym+SKjbE\n"
                + "/ggUTPUPEATyIZks0nLPkqI8Nc8X0tVlnBQm2urFxzUAvXT/EN5Jt05cSsSF/UWj\n"
                + "0kXOg5NpAgMBAAECggEAYKcquFD6tkxJ5B/sH3/ekesAKvXka67jamnc5xvRZfmw\n"
                + "lGgV7dhw9Gm11KMquefzvPS7i6NZwHcKVSVO8n3QqErEcUn7MBUW/54Bccjadz8c\n"
                + "T29pcF0GvmvrNuRaOy5mSr+Nf3ZLkrwI13ensXJvxEYpZMdkbliq7xQ6Ulnjq+df\n"
                + "mMmFNZxPeyGyNk4h3kfbSacOB3jDRZEt7hbu8wt60kLooXpUWcHu8eIGB5lY4RXY\n"
                + "vm0ZeFXCUHIUNN/KBLPywkYTzo9QPrMWq62Uju0eMObDzgiVN0itDKkl7gunDsPV\n"
                + "csjqT6W3PV1FhjK4s8X9nRHT9qQEothkAPC0HBGUFQKBgQDyCsFuDXOrC22uk1jB\n"
                + "9/nUeaj2jUnjcmTDz3OkLtXGGsOrMEYrMg39LTWI2kpOvqxYxQsRdi/P30ATg983\n"
                + "XdsDrsmswriOU5iIsq1zA/e6bueWzF7kirR04RtlRufNGgdjEZDn9jWSiqLCBcMw\n"
                + "xC0lNcnvzhrPloD+QJggfcbUJwKBgQDTJtmGJhlpa8uj40jkGG206N3sztXquMgh\n"
                + "HJLFvHxc8SQq8xyOgkz7JgjcnhywoL3liPH9PgGaSjsXC6GjON2IMZoIy55RmpXt\n"
                + "0kAOcp2oTmsFQsd646pF9GspwxlQDJGWPlY/ZWusU5n3GoGzyW1XYMIjlImJhd7u\n"
                + "eNIB58NF7wKBgCPB9wWdMyNn0tJhrlEpo10ChDW4ddwGQGC1Oy5cRPS5a4LU5Ojo\n"
                + "wixPC4OmTCgJ/0g2BAaWYUXig4LejpGNahfHMMA3HDDapMQ9MWa2hVijnc8nbjVx\n"
                + "UGub/gIeUYx5dfHq3G+Ibi2yEY18cHO557mBks28szVL1M67GD5kloUtAoGAMqaq\n"
                + "HYT8KY7DLdAyxwyZHIGT4t+G7yReVZsOFkLtrjnmTCs/WWwmXUS1cX64MSKOCwa7\n"
                + "zdzW85UE8bVHk+jrM2V6pst5cefcOU1rMkwKR7pv77YiNfdw/BDyt/TmGI67Scgw\n"
                + "VkrU9mSjlWQVYvRZt1EtoO1bxNKJNLlk3zIiZJ8CgYByWyykxdwu7KUd58AdRRwF\n"
                + "KL66XYTd1Pi29MqKVKsSfucRLUhBPeOcys8Y1OpTQK15sSOWuUerxtRRz+KT8s8t\n"
                + "VIrp6q7s6R7WVRpZIObnCZjkU8kCrlUwTPrcmdUqXgKtRWYF+cn8K40YZMjfiK6g\n"
                + "PYoFfz3HXIMZ7K8QkyUxfw==\n"
                + "-----END PRIVATE KEY-----";

        String privateKeyPEM = key2
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("\n", "")
                .replace("-----END PRIVATE KEY-----", "");

        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    private String sign(String signString) {
        String signedString = null;
        try {
            PrivateKey pkey = readPrivateKey();
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(pkey);
            signature.update(signString.getBytes());
            byte[] encryptedByteValue = Base64.getEncoder().encode(signature.sign());
            signedString = new String(encryptedByteValue, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception inside APIConsumption=" + e);
        }

        return signedString;
    }

}