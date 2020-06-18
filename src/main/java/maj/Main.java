package maj;

//import static spark.Spark.get;


import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main extends ResourceConfig {
    public Main() throws Exception {
        final Board board = new Board();
        packages("maj");
        register(new HK2Binder(board));
    }

    public static void main(String[] args) throws Exception {
        new Main().start(4567);
    }

    static class HK2Binder extends AbstractBinder {
        private Board board;

        public HK2Binder(Board board) {
            this.board = board;
        }

        @Override
        protected void configure() {
            bindFactory(new Factory<Board>() {
                @Override
                public Board provide() {
                    return board;
                }

                @Override
                public void dispose(Board board) {

                }
            }).to(Board.class);
        }
    }



    public void start(int port) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase("resources");

        Server jettyServer = new Server(port);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class,
                                                         "/api/*");

        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "maj");
        jerseyServlet.setInitParameter("javax.ws.rs.Application", "maj.Main");

        context.addServlet(DefaultServlet.class, "/").setInitOrder(1);
        context.addFilter(CorsFilter.class, "/*", EnumSet.allOf(DispatcherType.class));


        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jettyServer.destroy();
        }
    }
}