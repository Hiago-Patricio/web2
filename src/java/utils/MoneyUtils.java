package utils;

public class MoneyUtils {

    public static float convertRealToFloat(String real) {
        String aux;
        aux = real.replaceAll("[R$\\. ]", "");
        aux = aux.replaceAll(",", ".");
        return Float.parseFloat(aux);
    }
}
