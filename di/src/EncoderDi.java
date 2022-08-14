public class EncoderDi {

    private IEncoder iEncoder;

    public EncoderDi(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }


    public String encode(String message) {
        return iEncoder.encode(message);
    }

}