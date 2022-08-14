public class Main {
    public static void main(String[] args) {

        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        // # 1
        /*
        // Base 64 encoding
        IEncoder encoder = new Base64Encoder();
        String result = encoder.encode(url);
        System.out.println(result);

        //url encoding
        IEncoder urlEncoder = new UrlEncoder();
        String result_url = urlEncoder.encode(url);
        System.out.println(result_url);
         */


        // # 2 Encoder 클래스에서 변경이 필요함
        Encoder encoder2 = new Encoder();
        String result2 = encoder2.encode(url);
        System.out.println(result2);


        // # 3 DI 외부에서 내가 사용하는 객체 주입
        EncoderDi encoderDi = new EncoderDi(new Base64Encoder());
        String result3 = encoderDi.encode(url);
        System.out.println(result3);

        EncoderDi encoderDi_url = new EncoderDi(new UrlEncoder());
        String result4 = encoderDi_url.encode(url);
        System.out.println(result4);

    }
}