package ua.vza.kay.qp.controllers;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aleksandr on 19.07.15.
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {
    private final Set<Class<?>> classes;

    public ApplicationConfig() {
        HashSet<Class<?>> c = new HashSet<Class<?>>();
        c.add(ServiceController.class);
        c.add(FileService.class);
        c.add(SearchService.class);
        c.add(MultiPartFeature.class);
        classes = Collections.unmodifiableSet(c);
    }

    public Set<Class<?>> getClasses() {
        return classes;
    }
}
