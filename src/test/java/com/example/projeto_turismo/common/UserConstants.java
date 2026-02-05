package com.example.projeto_turismo.common;

import com.example.projeto_turismo.domains.Role;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.RegisterDto;

public class UserConstants {
    public static final RegisterDto USER = new RegisterDto("nome", "83-99171-731", "maxsuel.lima@dcx", "max123", Role.USER);
}
