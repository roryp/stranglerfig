# Modernizing Java Applications with the Strangler and Modern Web App Patterns

![Strangler pattern](Picture1.png)

Hey there! If you're like me, you're always on the lookout for ways to make your Java applications more scalable, maintainable, and performant. Well, you're in luck! Today, we're going to dive into the **Strangler Pattern** and the **Modern Web App (MWA) Pattern**. These patterns provide a fantastic framework for incrementally transitioning from monolithic systems to modular, resilient applications. And the best part? You can do it without any downtime or major disruptions. Let's get started!

---

## **The Strangler Pattern: Incremental Evolution**

Imagine a strangler fig tree gradually taking over its host. That's the inspiration behind the **Strangler Pattern**. Instead of a complete overhaul, you replace components of your legacy system incrementally. Here are some key benefits:

- **Minimized Risks**: Incremental changes reduce the likelihood of system-wide disruptions.  
- **No Downtime**: Updates happen without halting the system, ensuring continued availability.  
- **Parallel Operations**: Legacy and modern systems coexist during migration, providing flexibility for gradual transitions.  

By isolating and routing traffic to new components selectively, the Strangler Pattern enables phased modernization.

---

## **The Modern Web App Pattern: Foundation for Resilience**

The **Modern Web App (MWA) Pattern** is like a blueprint for creating cloud-native, scalable applications. It emphasizes resilience, security, and scalability. Here are its core tenets:

- **Microservices**: Breaking monoliths into independently deployable components.  
- **Containerization**: Ensuring consistency across diverse environments.  
- **Cloud-Native Services**: Leveraging tools like **Azure Kubernetes Service (AKS)** and **Azure App Service**.  
- **Automated Pipelines**: Enabling Continuous Integration and Continuous Deployment (CI/CD).  

This pattern helps you adopt best practices for building modern, efficient applications tailored for the cloud.

---

## **Combining Strangler and MWA Patterns**

Now, let's talk about how these patterns work together. Imagine you're migrating a `/customer` endpoint from a legacy system to a modernized architecture. You can deploy a router that directs requests to either the old or new implementation based on specific conditions.

### **Example: Routing with Strangler Pattern**

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

This example uses a centralized router to direct requests based on specific criteria, such as the format of the customer ID. It enables both legacy and modernized systems to coexist during the transition.

---

## **Martin Fowler's Original Strangler Pattern**

Martin Fowler introduced the Strangler Pattern as a way to incrementally replace parts of a legacy system with new functionality. The pattern is inspired by the strangler fig tree, which gradually grows around and replaces its host tree. This approach allows for a smooth transition from the old system to the new one without a complete rewrite.

### **Example: Martin Fowler's Strangler Pattern Code**

```java
public class StranglerApplication {
    public static void main(String[] args) {
        LegacySystem legacySystem = new LegacySystem();
        ModernSystem modernSystem = new ModernSystem();

        String request = "someRequest";
        if (isModernRequest(request)) {
            modernSystem.handleRequest(request);
        } else {
            legacySystem.handleRequest(request);
        }
    }

    private static boolean isModernRequest(String request) {
        // Logic to determine if the request should be handled by the modern system
        return request.contains("modern");
    }
}

class LegacySystem {
    void handleRequest(String request) {
        System.out.println("Handling request in legacy system: " + request);
    }
}

class ModernSystem {
    void handleRequest(String request) {
        System.out.println("Handling request in modern system: " + request);
    }
}
```

Martin Fowler introduced the Strangler Pattern in his article "Strangler Fig Application" published on his website. You can read the original article [here](https://martinfowler.com/bliki/StranglerFigApplication.html).

This example demonstrates how requests can be routed between the legacy and modern systems based on specific criteria, allowing for incremental modernization.

---

## **Comparison with the Reference App**

The sample implementation in this repository is similar to the reference app provided in the [Modern Java Web App Patterns](https://github.com/azure/modern-web-app-pattern-java) repository. Both implementations use the strangler fig pattern for incremental modernization.

The reference app demonstrates how to apply the strangler fig pattern for Modern Web Applications (MWA). This pattern involves gradually replacing parts of a legacy system with new functionality, allowing for incremental modernization without disrupting the entire system. The sample implementation in this repository follows a similar approach, showcasing how to route requests between legacy and modern services based on specific criteria.

For more details on the reference app and its use of the strangler fig pattern for MWA, visit the [Modern Java Web App Patterns](https://github.com/azure/modern-web-app-pattern-java) repository.

### **Details on the Reference App Implementation**

The reference app provided in the [Modern Java Web App Patterns](https://github.com/azure/modern-web-app-pattern-java) repository implements the strangler fig pattern in a detailed and comprehensive manner. Here are some key aspects of how the reference app implements the strangler fig pattern:

- **Gradual Replacement**: The reference app gradually replaces parts of the legacy system with new functionality. This allows for incremental modernization without disrupting the entire system.
- **Routing Logic**: The reference app uses routing logic to direct requests to either the legacy or modern implementation based on specific criteria. This ensures that both systems can coexist during the transition period.
- **Incremental Modernization**: The reference app demonstrates how to incrementally modernize a legacy system by replacing individual components with modern implementations. This approach minimizes risks and ensures a smooth transition.
- **Message Queues**: The reference app uses message queues to handle asynchronous communication between services, ensuring reliable and decoupled interactions.
- **Container Apps**: The reference app utilizes container apps to package and deploy services consistently across different environments, enhancing scalability and maintainability.
- **Detailed Documentation**: The reference app provides detailed documentation and step-by-step instructions for implementing the strangler fig pattern. This includes guidance on setting up the development environment, deploying the application, and managing the transition from legacy to modern systems.

For a comprehensive understanding of how the reference app implements the strangler fig pattern, refer to the [Modern Java Web App Patterns](https://github.com/azure/modern-web-app-pattern-java) repository and its accompanying documentation.

For more details on the Strangler Fig Pattern, visit the [Strangler Fig Pattern](docs/SranglerFig.md) documentation.

---

## **Using Azure App Configuration for Feature Flags**

The Modern Web App pattern reference sample uses Azure App Configuration to manage application settings and feature flags. Azure App Configuration is a managed service that provides a central place to manage application settings and feature flags. This is particularly useful in the context of the strangler fig pattern, as it allows for feature toggling and gradual rollout of new features.

### **Benefits of Azure App Configuration**

- **Centralized Management**: Manage all your application settings and feature flags in one place.
- **Dynamic Configuration**: Update configuration settings and feature flags without redeploying your application.
- **Feature Toggles**: Gradually roll out new features to users, reducing risk and ensuring a smooth transition.
- **Integration with Azure Services**: Seamlessly integrate with other Azure services like Azure Functions, Azure App Service, and more.

### **Example: Using Azure App Configuration**

Here's an example of how you can use Azure App Configuration to manage feature flags in a Spring Boot application:

1. **Add Dependency**: Add the Azure App Configuration dependency to your `pom.xml` file.

```xml
<dependency>
    <groupId>com.microsoft.azure</groupId>
    <artifactId>azure-spring-cloud-feature-management</artifactId>
    <version>2.3.0</version>
</dependency>
```

2. **Configure App Configuration**: Add the following configuration to your `application.properties` file.

```properties
spring.cloud.azure.appconfiguration.stores[0].endpoint=https://<your-app-configuration-name>.azconfig.io
spring.cloud.azure.appconfiguration.stores[0].connection-string=<your-connection-string>
```

3. **Use Feature Flags**: Use the `@FeatureGate` annotation to enable or disable features based on feature flags.

```java
@RestController
@RequestMapping("/api")
public class FeatureFlagController {

    @GetMapping("/feature")
    @FeatureGate(feature = "NewFeature")
    public ResponseEntity<String> getFeature() {
        return ResponseEntity.ok("New Feature is enabled!");
    }
}
```

This example demonstrates how to use Azure App Configuration to manage feature flags and enable or disable features dynamically.

---

## **Running the Spring Boot Sample**

To run the Spring Boot sample demonstrating the strangler fig pattern, follow these steps:

### **Prerequisites**

1. Ensure the following tools are installed:
   - **JDK 17**
   - **Maven**
   - **Visual Studio Code**
   - **Java Extension Pack for Visual Studio Code**

### **Steps to Run**

#### 1. Install JDK 17

Download and install JDK 17 from the [official website](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html). Follow the installation instructions for your operating system.

#### 2. Install Apache Maven

Download and install Apache Maven from the [official website](https://maven.apache.org/download.cgi). Follow the installation instructions for your operating system.

#### 3. Install Visual Studio Code

Download and install Visual Studio Code from the [official website](https://code.visualstudio.com/Download). Follow the installation instructions for your operating system.

#### 4. Install Java Extension Pack for Visual Studio Code

Install the Java Extension Pack for Visual Studio Code from the [Visual Studio Code Marketplace](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack). This extension pack includes essential Java tools such as Maven support and Java 17 compatibility.

#### 5. Clone the Repository

Start by cloning the repository:

```bash
git clone https://github.com/roryp/stranglerfig.git
cd stranglerfig
```

#### 6. Build the Project

Run the following command to build the project:

```bash
mvn clean install
```

#### 7. Run the Application

Run the following command to start the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start on port 8080 by default.

#### 8. Test the Application

You can test the application by sending requests to the `/api/customer` endpoint. For example:

```bash
curl http://localhost:8080/api/customer?id=MODERN_123
```

This request will be routed to the modern customer service. You can also test with a legacy customer ID:

```bash
curl http://localhost:8080/api/customer?id=LEGACY_456
```

This request will be routed to the legacy customer service.

## **Conclusion**

Modernizing Java applications with the **Strangler Pattern** and **MWA Pattern** enables organizations to achieve scalability, resilience, and maintainability incrementally. By combining these patterns with streamlined deployment, developers can modernize applications efficiently while minimizing risks.  

For detailed guidance, visit the [Modern Web App Pattern for Java](https://learn.microsoft.com/en-us/azure/architecture/web-apps/guides/enterprise-app-patterns/modern-web-app/java/guidance) documentation.

---

## **Strangler Fig Pattern**

The Strangler Fig Pattern is a software development pattern that is used to incrementally migrate a legacy system to a new system. The pattern is named after the strangler fig tree, which is a plant that grows on another tree and slowly strangles it. The pattern is used to avoid the risks associated with a complete system rewrite by allowing the new system to grow around the old system and gradually take over its functionality.

The original Contoso Fiber CAMS application has the capability of sending emails to customers with support guides. The application uses a blocking call to legacy email service. CAMS has grown in usage and the email functionality has been identified as bottleneck. The goal is to migrate the email functionality to a new service without disrupting the existing application.

Azure offers many services that can be used to build a new email service. The new email service is built using a combination of Azure Service Bus and Azure Container Apps. 

The Strangler Fig Pattern is a good fit for this scenario. The new email service will be developed and deployed alongside the existing email service. The new service will be integrated with the existing application and gradually take over the email functionality. Once the new service is fully operational, the old service can be decommissioned.

The Strangler Fig Pattern is implemented in the following steps:

1. **Identify the functionality to be migrated**: The first step is to identify the functionality that needs to be migrated. In this case, the email functionality of the Contoso Fiber CAMS application needs to be migrated to a new service.

The email functionality is implemented [here](https://github.com/Azure/modern-web-app-pattern-java/blob/main/apps/contoso-fiber/src/main/java/com/contoso/cams/services/SupportGuideEmailSender.java)

2. **Develop the new service**: The next step is to develop the new service that will replace the existing functionality. The new service should be designed to be compatible with the existing application and should provide the same functionality.

The new email service is implemented [here](https://github.com/Azure/modern-web-app-pattern-java/blob/main/apps/email-processor/src/main/java/com/contoso/cams/emailprocessor/EmailProcessor.java)

3. **Deploy the new service**: The new service is deployed alongside the existing application. The new service should be integrated with the existing application so that it can gradually take over the email functionality.

4. **Gradually migrate functionality**: The email functionality is gradually migrated from the old service to the new service. This can be done by routing a percentage of the email requests to the new service and gradually increasing the percentage over time.

The `CONTOSO_SUPPORT_GUIDE_REQUEST_SERVICE` environment variable changes the behavior of CAMS. Possible values for `CONTOSO_SUPPORT_GUIDE_REQUEST_SERVICE` are `email` and `queue`. 
* `email` refers to the old functionaly where the the monolithic CAMS application sent out emails directly.
* `queue` is the new functionality where messages are sent to to the new email service through Azure Service Bus.

![request-service-email-setting](docs/assets/request-service-email-setting.png)

![request-service-queue-setting](docs/assets/request-service-queue-setting.png)

5. **Decommission the old service**: Once the new service is fully operational and all email functionality has been migrated, the old service can be decommissioned.

## Additional Resources

[Strangler Fig Pattern](https://docs.microsoft.com/azure/architecture/patterns/strangler-fig) documentation.
