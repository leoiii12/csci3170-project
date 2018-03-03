/*
 * This file is generated by jOOQ.
*/
package com.exploration.jooq;


import com.exploration.jooq.tables.Nearearthasteroids;
import com.exploration.jooq.tables.Resources;
import com.exploration.jooq.tables.Spacecraftrentalrecords;
import com.exploration.jooq.tables.Spacecrafts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Db21 extends SchemaImpl {

    private static final long serialVersionUID = -594206792;

    /**
     * The reference instance of <code>db21</code>
     */
    public static final Db21 DB21 = new Db21();

    /**
     * The table <code>db21.NearEarthAsteroids</code>.
     */
    public final Nearearthasteroids NEAREARTHASTEROIDS = com.exploration.jooq.tables.Nearearthasteroids.NEAREARTHASTEROIDS;

    /**
     * The table <code>db21.Resources</code>.
     */
    public final Resources RESOURCES = com.exploration.jooq.tables.Resources.RESOURCES;

    /**
     * The table <code>db21.SpacecraftRentalRecords</code>.
     */
    public final Spacecraftrentalrecords SPACECRAFTRENTALRECORDS = com.exploration.jooq.tables.Spacecraftrentalrecords.SPACECRAFTRENTALRECORDS;

    /**
     * The table <code>db21.Spacecrafts</code>.
     */
    public final Spacecrafts SPACECRAFTS = com.exploration.jooq.tables.Spacecrafts.SPACECRAFTS;

    /**
     * No further instances allowed
     */
    private Db21() {
        super("db21", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Nearearthasteroids.NEAREARTHASTEROIDS,
            Resources.RESOURCES,
            Spacecraftrentalrecords.SPACECRAFTRENTALRECORDS,
            Spacecrafts.SPACECRAFTS);
    }
}
