package com.atuluttam.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class CustomPersistenceUnitInfo implements PersistenceUnitInfo {

    // This method defines the name of the persistence unit.
    // The persistence unit is a named configuration that JPA uses to group and configure entity management.
    @Override
    public String getPersistenceUnitName() {
        return "my-persistence-unit";  // The name of the persistence unit
    }

    // This method returns the class name of the JPA provider.
    // The JPA provider is responsible for implementing the JPA specification, such as Hibernate in this case.
    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";  // Hibernate as the JPA provider
    }

    // This method specifies the type of transaction the JPA provider should use.
    // RESOURCE_LOCAL means the transactions are managed by the application, not by a JTA (Java Transaction API) transaction manager.
    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;  // Local transactions (not using JTA)
    }

    // This method provides the JTA (Java Transaction API) DataSource.
    // Since we're using RESOURCE_LOCAL transactions, this method is not used, so we return null.
    @Override
    public DataSource getJtaDataSource() {
        // Creating and configuring a HikariCP data source for database connections
        HikariDataSource dataSource = new HikariDataSource();

        // Set the JDBC URL, username, and password for the MySQL database
        dataSource.setJdbcUrl("jdbc:mysql://localhost/hb1");  // JDBC URL for the MySQL database
        dataSource.setUsername("root");  // Database username
        dataSource.setPassword("root");  // Database password

        // Return the data source object
        return dataSource;
    }

    // This method provides the non-JTA DataSource.
    // Since we're using RESOURCE_LOCAL transactions, we don't need a non-JTA DataSource, so we return null.
    @Override
    public DataSource getNonJtaDataSource() {
        return null;  // No non-JTA DataSource needed
    }

    // This method returns a list of mapping file names (XML files for JPA mapping).
    // Since we are using annotated entity classes (no XML), we return null.
    @Override
    public List<String> getMappingFileNames() {
        return null;  // No XML mapping files
    }

    // This method returns a list of URLs to any JAR files that contain entity classes.
    // In our case, we're not using any external JARs, so we return null.
    @Override
    public List<URL> getJarFileUrls() {
        return null;  // No JAR files for entities
    }

    // This method returns the URL of the root directory of the persistence unit.
    // Since we don't use a persistence.xml file, we return null.
    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;  // No persistence.xml file
    }

    // This method returns a list of entity class names that should be managed by JPA.
    // In our case, we're using a "Product" entity.
    @Override
    public List<String> getManagedClassNames() {
        // We provide the full class name of our entity class (Product) here.
        return List.of("com.atuluttam.entities.Product");  // The Product entity class to be managed by JPA
    }

    // This method specifies whether to exclude unlisted classes from the persistence unit.
    // If true, only explicitly listed classes are included. In this case, we return false to include all classes.
    @Override
    public boolean excludeUnlistedClasses() {
        return false;  // Do not exclude unlisted classes
    }

    // This method returns the shared cache mode for the persistence unit.
    // Since we're not specifying a cache mode, we return null.
    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;  // No shared cache mode specified
    }

    // This method returns the validation mode for the persistence unit.
    // Validation modes are typically used to validate entities. Since we're not using validation in this case, we return null.
    @Override
    public ValidationMode getValidationMode() {
        return null;  // No validation mode specified
    }

    // This method returns properties for the persistence unit, such as Hibernate settings.
    // These properties configure how Hibernate interacts with the database, including schema management, SQL logging, etc.
    @Override
    public Properties getProperties() {
        Properties properties = new Properties();

        // Hibernate properties to manage schema creation
        properties.put("hibernate.hbm2ddl.auto", "update");  // Automatically update the schema if necessary
        properties.put("hibernate.show_sql", "true");  // Show SQL queries in the console
        properties.put("hibernate.format_sql", "true");  // Format the SQL output
        properties.put("hibernate.use_sql_comments", "true");  // Enable SQL comments in the output

        return properties;  // Return the Hibernate properties
    }

    // This method returns the version of the persistence XML schema.
    // Since we're not using a persistence.xml file, we return null.
    @Override
    public String getPersistenceXMLSchemaVersion() {
        return null;  // No persistence.xml file
    }

    // This method returns the class loader to use for loading classes. Since we don't need to provide a specific class loader, we return null.
    @Override
    public ClassLoader getClassLoader() {
        return null;  // No specific class loader needed
    }

    // This method allows for adding a class transformer. We don't need it here, so the method is empty.
    @Override
    public void addTransformer(ClassTransformer classTransformer) {
    }

    // This method provides a temporary class loader. Again, we don't need it here, so we return null.
    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;  // No temporary class loader needed
    }
}
