package com.api.notas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractCrudService<T, ID> implements CrudService<T, ID> { // Clase abstracta que implementa los métodos CRUD básicos para entidades JPA
    protected final JpaRepository<T, ID> repo; //

    protected AbstractCrudService(JpaRepository<T, ID> repo) { //Constructor 
        this.repo = repo;
    }

    @Transactional(readOnly = true) // Método para obtener todas las entidades
    @Override //Override
    public List<T> getAll() {
        return repo.findAll(); // Retorna una lista de todas las entidades
    }

    @Transactional(readOnly = true) // Método para obtener una entidad por su ID
    @Override
    public Optional<T> getById(ID id) {
        return repo.findById(id); // Retorna con la entidad si existe, o un Optional vacío si no
    }

    @Override // Método para guardar una nueva entidad
    public T save(T ent) { // Método para guardar una nueva entidad
        return repo.save(ent);
    }

    @Transactional
    @Override
    public T update(ID id, T ent) { // Método para actualizar una entidad existente
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("La entidad con ID " + id + " no existe."); // Verifica si la entidad con el ID proporcionado existe, si no, lanza una excepción
        }
        T existing = repo.findById(id).orElseThrow();
        BeanUtils.copyProperties(ent, existing, "id"); // Copia las propiedades de la entidad proporcionada a la entidad existente, excluyendo el ID para evitar sobrescribirlo
        return repo.save(existing); // Guarda la entidad actualizada y la retorna
    }

    @Override // Método para eliminar una entidad por su ID
    public void deleteById(ID id) {
        repo.deleteById(id); // Elimina la entidad con el ID proporcionado
    }
}
