package com.shinkarev.musicshop.dao.impl;

import com.shinkarev.musicshop.dao.InstrumentDao;
import com.shinkarev.musicshop.entity.Instrument;
import com.shinkarev.musicshop.entity.InstrumentStatusType;
import com.shinkarev.musicshop.entity.InstrumentType;
import com.shinkarev.musicshop.exception.DaoException;
import com.shinkarev.musicshop.pool.ConnectionPool;
import org.apache.logging.log4j.Level;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;


import static com.shinkarev.musicshop.dao.impl.InstrumentField.*;
import static com.shinkarev.musicshop.dao.impl.SqlQuery.*;


public class InstrumentDaoImpl implements InstrumentDao {


    @Override
    public int getInstrumentCount() throws DaoException {
        return rowCountByQuery(SQL_GET_ALL_INSTRUMENTS);
    }
    @Override
    public int getInstrumentCount(InstrumentType type) throws DaoException {
        return instrumentRowCountByQuery(type);
    }

    private int instrumentRowCountByQuery(InstrumentType type) throws DaoException {
        int result = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSTRUMENT_ROW_COUNT_BY_TYPE)
        ) {
            statement.setInt(1, InstrumentType.ordinal(type));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Can't count row count. ", ex);
            throw new DaoException("Can't count row count.", ex);
        }
        return result;
    }

    @Override
    public List<Instrument> findByPage(int page) throws DaoException {
        List<Instrument> instruments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(buildPageableQuery(SQL_GET_ALL_INSTRUMENTS + INSTRUMENT_ORDER_BY, page))) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Instrument instrument = InstrumentCreator.createInstrument(resultSet);
                instruments.add(instrument);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error with find all Users.", ex);
            throw new DaoException("Error with find all Users .", ex);
        }
        return instruments;
    }

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
            logger.log(Level.ERROR, "Error of creating list instrument", ex);
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
            logger.log(Level.ERROR, "Error of finding instrument", ex);
            throw new DaoException("Error of finding instrument", ex);
        }
        return Optional.ofNullable(instrument);
    }

     List<String> convertBlobToString(List<Blob> images) throws SQLException, IOException {
        List<String> stringImages = new ArrayList<>();
        for (Blob blob : images) {
            byte[] byteImage = blob.getBinaryStream().readAllBytes();
            byte[] encodeBase64 = Base64.getEncoder().encode(byteImage);
            String base64DataString = new String(encodeBase64, StandardCharsets.UTF_8);
            String image = INSTRUMENT_IMAGE_PARAM + base64DataString;
            stringImages.add(image);
        }
        return stringImages;
    }


    List<Blob> getInstrumentImages(long instrumentId) throws SQLException {
        List<Blob> images = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_INSTRUMENT_IMAGES)) {
            statement.setLong(1, instrumentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                images.add(resultSet.getBlob(INSTRUMENT_KEY_PARAM));
            }
        }
        return images;
    }

    @Override
    public boolean create(Instrument instrument) throws DaoException {
        boolean flag = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_INSTRUMENT, Statement.RETURN_GENERATED_KEYS)) {
            setParameters(instrument, statement);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                instrument.setInstrument_id(resultSet.getLong(1));
                flag = true;
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of creating instrument", ex);
            throw new DaoException("Error of creating instrument", ex);
        }
        return flag;
    }

    @Override
    public boolean addInstrument(Instrument instrument, List<InputStream> images) throws DaoException {
        boolean flag = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_INSTRUMENT, Statement.RETURN_GENERATED_KEYS)) {
            setParameters(instrument, statement);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                instrument.setInstrument_id(resultSet.getLong(1));
                flag = pinImagesToInstrument(instrument.getInstrument_id(), images);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of adding instrument", ex);
            throw new DaoException("Error of creating instrument", ex);
        }
        return flag;
    }

    private void setParameters(Instrument instrument, PreparedStatement statement) throws SQLException {
        statement.setString(1, instrument.getName());
        statement.setString(2, instrument.getBrand());
        statement.setString(3, instrument.getCountry());
        statement.setDouble(4, instrument.getPrice());
        statement.setDouble(5, instrument.getRating());
        statement.setString(6, instrument.getDescription());
        statement.setInt(7, InstrumentStatusType.ordinal(instrument.getInstrumentStatus()));
        statement.setInt(8, InstrumentType.ordinal(instrument.getType()));
    }

    private boolean pinImagesToInstrument(long instrumentId, List<InputStream> images) throws SQLException {
        int rowsUpdate = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SET_INSTRUMENT_IMAGE)) {
            for (InputStream image : images) {
                statement.setLong(1, instrumentId);
                statement.setBlob(2, image);
                if (statement.executeUpdate() == 1) {
                    rowsUpdate++;
                }
            }
        }
        return rowsUpdate == images.size();
    }

    @Override
    public boolean update(Instrument instrument) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_INSTRUMENT)) {
            statement.setString(1, instrument.getName());
            statement.setString(2, instrument.getBrand());
            statement.setString(3, instrument.getCountry());
            statement.setDouble(4, instrument.getPrice());
            statement.setString(5, instrument.getDescription());
            statement.setLong(6, instrument.getInstrument_id());
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of updating instrument", ex);
            throw new DaoException("Error of updating instrument", ex);
        }
        return rowsUpdate == 1;
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
            logger.log(Level.ERROR, "Error of finding instrument with " + brand, ex);
            throw new DaoException("Error of finding instruments", ex);
        }
        return instruments;
    }

    @Override
    public List<Instrument> findInstrumentByStatus(InstrumentStatusType status) throws DaoException {
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
            logger.log(Level.ERROR, "Error of finding instrument with " + status, ex);
            throw new DaoException("Error of finding instruments", ex);
        }
        return instruments;
    }

    @Override
    public List<Instrument> findInstrumentByType(InstrumentType type, int page) throws DaoException {
        List<Instrument> instruments = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(buildPageableQuery(SQL_FIND_INSTRUMENT_BY_TYPE + INSTRUMENT_ORDER_BY, page) )) {
            statement.setInt(1, InstrumentType.ordinal(type));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Instrument instrument = InstrumentCreator.createInstrument(resultSet);
                instruments.add(instrument);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of finding instruments with " + type, ex);
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
    public double getInstrumentRating(long instrumentId) throws DaoException {
        List<Integer> marks = new ArrayList<>();
        double rating = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_INSTRUMENT_RATING)) {
            statement.setLong(1, instrumentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int mark = Integer.parseInt(resultSet.getString(InstrumentField.RATING));
                marks.add(mark);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of finding instruments", ex);
            throw new DaoException("Error of getting rating", ex);
        }
        OptionalDouble average = marks.stream().mapToInt(e -> e).average();
        if (average.isPresent()) {
            rating = average.getAsDouble();
        }
        return (double) Math.round(rating * 10d) / 10d;
    }


    @Override
    public boolean isRated(long userId, long instrumentId) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_IS_RATED_BY_USER_ID)) {
            statement.setLong(1, userId);
            statement.setLong(2, instrumentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of finding instrument", ex);
            throw new DaoException("Error of getting rating", ex);
        }
        return result;
    }

    @Override
    public boolean isInBucket(long userId, long instrumentId) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_IS_IN_BUCKET)) {
            statement.setLong(1, userId);
            statement.setLong(2, instrumentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of finding instruments", ex);
            throw new DaoException("Error of getting rating", ex);
        }
        return result;
    }

    @Override
    public boolean setInstrumentRating(long userId, long instrumentId, int rating) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SET_INSTRUMENT_RATING)) {
            statement.setLong(1, userId);
            statement.setLong(2, instrumentId);
            statement.setInt(3, rating);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of finding instrument", ex);
            throw new DaoException("Error of getting rating", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeInstrumentStatusById(long instrumentId, InstrumentStatusType statusType) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CONTROL_INSTRUMENT_STATUS)) {
            statement.setInt(1, InstrumentStatusType.ordinal(statusType));
            statement.setLong(2, instrumentId);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of changing instrument status", ex);
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean changeInstrumentTypeById(long instrumentId, InstrumentType type) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CONTROL_INSTRUMENT_TYPE)) {
            statement.setInt(1, InstrumentType.ordinal(type));
            statement.setLong(2, instrumentId);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of changing instrument type", ex);
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean addItemToCart(long userId, long instrumentId) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_INSTRUMENT_TO_BUCKET)) {
            statement.setLong(1, userId);
            statement.setLong(2, instrumentId);
            statement.setInt(3, 1);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of adding instrument to cart", ex);
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean removeItemFromBucket(long userId, long instrumentId) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_INSTRUMENT_FROM_BUCKET)) {
            statement.setLong(1, userId);
            statement.setLong(2, instrumentId);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of removing instrument from cart", ex);
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public Map<Instrument, Integer> findAddedToCartItems(long userId) throws DaoException {
        Map<Instrument, Integer> cartItems = new HashMap<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDER_ITEMS)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Instrument instrument = InstrumentCreator.createInstrument(resultSet);
                int quantity = resultSet.getInt(INSTRUMENT_QUANTITY);
                cartItems.put(instrument, quantity);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of getting instrument from cart", ex);
            throw new DaoException("Error of finding instruments", ex);
        }
        return cartItems;
    }

    @Override
    public boolean clearUserBucket(long userId) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CLEAR_USER_BUCKET)) {
            statement.setLong(1, userId);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of clearing instrument from cart", ex);
            throw new DaoException("Error. Impossible get data from data base.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean addImageToInstrumentById(long instrumentId, InputStream inputStream) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SET_INSTRUMENT_IMAGE)) {
            statement.setLong(1, instrumentId);
            statement.setBlob(2, inputStream);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of adding instrument to db", ex);
            throw new DaoException("Error. Impossible put image to data base.", ex);
        }
        return rowsUpdate == 1;
    }

    @Override
    public boolean setInstrumentQuantity(long userId, long instrumentId, int quantity) throws DaoException {
        int rowsUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SET_INSTRUMENT_QUANTITY)) {
            statement.setInt(1, quantity);
            statement.setLong(2, userId);
            statement.setLong(3, instrumentId);
            rowsUpdate = statement.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Error of setting instrument quantity", ex);
            throw new DaoException("Error. Impossible put image to data base.", ex);
        }
        return rowsUpdate == 1;
    }
}
