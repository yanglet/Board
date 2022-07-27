package com.example.board.global.security.jwt.filter

import com.example.board.global.security.auth.CustomPrincipalDetailsService
import com.example.board.global.security.jwt.JwtProvider
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider? = null,
    private val customPrincipalDetailsService: CustomPrincipalDetailsService? = null
) : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt = request.getHeader("Authorization")
            if (StringUtils.hasText(jwt) && jwtProvider!!.isValidateToken(jwt)) {
                val userEmail: String? = jwtProvider!!.getEmailFromAccessToken(jwt)
                val userDetails: UserDetails = customPrincipalDetailsService!!.loadUserByUsername(userEmail)
                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (ex: Exception) {
            log.error("Could not set user authentication in security context", ex)
        }
        filterChain.doFilter(request, response)
    }
}