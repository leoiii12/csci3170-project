/*
 * This file is generated by jOOQ.
*/
package com.exploration.jooq.tables;


import com.exploration.jooq.Db21;
import com.exploration.jooq.Indexes;
import com.exploration.jooq.Keys;
import com.exploration.jooq.tables.records.ResourcesRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Resources extends TableImpl<ResourcesRecord> {

    private static final long serialVersionUID = 1281276679;

    /**
     * The reference instance of <code>db21.Resources</code>
     */
    public static final Resources RESOURCES = new Resources();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ResourcesRecord> getRecordType() {
        return ResourcesRecord.class;
    }

    /**
     * The column <code>db21.Resources.Type</code>.
     */
    public final TableField<ResourcesRecord, String> TYPE = createField("Type", org.jooq.impl.SQLDataType.CHAR(2).nullable(false), this, "");

    /**
     * The column <code>db21.Resources.Density</code>.
     */
    public final TableField<ResourcesRecord, Double> DENSITY = createField("Density", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>db21.Resources.Value</code>.
     */
    public final TableField<ResourcesRecord, Double> VALUE = createField("Value", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * Create a <code>db21.Resources</code> table reference
     */
    public Resources() {
        this(DSL.name("Resources"), null);
    }

    /**
     * Create an aliased <code>db21.Resources</code> table reference
     */
    public Resources(String alias) {
        this(DSL.name(alias), RESOURCES);
    }

    /**
     * Create an aliased <code>db21.Resources</code> table reference
     */
    public Resources(Name alias) {
        this(alias, RESOURCES);
    }

    private Resources(Name alias, Table<ResourcesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Resources(Name alias, Table<ResourcesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Db21.DB21;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.RESOURCES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ResourcesRecord> getPrimaryKey() {
        return Keys.KEY_RESOURCES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ResourcesRecord>> getKeys() {
        return Arrays.<UniqueKey<ResourcesRecord>>asList(Keys.KEY_RESOURCES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resources as(String alias) {
        return new Resources(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resources as(Name alias) {
        return new Resources(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Resources rename(String name) {
        return new Resources(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Resources rename(Name name) {
        return new Resources(name, null);
    }
}
