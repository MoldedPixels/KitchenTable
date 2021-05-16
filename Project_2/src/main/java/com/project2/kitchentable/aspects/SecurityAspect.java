package com.project2.kitchentable.aspects;

import java.util.UUID;

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
		if (isLoggedInAsUserType((ServerWebExchange) pjp.getArgs()[0], 1)) {
			pjp.proceed();
		}
		return;
	}
	
	@Around("familyHook()")
	public void isFamilyMember(ProceedingJoinPoint pjp) throws Throwable {
		if (pjp.getArgs().length == 0) {
			throw new Exception("Invalid arguments to adviced method " + pjp.getSignature());
		}
		
		if (this.isFamilyMember((ServerWebExchange) pjp.getArgs()[0], (UUID) pjp.getArgs()[1])) {
			pjp.proceed();
		}
	}

	@Around("headUserHook()")
	public void authorizedUser(ProceedingJoinPoint pjp) throws Throwable {
		if (pjp.getArgs().length == 0) {
			throw new Exception("Invalid arguments to adviced method " + pjp.getSignature());
		}

		if (this.isLoggedInAsUserType((ServerWebExchange) pjp.getArgs()[0], 2)) {
			pjp.proceed();
		}
		return;
	}

	@Around("adminHook()")
	public void adminUser(ProceedingJoinPoint pjp) throws Throwable {
		if (pjp.getArgs().length == 0) {
			throw new Exception("Invalid arguments to adviced method " + pjp.getSignature());
		}
		if (this.isLoggedInAsUserType((ServerWebExchange) pjp.getArgs()[0], 3)) {
			pjp.proceed();
		}
	}

	private boolean isLoggedInAsUserType(ServerWebExchange exchange, int ut) {
		User u = this.getUserFromSession(exchange);
		if (u == null || u.getUserType() < ut) {
			exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
			return false;
		}
		return true;
	}

	private boolean isFamilyMember(ServerWebExchange exchange, UUID fID) {
		User u = this.getUserFromSession(exchange);
		if (u == null || !(u.getFamilyID().equals(fID))) {
			return false;
		}
		return true;
	}

	private User getUserFromSession(ServerWebExchange exchange) {
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
			return null;
		}
		return u;
	}

	@Pointcut("@annotation(com.project2.kitchentable.aspects.Admin)")
	public void adminHook() {
		/* Empty method for Hook */ }

	@Pointcut("@annotation(com.project2.kitchentable.aspects.HeadUser)")
	public void headUserHook() {
		/* Empty method for Hook */ }
	
	@Pointcut("@annotation(com.project2.kitchentable.aspects.FamilyMember)")
	public void familyHook() {
		/* Empty method for Hook */ }

	@Pointcut("@annotation(com.project2.kitchentable.aspects.LoggedIn)")
	public void userHook() {
		/* Empty method for Hook */ }
}
