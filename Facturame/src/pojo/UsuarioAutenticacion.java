package pojo;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import factorias.FactoriaCRUD;
import operacionesCRUD.CRUDusuariosSistema;
import util.CreadorHashes;

public class UsuarioAutenticacion {

	private FactoriaCRUD fc;
	private CRUDusuariosSistema cu;
	private String nickname;
	private String hashContrasena;
	private CreadorHashes ch;
	private boolean admin;

	public UsuarioAutenticacion(String nickname, String pass)
			throws SQLException, IOException, NoSuchAlgorithmException {
		this.ch = CreadorHashes.getCreadorHashes();
		this.fc = new FactoriaCRUD();
		this.cu = (CRUDusuariosSistema) fc.crearCRUD(FactoriaCRUD.TIPO_US_SISTEMA);
		UsuarioSistema u = (UsuarioSistema) cu.buscarUno(nickname);
		this.hashContrasena = ch.generarHash(pass);
		if (u != null) {
			if (u.getHashContrasena().equalsIgnoreCase(hashContrasena)) {
				this.nickname = u.getNickname();
				this.admin = u.isAdmin();
			} else {
				this.nickname = null;
				this.admin = false;
			}
		} else {
			this.nickname = null;
			this.admin = false;
		}

	}

	public String getNickname() {
		return nickname;
	}

	public String getHashContrasena() {
		return hashContrasena;
	}

	public Boolean isAdmin() {
		return admin;
	}
}
