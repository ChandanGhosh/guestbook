package com.example.guestbook;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CassandraRepository<Guest, Integer> {

}
