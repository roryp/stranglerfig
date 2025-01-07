![Strangler Fig](Picture1.png)

Author’s Note: After announcing the Modern Web App Pattern in this blog (https://devblogs.microsoft.com/java/introducing-the-modern-web-app-mwa-pattern-for-java), we now focus on ones of its many pattens - the Strangler Fig pattern. This pattern incrementally modernizes a legacy Java application by “strangling” targeted areas and replacing them with new microservices or minimal web apps. We’ll explore an example showing how to integrate this approach and smoothly transition from a monolith to modern microservices.

# Modern Web App Patterns: Strangler Fig

## Introduction

In today's fast-paced technological landscape, modernizing Java applications is crucial for achieving scalability, maintainability, and performance. Legacy systems often struggle to keep up with the demands of modern users and evolving business requirements. By adopting modern architectural patterns, organizations can ensure their applications remain competitive and capable of handling increased workloads.

## The Strangler Pattern: A Strategy for Incremental Modernization

As applications age, the tools, technologies, and architectures they rely on can become outdated. Introducing new features can increase complexity, making these systems harder to maintain and evolve. Completely rewriting a complex system is often a massive and risky undertaking. The **Strangler Fig Pattern** offers a solution by allowing you to incrementally migrate a legacy system. This is achieved by gradually replacing specific pieces of functionality with new applications and services.

Inspired by the strangler fig tree that grows around and eventually replaces its host, this pattern involves creating a façade that intercepts requests to the backend legacy system. The façade routes these requests either to the legacy application or the new services. This approach enables existing features to be migrated gradually, while consumers continue using the same interface, unaware of the changes happening behind the scenes.

By gradually replacing the legacy system's features, the new system eventually encompasses all functionalities, allowing you to safely decommission the old system.

*Read Martin Fowler's original article on the Strangler Fig Application [here](https://martinfowler.com/bliki/StranglerFigApplication.html).*

### Example

Next lets look at a typical use case where the Strangler Pattern would make a good fit.
Imagine you're migrating a `/customer` endpoint from a legacy system to a modernized architecture. You can deploy a router that directs requests to either the old or new implementation based on specific conditions.

#### Java Code Example

```java
@RestController
@RequestMapping("/api")
public class CustomerRouterController {
    private final LegacyCustomerService legacyService;
    private final ModernCustomerService modernService;

    public CustomerRouterController(LegacyCustomerService legacyService, ModernCustomerService modernService) {
        this.legacyService = legacyService;
        this.modernService = modernService;
    }

    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomer(@RequestParam String id) {
        if (isModernCustomer(id)) {
            Customer customer = modernService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        }
        Customer customer = legacyService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    private boolean isModernCustomer(String id) {
        // Logic to determine if the request should be routed to the modern service
        return id.startsWith("MODERN_");
    }
}
```

This example uses a centralized router to direct requests based on specific criteria, such as the format of the customer ID. It enables both legacy and modern systems to coexist during the transition period.

## Running the example

Follow these steps to run the Spring Boot example demonstrating the Strangler Pattern.

### Prerequisites

Ensure you have the following tools installed:

- **JDK 17**: [Download](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- **Apache Maven**: [Download](https://maven.apache.org/download.cgi)
- **Visual Studio Code**: [Download](https://code.visualstudio.com/Download)
- **Java Extension Pack for Visual Studio Code**: [Marketplace](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

### Steps to Run

1. **Clone the Repository**

   ```bash
   git clone https://github.com/roryp/stranglerfig.git
   cd stranglerfig
   ```

2. **Build the Project**

   ```bash
   mvn clean install
   ```

3. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```

   The application starts on port 8080 by default.

4. **Test the Application**

   Send requests to the `/api/customer` endpoint:

   - **Modern Customer ID**

     ```bash
     curl http://localhost:8080/api/customer?id=MODERN_1
     ```

     This request is routed to the modern customer service.

   - **Legacy Customer IDs**

     ```bash
     curl http://localhost:8080/api/customer?id=LEGACY_1
     ```

     These requests are routed to the legacy customer service.

## Modern Java Web App (MWA) Patterns

Now that the Strangler Fig pattern has been introduced, it can be combined with other techniques to form the **Modern Web App (MWA) Pattern**. The MWA pattern emphasizes resilience, security, and scalability, leveraging cloud-native services and best practices. To centrally manage application settings, it uses [Azure App Configuration](https://azure.microsoft.com/services/app-configuration/), which supports feature toggling and the gradual rollout of new features.

Next, let's see how to add Azure App Configuration to a typical Spring Boot application:

1. **Add the Dependency** to your `pom.xml`:

   ```xml
   <dependency>
       <groupId>com.azure.spring</groupId>
       <artifactId>azure-spring-cloud-feature-management-web</artifactId>
       <version>2.3.0</version>
   </dependency>
   ```

2. **Configure Connection Settings** in `application.properties`:

   ```properties
   spring.cloud.azure.appconfiguration.stores[0].endpoint=https://<your-app-config-name>.azconfig.io
   spring.cloud.azure.appconfiguration.stores[0].connection-string=<your-connection-string>
   ```

3. **Example Feature Flag**

   ```java
   @RestController
   @RequestMapping("/api")
   public class FeatureFlagController {

       @GetMapping("/feature")
       @FeatureGate(feature = "BetaFeature")
       public ResponseEntity<String> getFeature() {
           return ResponseEntity.ok("Beta Feature is enabled!");
       }
   }
   ```

With this example, the `/feature` endpoint will only be active if the `BetaFeature` flag is enabled in Azure App Configuration. 
To see a practical example of the Strangler Pattern with feature flags, in action, we have built a reference application that demonstrates feature toggling and gradual migration of a legacy system to a modern architecture.
Clone the [Modern Web App respository](https://github.com/azure/modern-web-app-pattern-java) and follow the steps to deploy.
After you deploy the reference app, the application is automatically set up to use Azure App Configuration.

![edit-application-setting](./docs/edit-application-setting.png)

Now follow the steps in the reference application's [demo.md](https://github.com/Azure/modern-web-app-pattern-java/blob/main/demo.md) to simulate and observe these design patterns: strangler fig, queue-based load leveling, competing consumers, and distributed tracing.

## Conclusion

Modernizing Java applications using the Strangler Pattern and Modern Web App Pattern provides a strategic approach to incrementally evolve legacy systems. By leveraging these patterns, organizations can achieve scalability, maintainability, and performance while minimizing risks and ensuring continuous availability. Start your modernization journey today by adopting these proven architectural patterns with the help of the [Modern Java Web App Patterns Repository](https://github.com/azure/modern-web-app-pattern-java)

## Useful Links

- [Martin Fowler's Strangler Fig Application](https://martinfowler.com/bliki/StranglerFigApplication.html)
- [Azure App Configuration](https://azure.microsoft.com/services/app-configuration/)
