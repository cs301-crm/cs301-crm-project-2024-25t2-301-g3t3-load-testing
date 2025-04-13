package example.utils;

public class TargetEnvResolver {

  // Record to store environment-specific information
  public record EnvInfo(
      String pageUrl,
      String baseUrl,
      String usersFeederFile,
      String clientsFeederFile,
      String productsFeederFile
  ) {}

  // Resolve environment-specific configuration based on the target environment
  public static EnvInfo resolveEnvironmentInfo(String targetEnv) {
    return switch (targetEnv) {
      case "DEV" ->
          new EnvInfo(
                  "http://localhost:3000",
                  "http://localhost:9000/api/v1",
                  "data/users_dev.json",
                  "data/clients_dev.json",
                  "data/products_dev.csv");
      default ->
          new EnvInfo(
                  "http://localhost:3000",
                  "http://localhost:9000/api/v1",
                  "data/users_dev.json",
                  "data/clients_dev.json",
                  "data/products_dev.csv");
    };
  }
}
