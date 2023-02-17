package tobyspring.my.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tobyspring.my.boot.hello.HelloController;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyBootApplication {

	public static void main(String[] args) {
		// 1. 서블릿 컨테이너(톰캣 웹 서버) 띄우기
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
			// 2. 서블릿 등록
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				servletContext.addServlet("hello", new HttpServlet() {
					@Override
					protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						// 인증, 보안, 다국어, 공통 기능

						if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
							String name = req.getParameter("name");

							String result = new HelloController().hello(name);

							resp.setStatus(HttpStatus.OK.value());
							resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
							resp.getWriter().println("Hello " + result);
						} else {
							resp.setStatus(HttpStatus.NOT_FOUND.value());
						}
				} }).addMapping("/*");
			}
		});
		webServer.start();
	}
}
