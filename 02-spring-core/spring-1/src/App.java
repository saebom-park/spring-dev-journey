import org.springframework.context.ApplicationContext;
import org.springframework.context.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Compnent;

public class App {
    public statc void main(String[] args) {
        ApplicationContext context = AnnotationConfigApplicationContext(AppConfig.class);
    }
}