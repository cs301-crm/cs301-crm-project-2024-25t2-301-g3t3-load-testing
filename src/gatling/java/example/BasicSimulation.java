package example;

//import static example.endpoints.APIendpoints.withAuthenticationHeader;
import static example.groups.ScenarioGroups.*;
import static example.utils.Config.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class BasicSimulation extends Simulation {

  // Define HTTP configuration
  HttpProtocolBuilder httpProtocol =
          http.baseUrl(baseUrl)
                  .acceptHeader("application/json")
                  .userAgentHeader(
                          "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36");

  // Define login scenario
  ScenarioBuilder loginScenario = scenario("Login with OTP")
          .exitBlockOnFail()
          .on(
                  authenticate
          )
          .exitHereIfFailed();

  // Define create scenario
  ScenarioBuilder createClientScenario = scenario("Create Client")
          .exitBlockOnFail()
          .on(
                  authenticate,
                  pause(minPauseSec, maxPauseSec),
                  createClients
          )
          .exitHereIfFailed();

  // Define read scenario
  ScenarioBuilder readClientScenario = scenario("Read Client")
          .exitBlockOnFail()
          .on(
                  authenticate,
                  pause(minPauseSec, maxPauseSec),
                  createClients
          )
          .exitHereIfFailed();

  // Define update scenario
  ScenarioBuilder updateClientScenario = scenario("Update Client")
          .exitBlockOnFail()
          .on(
                  authenticate,
                  pause(minPauseSec, maxPauseSec),
                  createClients
          )
          .exitHereIfFailed();

  // Define delete scenario
  ScenarioBuilder deleteClientScenario = scenario("Delete Client")
          .exitBlockOnFail()
          .on(
                  authenticate,
                  pause(minPauseSec, maxPauseSec),
                  createClients
          )
          .exitHereIfFailed();

  // Define injection profile and execute the test
  {
    setUp(
//            loginScenario.injectOpen(constantUsersPerSec(1).during(30))
            loginScenario.injectOpen(atOnceUsers(1))
//            createClientScenario.injectOpen(atOnceUsers(1))
    ).protocols(httpProtocol);
  }
}
