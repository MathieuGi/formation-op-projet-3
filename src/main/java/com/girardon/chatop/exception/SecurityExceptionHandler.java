package com.girardon.chatop.exception;

import com.girardon.chatop.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(SecurityExceptionHandler.class);

    // Erreur d'authentification (identifiants incorrects)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleBadCredentials(BadCredentialsException ex) {
        return new ErrorResponse(
                "INVALID_CREDENTIALS",
                "Email ou mot de passe incorrect",
                null
        );
    }

    // Accès refusé (authentifié mais non autorisé)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDenied(AccessDeniedException ex) {
        return new ErrorResponse(
                "ACCESS_DENIED",
                "Accès non autorisé à cette ressource",
                null
        );
    }

    // Token JWT expiré
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleExpiredToken(ExpiredJwtException ex) {
        return new ErrorResponse(
                "TOKEN_EXPIRED",
                "Session expirée, veuillez vous reconnecter",
                null
        );
    }

    // Token JWT invalide
    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidToken(JwtException ex) {
        log.warn("Invalid JWT token: {}", ex.getMessage());
        return new ErrorResponse(
                "INVALID_TOKEN",
                "Token d'authentification invalide",
                null
        );
    }

    // Email déjà utilisé lors de l'inscription
    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEmailExists(UserExistsException ex) {
        return new ErrorResponse(
                "EMAIL_EXISTS",
                ex.getMessage(),
                null
        );
    }
}