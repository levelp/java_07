package webapp.storage;

import webapp.WebAppException;
import webapp.model.ContactType;
import webapp.model.Resume;
import webapp.model.Section;
import webapp.model.SectionType;
import webapp.sql.Sql;
import webapp.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * User: gkislin
 * Date: 14.07.2014
 */
public class SqlStorage implements IStorage {

    @Override
    public void clear() {
        Sql.execute("DELETE FROM RESUME", ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public void save(final Resume r) {
        Sql.execute(
                conn -> {
                    try (PreparedStatement st = conn.prepareStatement("INSERT INTO resume (uuid, full_name, location) VALUES(?,?,?)")) {
                        st.setString(1, r.getUuid());
                        st.setString(2, r.getFullName());
                        st.setString(3, r.getLocation());
                        st.execute();
                    }
                    replaceContact(conn, r);
                    replaceTextSection(conn, r);
                    return null;
                }
        );
    }

    @Override
    public void update(final Resume r) {
        Sql.execute(conn -> {
            try (PreparedStatement st = conn.prepareStatement("UPDATE resume SET full_name=?, location=? WHERE uuid=?")) {
                st.setString(1, r.getFullName());
                st.setString(2, r.getLocation());
                st.setString(3, r.getUuid());
                if (st.executeUpdate() == 0) {
                    throw new WebAppException("Resume not found", r);
                }
            }
            replaceContact(conn, r);
            replaceTextSection(conn, r);
            return null;
        });
    }

    @Override
    public Resume load(final String uuid) {
        return Sql.execute("SELECT r.uuid, r.full_name, r.location, c.type, c.value FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid WHERE r.uuid=?",
                st -> {
                    st.setString(1, uuid);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        Resume r = new Resume(uuid, rs.getString("full_name"), rs.getString("location"));
                        addContact(rs, r);
                        while (rs.next()) {
                            addContact(rs, r);
                        }
                        return r;
                    }
                    throw new WebAppException("Resume " + uuid + " is not found");
                });
    }

    @Override
    public void delete(final String uuid) {
        // Strategy
        Sql.execute("DELETE FROM RESUME WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new WebAppException("Resume " + uuid + "not exist", uuid);
            }
            return null;
        });
    }

    @Override
    public Collection<Resume> getAllSorted() {
        return Sql.execute("SELECT r.uuid, r.full_name, r.location, c.type, c.value  FROM RESUME r LEFT JOIN contact c ON r.uuid = c.resume_uuid",
                st -> {
                    ResultSet rs = st.executeQuery();
                    Map<String, Resume> map = new HashMap<>();
                    while (rs.next()) {
                        String uuid = rs.getString("uuid");
                        Resume resume = map.get(uuid);
                        if (resume == null) {
                            resume = new Resume(uuid, rs.getString("full_name"), rs.getString("location"));
                            map.put(uuid, resume);
                        }
                        addContact(rs, resume);
                    }
                    ArrayList<Resume> list = new ArrayList<>(map.values());
                    Collections.sort(list);
                    return list;
                });
    }

    @Override
    public Collection<Resume> searchByName(String query) {
        return Sql.execute("SELECT r.uuid, r.full_name, r.location, c.type, c.value " +
                        " FROM RESUME r LEFT JOIN contact c ON r.uuid = c.resume_uuid" +
                        " WHERE UPPER(full_name) LIKE ?",
                st -> {
                    st.setString(1, '%' + query.toUpperCase() + '%');
                    ResultSet rs = st.executeQuery();
                    Map<String, Resume> map = new HashMap<>();
                    while (rs.next()) {
                        String uuid = rs.getString("uuid");
                        Resume resume = map.get(uuid);
                        if (resume == null) {
                            resume = new Resume(uuid, rs.getString("full_name"), rs.getString("location"));
                            map.put(uuid, resume);
                        }
                        addContact(rs, resume);
                    }
                    ArrayList<Resume> list = new ArrayList<>(map.values());
                    Collections.sort(list);
                    return list;
                });
    }

    @Override
    public int size() {
        return Sql.execute("SELECT count(*) FROM RESUME", st -> {
            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getInt(1);
        });
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        if (!Util.isEmpty(value)) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            r.addContact(type, value);
        }
    }

    private void replaceContact(Connection conn, Resume r) throws SQLException {
        String uuid = r.getUuid();

        try (PreparedStatement st = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid=?")) {
            st.setString(1, uuid);
            st.execute();
        }
        try (PreparedStatement st = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                st.setString(1, uuid);
                st.setString(2, e.getKey().name());
                st.setString(3, e.getValue());
                st.addBatch();
            }
            st.executeBatch();
        }
    }

    private void replaceTextSection(Connection conn, Resume r) throws SQLException {
        String uuid = r.getUuid();

        try (PreparedStatement st = conn.prepareStatement("DELETE FROM text_section WHERE resume_uuid=?")) {
            st.setString(1, uuid);
            st.execute();
        }
        try (PreparedStatement st = conn.prepareStatement("INSERT INTO text_section (resume_uuid, type, values) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {
                st.setString(1, uuid);
                st.setString(2, e.getKey().name());
                st.setString(3, e.getValue().getValues().toString());
                st.addBatch();
            }
            st.executeBatch();
        }
    }

}
