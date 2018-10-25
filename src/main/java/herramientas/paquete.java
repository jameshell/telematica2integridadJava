package herramientas;

import java.math.BigInteger;

public class paquete {
    private String mensajeOriginal;
    private String mensajeSHA;
    private BigInteger claveDelRSA;

    public paquete(String mensajeOriginal, String mensajeSHA, BigInteger claveDelRSA) {
        this.mensajeOriginal = mensajeOriginal;
        this.mensajeSHA = mensajeSHA;
        this.claveDelRSA = claveDelRSA;
    }

    public String getMensajeOriginal() {
        return mensajeOriginal;
    }

    public void setMensajeOriginal(String mensajeOriginal) {
        this.mensajeOriginal = mensajeOriginal;
    }

    public String getMensajeSHA() {
        return mensajeSHA;
    }

    public void setMensajeSHA(String mensajeSHA) {
        this.mensajeSHA = mensajeSHA;
    }

    public BigInteger getClaveDelRSA() {
        return claveDelRSA;
    }

    public void setClaveDelRSA(BigInteger claveDelRSA) {
        this.claveDelRSA = claveDelRSA;
    }
}
