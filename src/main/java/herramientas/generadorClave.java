package herramientas;

import java.math.BigInteger;
public class generadorClave {
    private BigInteger coeficiente;
    private BigInteger exponente;
    private BigInteger modulo;

    public generadorClave(BigInteger coeficiente, BigInteger exponente, BigInteger modulo) {
        this.coeficiente = coeficiente;
        this.exponente = exponente;
        this.modulo = modulo;
    }
    public BigInteger generarClave(){
        BigInteger clave=coeficiente.modPow(exponente,modulo);
        return clave;
    }
}