package ru.teamsource.bankruptservice.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.teamsource.bankruptservice.model.PersonData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    public List<PersonData> executePersonDataScript() {
        return jdbcTemplate.query("{call fetch_person_data()}", new PersonDataRowMapper());
    }

    private static final class PersonDataRowMapper implements RowMapper<PersonData> {
        @Override
        public PersonData mapRow(ResultSet rs, int rowNum) throws SQLException {
            PersonData personData = new PersonData();
            personData.setInn(rs.getString("inn"));
            personData.setTotalCreditScore(rs.getInt("total_credit_score"));
            personData.setTotalApplications(rs.getInt("total_applications"));
            personData.setTotalCreditRequested(rs.getBigDecimal("total_credit_requested"));
            personData.setFirstName(rs.getString("first_name"));
            personData.setLastName(rs.getString("last_name"));
            personData.setDateOfBirth(rs.getDate("date_of_birth"));
            personData.setEmploymentStatus(rs.getString("employment_status"));
            personData.setAnnualIncome(rs.getBigDecimal("annual_income"));
            return personData;
        }
    }
}