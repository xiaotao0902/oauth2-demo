package com.pactera.ecplatform.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pactera.ecplatform.product.domain.OauthConfig;


@Repository("oauthConfigRepository")
public interface OauthConfigRepository extends CrudRepository<OauthConfig, Long> {

}
