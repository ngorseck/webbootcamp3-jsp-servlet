package com.samanecorporation.webbootcamp3.config;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.samanecorporation.webbootcamp3.entities.DepartmentEntity;
import com.samanecorporation.webbootcamp3.entities.DeptEmployeeEntity;
import com.samanecorporation.webbootcamp3.entities.EmployeeEntity;
import com.samanecorporation.webbootcamp3.entities.UserAccountEntity;



public class HibernateUtil {
	private static SessionFactory sessionFactory;

	private HibernateUtil() {
		
	}
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				PropertiesReader reader = new PropertiesReader("database.properties"); 
				Map<String, String> env = System.getenv();
				String userProd = env.get("WEB_DB_USER");
				String pwdProd = env.get("WEB_DB_PWD");
				String urlDbProd = env.get("WEB_URL_DB_PWD");
								
				Configuration configuration = new Configuration();

 				Properties settings = new Properties();
				settings.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
				if ((userProd != null && !userProd.isBlank()) && (pwdProd != null && !pwdProd.isBlank()) && (urlDbProd != null && !urlDbProd.isBlank())) {
					settings.put(AvailableSettings.URL, urlDbProd);
					settings.put(AvailableSettings.USER, userProd);
					settings.put(AvailableSettings.PASS, pwdProd);
				} else {
					settings.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/securitydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
					settings.put(AvailableSettings.USER, reader.getProperty("db.username"));
					settings.put(AvailableSettings.PASS, reader.getProperty("db.password"));
				}
				settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				//cette ligne est très importante
				settings.put(AvailableSettings.HBM2DDL_AUTO, "update");
				
				settings.put(AvailableSettings.SHOW_SQL, "true");
				settings.put(AvailableSettings.FORMAT_SQL, "true");

				settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(DepartmentEntity.class);
				configuration.addAnnotatedClass(EmployeeEntity.class);
				configuration.addAnnotatedClass(DeptEmployeeEntity.class);
				configuration.addAnnotatedClass(UserAccountEntity.class);
 
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
 				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				
 				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}