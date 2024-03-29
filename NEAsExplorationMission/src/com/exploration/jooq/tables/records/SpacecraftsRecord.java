/*
 * This file is generated by jOOQ.
*/
package com.exploration.jooq.tables.records;


import com.exploration.jooq.tables.Spacecrafts;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record8;
import org.jooq.Row8;
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
public class SpacecraftsRecord extends UpdatableRecordImpl<SpacecraftsRecord> implements Record8<String, String, Integer, Integer, Integer, Double, Integer, String> {

    private static final long serialVersionUID = -31526713;

    /**
     * Setter for <code>db21.Spacecrafts.AgencyName</code>.
     */
    public void setAgencyname(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>db21.Spacecrafts.AgencyName</code>.
     */
    public String getAgencyname() {
        return (String) get(0);
    }

    /**
     * Setter for <code>db21.Spacecrafts.ModelId</code>.
     */
    public void setModelid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>db21.Spacecrafts.ModelId</code>.
     */
    public String getModelid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>db21.Spacecrafts.Count</code>.
     */
    public void setCount(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>db21.Spacecrafts.Count</code>.
     */
    public Integer getCount() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>db21.Spacecrafts.DayCharge</code>.
     */
    public void setDaycharge(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>db21.Spacecrafts.DayCharge</code>.
     */
    public Integer getDaycharge() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>db21.Spacecrafts.MaxTripTime</code>.
     */
    public void setMaxtriptime(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>db21.Spacecrafts.MaxTripTime</code>.
     */
    public Integer getMaxtriptime() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>db21.Spacecrafts.MaxTripEnergy</code>.
     */
    public void setMaxtripenergy(Double value) {
        set(5, value);
    }

    /**
     * Getter for <code>db21.Spacecrafts.MaxTripEnergy</code>.
     */
    public Double getMaxtripenergy() {
        return (Double) get(5);
    }

    /**
     * Setter for <code>db21.Spacecrafts.MaxCapacity</code>.
     */
    public void setMaxcapacity(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>db21.Spacecrafts.MaxCapacity</code>.
     */
    public Integer getMaxcapacity() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>db21.Spacecrafts.Type</code>.
     */
    public void setType(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>db21.Spacecrafts.Type</code>.
     */
    public String getType() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<String, String, Integer, Integer, Integer, Double, Integer, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<String, String, Integer, Integer, Integer, Double, Integer, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Spacecrafts.SPACECRAFTS.AGENCYNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Spacecrafts.SPACECRAFTS.MODELID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Spacecrafts.SPACECRAFTS.COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Spacecrafts.SPACECRAFTS.DAYCHARGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return Spacecrafts.SPACECRAFTS.MAXTRIPTIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field6() {
        return Spacecrafts.SPACECRAFTS.MAXTRIPENERGY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return Spacecrafts.SPACECRAFTS.MAXCAPACITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Spacecrafts.SPACECRAFTS.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getAgencyname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getModelid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getDaycharge();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getMaxtriptime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double component6() {
        return getMaxtripenergy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component7() {
        return getMaxcapacity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getAgencyname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getModelid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getDaycharge();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getMaxtriptime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value6() {
        return getMaxtripenergy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getMaxcapacity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpacecraftsRecord value1(String value) {
        setAgencyname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpacecraftsRecord value2(String value) {
        setModelid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpacecraftsRecord value3(Integer value) {
        setCount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpacecraftsRecord value4(Integer value) {
        setDaycharge(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpacecraftsRecord value5(Integer value) {
        setMaxtriptime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpacecraftsRecord value6(Double value) {
        setMaxtripenergy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpacecraftsRecord value7(Integer value) {
        setMaxcapacity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpacecraftsRecord value8(String value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpacecraftsRecord values(String value1, String value2, Integer value3, Integer value4, Integer value5, Double value6, Integer value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SpacecraftsRecord
     */
    public SpacecraftsRecord() {
        super(Spacecrafts.SPACECRAFTS);
    }

    /**
     * Create a detached, initialised SpacecraftsRecord
     */
    public SpacecraftsRecord(String agencyname, String modelid, Integer count, Integer daycharge, Integer maxtriptime, Double maxtripenergy, Integer maxcapacity, String type) {
        super(Spacecrafts.SPACECRAFTS);

        set(0, agencyname);
        set(1, modelid);
        set(2, count);
        set(3, daycharge);
        set(4, maxtriptime);
        set(5, maxtripenergy);
        set(6, maxcapacity);
        set(7, type);
    }
}
