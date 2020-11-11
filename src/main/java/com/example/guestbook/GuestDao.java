package com.example.guestbook;

import java.util.ArrayList;
import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class GuestDao {

    private CqlSession session;
    private PreparedStatement selectGuests;

    private static final String guestsTableName = "guests";

    public GuestDao(CqlSession session, String keyspace, String localDataCenter) {
        this.session = session;
        maybeCreateGuestSchema(keyspace, localDataCenter);
        this.selectGuests = session.prepare(getSelectGuests(keyspace));
    }

    private String getSelectGuests(String keyspace) {
        return String.format("SELECT * FROM %s.%s;", keyspace, guestsTableName);
    }

    private void maybeCreateGuestSchema(String keyspace, String localDataCenter) {
        session.execute(String.format(""
                + "CREATE KEYSPACE IF NOT EXISTS %s WITH REPLICATION = {'class': 'NetworkTopologyStrategy', '%s': 1};",
                keyspace, localDataCenter));

        session.execute(String.format("CREATE TABLE IF NOT EXISTS %s.%s (name text, id int, description text, "
                + "age int, PRIMARY KEY (id));", keyspace, guestsTableName));
    }

    public Iterable<Guest> findAll() {
        ResultSet rs = session.execute(selectGuests.getQuery());
        List<Guest> guests = new ArrayList<>(rs.getAvailableWithoutFetching());

        for (Row row : rs) {
            guests.add(new Guest(row.getInt("id"), row.getString("name"), row.getString("address"), row.getInt("age")));
        }

        return guests;
    }

}
