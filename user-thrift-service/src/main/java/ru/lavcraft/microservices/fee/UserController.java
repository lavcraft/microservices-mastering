package ru.lavcraft.microservices.fee;

import info.developerblog.services.user.*;
import info.developerblog.spring.thrift.annotation.ThriftHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;

import java.util.Random;

/**
 * @author tolkv
 * @since 13/09/15
 */
@Slf4j
@ThriftHandler("/thrift/user")
public class UserController implements TUserService.Iface {
    @Override
    public TUser getUserById(TAuthData authData) throws TException {
        if (authData.getUserId() != 1)
            throw new TUserNotFoundException("User is not found!");
        return new TUser()
            .setId(authData.getUserId())
            .setFirstname("Test")
            .setLastname("Testovich")
            .setBalance(new TBalance()
                .setAmount(new Random().nextInt())
                .setCurrency("RUR")
            );
    }
}
