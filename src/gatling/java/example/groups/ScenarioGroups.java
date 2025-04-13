package example.groups;

import static example.endpoints.APIendpoints.*;
import static example.endpoints.WebEndpoints.*;
import static example.utils.Config.*;
import static example.utils.Keys.*;
import static io.gatling.javaapi.core.CoreDsl.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gatling.javaapi.core.*;
import java.util.ArrayList;
import java.util.List;

public class ScenarioGroups {

    // Define a feeder for user data
    // Reference: https://docs.gatling.io/reference/script/core/feeder/
    private static final FeederBuilder<Object> usersFeeder = jsonFile(usersFeederFile).circular();
    private static final FeederBuilder<Object> clientsFeeder = jsonFile(clientsFeederFile).circular();

    // Define authentication process
    public static final ChainBuilder authenticate =
      group("authenticated")
          .on(
                  loginPage,
                  feed(usersFeeder),
                  pause(minPauseSec, maxPauseSec),
                  initialLogin,
                  pause(minPauseSec, maxPauseSec),
                  getOtp,
                  pause(minPauseSec, maxPauseSec),
                  verifyOtp
        );

    public static final ChainBuilder createClients =
            group("created")
                    .on(
                            feed(clientsFeeder),
                            pause(minPauseSec, maxPauseSec),
                            createClient
                    );