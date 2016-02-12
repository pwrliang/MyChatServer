package com.gl.mychat.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;

public class TMain {

	public static void main(String[] args) throws InterruptedException {
		Key key = MacProvider.generateKey();

		String s = Jwts.builder().setSubject("Joe")
				.signWith(SignatureAlgorithm.HS512, key).compact();
		System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(s)
				.getBody().getSubject().equals("Joe"));
	}

}
