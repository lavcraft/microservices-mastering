package ru.lavcraft.microservices.eureka;

import info.developerblog.services.user.TAuthData;
import info.developerblog.services.user.TAuthToken;
import info.developerblog.services.user.TUnauthorizedException;
import org.apache.thrift.TException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aatarasoff.thrift.api.gateway.core.AuthTokenExchanger;

/**
 * Created by aleksandr on 07.10.15.
 */
@Configuration
public class AuthenticationConfiguration {
    @Bean
    AuthTokenExchanger authTokenExchanger() {
        return new AuthTokenExchanger<TAuthToken, TAuthData>() {
            @Override
            public TAuthToken createEmptyAuthToken() {
                return new TAuthToken();
            }

            @Override
            public TAuthData process(TAuthToken authToken) throws TException {
                if (authToken.getToken().equals("ABCD")) {
                    return new TAuthData(1L);
                }

                throw new TUnauthorizedException("UNAUTHORIZED");
            }
        };
    }
}
