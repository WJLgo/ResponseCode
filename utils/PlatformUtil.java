package com.hhu.ts.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * @author lxb
 * @description: TODO
 * @date 2020/11/18 16:27
 */
@Component
public class PlatformUtil {
//    private static final String aksk = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCUNxSv7nres3wbQdgFzaZP4WFdznWEDcq+1wotFoQH1r/0E/oVlt0oLnkI05mYrH/xgKEiE4LtNhizgFqJ5Hl+1FhEia9NpX6kmTJ4FPrLonKn69v2GUBFelKBvrc5eLxDnc4xZmTr7g0TMccixYAL29xLuzT+PBSUs7k75TqXxKqcEhX/hd4oY0wRccjIyZBi3gwuAOnorSgMmBDQbZLirwgvb7c8eb+oOEBVpAZZdW60+ulpRbJjrOY9oJiiBiG6Flumv3O4BjgZKJyO0FFzfUOe1jZDJ4oskdq4msYVliK9w3brShICfrWarKNrCPtvPpZRJaERo9IdSgyuRBLDAgMBAAECggEAA5EDvWwGpt84yEsgUJtvOhLZTfGO4FuYmlkAVK9rA+lF+zCT3vsfXT0pCm5VDthvx7bpqbFAgEbB92fKRfEqf0PKz/NkNYEa+Aj7ruoKUuYYzjOdumFHK6TS4W5eaFKgbRwZ/xRQr2CuDd0vxoVsHFzxAx2BG/EEKcPdXDSa4VX/vBZ5ye3VzLJnllheJ1mZQwZ+9tlUQ7JjL6WQ2EoFyNP+O4pgSISpur04P3BmW5rYJus4ApaqfojLXnF0USKZgRVJN15vTqof2EFQskoQDpRmDOjoYlBjG0iVHWCGKmoBXulVuX8n9IaUQH/6G6GuNe/c+1OYkCFWuACyRaWQeQKBgQDYKvVyQ+izyI5gdBKexT1SPKohLmYgvT1tvIkA5t0z1M+7DEN2vBf7HmCEWsbhb/T6dOeEBkLLuBSM1FCPgKgc/5oRDCC2SJpC4E4JTBsF8GT8GIURua7EWGoDhJLxEhsgjoiWA5LeSLIXLTosw8o381JlSSMjOEidOxZH9ZMcVQKBgQCvhqsGsMxy2d+Wl7KFxzMvRovniJEHA7iicbPAOuohOrZiJ3UBiIHem963fW/tOx9rCSWWxHbLNBIdeMNU2Csasep3oFu/sRjCrLvm7TKPbPGKrkH/fSvrtLahJxpAAF5PeADSfBuc+DYCaYOSedN8mz2yN83p2GXx8l5BVoGKtwKBgQCttRe30spUbn27FItB+s3kH8u3DkoTVP7gB/kwOFTZKdIkRR6Rc2Tx7t8wfKIE1qrN+NNyTHWwryQUZyi1qHVvmBZ+THidAoqz4vvSjM3KX/tGeY21SLkgHsxSN169SgdyihdINWb4KQKzjSyKfGsruuQjcDpEOx5e+vRUrwqgYQKBgQCjE9JoV/DLsEL8rhouEqIqhTfMuORNiRaMzmj764DFuL/kt9YMw6Ati9HD1uYbmbeaAZiL2CKqrsk52o5YkLKTjcNSu4kis05W6Jd3eN9lxAkyGK62ybv5tR+M4ECSQAVffhX9eVNKZo3/dbrJZyind7A9mX1VizlELjOGLvISRwKBgQC1TMEFOZmCc7EO+V8rOylB3x7oLYFEUd2yQ9dtM7rJTE1pwPvHwfQhJkk+AEs2D3rJeDCPyNTztZW3yvcP1zoLrNqfUf6j4nsvIIE4ZnvVM5cDkpn6Mn7P6rvWEi4Enk0VnQxLiTae7jlq7715J273Gb2Iecok3igMwF4//thkEw==";
//    private static final String userId = "8517f8d90224508f571512befdc39a1d";
//    public static final String address = "http://192.168.10.75:30003";


    private static String aksk;

    private static String userId;

    private static String url;

    private static String port;

    @Autowired
//    private PlatformUtil(@Value("${dsep.url}") String url, @Value("${dsep.port}") String port,
//                         @Value("${dsep.userId}") String userId, @Value("${dsep.privateKey}") String privateKey) {
    private PlatformUtil(@Value("192.168.10.75") String url, @Value("30003") String port,
                         @Value("8517f8d90224508f571512befdc39a1d") String userId, @Value("MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCUNxSv7nres3wbQdgFzaZP4WFdznWEDcq+1wotFoQH1r/0E/oVlt0oLnkI05mYrH/xgKEiE4LtNhizgFqJ5Hl+1FhEia9NpX6kmTJ4FPrLonKn69v2GUBFelKBvrc5eLxDnc4xZmTr7g0TMccixYAL29xLuzT+PBSUs7k75TqXxKqcEhX/hd4oY0wRccjIyZBi3gwuAOnorSgMmBDQbZLirwgvb7c8eb+oOEBVpAZZdW60+ulpRbJjrOY9oJiiBiG6Flumv3O4BjgZKJyO0FFzfUOe1jZDJ4oskdq4msYVliK9w3brShICfrWarKNrCPtvPpZRJaERo9IdSgyuRBLDAgMBAAECggEAA5EDvWwGpt84yEsgUJtvOhLZTfGO4FuYmlkAVK9rA+lF+zCT3vsfXT0pCm5VDthvx7bpqbFAgEbB92fKRfEqf0PKz/NkNYEa+Aj7ruoKUuYYzjOdumFHK6TS4W5eaFKgbRwZ/xRQr2CuDd0vxoVsHFzxAx2BG/EEKcPdXDSa4VX/vBZ5ye3VzLJnllheJ1mZQwZ+9tlUQ7JjL6WQ2EoFyNP+O4pgSISpur04P3BmW5rYJus4ApaqfojLXnF0USKZgRVJN15vTqof2EFQskoQDpRmDOjoYlBjG0iVHWCGKmoBXulVuX8n9IaUQH/6G6GuNe/c+1OYkCFWuACyRaWQeQKBgQDYKvVyQ+izyI5gdBKexT1SPKohLmYgvT1tvIkA5t0z1M+7DEN2vBf7HmCEWsbhb/T6dOeEBkLLuBSM1FCPgKgc/5oRDCC2SJpC4E4JTBsF8GT8GIURua7EWGoDhJLxEhsgjoiWA5LeSLIXLTosw8o381JlSSMjOEidOxZH9ZMcVQKBgQCvhqsGsMxy2d+Wl7KFxzMvRovniJEHA7iicbPAOuohOrZiJ3UBiIHem963fW/tOx9rCSWWxHbLNBIdeMNU2Csasep3oFu/sRjCrLvm7TKPbPGKrkH/fSvrtLahJxpAAF5PeADSfBuc+DYCaYOSedN8mz2yN83p2GXx8l5BVoGKtwKBgQCttRe30spUbn27FItB+s3kH8u3DkoTVP7gB/kwOFTZKdIkRR6Rc2Tx7t8wfKIE1qrN+NNyTHWwryQUZyi1qHVvmBZ+THidAoqz4vvSjM3KX/tGeY21SLkgHsxSN169SgdyihdINWb4KQKzjSyKfGsruuQjcDpEOx5e+vRUrwqgYQKBgQCjE9JoV/DLsEL8rhouEqIqhTfMuORNiRaMzmj764DFuL/kt9YMw6Ati9HD1uYbmbeaAZiL2CKqrsk52o5YkLKTjcNSu4kis05W6Jd3eN9lxAkyGK62ybv5tR+M4ECSQAVffhX9eVNKZo3/dbrJZyind7A9mX1VizlELjOGLvISRwKBgQC1TMEFOZmCc7EO+V8rOylB3x7oLYFEUd2yQ9dtM7rJTE1pwPvHwfQhJkk+AEs2D3rJeDCPyNTztZW3yvcP1zoLrNqfUf6j4nsvIIE4ZnvVM5cDkpn6Mn7P6rvWEi4Enk0VnQxLiTae7jlq7715J273Gb2Iecok3igMwF4//thkEw==") String privateKey) {

        PlatformUtil.userId = userId;
        PlatformUtil.url = url;
        PlatformUtil.aksk = privateKey;
        PlatformUtil.port = port;
    }

//    public static void main(String[] args)
//            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException,
//            IllegalBlockSizeException, InvalidKeySpecException, IOException {
//        String tableName = "dim_wr_ad_b";
//
//        // 通过AKSK生成密文
//        String cipherText = getCipherText();
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        String token = getToken(restTemplate, cipherText);
//        if (token != null) {
//            System.out.println(token);
//            queryData(restTemplate, token, tableName);
//            queryDataByFilter(restTemplate, token, tableName);
//        }
//    }

    /**
     * 通过AKSK生成密文
     *
     * @return 密文
     */
    public static String getCipherText()
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 根据用户ID和当前时间，拼接成待加密的内容
        String secretBody = userId + "#" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // 使用RSA的私钥对其进行加密
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(aksk));
        PrivateKey privateKey = factory.generatePrivate(privateKeySpec);
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] finalData = encryptCipher.doFinal(secretBody.getBytes());
        String finalDataString = new String(Base64.getEncoder().encode(finalData));

        // 最终用于获取token的字符串
        return userId + "#" + finalDataString;
    }

    /**
     * 获取token
     *
     * @param cipherText 密文
     * @return token
     */
    public static String getToken(RestTemplate restTemplate, String cipherText) throws IOException {
        String uri = "http://" + url + ":" + port + "/x-authentication-service/v1/tokens";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        requestHeaders.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<Object> request = new HttpEntity<>(cipherText, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseEntity.getBody());
            return rootNode.get("data").asText();
        } else {
            System.out.println("getToken failed ------------------- ");
        }
        return null;
    }

    public static String queryData(RestTemplate restTemplate, String token, String tableName) {
        String uri = "http://" + url + ":" + port + "/x-data-resource-service/v1/resources/data-provider";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("x-token", token);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        requestHeaders.setContentType(MediaType.TEXT_PLAIN);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
                .pathSegment(tableName)
                .queryParam("columns", "id,adnm");

        HttpEntity<Object> request = new HttpEntity<>(null, requestHeaders);

        ResponseEntity<byte[]> responseEntity = restTemplate
                .exchange(builder.build().toUriString(), HttpMethod.GET, request, byte[].class, tableName);
        if (responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
            byte[] body = responseEntity.getBody();
            String result = new String(body, StandardCharsets.UTF_8);
            System.out.println("queryData ===================== ");
            System.out.println(result);
            return result;
        } else {
            System.out.println("queryData failed ------------------- ");
        }
        return null;
    }

    public static Object queryDataByFilter(RestTemplate restTemplate, String token, String tableName, String filter, String values) throws Exception {
        String uri = "http://" + url + ":" + port + "/x-data-resource-service/v1/resources/data-provider";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("x-token", token);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
                .pathSegment(tableName);
        if (StringUtils.isNotBlank(values)) {
            builder.queryParam("columns", values);
        }

        builder.queryParam("filter", filter);

        HttpEntity<Object> request = new HttpEntity<>(null, requestHeaders);
        ResponseEntity<byte[]> responseEntity = restTemplate
                .exchange(builder.build().toUriString(), HttpMethod.GET, request, byte[].class);
        if (responseEntity.getStatusCode() == HttpStatus.OK || responseEntity.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("queryDataByFilter ===================== ");
            byte[] body = responseEntity.getBody();
            if (body == null) {
                return null;
            }
            String result = new String(body, StandardCharsets.UTF_8);

            return result;
        } else {
            System.out.println("queryDataByFilter failed ------------------- ");
            throw new Exception("请求中台接口异常");
        }
    }


}

