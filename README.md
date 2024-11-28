# Modernizing Java Applications with the Strangler and Modern Web App Patterns

![Strangler pattern](Picture1.png)


Modernizing Java applications is essential for organizations aiming to improve scalability, maintainability, and performance while embracing cloud-native architectures. The **Strangler Pattern**, combined with the **Modern Web App (MWA) Pattern**, provides a robust framework for incrementally transitioning from monolithic systems to modular, resilient applications. This approach ensures seamless migration, minimizes risks, and aligns legacy systems with modern development practices inspired by frameworks like the **Reliable Web App (RWA) Pattern**.

---

## **The Strangler Pattern: Incremental Evolution**

The **Strangler Pattern**, inspired by the gradual takeover of a host by a strangler fig tree, allows developers to replace components of legacy systems incrementally. Instead of a wholesale migration, specific functionalities are routed to new, modern implementations. Key benefits of this pattern include:

- **Minimized Risks**: Incremental changes reduce the likelihood of system-wide disruptions.  
- **No Downtime**: Updates happen without halting the system, ensuring continued availability.  
- **Parallel Operations**: Legacy and modern systems coexist during migration, providing flexibility for gradual transitions.  

By isolating and routing traffic to new components selectively, the Strangler Pattern enables phased modernization.

---

## **The Modern Web App Pattern: Foundation for Resilience**

The **Modern Web App (MWA) Pattern** serves as a blueprint for creating cloud-native, scalable applications. Drawing principles from the **RWA Pattern**, it emphasizes resilience, security, and scalability. Its core tenets include:

- **Microservices**: Breaking monoliths into independently deployable components.  
- **Containerization**: Ensuring consistency across diverse environments.  
- **Cloud-Native Services**: Leveraging tools like **Azure Kubernetes Service (AKS)** and **Azure App Service**.  
- **Automated Pipelines**: Enabling Continuous Integration and Continuous Deployment (CI/CD).  

This pattern facilitates the adoption of best practices for building modern, efficient applications tailored for the cloud.

---

## **Combining Strangler and MWA Patterns**

Integrating these patterns ensures organizations can modernize at their own pace while reaping the benefits of cloud-native technologies. For example, consider the migration of a `/customer` endpoint from a legacy system to a modernized architecture. This process involves deploying a router that directs requests to either the old or new implementation based on specific conditions.

### **Example: Routing with Strangler Pattern**

```java
@RestController
@RequestMapping("/api")
public class CustomerRouterController {
    private final LegacyCustomerService legacyService;
    private final ModernCustomerService modernService;

    @Autowired
    public CustomerRouterController(LegacyCustomerService legacyService, ModernCustomerService modernService) {
        this.legacyService = legacyService;
        this.modernService = modernService;
    }

    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomer(@RequestParam String id) {
        if (isModernCustomer(id)) {
            return modernService.getCustomer(id);
        }
        return legacyService.getCustomer(id);
    }

    private boolean isModernCustomer(String id) {
        // Logic to determine if the request should be routed to the modern service
        return id.startsWith("MODERN_");
    }
}
```

This example uses a centralized router to direct requests based on specific criteria, such as the format of the customer ID. It enables both legacy and modernized systems to coexist during the transition.

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

Modernizing Java applications with the **Strangler Pattern** and **MWA Pattern** enables organizations to achieve scalability, resilience, and maintainability incrementally. By combining these patterns with streamlined deployment using Azure Developer CLI (`azd`), developers can modernize applications efficiently while minimizing risks.  

For detailed guidance, visit the [Modern Web App Pattern for Java](https://learn.microsoft.com/en-us/azure/architecture/web-apps/guides/enterprise-app-patterns/modern-web-app/java/guidance) documentation.
