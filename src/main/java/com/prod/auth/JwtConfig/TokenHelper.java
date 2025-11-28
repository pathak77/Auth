@Component
public class JWTTokenHelper {
private String appName;
private String secretKey;

       private int expiration;

    public String generateToken(String userName){
        return generateToken(userName, generateExpirationDate(), getSigningKey());
    }
}