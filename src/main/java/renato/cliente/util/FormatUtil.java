package renato.cliente.util;

import br.com.caelum.stella.format.CPFFormatter;

public class FormatUtil {

    public static String formatCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        CPFFormatter cpfFormatter = new CPFFormatter();
        return cpfFormatter.format(cpf);
    }

    public static String unformatCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        CPFFormatter cpfFormatter = new CPFFormatter();
        return cpfFormatter.unformat(cpf);
    }

}
