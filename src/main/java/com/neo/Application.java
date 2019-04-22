package com.neo;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

 
@SpringBootApplication
@EnableScheduling
@MapperScan("com.neo.mapper")
@EnableTransactionManagement
public class Application extends TomcatEmbeddedServletContainerFactory {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	public EmbeddedServletContainer getEmbeddedServletContainer(ServletContextInitializer... initializers)  
    {  
        //设置端口  
        this.setPort(8080);  
        return super.getEmbeddedServletContainer(initializers);  
    }  
      
    protected void customizeConnector(Connector connector)  
    {  
        super.customizeConnector(connector);  
        Http11NioProtocol protocol = (Http11NioProtocol)connector.getProtocolHandler();  
        //设置最大连接数  
        protocol.setMaxConnections(10000);  
        //设置最大线程数  
        protocol.setMaxThreads(3000);  
        protocol.setConnectionTimeout(30000);  
    }  
}  

