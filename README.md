# Modern Web App Patterns: Strangler Fig

## Introduction

In today's fast-paced technological landscape, modernizing Java applications is crucial for achieving scalability, maintainability, and performance. Legacy systems often struggle to keep up with the demands of modern users and evolving business requirements. By adopting modern architectural patterns, organizations can ensure their applications remain competitive and capable of handling increased workloads.

## The Strangler Pattern: A Strategy for Incremental Modernization

Originally introduced by Martin Fowler, the **Strangler Pattern** offers a method to incrementally replace parts of a legacy system with new functionality. Inspired by the strangler fig tree that gradually overtakes its host, this approach allows for a smooth transition from the old system to the new one without the need for a complete rewrite.

**Key Benefits of the Strangler Pattern:**

- **Minimized Risks**: Incremental changes reduce the likelihood of system-wide disruptions.
- **No Downtime**: Updates occur without halting the system, ensuring continued availability.
- **Parallel Operations**: Legacy and modern systems coexist during migration, providing flexibility for gradual transitions.

By selectively isolating and routing traffic to new components, the Strangler Pattern enables phased modernization while maintaining system stability.

*Read Martin Fowler's original article on the Strangler Fig Application [here](https://martinfowler.com/bliki/StranglerFigApplication.html).*

## Implementing the Strangler Pattern with Modern Web App (MWA) Patterns

Combining the Strangler Pattern with the **Modern Web App (MWA) Pattern** provides a robust foundation for creating cloud-native, scalable applications. The MWA pattern emphasizes resilience, security, and scalability, leveraging cloud services and best practices.

**Core Tenets of the MWA Pattern:**

- **Microservices**: Breaking monoliths into independently deployable components.
- **Containerization**: Ensuring consistency across diverse environments using containers.
- **Cloud-Native Services**: Leveraging platforms like **Azure Kubernetes Service (AKS)** and **Azure App Service**.
- **Automated Pipelines**: Implementing Continuous Integration and Continuous Deployment (CI/CD) for rapid delivery.

### Example: Routing with the Strangler Pattern

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

## Reference Implementation: Modern Java Web App Patterns

The sample implementation in this repository mirrors the approach provided in the [Modern Java Web App Patterns](https://github.com/azure/modern-web-app-pattern-java) repository. Both implementations utilize the Strangler Pattern for incremental modernization.

**Key Aspects of the Reference App Implementation:**

- **Gradual Replacement**: Replacing parts of the legacy system incrementally with new functionality.
- **Routing Logic**: Directing requests to the appropriate service (legacy or modern) based on specific criteria.
- **Asynchronous Communication**: Using message queues for reliable, decoupled interactions between services.
- **Containerization**: Deploying services consistently across environments using container apps.
- **Comprehensive Documentation**: Providing step-by-step instructions for setting up, deploying, and managing the application transition.

For detailed guidance, visit the [Modern Java Web App Patterns](https://github.com/azure/modern-web-app-pattern-java) repository.

*For more on the Strangler Pattern, see the [Strangler Fig Pattern documentation](docs/StranglerFig.md).*

## Running the Spring Boot Sample

Follow these steps to run the Spring Boot sample demonstrating the Strangler Pattern.

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
     curl http://localhost:8080/api/customer?id=LEGACY_2
     ```

     These requests are routed to the legacy customer service.

### Integration Tests

Integration tests are included to verify the retrieval of sample customers from both legacy and modern services. These tests ensure correct routing and data retrieval based on customer IDs.

## Appendix

### FF4j: Feature Flipping for Java

[FF4j (Feature Flipping for Java)](https://ff4j.org/) is a library that enables dynamic activation and deactivation of features in Java applications. It allows developers to manage features without redeploying code, facilitating:

- **Dynamic Feature Management**: Toggle features at runtime.
- **A/B Testing**: Gradually roll out features to subsets of users.
- **Monitoring and Auditing**: Track feature usage and performance.

FF4j can be integrated into Spring Boot applications, providing a web console and APIs for feature management.

### Azure App Configuration for Feature Flags

The Modern Web App pattern reference sample leverages [Azure App Configuration](https://azure.microsoft.com/services/app-configuration/) to manage application settings and feature flags centrally. This service is particularly useful when implementing the Strangler Pattern, as it supports feature toggling and the gradual rollout of new features.

**Benefits of Azure App Configuration:**

- **Centralized Management**: Simplify configuration across multiple applications and environments.
- **Real-Time Updates**: Modify settings and feature flags without application restarts.
- **Feature Flags**: Control feature exposure to users, enabling staged rollouts.
- **Seamless Integration**: Works well with Azure services and supports various programming languages.

#### Example: Using Azure App Configuration in a Spring Boot Application

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

3. **Implement Feature Flags** in your code:

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

   With this setup, the `/feature` endpoint will only be active if the `BetaFeature` flag is enabled in Azure App Configuration.

## Useful Links

- [Martin Fowler's Strangler Fig Application](https://martinfowler.com/bliki/StranglerFigApplication.html)
- [Modern Java Web App Patterns Repository](https://github.com/azure/modern-web-app-pattern-java)
- [Strangler Fig Pattern Documentation](docs/StranglerFig.md)
- [JDK 17 Downloads](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Apache Maven Downloads](https://maven.apache.org/download.cgi)
- [Visual Studio Code Downloads](https://code.visualstudio.com/Download)
- [Java Extension Pack for Visual Studio Code](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- [FF4j Official Website](https://ff4j.org/)
- [Azure App Configuration](https://azure.microsoft.com/services/app-configuration/)
