JEET.Lab4 - Enterprise Calculator (EJB & Jakarta EE)
This project was developed based on the "Lab.4: Building Jakarta Enterprise Application using: EJBs, Maven and JSF/BootsFaces" student guide prepared by Łukasz Laszko. The application simulates long-term business methods using various EJB components within a distributed architecture.

🎓 Academic Scope
This laboratory work focuses on the implementation and management of enterprise-level components:

EJB Components: Mastering the use of Stateless, Stateful, and Singleton session beans.

Interface Management: Implementing and distinguishing between Local and Remote interfaces for EJB communication.

JNDI Namespace: Understanding how components are registered and looked up within the application server's naming directory.

Jakarta EE Lifecycle: Managing the deployment and pooling of enterprise beans on GlassFish Server.

🛠 Technical Architecture
Enterprise Application (EA): A multi-module Maven project consisting of EJB, Web, and EAR (Enterprise Archive) modules.

Backend (EJB): Business logic handled by Stateless and Stateful beans, with results aggregated in a Singleton bean.

Frontend (JSF & BootsFaces): A web-based client utilizing JSF ManagedBeans and the BootsFaces framework for a responsive user interface.

AJAX Integration: Utilizing asynchronous requests to update specific UI components (like results tables) without full page refreshes.

🚀 Key Features
Asynchronous Calculations: Simulating business logic through different types of EJBs to analyze server behavior.

Server Pool Analysis: Monitoring component activity, idle timeouts, and pooling settings via the GlassFish Admin Console.

Dynamic UI: Using BootsFaces components and AJAX for modern web interaction.

📚 Acknowledgment
This work is part of the JavaEE Technologies - Lab.4 curriculum, following the advanced structural and technical guidelines provided by Łukasz Laszko.
