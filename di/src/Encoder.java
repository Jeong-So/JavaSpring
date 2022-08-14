import java.util.Base64;

public class Encoder {

    private IEncoder iEncoder;

    public Encoder() {
//        this.iEncoder = new Base64Encoder();
        this.iEncoder = new UrlEncoder();
        // 직접 변경 필요
    }


    public String encode(String message) {
        return iEncoder.encode(message);
    }

}
