package ru.lavcraft.microservices.fee;

import info.developerblog.services.user.TBalance;
import info.developerblog.services.user.TUser;
import info.developerblog.services.user.TUserNotFoundException;
import info.developerblog.services.user.TUserService;
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
    public TUser getUserById(long id) throws TException {
        if (id != 1)
            throw new TUserNotFoundException("User is not found!");
        return new TUser()
            .setId(id)
            .setFirstname("Test")
            .setLastname("Testovich")
            .setBalance(new TBalance()
                .setAmount(new Random().nextInt())
                .setCurrency("RUR")
            );
    }
}
