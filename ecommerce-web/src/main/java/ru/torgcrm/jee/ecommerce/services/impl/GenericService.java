package ru.torgcrm.jee.ecommerce.services.impl;

import ru.torgcrm.jee.ecommerce.domain.GenericEntity;
import ru.torgcrm.jee.ecommerce.dto.GenericDTO;
import ru.torgcrm.jee.ecommerce.mappers.EntityMapper;
import ru.torgcrm.jee.ecommerce.repository.GenericRepository;
import ru.torgcrm.jee.ecommerce.services.IGenericService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Ilya Durdyev, funbanji@gmail.com
 */
public abstract class GenericService<
        D extends GenericDTO,
        R extends GenericRepository,
        M extends EntityMapper> implements IGenericService<D> {

    @Inject
    private R repository;
    @Inject
    private M mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List findAll() {
        List list = repository.findAll();
        if (list != null) {
            return mapper.toDto(list);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D findById(Long id) {
        GenericEntity entity = repository.findById(id);
        if (entity != null) {
            return (D) mapper.toDto(entity);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List findAllByProjectId(Long projectId) {
        List list = repository.findAllByProjectId(projectId);
        if (list != null) {
            return mapper.toDto(list);
        }
        return null;
    }

    protected R getRepository() {
        return repository;
    }

    protected M getMapper() {
        return mapper;
    }
}