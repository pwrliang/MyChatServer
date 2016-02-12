package com.gl.mychat.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.servlet.http.HttpServletRequest;

public class Utility {
	// public static boolean validate(String token, Key key) {
	// try {
	// Jwts.parser().setSigningKey(key).parseClaimsJws(token);
	// } catch (SignatureException e) {
	// return false;
	// }
	// return true;
	// }

	public static String generateToken(String input, Key key) {
		return Jwts.builder().setSubject(input)
				.signWith(SignatureAlgorithm.HS512, key).compact();
	}

	public static String validate(String token, Key key) {
		if (token == null)
			return null;
		try {
			return Jwts.parser().setSigningKey(key).parseClaimsJws(token)
					.getBody().getSubject();
		} catch (SignatureException e) {
//			e.printStackTrace();
		}
		return null;
	}

	public static byte[] getBytesFromStream(InputStream inputStream)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = inputStream.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		return baos.toByteArray().length == 0 ? null : baos.toByteArray();
	}

	public static boolean isEmpty(String input) {
		if (input == null || input.length() == 0)
			return true;
		return false;
	}
}
