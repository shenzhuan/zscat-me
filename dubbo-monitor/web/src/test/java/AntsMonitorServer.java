import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * jetty热部署启动
 */
public class AntsMonitorServer {

    public static void main(String[] args) {
        Server server = new Server(9020);

        WebAppContext context = new WebAppContext();

        context.setContextPath("/");
        context.setDescriptor("./web/src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("./web/src/main/webapp");
        context.setParentLoaderPriority(true);
        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
