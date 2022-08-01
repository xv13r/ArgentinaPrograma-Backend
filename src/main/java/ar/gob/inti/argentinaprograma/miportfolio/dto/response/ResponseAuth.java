package ar.gob.inti.argentinaprograma.miportfolio.dto.response;

public class ResponseAuth {
    private String email;
    private String accessToken;

    public ResponseAuth() {
    }

    public ResponseAuth(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
