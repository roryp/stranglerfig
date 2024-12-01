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
