package com.shinkarev.musicshop.entity;

import java.util.List;

public class Instrument extends Entity {
    private long instrument_id;
    private String name;
    private String brand;
    private String country;
    private double price;
    private double rating;
    private String description;
    private InstrumentStatusType instrumentStatus;
    private InstrumentType type;
    private List<String> image;

    public Instrument() {
    }

    public Instrument(String name, String brand, String country, double price, int rating, String description,
                      InstrumentStatusType instrumentStatus, InstrumentType type, List<String> image) {
        this.name = name;
        this.brand = brand;
        this.country = country;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.instrumentStatus = instrumentStatus;
        this.type = type;
        this.image = image;
    }

    public Instrument(String name, String brand, String country, double price, double rating, String description, InstrumentStatusType instrumentStatus, InstrumentType type) {
        this.name = name;
        this.brand = brand;
        this.country = country;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.instrumentStatus = instrumentStatus;
        this.type = type;
    }

    public Instrument(long instrument_id, String name, String brand, String country, double price, String description) {
        this.instrument_id = instrument_id;
        this.name = name;
        this.brand = brand;
        this.country = country;
        this.price = price;
        this.description = description;
    }

    public long getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(long instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InstrumentStatusType getInstrumentStatus() {
        return instrumentStatus;
    }

    public void setInstrumentStatus(InstrumentStatusType instrumentStatus) {
        this.instrumentStatus = instrumentStatus;
    }

    public InstrumentType getType() {
        return type;
    }

    public void setType(InstrumentType type) {
        this.type = type;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Instrument that = (Instrument) o;
        if (this.instrument_id != that.instrument_id) {
            return false;
        }
        if (!this.name.equals(that.getName())) {
            return false;
        }
        if (!this.brand.equals(that.brand)) {
            return false;
        }
        if (!this.country.equals(that.country)) {
            return false;
        }
        if (this.price != that.price) {
            return false;
        }
        if (this.rating != that.rating) {
            return false;
        }
        if (!this.description.equals(that.description)) {
            return false;
        }
        if (this.instrumentStatus != that.instrumentStatus) {
            return false;
        }
        return this.type != that.type;
    }

    @Override
    public int hashCode() {
        int result = (int) (this.instrument_id ^ (this.instrument_id >>> 32));
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.brand != null ? this.brand.hashCode() : 0);
        result = 31 * result + (this.country != null ? this.country.hashCode() : 0);
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        long temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) this.rating;
        result = 31 * result + (this.instrumentStatus != null ? this.instrumentStatus.hashCode() : 0);
        result = 31 * result + (this.type != null ? this.type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Instrument{");
        sb.append("instrument_id=").append(instrument_id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", price=").append(price);
        sb.append(", rating=").append(rating);
        sb.append(", description='").append(description).append('\'');
        sb.append(", instrumentStatus=").append(instrumentStatus);
        sb.append(", type=").append(type);
        sb.append(", image=").append(image);
        sb.append('}');
        return sb.toString();
    }
}
