package com.herokuapp.skmtodoapp.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

@Configuration
public class GenericRestErrorController {

	@Bean
	public ErrorAttributes getErrorAttributes() {
		return new DefaultErrorAttributes() {

			@Override
			public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
				Map<String, Object> error = super.getErrorAttributes(webRequest, includeStackTrace);
				Object errorCode = error.get("status");
				Object errorMessage = error.get("error");
				error = new LinkedHashMap<>();
				error.put("code", errorCode);
				error.put("message", errorMessage);
				return error;
			}

		};
	}
}
