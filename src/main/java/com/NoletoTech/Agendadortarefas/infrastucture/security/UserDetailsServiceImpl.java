package com.NoletoTech.Agendadortarefas.infrastucture.security;


import com.NoletoTech.Agendadortarefas.business.dto.UsuarioDTO;
import com.NoletoTech.Agendadortarefas.infrastucture.client.usuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  {

    @Autowired
    private usuarioClient client;

    public UserDetails
    carregaDadosUsuario(String email, String token){
        UsuarioDTO usuarioDTO = client.buscaPorEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }

}
