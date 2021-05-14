package com.project2.kitchentable.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.utils.JWTParser;

@Component
@Aspect
public class SecurityAspect {

	@Autowired
	private JWTParser tokenService;

	@Around("userHook()")
	public void loggedIn(ProceedingJoinPoint pjp) throws Throwable {
		if (pjp.getArgs().length == 0) {
			throw new Exception("Invalid arguments to adviced method " + pjp.getSignature());
		}
		ServerWebExchange exchange = (ServerWebExchange) pjp.getArgs()[0];
		try {
			if (exchange.getRequest().getCookies().get("token") != null) {
				String token = exchange.getRequest().getCookies().getFirst("token").getValue();
				if (!token.equals("")) {
					pjp.proceed();
				}
			}
		} catch (Exception e) {
			exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
			return;
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return;
	}

	@Around("headUserHook()")
	public void authorizedUser(ProceedingJoinPoint pjp) throws Throwable {
		if (pjp.getArgs().length == 0) {
			throw new Exception("Invalid arguments to adviced method " + pjp.getSignature());
		}
		User u = null;
		ServerWebExchange exchange = (ServerWebExchange) pjp.getArgs()[0];
		try {
			if (exchange.getRequest().getCookies().get("token") != null) {
				String token = exchange.getRequest().getCookies().getFirst("token").getValue();
				if (!token.equals("")) {
					u = tokenService.parser(token);
				}
			}
		} catch (Exception e) {
			exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
			return;
		}

		if (u != null && u.getUserType() == 2) {
			// We're a head user, the advised method can be called.
			pjp.proceed();
		} else {
			exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		}
		return;
	}

	@Around("adminHook()")
	public void adminUser(ProceedingJoinPoint pjp) throws Throwable {
		if (pjp.getArgs().length == 0) {
			throw new Exception("Invalid arguments to adviced method " + pjp.getSignature());
		}
		ServerWebExchange exchange = (ServerWebExchange) pjp.getArgs()[0];
		User u = null;

		try {
			if (exchange.getRequest().getCookies().get("token") != null) {
				String token = exchange.getRequest().getCookies().getFirst("token").getValue();
				if (!token.equals("")) {
					u = tokenService.parser(token);
				}
			}
		} catch (Exception e) {
			exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
			return;
		}

		if (u != null && u.getUserType() == 3) {
			// We're an admin, the advised method can be called.
			pjp.proceed();
		} else {
			exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		}
		return;
	}

	@Pointcut("@annotation(com.project2.kitchentable.aspects.Admin)")
	public void adminHook() {
		/* Empty method for Hook */ }

	@Pointcut("@annotation(com.project2.kitchentable.aspects.HeadUser)")
	public void headUserHook() {
		/* Empty method for Hook */ }
	@Pointcut("@annotation(com.project2.kitchentable.aspects.LoggedIn)")
	public void userHook() {
		/* Empty method for Hook */ }
}
