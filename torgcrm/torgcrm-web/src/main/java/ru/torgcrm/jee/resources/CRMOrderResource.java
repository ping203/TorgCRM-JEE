package ru.torgcrm.jee.resources;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.transaction.annotation.Transactional;
import ru.torgcrm.jee.ecommerce.dto.OrderDTO;
import ru.torgcrm.jee.ecommerce.dto.ProductDTO;
import ru.torgcrm.jee.ecommerce.resources.AbstractResource;
import ru.torgcrm.jee.ecommerce.services.OrderService;
import ru.torgcrm.jee.ecommerce.services.ProductService;
import ru.torgcrm.jee.ecommerce.services.ProjectService;
import ru.torgcrm.jee.ecommerce.security.Secured;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ilya Durdyev, funbanji@gmail.com
 */
@Path("/orders")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CRMOrderResource extends AbstractResource {

    @Inject
    private OrderService orderService;
    @Inject
    private RuntimeService runtimeService;
    @Inject
    private ProductService productService;
    @Inject
    private ProjectService projectService;

    @GET
    @Secured
    public List<OrderDTO> getAll() {
        return orderService.findAll();
    }

    @DELETE
    @Secured
    @Path("/{id}")
    public String deleteOrder(@PathParam("id") Long orderId) {
        orderService.delete(orderId);
        return getOkStatus();
    }

    @PUT
    @Secured
    @Transactional
    public String saveOrder(OrderDTO order) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("newOrder");
        order.setProcessId(processInstance.getProcessDefinitionId());
        ProductDTO product = productService.findById(28L);
        if(product != null) {
            List<ProductDTO> products = new ArrayList<>();
            products.add(product);
            order.setProducts(products);
        }
        order.setProject(projectService.findOneByHost("domain.ru"));

        orderService.persist(order);
        return getOkStatus();
    }
}
