package com.example.projeto_turismo.services;

import com.example.projeto_turismo.domains.Avaliacao;
import com.example.projeto_turismo.dto.*;
import com.example.projeto_turismo.exceptions.EventFullException;
import com.example.projeto_turismo.domains.User;
import com.example.projeto_turismo.repositorys.AvaliacaoRepository;
import com.example.projeto_turismo.repositorys.UserRepository;
import com.example.projeto_turismo.repositorys.UsuarioLogadoProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private UsuarioLogadoProvider usuarioLogadoProvider;
    private AvaliacaoRepository avaliacaoRepository;

    public UserService(UserRepository userRepository, UsuarioLogadoProvider usuarioLogadoProvider, AvaliacaoRepository avaliacaoRepository) {
        this.userRepository = userRepository;
        this.usuarioLogadoProvider = usuarioLogadoProvider;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public UserDto create(RegisterDto dto){
        if(userRepository.existsByLoginIgnoreCase(dto.login())){
            throw new EventFullException("Já existe usuário com esse login");
        }

        User user1 = new User();
        user1.setNome(dto.nome());
        user1.setTelefone(dto.telefone());
        user1.setLogin(dto.login());
        user1.setSenha(dto.senha());
        user1.setRole(dto.role());
        User salvo = userRepository.save(user1);
        return new UserDto(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTelefone(),
                salvo.getLogin(),
                salvo.getRole());

    }
    public UserDto findById(){
        User user = usuarioLogadoProvider.pegarUsuarioLogado();

        return new UserDto(
                user.getId(),
                user.getNome(),
                user.getTelefone(),
                user.getLogin(),
                user.getRole()
        );

    }
    public List<UserDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getNome(),
                        user.getTelefone(),
                        user.getLogin(),
                        user.getRole()
                ))
                .toList();
    }
    public void delete(){
        User user = usuarioLogadoProvider.pegarUsuarioLogado();
        userRepository.delete(user);
    }
    public UserDto update(UserUpdateDto dto){

        User user = usuarioLogadoProvider.pegarUsuarioLogado();

        user.setNome(dto.nome());
        user.setTelefone(dto.telefone());

        if (!user.getLogin().equalsIgnoreCase(dto.login())) {
            if (userRepository.existsByLoginIgnoreCase(dto.login())){
                throw new EventFullException("Login já existente");
            }
            user.setLogin(dto.login());

        }
        User user1 = userRepository.save(user);
        return new UserDto(
                user1.getId(),
                user1.getNome(),
                user1.getTelefone(),
                user1.getLogin(),
                user1.getRole()
        );

    }
    public List<AvaliacaoResponseDto> findMyAvaliacao(){
        User user = usuarioLogadoProvider.pegarUsuarioLogado();

        List<Avaliacao> avaliacoes = avaliacaoRepository.findByUser(user);

        if(avaliacoes.isEmpty()){
            throw new EventFullException("Esse usuário não avaliou nada");
        }
        return avaliacoes
                .stream()
                .map(avaliacao -> new AvaliacaoResponseDto(
                    avaliacao.getNota(),
                    avaliacao.getComentario(),
                    avaliacao.getUser().getId(),
                    avaliacao.getPontoTuristico().getId(),
                    avaliacao.getRestaurante().getId(),
                    avaliacao.getDataAvaliacao()))
                .toList();
    }

    public UserMeuPerfil meuPerfil(){
        User user = usuarioLogadoProvider.pegarUsuarioLogado();
        return new UserMeuPerfil(
                user.getNome(),
                user.getTelefone(),
                user.getLogin()
        );
    }
}
