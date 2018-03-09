package ru.torgcrm.jee.ecommerce.services;

import ru.torgcrm.jee.ecommerce.dto.GenericDTO;

import java.util.List;

/**
 * Generic Service
 *
 * @author Ilya Durdyev, funbanji@gmail.com
 */
public interface IGenericService<D extends GenericDTO> {
    /**
     * Get all products
     *
     * @return {@link GenericDTO}
     */
    List<D> findAll();

    /**
     * Find by product id
     *
     * @param id product id
     * @return {@link GenericDTO}
     */
    D findById(Long id);

    /**
     * Find all products by project id {@link ru.torgcrm.jee.ecommerce.domain.Project}
     *
     * @param projectId project id
     * @return {@link GenericDTO}
     */
    List<D> findAllByProjectId(Long projectId);
}