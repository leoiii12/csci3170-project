/*
 * This file is generated by jOOQ.
*/
package com.exploration.jooq.tables.records;


import com.exploration.jooq.tables.Nearearthasteroids;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class NearearthasteroidsRecord extends UpdatableRecordImpl<NearearthasteroidsRecord> implements Record6<String, Double, String, Integer, Double, String> {

    private static final long serialVersionUID = -1859371927;

    /**
     * Setter for <code>db21.NearEarthAsteroids.NeaId</code>.
     */
    public void setNeaid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>db21.NearEarthAsteroids.NeaId</code>.
     */
    public String getNeaid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>db21.NearEarthAsteroids.Distance</code>.
     */
    public void setDistance(Double value) {
        set(1, value);
    }

    /**
     * Getter for <code>db21.NearEarthAsteroids.Distance</code>.
     */
    public Double getDistance() {
        return (Double) get(1);
    }

    /**
     * Setter for <code>db21.NearEarthAsteroids.Family</code>.
     */
    public void setFamily(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>db21.NearEarthAsteroids.Family</code>.
     */
    public String getFamily() {
        return (String) get(2);
    }

    /**
     * Setter for <code>db21.NearEarthAsteroids.MinDuration</code>.
     */
    public void setMinduration(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>db21.NearEarthAsteroids.MinDuration</code>.
     */
    public Integer getMinduration() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>db21.NearEarthAsteroids.MinEnergy</code>.
     */
    public void setMinenergy(Double value) {
        set(4, value);
    }

    /**
     * Getter for <code>db21.NearEarthAsteroids.MinEnergy</code>.
     */
    public Double getMinenergy() {
        return (Double) get(4);
    }

    /**
     * Setter for <code>db21.NearEarthAsteroids.ResourceType</code>.
     */
    public void setResourcetype(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>db21.NearEarthAsteroids.ResourceType</code>.
     */
    public String getResourcetype() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<String, Double, String, Integer, Double, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<String, Double, String, Integer, Double, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Nearearthasteroids.NEAREARTHASTEROIDS.NEAID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field2() {
        return Nearearthasteroids.NEAREARTHASTEROIDS.DISTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Nearearthasteroids.NEAREARTHASTEROIDS.FAMILY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Nearearthasteroids.NEAREARTHASTEROIDS.MINDURATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field5() {
        return Nearearthasteroids.NEAREARTHASTEROIDS.MINENERGY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Nearearthasteroids.NEAREARTHASTEROIDS.RESOURCETYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getNeaid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component2() {
        return getDistance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getFamily();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getMinduration();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component5() {
        return getMinenergy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getResourcetype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getNeaid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value2() {
        return getDistance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getFamily();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getMinduration();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value5() {
        return getMinenergy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getResourcetype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NearearthasteroidsRecord value1(String value) {
        setNeaid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NearearthasteroidsRecord value2(Double value) {
        setDistance(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NearearthasteroidsRecord value3(String value) {
        setFamily(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NearearthasteroidsRecord value4(Integer value) {
        setMinduration(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NearearthasteroidsRecord value5(Double value) {
        setMinenergy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NearearthasteroidsRecord value6(String value) {
        setResourcetype(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NearearthasteroidsRecord values(String value1, Double value2, String value3, Integer value4, Double value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached NearearthasteroidsRecord
     */
    public NearearthasteroidsRecord() {
        super(Nearearthasteroids.NEAREARTHASTEROIDS);
    }

    /**
     * Create a detached, initialised NearearthasteroidsRecord
     */
    public NearearthasteroidsRecord(String neaid, Double distance, String family, Integer minduration, Double minenergy, String resourcetype) {
        super(Nearearthasteroids.NEAREARTHASTEROIDS);

        set(0, neaid);
        set(1, distance);
        set(2, family);
        set(3, minduration);
        set(4, minenergy);
        set(5, resourcetype);
    }
}