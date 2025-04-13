package com.kadoo.education.service;

import com.kadoo.education.model.Usuario;
import java.util.List;

public interface UsuarioService {
    Usuario salvar(Usuario usuario);
    List<Usuario> listarTodos();
    Usuario buscarPorId(Long id);
}
