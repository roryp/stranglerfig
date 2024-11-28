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
   - **Java 11 or later**
   - **Maven**

### **Steps to Run**

#### 1. Clone the Repository

Start by cloning the repository:

```bash
git clone https://github.com/roryp/stranglerfig.git
cd stranglerfig
```

#### 2. Build the Project

Run the following command to build the project:

```bash
mvn clean install
```

#### 3. Run the Application

Run the following command to start the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start on port 8080 by default.

#### 4. Test the Application

You can test the application by sending requests to the `/api/customer` endpoint. For example:

```bash
curl http://localhost:8080/api/customer?id=MODERN_123
```

This request will be routed to the modern customer service. You can also test with a legacy customer ID:

```bash
curl http://localhost:8080/api/customer?id=LEGACY_456
```

This request will be routed to the legacy customer service.

---

## **Deploying the Full Referernce architecture with Azure Developer CLI (azd)**

The Azure Developer CLI (azd) simplifies the process of deploying the Modern Web App Pattern to Azure. Follow these steps to deploy the application using `azd`:

### **Prerequisites**

1. Install the **Azure Developer CLI**:  
   [Install Azure Developer CLI](https://learn.microsoft.com/en-us/azure/developer/azure-developer-cli/install-azd)

2. Ensure the following tools are installed:  
   - **Java 17 or later**
   - **Docker**

3. An active Azure subscription.

---

### **Steps to Deploy**

#### 1. Clone the Repository

Start by cloning the Modern Web App reference project:

```bash
git clone https://github.com/Azure/modern-web-app-pattern-java.git
cd modern-web-app-pattern-java
```

#### 2. Initialize Azure Developer CLI Project

Run the following command to initialize the `azd` environment:

```bash
azd init
```

You’ll be prompted to select a template. Choose the template for the Modern Web App if it is listed, or confirm the directory contains the correct application files.

#### 3. Provision Azure Resources and Deploy

Run the following command to provision the necessary Azure resources and deploy the application:

```bash
azd up
```

This command performs the following actions:
- Provisions Azure resources, including **App Service**, **Azure Database for PostgreSQL**, and **Azure Key Vault**.
- Deploys the application to the provisioned resources.
- Configures environment variables and connections.

During execution, you’ll be prompted to:
- Authenticate to Azure using your account credentials.
- Select an Azure subscription and region.

#### 4. Retrieve the Application URL

After deployment, the CLI will output the application’s endpoint. You can also retrieve it with:

```bash
azd show
```

The `azd show` command displays essential information, including the URL for your deployed app.

---

### **Testing the Deployment**

Visit the deployed application URL in your browser to verify that the deployment is successful. You should see the running Modern Web App.

---

### **Tear Down Resources**

If you need to clean up the deployed resources, use:

```bash
azd down
```

This command deletes all resources created by the `azd up` command, ensuring no unnecessary charges in your Azure account.

---

## **Conclusion**

Modernizing Java applications with the **Strangler Pattern** and **MWA Pattern** enables organizations to achieve scalability, resilience, and maintainability incrementally. By combining these patterns with streamlined deployment using Azure Developer CLI (`azd`), developers can modernize applications efficiently while minimizing risks.  

For detailed guidance, visit the [Modern Web App Pattern for Java](https://learn.microsoft.com/en-us/azure/architecture/solution-ideas/articles/modern-web-apps-java) documentation.
