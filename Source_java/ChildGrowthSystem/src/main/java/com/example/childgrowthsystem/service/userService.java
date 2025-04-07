package com.example.childgrowthsystem.service;

import com.example.childgrowthsystem.entity.Users;
import com.example.childgrowthsystem.reponsitory.userReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class userService implements userReponsitory {

    @Autowired
    private userReponsitory userReponsitory;

    @Override
    public Users findByUsername(String username) {
        return userReponsitory.findByUsername(username);
    }

    @Override
    public void flush() {
        userReponsitory.flush();
    }

    @Override
    public <S extends Users> S saveAndFlush(S entity) {
        return userReponsitory.saveAndFlush(entity);
    }

    @Override
    public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
        return userReponsitory.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Users> entities) {
        userReponsitory.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        userReponsitory.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        userReponsitory.deleteAllInBatch();
    }

    @Override
    public Users getOne(Long aLong) {
        return userReponsitory.getOne(aLong);
    }

    @Override
    public Users getById(Long aLong) {
        return userReponsitory.getById(aLong);
    }

    @Override
    public Users getReferenceById(Long aLong) {
        return userReponsitory.getReferenceById(aLong);
    }

    @Override
    public <S extends Users> Optional<S> findOne(Example<S> example) {
        return userReponsitory.findOne(example);
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example) {
        return userReponsitory.findAll(example);
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
        return userReponsitory.findAll(example, sort);
    }

    @Override
    public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
        return userReponsitory.findAll(example, pageable);
    }

    @Override
    public <S extends Users> long count(Example<S> example) {
        return userReponsitory.count(example);
    }

    @Override
    public <S extends Users> boolean exists(Example<S> example) {
        return userReponsitory.exists(example);
    }

    @Override
    public <S extends Users, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return userReponsitory.findBy(example, queryFunction);
    }

    @Override
    public <S extends Users> S save(S entity) {
        return userReponsitory.save(entity);
    }

    @Override
    public <S extends Users> List<S> saveAll(Iterable<S> entities) {
        return userReponsitory.saveAll(entities);
    }

    @Override
    public Optional<Users> findById(Long aLong) {
        return userReponsitory.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return userReponsitory.existsById(aLong);
    }

    @Override
    public List<Users> findAll() {
        return userReponsitory.findAll();
    }

    @Override
    public List<Users> findAllById(Iterable<Long> longs) {
        return userReponsitory.findAllById(longs);
    }

    @Override
    public long count() {
        return userReponsitory.count();
    }

    @Override
    public void deleteById(Long aLong) {
        userReponsitory.deleteById(aLong);
    }

    @Override
    public void delete(Users entity) {
        userReponsitory.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        userReponsitory.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Users> entities) {
        userReponsitory.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        userReponsitory.deleteAll();
    }

    @Override
    public List<Users> findAll(Sort sort) {
        return userReponsitory.findAll(sort);
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return userReponsitory.findAll(pageable);
    }
}
