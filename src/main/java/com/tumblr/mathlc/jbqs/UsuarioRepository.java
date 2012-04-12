package com.tumblr.mathlc.jbqs;

import java.util.List;

public interface UsuarioRepository {
	void save(Usuario u);
	List<Usuario> search(String f);
}
