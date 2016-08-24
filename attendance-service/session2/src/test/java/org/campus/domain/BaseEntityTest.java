package org.campus.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by amey on 24/8/16.
 */
public class BaseEntityTest {
    @Test
    public void shouldGenerateIdentifier() throws Exception {
        assertThat(BaseEntity.generateIdentifier(), notNullValue());
    }

}