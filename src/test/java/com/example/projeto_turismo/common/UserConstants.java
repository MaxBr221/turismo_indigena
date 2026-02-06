package com.example.projeto_turismo.common;

import com.example.projeto_turismo.domains.Role;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.dto.RegisterDto;

public class UserConstants {
    public static final RegisterDto REGISTER_DTO = new RegisterDto("maxsuel", "83-99171-0731", "maxsuel.lima@dcx", "max123", Role.USER);

    public static final User USER = new User("maxsuel", "83-99171-0731", "maxsuel.lima@dcx", "max123", Role.USER);

    public static final RegisterDto INVALID_USER = new RegisterDto(" ", " ", " ", " ", Role.USER);
}
