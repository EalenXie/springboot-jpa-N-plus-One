1. This project is a typical example of solving the problem of JPA lazy loading N+1:
    
    base on Spring Boot 1.5.6

2. The key is based on two annotations:
   
    @NamedEntityGraph : Annotation on entity ; @EntityGraph : Annotation on repository
    
3. Before you start up application: 
        
    please modify your own database config: /application.yml  
    
    in this demo : 
            
        please set /application.yml  jpa.hibernate.ddl-auto=update  jpa.show-sql=true                                              
                           
    
    
   
    

