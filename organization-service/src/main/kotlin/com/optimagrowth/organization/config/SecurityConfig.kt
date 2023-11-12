package com.optimagrowth.organization.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

//import com.c4_soft.springaddons.security.oidc.starter.synchronised.ExpressionInterceptUrlRegistryPostProcessor
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@Configuration
@EnableMethodSecurity
class SecurityConfig {}
//
//    @Bean
//    fun expressionInterceptUrlRegistryPostProcessor(): ResourceServerExpressionInterceptUrlRegistryPostProcessor? {
//        // @formatter:off
//        return ResourceServerExpressionInterceptUrlRegistryPostProcessor { registry ->
//            registry
//                .requestMatchers(AntPathRequestMatcher("/v1/**")).hasRole("ADMIN")
//                .anyRequest().authenticated()
//        }
//        // @formatter:on
//    }
//}
//
//    @Bean
//    fun expressionInterceptUrlRegistryPostProcessor(): ExpressionInterceptUrlRegistryPostProcessor {
//        return ExpressionInterceptUrlRegistryPostProcessor { registry ->
//            registry
//                .requestMatchers("/secured-route")
//                .hasRole("AUTHORIZED_PERSONNEL")
//                .anyRequest().authenticated()
//        }
//    }
//}
//    @Autowired
//    @Throws(Exception::class)
//    fun configureGlobal(auth: AuthenticationManagerBuilder) {
//        val keycloakAuthenticationProvider: KeycloakAuthenticationProvider = keycloakAuthenticationProvider()
//        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(SimpleAuthorityMapper())
//        auth.authenticationProvider(keycloakAuthenticationProvider)
//    }
//
//    @Bean
//    @Override
//    protected fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {
//        return RegisterSessionAuthenticationStrategy(SessionRegistryImpl())
//    }
//
//    @Bean
//    fun KeycloakConfigResolver(): KeycloakConfigResolver {
//        return KeycloakSpringBootConfigResolver()
//    }
//}
