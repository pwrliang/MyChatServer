package com.gl.mychat.Utils;

public class ErrorAnalysisUtility {
	public static String getErrorMessage(Exception e) {
		if (e.getCause() != null) {
			String errorMessage = e.getCause().getMessage();
			if (errorMessage.contains("ORA-00001")) {
				return "该用户名已被使用";
			}
		}
		return e.toString();
	}
}
