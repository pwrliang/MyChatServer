package com.gl.mychat.servlets;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ProjectInit implements ServletContextListener {
	public static final Key key = MacProvider.generateKey();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("MyChat Server - starting");
	}
}
