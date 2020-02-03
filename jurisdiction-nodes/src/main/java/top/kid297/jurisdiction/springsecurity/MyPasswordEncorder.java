package top.kid297.jurisdiction.springsecurity;


import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncorder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
