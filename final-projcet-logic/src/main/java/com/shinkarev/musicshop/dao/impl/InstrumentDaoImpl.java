package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.InstrumentDao;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shinkarev.musicshop.dao.impl.SqlQuery.*;


public class InstrumentDaoImpl implements InstrumentDao {
    @Override
    public List<Instrument> findAll() throws DaoException {
        List<Instrument> instruments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_INSTRUMENTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Instrument instrument = InstrumentCreator.createInstrument(resultSet);
                instruments.add(instrument);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error of creating list instrument", ex);
        }
        return instruments;
    }

    @Override
    public Optional<Instrument> findEntityById(Long id) throws DaoException {
        Instrument instrument = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_INSTRUMENT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                instrument = InstrumentCreator.createInstrument(resultSet);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error of finding instrument", ex);
        }
        return Optional.ofNullable(instrument);
    }

    @Override
    public boolean create(Instrument instrument) throws DaoException {
        boolean flag = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_INSTRUMENT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, instrument.getName());
            statement.setString(2, instrument.getBrand());
            statement.setString(3, instrument.getCountry());
            statement.setDouble(4, instrument.getPrice());
            statement.setDouble(5, instrument.getRating());
            statement.setString(6, instrument.getDescription()); // TODO THIS FIELD UNIQUE IN BD FOR UNSAVING DUPLICATES and what can I do if EXCEPTION happened for this reason
            statement.setInt(7, InstrumentStatusType.ordinal(instrument.getInstrumentStatus()));
            statement.setInt(8, InstrumentType.ordinal(instrument.getType()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                instrument.setInstrument_id(resultSet.getLong(1)); //TODO for what ?
                flag = true;
            }
        } catch (SQLException ex) {
            throw new DaoException("Error of creating instrument", ex);
        }
        return flag;
    }

    @Override
    public boolean update(Instrument instrument) throws DaoException { // TODO For what boolean ?
        boolean flag;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_INSTRUMENT)) {
            statement.setString(1, instrument.getName());
            statement.setString(2, instrument.getBrand());
            statement.setString(3, instrument.getCountry());
            statement.setDouble(4, instrument.getPrice());
            statement.setDouble(5, instrument.getRating());
            statement.setString(6, instrument.getDescription());
            statement.setInt(7, InstrumentStatusType.ordinal(instrument.getInstrumentStatus()));
            statement.setInt(8, InstrumentType.ordinal(instrument.getType()));
            flag = statement.executeUpdate() != 0;
        } catch (SQLException ex) {
            throw new DaoException("Error of updating instrument", ex);
        }
        return flag;
    }

    @Override
    public List<Instrument> findInstrumentByBrand(String brand) throws DaoException {
        List<Instrument> instruments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_INSTRUMENT_BY_BRAND)) {
            statement.setString(1, brand);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Instrument instrument = InstrumentCreator.createInstrument(resultSet);
                instruments.add(instrument);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error of finding instruments", ex);
        }
        return instruments;
    }

    @Override
    public List<Instrument> findInstrumentBuStatus(InstrumentStatusType status) throws DaoException {
        List<Instrument> instruments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_INSTRUMENT_BY_STATUS)) {
            statement.setInt(1, InstrumentStatusType.ordinal(status));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Instrument instrument = InstrumentCreator.createInstrument(resultSet);
                instruments.add(instrument);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error of finding instruments", ex);
        }
        return instruments;
    }

    @Override
    public List<Instrument> findInstrumentByType(InstrumentType type) throws DaoException {
        List<Instrument> instruments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_INSTRUMENT_BY_TYPE)) {
            statement.setInt(1, InstrumentType.ordinal(type));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Instrument instrument = InstrumentCreator.createInstrument(resultSet);
                instruments.add(instrument);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error of finding instruments", ex);
        }
        return instruments;
    }

    @Override
    public List<Instrument> findInstrumentByRating(int rating, InstrumentType type) throws DaoException {
        List<Instrument> instruments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_INSTRUMENT_BY_RATING)) {
            statement.setInt(1, rating);
            statement.setInt(2, InstrumentType.ordinal(type));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Instrument instrument = InstrumentCreator.createInstrument(resultSet);
                instruments.add(instrument);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error of finding instruments", ex);
        }
        return instruments;
    }

    @Override
    public double getInstrumentRating(Instrument instrument) throws DaoException {
        List<Integer> marks = new ArrayList<>();
        double rating = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_INSTRUMENT_RATING)) {
            statement.setLong(1, instrument.getInstrument_id());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int mark = Integer.parseInt(resultSet.getString(InstrumentField.RATING));
                marks.add(mark);
            }
        } catch (SQLException ex) {
            throw new DaoException("Error of getting rating", ex);
        }
        if (marks.size() > 0) {
            for (Integer mark : marks) {
                rating += mark;
            }
            rating /= marks.size(); // TODO will write code for getting 2 numbers after point
        }
        return rating;
    }
}
