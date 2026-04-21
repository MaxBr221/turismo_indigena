package com.example.projeto_turismo.common;

import com.example.projeto_turismo.domains.Role;
import com.example.projeto_turismo.dto.RegisterDto;
import com.example.projeto_turismo.dto.UserDto;
import com.example.projeto_turismo.dto.UserUpdateDto;

public class UserConstants {
    private static final String NOME_USER = "maxsuel";
    private static final String TELEFONE_USER = "83-99171-0731";
    private static final String LOGIN_USER = "maxsuel.lima@dcx";
    private static final String SENHA_USER = "max123";

    public static final RegisterDto REGISTER_DTO = new RegisterDto(NOME_USER, TELEFONE_USER, LOGIN_USER, SENHA_USER, Role.USER);

    public static final UserDto USER = new UserDto(1L ,NOME_USER, TELEFONE_USER, LOGIN_USER, Role.USER);

    public static final RegisterDto INVALID_USER = new RegisterDto(" ", " ", " ", " ", Role.USER);

    public static final UserUpdateDto UPDATE_DTO = new UserUpdateDto(NOME_USER, TELEFONE_USER, LOGIN_USER);


}
