package br.com.fatec.fitcontrol.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.fatec.fitcontrol.exception.DAOException;
import br.com.fatec.fitcontrol.exception.EmailInvalidoException;

public class Helper {
	public static void emailValido(String email) throws EmailInvalidoException {
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches()) {
				throw new EmailInvalidoException("E-mail inválido");
			}
		} else {
			throw new EmailInvalidoException("E-mail inválido");
		}
	}

	public static void close(Connection conn) throws DAOException {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new DAOException(sqlException);
			}
		}
	}

	public static void close(PreparedStatement st) throws DAOException {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException sqlException) {
				throw new DAOException(sqlException);
			}
		}
	}

	public static void close(ResultSet rs) throws DAOException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqlException) {
				throw new DAOException(sqlException);
			}
		}
	}
}
