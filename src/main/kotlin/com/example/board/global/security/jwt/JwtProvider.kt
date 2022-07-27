package com.example.board.global.security.jwt

import com.example.board.domain.member.entity.Member
import com.example.board.global.security.jwt.exception.TokenHasExpiredException
import com.example.board.global.security.jwt.exception.TokenIsInvalidException
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import javax.annotation.PostConstruct

@Service
class JwtProvider {
    @Value("{JWT.SECRET.KEY}")
    var SECRET_KEY: String? = "";
    @Value("{JWT.ACCESS.TOKEN.VALIDITY}")
    val JWT_ACCESS_TOKEN_VALIDITY: Long? = 0;
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostConstruct
    protected fun init() {
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY!!.toByteArray(StandardCharsets.UTF_8))
    }

    fun generateAccessToken(member: Member): String? {
        val now = Date()
        val expireDate: Date = Date(now.time + JWT_ACCESS_TOKEN_VALIDITY!!)

        return Jwts.builder()
            .setSubject(member.email)
            .setIssuedAt(now)
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact()
    }

    fun getEmailFromAccessToken(accessToken: String): String?{
        val claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(accessToken)
            .body;

        return claims.subject;
    }

    fun isValidateToken(accessToken: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken)
            true
        } catch (ex: SignatureException) {
            log.error("Invalid JWT signature")
            throw TokenIsInvalidException()
        } catch (ex: MalformedJwtException) {
            log.error("Invalid JWT token")
            throw TokenIsInvalidException()
        } catch (ex: ExpiredJwtException) {
            log.error("Expired JWT token")
            throw TokenHasExpiredException()
        } catch (ex: UnsupportedJwtException) {
            log.error("Unsupported JWT token")
            throw TokenIsInvalidException()
        } catch (ex: IllegalArgumentException) {
            log.error("JWT claims string is empty.")
            throw TokenIsInvalidException()
        }
    }
}