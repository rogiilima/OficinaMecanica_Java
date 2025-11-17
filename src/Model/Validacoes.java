package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacoes {


    public boolean validarEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(emailRegex); // compila a regra do regex com email

        Matcher matcher = pattern.matcher(email); // pattern.matcher(email) -> ler o regex compilado,
        // etapa necessária para verificar se o email está correto

        return matcher.matches(); // retorna se o email está correto ou não
    }
}
